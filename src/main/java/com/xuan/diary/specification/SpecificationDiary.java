package com.xuan.diary.specification;

import com.xuan.diary.common.status.Status;
import com.xuan.diary.model.entity.Diary;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Component
public class SpecificationDiary {
    public Specification<Diary> create(List<Integer> diaryIds, String title, boolean favoritesList, Timestamp timeStart, Timestamp timeEnd) {
        return (root, cq, cb) -> {
            Predicate p = cb.conjunction();

            if (!diaryIds.isEmpty()) {
                p = cb.and(p, root.get("id").in(diaryIds));
            }
            if (!StringUtils.isEmpty(title)) {
                p = cb.and(p, cb.like(root.get("title"), "%" + title + "%"));
            }
            if (favoritesList) {
                p = cb.and(p, cb.isTrue(root.get("favoritesList")));
            }
            if (Objects.nonNull(timeStart) && Objects.nonNull(timeEnd) && timeStart.before(timeEnd)) {
                p = cb.and(p, cb.between(root.get("dateCreate"), timeStart.toLocalDateTime(), timeEnd.toLocalDateTime()));
            }
            p = cb.and(p, cb.equal(root.get("status"), Status.ACTIVE));
            return p;
        };
    }
}
