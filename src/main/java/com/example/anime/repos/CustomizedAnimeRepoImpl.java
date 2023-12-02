package com.example.anime.repos;

import com.example.anime.domain.Anime;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CustomizedAnimeRepoImpl implements CustomizedAnimeRepo<Anime> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Anime> findAnimeByStatusAndTypeIn(List<String> status, List<String> type) {
        TypedQuery<Anime> query;
        List<Anime> result = null;
        if (!status.isEmpty() && !type.isEmpty()) {
            query = entityManager.createQuery(
                    "SELECT anime FROM Anime anime WHERE anime.status IN (:status) and anime.type IN(:type)", Anime.class);
            result = query.setParameter("status", status).setParameter("type", type).getResultList();
        }
        if (!status.isEmpty() && type.isEmpty()) {
            query = entityManager.createQuery(
                    "SELECT anime FROM Anime anime WHERE anime.status IN (:status)", Anime.class);
            result = query.setParameter("status", status).getResultList();
        }
        if (status.isEmpty() && !type.isEmpty()) {
            query = entityManager.createQuery(
                    "SELECT anime FROM Anime anime WHERE anime.type IN (:type)", Anime.class);
            result = query.setParameter("type", type).getResultList();
        }
        return result;

    }
}
