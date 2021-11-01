package com.xuan.diary.service.impl;

import com.xuan.diary.common.exception.NotFoundException;
import com.xuan.diary.common.status.Status;
import com.xuan.diary.mapper.ThemeMapper;
import com.xuan.diary.model.entity.Theme;
import com.xuan.diary.model.request.ThemeRequest;
import com.xuan.diary.model.response.ThemeResponse;
import com.xuan.diary.repository.DiaryRepository;
import com.xuan.diary.repository.ThemeRepository;
import com.xuan.diary.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService {
    private final ThemeRepository themeRepository;

    private final ThemeMapper themeMapper;

    private final DiaryRepository diaryRepository;

    /**
     * get All ThemeResponse
     *
     * @return List of ThemeResponse
     */
    @Override
    public List<ThemeResponse> findAllByStatus() {
        return themeMapper.map(themeRepository.findAllByStatus(Status.ACTIVE));
    }

    /**
     * create ThemeResponse
     *
     * @return ThemeResponse
     * @RequestBody gradeRequest
     */
    @Override
    public ThemeResponse create(ThemeRequest themeRequest) {
        Theme theme = themeMapper.map(themeRequest);
        System.out.println(theme.toString());
        return themeMapper.map(themeRepository.save(theme));
    }

    /**
     * update ThemeResponse
     *
     * @return ThemeResponse
     * @RequestBody gradeRequest
     */
    @Override
    public ThemeResponse update(ThemeRequest themeRequest, Integer id) {
        Theme theme = themeRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Theme"));
        Theme themeMap = themeMapper.map(themeRequest, theme);
        return themeMapper.map(themeRepository.save(themeMap));
    }

    /**
     * Delete Theme By Id
     */

    @Modifying
    @Override
    public void deleteThemeById(Integer id) {
        themeRepository.deleteThemeById(id);
    }
}
