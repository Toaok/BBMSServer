package com.dissertaion.bbms.servlet;

import com.dissertaion.bbms.service.BookInfoService;
import com.dissertaion.bbms.service.BookService;
import com.dissertaion.bbms.utils.BBMSExtractDataFromHTML;
import com.dissertaion.bbms.utils.BBMSURLHandle;
import com.dissertaion.bbms.utils.BBMSUtils;
import com.dissertaion.bbms.vo.Book;
import com.dissertaion.bbms.vo.BookInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author TOAOK
 * @version 1.0  2017/9/29.
 */
@Component
public class BookServlet extends HttpServlet {
    private BookService bookService;
    private BookInfoService bookInfoService;
    private String model;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        model = request.getParameter("cmd");
        System.out.println(model);
        if (model != null && model.equals("search")) {
            search(request, response);
        }
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求URL
        String requestQueryString = request.getQueryString();
        System.out.println(requestQueryString);
        //获取请求结果
        String result = bookService.getValue(requestQueryString, "");
        //如果没有获取到数据，重新请求
        if (result == null || result.equals("") || result.equals("[]")) {
            System.out.println("数据库中未请求到数据");
            //获取请求参数
            String sw = request.getParameter("sw");
            String pages = request.getParameter("pages");
            //根据请求参数重新拼接URL
            BBMSURLHandle mURLHandle = new BBMSURLHandle();
            String url = mURLHandle.getURL(model, sw, pages);
            System.out.println(url);
            //从请求页面的html中提取数据
            BBMSExtractDataFromHTML extractDataFromHTML = new BBMSExtractDataFromHTML(BBMSUtils.getCookies(url));
            List<BookInfo> list = extractDataFromHTML.parseHtml(url);
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("searchTotal", extractDataFromHTML.getSearchResultTotal());
            jsonObject.accumulate("BookInfo", list);
            result = jsonObject.toString();
            System.out.println(result);
            response.getWriter().write(result);
            //将json数据保存到数据库中
            if (!result.equals("") && !result.equals("[]")) {
                Book book = new Book();
                if (requestQueryString.length() > 255) {
                    requestQueryString = requestQueryString.substring(0, 255);
                }
                book.setPath(requestQueryString);
                book.setValue(result);
                bookService.saveOrUpdate(book);
            }
            //将图书信息保存到数据库
            for (BookInfo bookInfo : list) {
                bookInfoService.saveOrUpdate(bookInfo);
            }
        } else {
            System.out.println(result);
            response.getWriter().write(result);
        }
    }

    public BookService getBookService() {
        return bookService;
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public BookInfoService getBookInfoService() {
        return bookInfoService;
    }

    public void setBookInfoService(BookInfoService bookInfoService) {
        this.bookInfoService = bookInfoService;
    }
}
