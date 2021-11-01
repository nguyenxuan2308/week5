package com.xuan.diary.service.impl;

import com.xuan.diary.common.exception.NotFoundException;
import com.xuan.diary.common.status.Status;
import com.xuan.diary.mapper.DiaryMapper;
import com.xuan.diary.mapper.DiaryThemeMapper;
import com.xuan.diary.mapper.ImageMapper;
import com.xuan.diary.mapper.ThemeMapper;
import com.xuan.diary.model.entity.Diary;
import com.xuan.diary.model.entity.DiaryTheme;
import com.xuan.diary.model.entity.Image;
import com.xuan.diary.model.entity.Theme;
import com.xuan.diary.model.request.ContentDiaryRequest;
import com.xuan.diary.model.request.DiaryRequest;
import com.xuan.diary.model.response.DiaryResponse;
import com.xuan.diary.repository.DiaryRepository;
import com.xuan.diary.repository.DiaryThemeRepository;
import com.xuan.diary.repository.ImageRepository;
import com.xuan.diary.repository.ThemeRepository;
import com.xuan.diary.service.DiaryService;
import com.xuan.diary.specification.SpecificationDiary;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {
    private final DiaryRepository diaryRepository;

    private final ImageRepository imageRepository;

    private final ThemeRepository themeRepository;

    private final DiaryThemeRepository diaryThemeRepository;

    private final DiaryMapper diaryMapper;

    private final ImageMapper imageMapper;

    private final ThemeMapper themeMapper;

    private final DiaryThemeMapper diaryThemeMapper;

    private final SpecificationDiary specificationDiary;

    /**
     * Convert  Diary to  DiaryResponse
     *
     * @param diary
     * @return
     */
    public DiaryResponse covertDiaryToDiaryResponse(Diary diary) {
        //  get list id theme in table diarytheme
        List<DiaryTheme> diaryThemes = diaryThemeRepository.findAllByStatusAndDiaryId(Status.ACTIVE, diary.getId());
        // get list detail theme by list id
        List<Theme> themes = themeRepository.findAllByIdIn(diaryThemeMapper.map(diaryThemes));
        return diaryMapper.map(diary, themeMapper.map(themes));
    }

    /**
     * Convert List Diary to List DiaryResponse
     *
     * @return List<DiaryResponse>
     */
    public List<DiaryResponse> covertDiariesToDiaryResponses(List<Diary> diaries) {
        List<DiaryResponse> diaryResponses = new ArrayList<>();
        for (Diary diary : diaries) {
            diaryResponses.add(covertDiaryToDiaryResponse(diary));
        }
        return diaryResponses;
    }

    /**
     * get All DiaryResponse
     *
     * @return List of DiaryResponse
     */
    @Override

    public List<DiaryResponse> findAllByStatus() {
        List<Diary> diaries = diaryRepository.findAllByStatus(Status.ACTIVE);
        return covertDiariesToDiaryResponses(diaries);
    }

    /**
     * get By Id DiaryResponse
     *
     * @return DiaryResponse
     */
    @Override
    public DiaryResponse findByIdDiary(Integer id) {
        Diary diary = diaryRepository.getById(id);
        //  get list id theme in table diarytheme
        List<DiaryTheme> diaryThemes = diaryThemeRepository.findAllByDiaryId(diary.getId());
        // get list detail theme by list id
        List<Theme> themes = themeRepository.findAllByIdIn(diaryThemeMapper.map(diaryThemes));
        return diaryMapper.map(diary, themeMapper.map(themes));
    }

    /**
     * get All DiaryResponse By Id Of User
     *
     * @return List of DiaryResponse
     */
    @Override
    public List<DiaryResponse> findByIdUser(Integer id) {
        List<Diary> diaries = diaryRepository.findAllByUserIdAndStatus(id, Status.ACTIVE);
        return covertDiariesToDiaryResponses(diaries);
    }

    @Override
    public List<DiaryResponse> filter(List<Integer> themeIds, String title, boolean favoritesList, Timestamp timeStart, Timestamp timeEnd) {
        // get list diaryIds by list diaryIds
        List<Integer> diaryIds = diaryThemeRepository.findAllByStatusAndThemeIdIn(Status.ACTIVE, themeIds);
        // use specification to filter
        Specification<Diary> specificationFilter = specificationDiary.create(diaryIds, title, favoritesList, timeStart, timeEnd);
        List<Diary> diaries = diaryRepository.findAll(specificationFilter);
        return covertDiariesToDiaryResponses(diaries);
    }


    /**
     * create DiaryResponse
     *
     * @return DiaryResponse
     */
    @Override
    public DiaryResponse create(DiaryRequest request) {
        Diary diary = diaryMapper.map(request);
        diary = diaryRepository.save(diary);
        // save list url image of diary
        List<Image> images = imageMapper.map(request.getUrlImages(), diary.getId());
        imageRepository.saveAll(images);
        // save list DiaryTheme
        List<DiaryTheme> diaryThemes = diaryThemeMapper.map(request.getThemeIds(), diary.getId());
        diaryThemeRepository.saveAll(diaryThemes);
        return diaryMapper.map(diary);
    }

    /**
     * update DiaryResponse
     *
     * @return DiaryResponse
     */
    @Override
    public DiaryResponse update(DiaryRequest request, Integer id) {
        Diary diary = diaryRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Diary"));
        diary = diaryMapper.map(request, diary);
        diaryRepository.save(diary);
        // delete  list url image old by diaryId
        imageRepository.deleteAllByDiaryId(id);
        // update new list url image of diary
        List<Image> images = imageMapper.map(request.getUrlImages(), id);
        imageRepository.saveAll(images);
        // delete  list diarytheme old by diaryId
        diaryThemeRepository.deleteAllByDiaryId(id);
        // update new list diarytheme image of diary
        List<DiaryTheme> diaryThemes = diaryThemeMapper.map(request.getThemeIds(), diary.getId());
        diaryThemeRepository.saveAll(diaryThemes);
        return diaryMapper.map(diary);
    }

    @Override
    public DiaryResponse updateContent(ContentDiaryRequest contentRequest, Integer id) {
        Diary diary = diaryRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Diary"));
        diary = diary.setContent(contentRequest.getContent());
        diary = diaryRepository.save(diary);
        // delete  list url image old by diaryId
        imageRepository.deleteAllByDiaryId(id);
        // update new list url image of diary
        List<Image> images = imageMapper.map(contentRequest.getUrlImages(), id);
        imageRepository.saveAll(images);
        return diaryMapper.map(diary);
    }

    @Override
    public DiaryResponse updateTheme(List<Integer> themeIds, Integer id) {
        diaryThemeRepository.deleteAllByDiaryId(id);
        List<DiaryTheme> diaryThemes = diaryThemeMapper.map(themeIds, id);
        diaryThemeRepository.saveAll(diaryThemes);
        Diary diary = diaryRepository.getById(id);
        return covertDiaryToDiaryResponse(diary);
    }

    @Override
    public DiaryResponse updateFavoriteList(Integer id) {
        Diary diary = diaryRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Diary"));
        diary.setFavoritesList(true);
        diary = diaryRepository.save(diary);
        return diaryMapper.map(diary);
    }

    /**
     * Delete diary By Id
     *
     * @param id
     */
    @Override
    public void deleteDiaryById(Integer id) {
        imageRepository.deleteAllByDiaryId(id);
        diaryThemeRepository.deleteAllByDiaryId(id);
        diaryRepository.deleteDiaryById(id);
    }

}
