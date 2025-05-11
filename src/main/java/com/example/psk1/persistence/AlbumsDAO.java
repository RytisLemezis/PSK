package com.example.psk1.persistence;

import com.example.psk1.entities.Album;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class AlbumsDAO {
    @Inject
    private EntityManager em;

    public void persist(Album album){
        this.em.persist(album);
    }

    public Album findOne(Long id) {
        return em.find(Album.class, id);
    }

    public Album update(Album album){
        return em.merge(album);
    }

    public List<Album> loadAll() {
        return em.createQuery("SELECT b FROM Album b", Album.class).getResultList();
    }

    public List<Album> findAlbumsByGenreId(Long genreId) {
        return em.createQuery(
                        "SELECT DISTINCT b FROM Album b " +
                                "JOIN FETCH b.artist " +
                                "JOIN b.genres g " +
                                "WHERE g.id = :genreId", Album.class)
                .setParameter("genreId", genreId)
                .getResultList();
    }
}
