package com.rcc.dev.backend.controller;

import com.rcc.dev.backend.dto.article.ArticleRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.model.Article;
import com.rcc.dev.backend.service.article.iservice.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/list")
    public RCCResponse<Object> list(){
        return articleService.listArticle();
    }

    @PostMapping("/save")
    public RCCResponse<Object> save(@RequestBody ArticleRequest articleRequest){
        return articleService.save(articleRequest);
    }

    @GetMapping("/detail/{id}")
    public RCCResponse<Object> detail(@PathVariable("id") Long id){
        return articleService.getDetail(id);
    }

    @DeleteMapping("/delete/{id}")
    public RCCResponse<Object> delete(@PathVariable("id") Long id){
        return articleService.delete(id);
    }
}
