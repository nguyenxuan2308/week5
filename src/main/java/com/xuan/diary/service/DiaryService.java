package com.xuan.diary.service;

import com.xuan.diary.model.request.ContentDiaryRequest;
import com.xuan.diary.model.request.DiaryRequest;
import com.xuan.diary.model.response.DiaryResponse;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface DiaryService {

    List<DiaryResponse> findAllByStatus();

    DiaryResponse findByIdDiary(Integer id);

    List<DiaryResponse> findByIdUser(Integer id);

    List<DiaryResponse> filter(List<Integer> themeIds, String title, boolean favoriteList, Timestamp timeStart, Timestamp timeEnd);

    DiaryResponse create(DiaryRequest diaryRequest);

    DiaryResponse update(DiaryRequest diaryRequest, Integer id);

    DiaryResponse updateContent(ContentDiaryRequest contentDiaryRequest, Integer id);

    DiaryResponse updateTheme(List<Integer> themeIds, Integer id);

    DiaryResponse updateFavoriteList(Integer id);

    void deleteDiaryById(Integer id);
}
