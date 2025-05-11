package com.example.psk1.ui;

import com.example.psk1.entities.Artist;
import com.example.psk1.entities.Genre;
import com.example.psk1.services.GenreService;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class GenresController implements Serializable {
    @Inject
    private GenreService genreService;

    @Getter
    @Setter
    private Genre selectedGenre = new Genre();

    @Setter
    @Getter
    private List<Genre> genres = new ArrayList<>();

    @PostConstruct
    public void init() {
        loadGenres();
    }

    private void loadGenres() {
        genres = genreService.getAllGenres();
    }

    public String createGenre() {
        genreService.createGenre(selectedGenre);
        selectedGenre = new Genre();
        loadGenres();
        return "genres.xhtml?faces-redirect=true";
    }

    public String updateGenre() {
        genreService.updateGenre(selectedGenre);
        selectedGenre = new Genre();
        loadGenres();
        return "genres.xhtml?faces-redirect=true";
    }

    public void prepareNewGenre() {
        selectedGenre = new Genre();
    }

}