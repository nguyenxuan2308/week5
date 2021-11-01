package com.xuan.diary.mapper;

import com.xuan.diary.common.status.Status;
import com.xuan.diary.model.entity.DiaryTheme;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DiaryThemeMapper {
    /**
     * mapper List url and Id Diary to Image Entity
     *
     * @return List Image Entity
     */
    public List<DiaryTheme> map(List<Integer> themeIds, Integer diaryId) {
        return themeIds.stream().map(it -> new DiaryTheme().setDiaryId(diaryId).setThemeId(it).setStatus(Status.ACTIVE)).collect(Collectors.toList());
    }

    /**
     * mapper List url and Id Diary to Image Entity
     *
     * @return List Image Entity
     */
    public List<Integer> map(List<DiaryTheme> diaryThemes) {
        return diaryThemes.stream().map(DiaryTheme::getThemeId).collect(Collectors.toList());
    }
}
