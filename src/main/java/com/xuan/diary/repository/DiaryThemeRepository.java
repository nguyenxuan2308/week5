package com.xuan.diary.repository;

import com.xuan.diary.common.status.Status;
import com.xuan.diary.model.entity.DiaryTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface DiaryThemeRepository extends JpaRepository<DiaryTheme, Integer> {

    List<DiaryTheme> findAllByDiaryId(Integer id);

    List<DiaryTheme>  findAllByStatusAndDiaryId(Status status, Integer id);

    @Query( "SELECT distinct(d.diaryId) FROM diary_themes d WHERE d.status like ?1 and d.themeId in ?2")
    List<Integer> findAllByStatusAndThemeIdIn(Status status, List<Integer> ids);

    @Query("UPDATE diary_themes u SET u.status = 'DELETE' WHERE u.diaryId = ?1")
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    void deleteAllByDiaryId(Integer id);
}
