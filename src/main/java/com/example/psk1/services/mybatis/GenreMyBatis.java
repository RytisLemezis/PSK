package com.example.psk1.services.mybatis;

import com.example.psk1.mybatis.dao.GenreMapper;
import com.example.psk1.mybatis.model.Genre;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class GenreMyBatis {

    @Inject
    private GenreMapper genreMapper;

    private List<Genre> allGenres;

    private Genre genreToCreate = new Genre();

    @PostConstruct
    public void init() {
        loadAllGenres();
    }

    private void loadAllGenres() {
        allGenres = genreMapper.selectAll();
    }

    @Transactional
    public String createGenre() {
        genreMapper.insert(genreToCreate);
        return "/myBatis/genres?faces-redirect=true";
    }

    public List<Genre> getAllGenres() {
        return allGenres;
    }

    public Genre getGenreToCreate() {
        return genreToCreate;
    }

    public void setGenreToCreate(Genre genreToCreate) {
        this.genreToCreate = genreToCreate;
    }
}

