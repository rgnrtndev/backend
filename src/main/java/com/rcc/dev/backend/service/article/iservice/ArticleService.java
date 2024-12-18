package com.rcc.dev.backend.service.article.iservice;

import com.rcc.dev.backend.dto.article.ArticleRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.model.Article;

import java.util.List;

public interface ArticleService {
    RCCResponse<Object> listArticle();
    RCCResponse<Object> save(ArticleRequest articleRequest);
    RCCResponse<Object> getDetail(Long id);
    RCCResponse<Object> delete(Long id);
}
