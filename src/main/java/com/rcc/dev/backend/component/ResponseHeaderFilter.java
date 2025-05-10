//package com.rcc.dev.backend.component;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//public class ResponseHeaderFilter implements Filter {
//
//    @Value("${x-frame-options:DENY}")
//    private String xFrameOptions;
//
//    @Value("${x-content-type-options:nosniff}")
//    private String xContentTypeOptions;
//
//    @Value("${strict-transport-security:max-age=16070400; includeSubDomains}")
//    private String strictTransportSecurity;
//
//    @Value("${x-xss-protection:1; mode=block}")
//    private String xXssProtection;
//
//    @Value("${cache-control:no-store}")
//    private String cacheControl;
//
//    @Value("${referrer-policy:no-referrer}")
//    private String referrerPolicy;
//
//    @Value("${content-security-policy:default-src 'self' http: https: data: blob: 'unsafe-inline'; frame-ancestors 'none';}")
//    private String contentSecurityPolicy;
//
//    @Value("${permission-policy:fullscreen=(self), geolocation=(), microphone=()}")
//    private String permissionPolicy;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletResponse res = (HttpServletResponse) response;
//
//        // Apply security headers
//        res.setHeader("X-Frame-Options", xFrameOptions);
//        res.setHeader("X-Content-Type-Options", xContentTypeOptions);
//        res.setHeader("Strict-Transport-Security", strictTransportSecurity);
//        res.setHeader("X-XSS-Protection", xXssProtection);
//        res.setHeader("Cache-Control", cacheControl);
//        res.setHeader("Referrer-Policy", referrerPolicy);
//        res.setHeader("Content-Security-Policy", contentSecurityPolicy);
//        res.setHeader("Permissions-Policy", permissionPolicy);
//
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}
