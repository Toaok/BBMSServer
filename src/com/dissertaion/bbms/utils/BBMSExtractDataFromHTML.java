package com.dissertaion.bbms.utils;

import com.dissertaion.bbms.vo.BookInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by TOAOK on 2017/9/20.
 */

public class BBMSExtractDataFromHTML {

    private static final String USER_AGENT[] = {
            "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.3; .NET4.0C; .NET4.0E; rv:11.0) like Gecko"};
    private  Map<String, String> cookies;

    private static final int TIME_OUT = 5000;
    private static final String CONTENT_ENCODING = "gzip";
    private static final String TAG_NAME = "TABLE.book1";

    private int searchResultTotal;
    private BBMSURLHandle mURLHandler;

    public BBMSExtractDataFromHTML(Map<String,String> cookies) {
        mURLHandler = new BBMSURLHandle();
        System.out.println(cookies.size());
        this.cookies=cookies;
    }

    public List parseHtml(String url) throws IOException {
        ArrayList<BookInfo> bookInfos = new ArrayList();

        Document doc = Jsoup.connect(url).userAgent(USER_AGENT[(int) Math.random() * 10 % 2]).timeout(TIME_OUT).get();

        Elements elements = doc.select(TAG_NAME);
        searchResultTotal=Integer.parseInt(BBMSUtils.getValue(doc.select("div#searchinfo>b").first().text(),"书 "," 种","0"));
        for (int i = 0; i < elements.size(); ++i) {
            BookInfo bookInfo = new BookInfo();
            Element e = elements.get(i);
            Element detail = e.select("tbody>tr>td>table>tbody>tr>td>a").first();
            String href = detail.attr("href");
            String name = detail.text();
            Document detailInfo = dispatcher(href);
            bookInfo.setBookName(BBMSUtils.getValue(name, ")", name.length(),""));
            Element detailElement = detailInfo.select("div>div>div>div.bookInfo.clearfix").first();
            bookInfo = parseCompBookInfo(detailElement, parseToPartOfBookInfo(e, bookInfo));
            bookInfos.add(bookInfo);
        }
        return bookInfos;
    }

    public int getSearchResultTotal() {
        return searchResultTotal;
    }

    private Document dispatcher(String url) throws IOException {
        url.replaceAll("", ";");
        return Jsoup.connect(mURLHandler.getURL(url)).userAgent(USER_AGENT[(int) Math.random() * 10 % 2]).cookies(cookies).timeout(TIME_OUT).get();
    }

    private int offset = 0;

    private BookInfo parseCompBookInfo(Element e, BookInfo partOfBookinfo) {
        //封面
        if (e == null) {
            return partOfBookinfo;
        }
        Element img = e.select("div>img").first();
        String bookCover = img.attr("src");
//        System.out.println(bookCover);
        //图书信息
        Element info = e.select("div.right.info").first();
        Elements detailInfo = info.select("table>tbody>tr");
        Elements detailInfoValue = detailInfo.get(0).select("td>p");

        //重置偏移量
        offset = 0;
        String author = getContent(detailInfoValue, 0, "【作 者】");
        String publish = getContent(detailInfoValue, 0, "【出版发行】");
        String ISBN = getContent(detailInfoValue, 0, "【ISBN号】");
        String price = getContent(detailInfoValue, 0, "【原书定价】");

        //重置偏移量
        offset = 0;
        //分类号&内容提要
        String classify = getContent(detailInfo.get(1).select("td>p"), 0, "【中图法分类号】");
        //重置偏移量
        offset = 0;
        String summary = getContent(e.select("div>p"), 0, "【内容提要】");


        partOfBookinfo.setBookIsbn(ISBN);
        partOfBookinfo.setBookAuthor(author);
        partOfBookinfo.setBookPublish(publish);
        partOfBookinfo.setBookPrice(price);
        partOfBookinfo.setBookSummary(summary);
        partOfBookinfo.setBookClassify(classify);
        partOfBookinfo.setBookCover(bookCover);
        return partOfBookinfo;
    }

    private BookInfo parseToPartOfBookInfo(Element e, BookInfo bookinfo) {

        if (e == null) {
            return bookinfo;
        }
        //馆存
        String cnum = e.select("tbody>tr>td>div>div>a").first().text();

        if (cnum != null && !cnum.equals("")) {
            bookinfo.setBookCnum(Integer.parseInt(cnum));
        }

        // System.out.println(bookinfo);
        return bookinfo;
    }

    private String getContent(Elements elements, int position, String invalidateCondition) {
        String content = "";
        int startIndex = 0;
        if (elements != null) {
            if (offset < elements.size()) {
                content = elements.get(position + offset).text();
                startIndex = content.indexOf(invalidateCondition);
                while (startIndex < 0 && offset < elements.size()) {
                    content = elements.get(position + offset).text();
                    startIndex = content.indexOf(invalidateCondition);
                    offset++;
                }
                if (startIndex < 0) {
                    content = "";
                }
            }
        }
        return BBMSUtils.getValue(content, startIndex + invalidateCondition.length(), content.length(),"");
    }


}
