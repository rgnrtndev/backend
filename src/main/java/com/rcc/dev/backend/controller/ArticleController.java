package com.rcc.dev.backend.controller;

import com.rcc.dev.backend.dto.article.ArticleRequest;
import com.rcc.dev.backend.model.Article;
import com.rcc.dev.backend.service.article.iservice.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("/save")
    public Article save(@RequestBody ArticleRequest articleRequest){
        return articleService.save(articleRequest);
    }

    @GetMapping("/detail/{id}")
    public Article detail(@PathVariable("id") Long id){
        return articleService.getDetail(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        articleService.delete(id);
    }
}
