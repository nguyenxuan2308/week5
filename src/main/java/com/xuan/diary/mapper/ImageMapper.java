package com.xuan.diary.mapper;

import com.xuan.diary.common.status.Status;
import com.xuan.diary.model.entity.Image;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImageMapper {
    /**
     * mapper List url and Id Diary to Image Entity
     *
     * @return List Image Entity
     */
    public List<Image> map(List<String> urls, Integer diaryId) {
        return urls.stream().map(it -> new Image().setDiaryId(diaryId).setUrl(it).setStatus(Status.ACTIVE)).collect(Collectors.toList());
    }
}
