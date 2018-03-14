package com.dissertaion.bbms.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author TOAOK
 * @version 1.0  2017/9/26.
 */
public class BBMSUtils {
    public static final int TIME_OUT = 1000 * 5;
    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";

    public static String HttpGet(String path){
        try {
            return HttpGet(new URL(path));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String HttpGet(URL url) {

        HttpURLConnection connection = null;
        String response = "";
        try {
            CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
            connection = (HttpURLConnection) url.openConnection();

            //设置连接参数
            connection.setReadTimeout(TIME_OUT);
            connection.setConnectTimeout(TIME_OUT);
            connection.setDoInput(true);
            connection.setRequestMethod(METHOD_GET);
            //设置请求消息头
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-ALive");
            connection.setRequestProperty("charset","utf-8");
            connection.connect();

            switch (connection.getResponseCode()) {

                case HttpURLConnection.HTTP_NOT_FOUND:
                    Thread.sleep(TIME_OUT);
                    break;
                case HttpURLConnection.HTTP_OK:
                    InputStream inputStream = null;
                    BufferedReader reader = null;
                    try {
                        inputStream = connection.getInputStream();

                        StringBuffer buffer = new StringBuffer();

                        reader = new BufferedReader(new InputStreamReader(inputStream));
                        String readLine = null;

                        while ((readLine = reader.readLine()) != null) {
                            buffer.append(readLine);
                        }
                        response = buffer.toString();
                    } finally {
                        if (reader != null) ;
                        {
                            reader.close();
                        }
                        connection.disconnect();
                    }
                    break;
                case HttpURLConnection.HTTP_MOVED_TEMP:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }




    public static String getRequestPath(HttpServletRequest request) {
        String requestUrl = /*request.getScheme() //当前链接使用的协议
                + "://" + request.getServerName()//服务器地址
                + ":" + request.getServerPort() //端口号
                + request.getContextPath() //应用名称
                + request.getServletPath() //请求的相对url
                + "?" + */request.getQueryString(); //请求参数
        return requestUrl;
    }

    public static Map<String,String > getCookies(String path) {
        Map<String,String > cookies = new HashMap<>();
        try {
            CookieManager manager = new CookieManager();
            manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            CookieHandler.setDefault(manager);
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.getHeaderFields();
            CookieStore store = manager.getCookieStore();
            List<HttpCookie> cookiesList = store.getCookies();
            for(HttpCookie cookie: cookiesList) {
                cookies.put(cookie.getName(), cookie.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cookies;
    }


    public static String getValue(String value, String startStr, int endIndex,String defaultValue) {
        int startIndex = value.indexOf(startStr);
        defaultValue=getValue(value, startIndex + startStr.length(), endIndex, defaultValue);
        return defaultValue;
    }


    public static String getValue(String value, int startIndex, int endIndex,String defaultValue) {
        if (value != null && startIndex >= 0 && endIndex >= 0 && startIndex <= endIndex) {
            defaultValue= value.substring(startIndex, endIndex);
        }
        return defaultValue;
    }

    public static String getValue(String value, int startIndex, String endStr,String defaultValue) {
        int endIndex = value.indexOf(endStr, startIndex);
        defaultValue=getValue(value, startIndex, endIndex,defaultValue);
        return defaultValue;
    }

    public static String getValue(String value, String startStr, String endStr,String defaultValue) {
        int startIndex = value.indexOf(startStr);
        int endIndex = value.indexOf(endStr, startIndex);
        defaultValue=getValue(value, startIndex + startStr.length(), endIndex,defaultValue);
        return defaultValue;
    }
}
