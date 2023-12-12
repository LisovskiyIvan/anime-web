package com.example.anime.repos;

import com.example.anime.domain.Anime;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
public class CustomizedAnimeRepoImpl implements CustomizedAnimeRepo<Anime> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Anime> findAllByParams(List<String> status, List<String> type, List<String> genre, Pageable pageable) {
        List<Anime> result;
        String sort = getSort(pageable);
        long animeTotal = createCountQuery(status, type, genre).getSingleResult();
        result = createQuery(status, type, genre, sort)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        return new PageImpl<>(result, pageable, animeTotal);

    }

    private TypedQuery<Anime> createQuery(List<String> status, List<String> type, List<String> genre, String sortField) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Anime> criteriaQuery = criteriaBuilder.createQuery(Anime.class);
        Root<Anime> root = criteriaQuery.from(Anime.class);
        Predicate predicateForStatus;
        Predicate predicateForType;
        Predicate predicateForGenre;
        List<Predicate> predicateList = new ArrayList<>();
        if (!status.isEmpty()) {
            predicateForStatus = root.get("status").in(status);
            predicateList.add(predicateForStatus);
        }
        if (!type.isEmpty()) {
            predicateForType = root.get("type").in(type);
            predicateList.add(predicateForType);
        }
        if (!genre.isEmpty()) {
            predicateForGenre = root.get("genres").in(genre);
            predicateList.add(predicateForGenre);
        }
        Predicate[] predicates = predicateList.toArray(new Predicate[0]);
        Predicate finalPredicate = criteriaBuilder.and(predicates);
        CriteriaQuery<Anime> finalQuery = criteriaQuery
                .select(root)
                .where(finalPredicate);
        if (!sortField.isEmpty()) {
            finalQuery = criteriaQuery
                    .select(root)
                    .where(finalPredicate)
                    .orderBy(criteriaBuilder.desc(root.get(sortField)));
        }
        return entityManager.createQuery(finalQuery);
    }

    private TypedQuery<Long> createCountQuery(List<String> status, List<String> type, List<String> genre) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Anime> root = criteriaQuery.from(Anime.class);
        Predicate predicateForStatus;
        Predicate predicateForType;
        Predicate predicateForGenre;
        List<Predicate> predicateList = new ArrayList<>();
        if (!status.isEmpty()) {
            predicateForStatus = root.get("status").in(status);
            predicateList.add(predicateForStatus);
        }
        if (!type.isEmpty()) {

            predicateForType = root.get("type").in(type);
            predicateList.add(predicateForType);
        }
        if (!genre.isEmpty()) {
            predicateForGenre = root.get("genres").in(genre);
            predicateList.add(predicateForGenre);
        }
        Predicate[] predicates = predicateList.toArray(new Predicate[0]);
        Predicate finalPredicate = criteriaBuilder.and(predicates);
        return entityManager.createQuery(criteriaQuery.select(criteriaBuilder.count(root)).where(finalPredicate));
    }


    private static String getSort(Pageable pageable) {
        String sort = "";
        if (pageable.getSort() != Sort.unsorted()) {
            for (Sort.Order order : pageable.getSort()) {
                sort = order.getProperty();
            }
        }
        return sort;
    }
}
