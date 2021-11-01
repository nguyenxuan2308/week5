package com.xuan.diary.mapper;

import com.xuan.diary.common.status.Status;
import com.xuan.diary.model.entity.Diary;
import com.xuan.diary.model.entity.DiaryTheme;
import com.xuan.diary.model.entity.Theme;
import com.xuan.diary.model.request.DiaryRequest;
import com.xuan.diary.model.response.DiaryResponse;
import com.xuan.diary.model.response.ThemeResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DiaryMapper {
    /**
     * mapper DiaryRequest to Diary Entity
     *
     * @return Diary
     */
    public Diary map(DiaryRequest request) {

        return new Diary().setUserId(request.getUserId())
                .setTitle(request.getTitle())
                .setContent(request.getContent())
                .setFavoritesList(request.isFavoritesList())
                .setStatus(Status.valueOf(request.getStatus()));
    }

    /**
     * mapper Diary to DiaryResponse
     *
     * @return DiaryResponse
     */
    public DiaryResponse map(Diary diary) {
        return new DiaryResponse()
                .setId(diary.getId())
                .setUserId(diary.getUserId())
                .setTitle(diary.getTitle())
                .setContent(diary.getContent())
                .setFavoritesList(diary.isFavoritesList())
                .setDateCreate(diary.getDateCreate())
                .setDateUpdate(diary.getDateUpdate());
    }

    /**
     * mapper Diary to DiaryResponse
     *
     * @return DiaryResponse
     */
    public DiaryResponse map(Diary diary, List<ThemeResponse> themeResponses) {
        return new DiaryResponse()
                .setId(diary.getId())
                .setThemes(themeResponses)
                .setUserId(diary.getUserId())
                .setTitle(diary.getTitle())
                .setContent(diary.getContent())
                .setFavoritesList(diary.isFavoritesList())
                .setDateCreate(diary.getDateCreate())
                .setDateUpdate(diary.getDateUpdate());
    }

    /**
     * mapper List Diary to List DiaryResponse
     *
     * @return List<DiaryResponse>
     */
    public List<DiaryResponse> map(List<Diary> diaries) {
        return diaries.stream().map(this::map).collect(Collectors.toList());
    }

    /**
     * mapper DiaryRequest to Diary Exist
     *
     * @return Diary
     */
    public Diary map(DiaryRequest source, Diary target) {
        return target.setContent(source.getContent())
                .setTitle(source.getTitle())
                .setUserId(source.getUserId())
                .setContent(source.getContent())
                .setFavoritesList(source.isFavoritesList());
    }

}
