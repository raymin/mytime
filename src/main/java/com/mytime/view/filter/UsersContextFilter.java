package com.mytime.view.filter;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class UsersContextFilter extends GenericFilterBean {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        //TODO 对seeesion进行包装
    }

}
