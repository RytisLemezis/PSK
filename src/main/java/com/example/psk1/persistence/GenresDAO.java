package com.example.psk1.persistence;

import com.example.psk1.entities.Artist;
import com.example.psk1.entities.Genre;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class GenresDAO {
    @Inject
    private EntityManager em;

    public void persist(Genre genre){
        this.em.persist(genre);
    }

    public Genre findOne(Long id){
        return em.find(Genre.class, id);
    }

    public Genre update(Genre genre){
        return em.merge(genre);
    }

    public List<Genre> findAllWithAlbums() {
        return em.createQuery("SELECT g FROM Genre g LEFT JOIN FETCH g.albums ORDER BY g.name", Genre.class)
                .getResultList();
    }

    public List<Genre> loadAll() {
        return em.createQuery("SELECT g FROM Genre g", Genre.class).getResultList();
    }
}
