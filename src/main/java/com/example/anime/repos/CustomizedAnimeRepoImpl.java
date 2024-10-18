package com.example.anime.repos;

import com.example.anime.domain.Anime;
import com.example.anime.domain.Genre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomizedAnimeRepoImpl implements CustomizedAnimeRepo<Anime> {

    private static final String STATUS_FIELD = "status";
    private static final String TYPE_FIELD = "type";
    private static final String GENRES_FIELD = "genres";
    public static final String BLANK_SORT = "";
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Page<Anime>> findAllByParams(List<String> status, List<String> type, List<Genre> genre, Pageable pageable) {
        List<Anime> result;
        String sort = getSort(pageable);
        PageImpl<Anime> page;
        long animeTotal = createCountQuery(status, type, genre).getSingleResult();
        result = createQuery(status, type, genre, sort)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        page = new PageImpl<>(result, pageable, animeTotal);
        return Optional.of(page);

    }

    private TypedQuery<Anime> createQuery(List<String> status, List<String> type, List<Genre> genre, String sortField) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Anime> criteriaQuery = criteriaBuilder.createQuery(Anime.class);
        Root<Anime> anime = criteriaQuery.from(Anime.class);
        List<Predicate> predicateList = getPredicates(status, type, genre, criteriaBuilder, anime);
        Predicate[] predicates = predicateList.toArray(new Predicate[0]);
        Predicate finalPredicate = criteriaBuilder.and(predicates);
        CriteriaQuery<Anime> finalQuery = criteriaQuery
                .select(anime)
                .where(finalPredicate);
        if (!sortField.isEmpty()) {
            finalQuery.orderBy(criteriaBuilder.desc(anime.get(sortField)));
        }
        return entityManager.createQuery(finalQuery);
    }

    private TypedQuery<Long> createCountQuery(List<String> status, List<String> type, List<Genre> genre) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Anime> anime = criteriaQuery.from(Anime.class);
        List<Predicate> predicateList = getPredicates(status, type, genre, criteriaBuilder, anime);
        Predicate[] predicates = predicateList.toArray(new Predicate[0]);
        Predicate finalPredicate = criteriaBuilder.and(predicates);
        return entityManager.createQuery(criteriaQuery.select(criteriaBuilder.count(anime)).where(finalPredicate));
    }

    private List<Predicate> getPredicates(List<String> status, List<String> type, List<Genre> genre, CriteriaBuilder criteriaBuilder, Root<Anime> anime) {
        List<Predicate> predicateList = new ArrayList<>();
        if (!status.isEmpty()) {
            predicateList.add(anime.get(STATUS_FIELD).in(status));
        }
        if (!type.isEmpty()) {
            predicateList.add(anime.get(TYPE_FIELD).in(type));
        }
        if (!genre.isEmpty()) {
            for (Genre g : genre) {
                predicateList.add(criteriaBuilder.isMember(g, anime.get(GENRES_FIELD)));
            }
        }
        return predicateList;
    }
    private static String getSort(Pageable pageable) {
        String sort = BLANK_SORT;
        if (pageable.getSort() != Sort.unsorted()) {
            for (Sort.Order order : pageable.getSort()) {
                sort = order.getProperty();
            }
        }
        return sort;
    }
}
