package com.xuan.diary.service;

import com.xuan.diary.model.request.ThemeRequest;
import com.xuan.diary.model.response.ThemeResponse;

import java.util.List;

public interface ThemeService {

    List<ThemeResponse> findAllByStatus();

    ThemeResponse create(ThemeRequest themeRequest);

    ThemeResponse update(ThemeRequest themeRequest, Integer id);

    void deleteThemeById(Integer id);
}
