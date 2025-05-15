package com.example.psk1.services.mybatis;

import com.example.psk1.mybatis.dao.ArtistMapper;
import com.example.psk1.mybatis.model.Artist;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class ArtistMyBatis {

    @Inject
    private ArtistMapper artistMapper;

    private List<Artist> allArtists;

    private Artist artistToCreate = new Artist();

    @PostConstruct
    public void init() {
        loadAllArtists();
    }

    private void loadAllArtists() {
        allArtists = artistMapper.selectAll();
    }

    @Transactional
    public String createArtist() {
        artistMapper.insert(artistToCreate);
        return "/myBatis/artists?faces-redirect=true";
    }

    public List<Artist> getAllArtists() {
        return allArtists;
    }

    public Artist getArtistToCreate() {
        return artistToCreate;
    }

    public void setArtistToCreate(Artist artistToCreate) {
        this.artistToCreate = artistToCreate;
    }
}