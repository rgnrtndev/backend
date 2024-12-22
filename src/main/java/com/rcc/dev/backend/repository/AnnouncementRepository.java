package com.rcc.dev.backend.repository;

import com.rcc.dev.backend.dto.announcement.pojo.AnnouncementPojo;
import com.rcc.dev.backend.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    @Query(value = "select " +
            "s.id as Id," +
            "s.title as Title, " +
            "s.description as Description, " +
            "g.image_base64 as ImageBase64 " +
            "from announcement s " +
            "JOIN gallery g ON s.gallery_id = g.id", nativeQuery = true)
    List<AnnouncementPojo> findAllSlider();
}
