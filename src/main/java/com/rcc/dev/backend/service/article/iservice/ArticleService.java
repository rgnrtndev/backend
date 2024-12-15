package com.rcc.dev.backend.service.article.iservice;

import com.rcc.dev.backend.dto.article.ArticleRequest;
import com.rcc.dev.backend.model.Article;

import java.util.List;

public interface ArticleService {
    List<Article> listArticle();
    Article save(ArticleRequest articleRequest);
    Article getDetail(Long id);
    void delete(Long id);
}
