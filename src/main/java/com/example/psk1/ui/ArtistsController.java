package com.example.psk1.ui;

import com.example.psk1.entities.Artist;
import com.example.psk1.services.ArtistService;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class ArtistsController implements Serializable {
    @Inject
    private ArtistService artistService;

    @Getter
    @Setter
    private Artist selectedArtist = new Artist();

    @Setter
    @Getter
    private List<Artist> artists = new ArrayList<>();

    @PostConstruct
    public void init() {
        loadArtists();
    }

    private void loadArtists() {
        artists = artistService.getAllArtists();
    }

    public String createArtist() {
        artistService.createArtist(selectedArtist);
        selectedArtist = new Artist();
        loadArtists();
        return "artists.xhtml?faces-redirect=true";
    }

    public String updateArtist() {
        artistService.updateArtist(selectedArtist);
        selectedArtist = new Artist();
        loadArtists();
        return "artists.xhtml?faces-redirect=true";
    }

    public void prepareNewArtist() {
        selectedArtist = new Artist();
    }

}
