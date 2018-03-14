package com.dissertaion.bbms.utils;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by TOAOK on 2017/9/21.
 */

public class BBMSURLHandle {

    private static final String BASE_PATH = "http://book.duxiu.com";

    /**
     * 为该URL添加使用协议和服务器地址
     *
     * @param query
     * @return
     */
    public String getURL(String query) {
        StringBuffer url = new StringBuffer();
        if (!query.startsWith("http://") && !query.startsWith("https://")) {
            url.append(BASE_PATH);
            url.append("/");
            url.append(query);
        }
        return url.toString();
    }

    public String getURL(String model, String sw) throws MalformedURLException {

        return getURL(model, sw, "1");
    }

    public String getURL(String model, String sw, String pages) throws MalformedURLException {
        StringBuffer url = new StringBuffer();
        url.append(getPath(BASE_PATH, model));
        if (pages == null || pages.equals("")) {
            pages = "1";
        }
        url.append(getQuery(sw, pages));
        return url.toString();
    }


    private String getPath(String base, String model) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(base);
        buffer.append("/");
        buffer.append(model);
        return buffer.toString();
    }

    private String getQuery(String sw, String pages) {
        Map<String, String> map = new HashMap<>();
        map.put("Pages", pages);
        try {

            sw = URLEncoder.encode(sw, "gbk");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("sw", sw);
        return encodeQuery(map);
    }

    private String encodeQuery(Map<String, String> map) {
        StringBuffer buffer = new StringBuffer();
        for (String string : map.keySet()) {
            if (buffer.length() > 0) {
                buffer.append("&");
            } else {
                buffer.append("?");
            }
            buffer.append(string);
            buffer.append("=");
            buffer.append(map.get(string));
        }
        buffer.append("&encode=utf-8");
        return buffer.toString();
    }


}
