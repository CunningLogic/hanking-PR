package com.hanking.pr.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class CharacterEncodingFilter implements Filter {
    public void destroy() {
    	
    }	

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
    	HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        MyCharacterEncodingRequest requestWrapper = new MyCharacterEncodingRequest(request);
        chain.doFilter(requestWrapper, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }
}

class MyCharacterEncodingRequest extends HttpServletRequestWrapper {
    private HttpServletRequest request;

    public MyCharacterEncodingRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public String getParameter(String name) {
        try {
            String value = this.request.getParameter(name);
            if (value == null) {
                return null;
            }
            if (!this.request.getMethod().equalsIgnoreCase("get")) {
                return value;
            }
            value = new String(value.getBytes("ISO8859-1"), this.request.getCharacterEncoding());
            return value;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}