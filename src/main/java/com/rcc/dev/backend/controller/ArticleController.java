package com.rcc.dev.backend.controller;

import com.rcc.dev.backend.dto.article.ArticleRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.model.Article;
import com.rcc.dev.backend.service.article.iservice.ArticleService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/list")
    public ResponseEntity<RCCResponse<Object>> list(HttpServletRequest httpServletRequest){
        return articleService.listArticle(httpServletRequest);
    }

    @PostMapping("/update")
    public ResponseEntity<RCCResponse<Object>> update(HttpServletRequest httpServletRequest, @RequestBody ArticleRequest articleRequest){
        return articleService.update(httpServletRequest, articleRequest);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<RCCResponse<Object>> detail(HttpServletRequest httpServletRequest, @PathVariable("id") Long id){
        return articleService.getDetail(httpServletRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RCCResponse<Object>> delete(HttpServletRequest httpServletRequest, @PathVariable("id") Long id){
        return articleService.delete(httpServletRequest, id);
    }
}
