package com.rcc.dev.backend.service.article.iservice;

import com.rcc.dev.backend.dto.article.ArticleRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.model.Article;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ArticleService {
    ResponseEntity<RCCResponse<Object>> listArticle(HttpServletRequest httpServletRequest);
    ResponseEntity<RCCResponse<Object>> update(HttpServletRequest httpServletRequest, ArticleRequest articleRequest);
    ResponseEntity<RCCResponse<Object>> getDetail(HttpServletRequest httpServletRequest, Long id);
    ResponseEntity<RCCResponse<Object>> delete(HttpServletRequest httpServletRequest, Long id);
}
