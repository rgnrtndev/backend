package com.rcc.dev.backend.service.article.impl;

import com.rcc.dev.backend.dto.article.ArticleRequest;
import com.rcc.dev.backend.model.Article;
import com.rcc.dev.backend.repository.ArticleRepository;
import com.rcc.dev.backend.service.article.iservice.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public List<Article> listArticle() {
        return articleRepository.findAll();
    }

    @Override
    public Article save(ArticleRequest articleRequest) {
        Article article = Article.builder()
                .id(articleRequest.getId())
                .description(articleRequest.getDescription())
                .galleryId(articleRequest.getGalleryId())
                .build();
        return articleRepository.save(article);
    }

    @Override
    public Article getDetail(Long id) {
        Article article = articleRepository.findById(id).get();
        return article;
    }

    @Override
    public void delete(Long id) {
        Article article = articleRepository.findById(id).get();
        articleRepository.delete(article);
    }
}
