package com.rcc.dev.backend.repository;

import com.rcc.dev.backend.dto.slider.pojo.SliderPojo;
import com.rcc.dev.backend.model.Slider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SliderRepository extends JpaRepository<Slider, Long> {

    @Query(value = "select " +
            "s.id as Id," +
            "s.title as Title, " +
            "s.description as Description, " +
            "g.image_base64 as ImageBase64 " +
            "from slider s " +
            "JOIN gallery g ON s.gallery_id = g.id", nativeQuery = true)
    List<SliderPojo> findAllSlider();
}
