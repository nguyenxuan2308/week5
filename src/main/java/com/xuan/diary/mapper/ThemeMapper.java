package com.xuan.diary.mapper;

import com.xuan.diary.common.status.Status;
import com.xuan.diary.model.entity.Theme;
import com.xuan.diary.model.request.ThemeRequest;
import com.xuan.diary.model.response.ThemeResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class ThemeMapper {

    /**
     * mapper GradeRequest to Grade Entity
     *
     * @return Grade
     */
    public  Theme map(ThemeRequest request) {
        return new Theme().setName(request.getName())
                .setStatus(Status.valueOf(request.getStatus()));
    }

    /**
     * mapper Grade to GradeResponse
     *
     * @return GradeResponse
     */
    public  ThemeResponse map(Theme theme) {
        return new ThemeResponse().setId(theme.getId()).setName(theme.getName());
    }

    /**
     * mapper List Grade to List GradeResponse
     *
     * @return List<GradeResponse>
     */
    public  List<ThemeResponse> map(List<Theme> themes) {
        return themes.stream().map(this::map).collect(Collectors.toList());
    }

    /**
     * mapper GradeRequest to Grade Other
     * @return Grade
     */
    public  Theme map(ThemeRequest source, Theme target){
        return  target.setName(source.getName());
    }
}
