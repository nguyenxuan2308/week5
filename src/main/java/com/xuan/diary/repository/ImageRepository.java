package com.xuan.diary.repository;

import com.xuan.diary.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    @Query("UPDATE images u SET u.status = 'DELETE' WHERE u.diaryId = ?1")
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    void deleteAllByDiaryId(Integer id);
}
