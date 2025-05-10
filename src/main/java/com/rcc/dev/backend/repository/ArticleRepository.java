package com.rcc.dev.backend.repository;

import com.rcc.dev.backend.dto.article.ArticleDetailResponse;
import com.rcc.dev.backend.dto.article.pojo.ArticlePojo;
import com.rcc.dev.backend.model.Article;
import org.eclipse.angus.mail.util.QEncoderStream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query(value = "select a.id as Id, " +
            "a.description as Description, " +
            "g.image_base64 as ImageBase64 " +
            "from article a " +
            "join gallery g on g.id = a.gallery_id"
            , nativeQuery = true)
    List<ArticlePojo> getListArticle();

    @Query(value = "select a.id as Id, " +
            "a.description as Description, " +
            "g.image_base64 as ImageBase64 " +
            "from article a " +
            "join gallery g on g.id = a.gallery_id where a.id = ?1"
            , nativeQuery = true)
    ArticleDetailResponse getDetailArticle(Long id);
}
