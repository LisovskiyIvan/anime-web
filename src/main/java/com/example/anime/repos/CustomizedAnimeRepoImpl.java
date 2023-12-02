package com.example.anime.repos;

import com.example.anime.domain.Anime;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
@Slf4j
public class CustomizedAnimeRepoImpl implements CustomizedAnimeRepo<Anime> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Anime> findAnimeByStatusAndTypeIn(List<String> status, List<String> type, Pageable pageable) {
        TypedQuery<Anime> query;
        List<Anime> result;
        Page<Anime> page = null;
        String sort = getSort(pageable);
        if (!status.isEmpty() && !type.isEmpty()) {
            query = entityManager.createQuery(
                    "SELECT anime FROM Anime anime WHERE anime.status IN (:status) and anime.type IN(:type)" + sort, Anime.class);
            long animeTotal =  entityManager.createQuery(
                    "SELECT count(anime) FROM Anime anime WHERE anime.status IN (:status) and anime.type IN(:type)", Long.class)
                    .setParameter("status", status)
                    .setParameter("type", type)
                    .getSingleResult();
            result = query
                    .setParameter("status", status)
                    .setParameter("type", type)
                    .setFirstResult((int) pageable.getOffset())
                    .setMaxResults(pageable.getPageSize())
                    .getResultList();
            page = new PageImpl<>(result, pageable, animeTotal);
        }
        if (!status.isEmpty() && type.isEmpty()) {
            query = entityManager.createQuery(
                    "SELECT anime FROM Anime anime WHERE anime.status IN (:status)" + sort, Anime.class);
            long animeTotal =  entityManager.createQuery(
                            "SELECT count(anime) FROM Anime anime WHERE anime.status IN (:status)", Long.class)
                    .setParameter("status", status)
                    .getSingleResult();
            result = query
                    .setParameter("status", status)
                    .setFirstResult((int) pageable.getOffset())
                    .setMaxResults(pageable.getPageSize())
                    .getResultList();
            page = new PageImpl<>(result, pageable, animeTotal);
        }
        if (status.isEmpty() && !type.isEmpty()) {
            query = entityManager.createQuery(
                    "SELECT anime FROM Anime anime WHERE anime.type IN (:type)" + sort, Anime.class);
            long animeTotal =  entityManager.createQuery(
                    "SELECT count(anime) FROM Anime anime WHERE anime.type IN (:type)", Long.class)
                    .setParameter("type", type)
                    .getSingleResult();
            result = query
                    .setParameter("type", type)
                    .setFirstResult((int) pageable.getOffset())
                    .setMaxResults(pageable.getPageSize())
                    .getResultList();
            page = new PageImpl<>(result, pageable, animeTotal);
        }
        return page;

    }

    private static String getSort(Pageable pageable) {
        String sort = "";
        if (pageable.getSort() != Sort.unsorted()) {
            StringBuilder orderBy = new StringBuilder(" ORDER BY ");
            for (Sort.Order order : pageable.getSort())
            {
                orderBy.append(order.getProperty()).append(" ").append(order.getDirection()).append(", ");
            }
            orderBy.deleteCharAt(orderBy.length() - 1);
            orderBy.deleteCharAt(orderBy.length() - 1);
            sort = orderBy.toString();
        }
        return sort;
    }
}
