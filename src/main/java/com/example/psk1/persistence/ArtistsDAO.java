package com.example.psk1.persistence;

import com.example.psk1.entities.Artist;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ArtistsDAO {
    @Inject
    private EntityManager em;

    public void persist(Artist artist){
        this.em.persist(artist);
    }

    public List<Artist> loadAll() {
        return em.createQuery("SELECT a FROM Artist a", Artist.class).getResultList();
    }

    public Artist findOne(Long id){
        return em.find(Artist.class, id);
    }

    public Artist update(Artist artist){
        return em.merge(artist);
    }
}
