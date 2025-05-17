package com.example.psk1.services.mybatis;

import com.example.psk1.mybatis.dao.ArtistMapper;
import com.example.psk1.mybatis.model.Artist;
import com.example.psk1.mybatis.model.Genre;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ArtistMyBatis implements Serializable {

    @Inject
    private ArtistMapper artistMapper;

    @Getter
    @Setter
    private List<Artist> allArtists;

    @Setter
    @Getter
    private Artist artistToCreate = new Artist();

    @Setter
    @Getter
    private Artist artistToEdit = new Artist();

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
        loadAllArtists();             // reload list after insert
        artistToCreate = new Artist(); // reset create form
        return "/myBatis/artists?faces-redirect=true";
    }

    @Transactional
    public String updateArtist() {
        artistMapper.updateByPrimaryKey(artistToEdit);
        loadAllArtists();
        artistToEdit = null;
        return null;
    }



}
