package ua.com.hav.pb.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by yulia on 18.07.2017.
 */
//@WebFilter(urlPatterns = {"/*"}, servletNames = "dispatcherServlet")
public class SecurityFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        String requestURI = request.getRequestURI();
//        System.out.println("requestURI = " + requestURI);
        boolean check = false;
        String[] urls = {"/login", "/index", "/register", "/regform", "WEB-INF"};
        for (int i = 0; i < urls.length; i++) {
            if (requestURI.contains(urls[i])) {
                check = true;
                break;
            }
        }
//        System.out.println("user: => " + session.getAttribute("user"));
//        System.out.println("check = " + check);
        if (!check && session.getAttribute("loggeduser") == null) {
            request.getRequestDispatcher("/index")
                    .forward(servletRequest, servletResponse);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
