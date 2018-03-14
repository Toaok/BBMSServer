package com.dissertaion.bbms.servlet.proxy;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author TOAOK
 * @version 1.0  2017/10/13.
 */
public class DelegatingServletProxy extends GenericServlet {

    private String taggetBean;
    private Servlet proxy;

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        proxy.service(servletRequest, servletResponse);
    }

    @Override
    public void init() throws ServletException {
        this.taggetBean=getServletName();
        getServletBean();
        proxy.init(getServletConfig());
    }

    public void getServletBean() {
        WebApplicationContext webApplicationContext= WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        proxy= (Servlet) webApplicationContext.getBean(taggetBean);
    }
}
