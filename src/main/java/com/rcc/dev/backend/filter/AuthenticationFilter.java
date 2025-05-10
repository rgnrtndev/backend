//package com.rcc.dev.backend.filter;
//
//import com.rcc.dev.backend.constant.ApiConstant;
//import com.rcc.dev.backend.model.User;
//import com.rcc.dev.backend.util.CacheUtil;
//import com.rcc.dev.backend.util.JWTUtils;
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.Objects;
//
//@Component
//@RequiredArgsConstructor
//public class AuthenticationFilter implements Filter {
//
//    private final CacheUtil cacheUtil;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//        if(Objects.nonNull(httpServletRequest.getHeader(ApiConstant.HEADER_STRING_AUTH))){
//            var userData = JWTUtils.claimObjectValue(httpServletRequest, ApiConstant.KEY_CLAIM_USER_LOGIN, User.class);
//
//        }
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}
