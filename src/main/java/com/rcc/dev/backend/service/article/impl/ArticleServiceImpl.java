package com.rcc.dev.backend.service.article.impl;

import com.rcc.dev.backend.constant.ResponseCode;
import com.rcc.dev.backend.dto.article.ArticleRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.model.Article;
import com.rcc.dev.backend.repository.ArticleRepository;
import com.rcc.dev.backend.service.article.iservice.ArticleService;
import com.rcc.dev.backend.util.AuthenticationUtils;
import com.rcc.dev.backend.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final AuthenticationUtils authenticationUtils;

    @Override
    public ResponseEntity<RCCResponse<Object>> listArticle(HttpServletRequest httpServletRequest) {
        try {
            var listArticle = articleRepository.getListArticle();
            return ResponseEntity.ok(ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_ALL_DATA,
                    ResponseCode.CommonEng.SUCCESS_GET_ALL_DATA,
                    listArticle
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            ));
        }
    }

    @Transactional
    @Override
    public ResponseEntity<RCCResponse<Object>> update(HttpServletRequest httpServletRequest, ArticleRequest articleRequest) {
        var userData = authenticationUtils.validateAuthentication(httpServletRequest);
        try {
            Article article = Article.builder()
                    .id(articleRequest.getId() == null || articleRequest.getId().equals(0L) ? null : articleRequest.getId())
                    .description(articleRequest.getDescription())
                    .galleryId(articleRequest.getGalleryId())
                    .createdBy(userData.getId())
                    .build();

            var savedArticle = articleRepository.save(article);
            return ResponseEntity.ok(ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_SAVE_DATA,
                    ResponseCode.CommonEng.SUCCESS_SAVE_DATA,
                    savedArticle
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            ));
        }
    }

    @Override
    public ResponseEntity<RCCResponse<Object>> getDetail(HttpServletRequest httpServletRequest, Long id) {
        try {
            var article = articleRepository.getDetailArticle(id);

            return ResponseEntity.ok(ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_DATA_DETAIL,
                    ResponseCode.CommonEng.SUCCESS_GET_DATA_DETAIL,
                    article
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            ));
        }
    }

    @Transactional
    @Override
    public ResponseEntity<RCCResponse<Object>> delete(HttpServletRequest httpServletRequest, Long id) {
        var userData = authenticationUtils.validateAuthentication(httpServletRequest);
        try {
            var article = articleRepository.findById(id);
            if(article.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseUtil.response(
                        ResponseCode.SUCCESS_RESPONSE_CODE,
                        ResponseCode.CommonIdn.DATA_NOT_FOUND,
                        ResponseCode.CommonEng.DATA_NOT_FOUND
                ));
            }
            articleRepository.delete(article.get());
            return ResponseEntity.ok(ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_DELETED_DATA,
                    ResponseCode.CommonEng.SUCCESS_DELETED_DATA
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            ));
        }
    }
}
