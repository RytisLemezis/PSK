package com.example.psk1.services;

import com.example.psk1.entities.Artist;
import com.example.psk1.entities.Award;
import com.example.psk1.persistence.ArtistsDAO;
import com.example.psk1.persistence.AwardsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class ArtistService {
    @Inject
    private ArtistsDAO artistsDAO;

    @Inject
    private AwardsDAO awardsDAO;

    public List<Artist> getAllArtists() {
        return artistsDAO.loadAll();
    }

    @Transactional
    public void createArtist(Artist artist) {
        artistsDAO.persist(artist);
    }

    public Artist getArtistById(Long id) {
        return artistsDAO.findOne(id);
    }

    @Transactional
    public void updateArtist(Artist artist) {
        artistsDAO.update(artist);
    }
}
