package com.rcc.dev.backend.service.article.impl;

import com.rcc.dev.backend.constant.ResponseCode;
import com.rcc.dev.backend.dto.article.ArticleRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.model.Article;
import com.rcc.dev.backend.repository.ArticleRepository;
import com.rcc.dev.backend.service.article.iservice.ArticleService;
import com.rcc.dev.backend.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public RCCResponse<Object> listArticle() {
        try {
            var listArticle = articleRepository.findAll();
            return ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_ALL_DATA,
                    ResponseCode.CommonEng.SUCCESS_GET_ALL_DATA,
                    listArticle
            );
        }catch (Exception e){
            return ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            );
        }
    }

    @Transactional
    @Override
    public RCCResponse<Object> save(ArticleRequest articleRequest) {
        try {
            Article article = Article.builder()
                    .id(articleRequest.getId() == null || articleRequest.getId().equals(0L) ? 1L : articleRequest.getId())
                    .description(articleRequest.getDescription())
                    .galleryId(articleRequest.getGalleryId())
                    .build();

            var savedArticle = articleRepository.save(article);
            return ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_SAVE_DATA,
                    ResponseCode.CommonEng.SUCCESS_SAVE_DATA,
                    savedArticle
            );
        }catch (Exception e){
            return ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            );
        }
    }

    @Override
    public RCCResponse<Object> getDetail(Long id) {
        try {
            var article = articleRepository.findById(id);
            return article.<RCCResponse<Object>>map(value -> ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_DATA_DETAIL,
                    ResponseCode.CommonEng.SUCCESS_GET_DATA_DETAIL,
                    value
            )).orElseGet(() -> ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.DATA_NOT_FOUND,
                    ResponseCode.CommonEng.DATA_NOT_FOUND,
                    article
            ));
        }catch (Exception e){
            return ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            );
        }
    }

    @Transactional
    @Override
    public RCCResponse<Object> delete(Long id) {
        try {
            var article = articleRepository.findById(id);
            if(article.isEmpty()){
                return ResponseUtil.response(
                        ResponseCode.SUCCESS_RESPONSE_CODE,
                        ResponseCode.CommonIdn.DATA_NOT_FOUND,
                        ResponseCode.CommonEng.DATA_NOT_FOUND
                );
            }
            articleRepository.delete(article.get());
            return ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_DELETED_DATA,
                    ResponseCode.CommonEng.SUCCESS_DELETED_DATA
            );
        }catch (Exception e){
            return ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            );
        }
    }
}
