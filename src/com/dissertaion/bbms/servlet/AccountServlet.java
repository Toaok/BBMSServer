package com.dissertaion.bbms.servlet;

import com.dissertaion.bbms.service.AccountService;
import com.dissertaion.bbms.service.BorrowInfoService;
import com.dissertaion.bbms.service.ReaderInfoService;
import com.dissertaion.bbms.service.SubscribeService;
import com.dissertaion.bbms.utils.BBMSUtils;
import com.dissertaion.bbms.vo.Account;
import com.dissertaion.bbms.vo.ReaderInfo;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author TOAOK
 * @version 1.0  2017/10/16.
 */
public class AccountServlet extends HttpServlet {
    //账号
    private AccountService accountService;
    //订阅
    private SubscribeService subscribeService;
    //借阅
    private BorrowInfoService borrowInfoService;
    //读者
    private ReaderInfoService readerInfoService;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        String cmd = request.getParameter("cmd");
        System.out.println(cmd);
        if (cmd != null) {
            if (cmd.equals("validation")) {
                validation(request, response);
            } else if (cmd.equals("querySubscribe")) {
                querySubscribeInfo(request, response);
            } else if (cmd.equals("subscribe")) {
                subscribeBook(request, response);
            } else if (cmd.equals("unsubscribe")) {
                unsubscribeBook(request, response);
            } else if (cmd.equals("queryBorrow")) {
                queryBorrowInfo(request, response);
            } else if (cmd.equals("borrow")) {
                borrowBookInfo(request, response);
            } else if (cmd.equals("return")) {
                returnBookInfo(request, response);
            } else if (cmd.equals("queryReader")) {
                queryReaderInfo(request, response);
            } else if (cmd.equals("updateReader")) {
                updateReaderInfo(request, response);
            } else if (cmd.equals("deleteReader")) {
                deleteReaderInfo(request, response);
            }
        }
    }

    //校验
    private void validation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accountParameter = new String(request.getParameter("account").getBytes("ISO8859-1"), "UTF-8");
        String passwordParameter = request.getParameter("password");

        String requestPath = BBMSUtils.getRequestPath(request);
        System.out.println(requestPath);
        Account account = new Account();
        account.setAccount(accountParameter);
        account.setPassword(passwordParameter);
        account = accountService.query(account);
        String result = "";
        if (account != null) {
            boolean isexist = true;
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("isexist", isexist);
            jsonObject.accumulate("account", account.toString());

            result = jsonObject.toString();
        } else {
            boolean isexist = false;
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("isexist", isexist);
            result = jsonObject.toString();
        }
        System.out.println(result);
        response.getWriter().write(result);
    }

    //查询订阅记录
    public void querySubscribeInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int accountId = Integer.parseInt(request.getParameter("account_id"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("subscribe", subscribeService.query(accountId).toString());
        response.getWriter().write(jsonObject.toString());
    }

    //订阅
    public void subscribeBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int accountId = Integer.parseInt(request.getParameter("account_id"));
        String bookIsbn = request.getParameter("book_isbn");
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("append_status", subscribeService.append(accountId, bookIsbn));
        jsonObject.accumulate("subscribe", subscribeService.query(accountId).toString());
        response.getWriter().write(jsonObject.toString());
    }

    //退订
    public void unsubscribeBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int accountId = Integer.parseInt(request.getParameter("account_id"));
        String bookIsbn = request.getParameter("book_isbn");
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("delete_status", subscribeService.delete(accountId, bookIsbn));
        jsonObject.accumulate("subscribe", subscribeService.query(accountId).toString());
        response.getWriter().write(jsonObject.toString());
    }


    //查询借阅记录
    public void queryBorrowInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String readerId = request.getParameter("readerId");
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("borrowinfo", borrowInfoService.query(readerId).toString());
        response.getWriter().write(jsonObject.toString());

//        borrowInfoService.query()
    }

    //借阅
    public void borrowBookInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String readerId = request.getParameter("readerId");
        String bookIsbn = request.getParameter("bookIsbn");
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("add_status", borrowInfoService.add(readerId, bookIsbn));
        jsonObject.accumulate("borrowinfo", borrowInfoService.query(readerId));
        response.getWriter().write(jsonObject.toString());
    }

    //归还
    public void returnBookInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String readerId = request.getParameter("readerId");
        String bookIsbn = request.getParameter("bookIsbn");
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("add_status", borrowInfoService.delete(readerId, bookIsbn));
        jsonObject.accumulate("borrowinfo", borrowInfoService.query(readerId));
        response.getWriter().write(jsonObject.toString());
    }


    //更新读者信息
    public void updateReaderInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ReaderInfo readerInfo = new ReaderInfo();
        String readerId=request.getParameter(ReaderInfo.READER_ID);
        readerInfo.setReaderName(request.getParameter(ReaderInfo.READER_NAME));
        readerInfo.setReaderSex(request.getParameter(ReaderInfo.READER_SEX));
        readerInfo.setReaderSpec(request.getParameter(ReaderInfo.READER_SPEC));
        readerInfo.setReaderPhone(request.getParameter(ReaderInfo.READER_PHONE));
        readerInfo.setReaderSchool(request.getParameter(ReaderInfo.READER_SCHOOL));
        readerInfo.setReaderQianming(request.getParameter(ReaderInfo.READER_QIANMING));
        readerInfo.setReaderImage(request.getParameter(ReaderInfo.READER_IMAGE).getBytes());
        readerInfoService.saveOrUpdate(readerId, readerInfo);
    }

    //查询读者信息
    public void queryReaderInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String readerId = request.getParameter("readerId");
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("readerinfo", readerInfoService.query(readerId).toString());
        response.getWriter().write(jsonObject.toString());
    }

    //删除读者信息
    public void deleteReaderInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }


    public AccountService getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public SubscribeService getSubscribeService() {
        return subscribeService;
    }

    public void setSubscribeService(SubscribeService subscribeService) {
        this.subscribeService = subscribeService;
    }

    public BorrowInfoService getBorrowInfoService() {
        return borrowInfoService;
    }

    public void setBorrowInfoService(BorrowInfoService borrowInfoService) {
        this.borrowInfoService = borrowInfoService;
    }

    public ReaderInfoService getReaderInfoService() {
        return readerInfoService;
    }

    public void setReaderInfoService(ReaderInfoService readerInfoService) {
        this.readerInfoService = readerInfoService;
    }

}
