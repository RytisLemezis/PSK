package com.example.psk1.services.mybatis;

import com.example.psk1.mybatis.dao.GenreMapper;
import com.example.psk1.mybatis.model.Genre;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class GenreMyBatis implements Serializable {

    @Inject
    private GenreMapper genreMapper;

    @Setter
    @Getter
    private List<Genre> allGenres;

    @Setter
    @Getter
    private Genre genreToCreate = new Genre();

    @Setter
    @Getter
    private Genre genreToEdit = new Genre();

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
        loadAllGenres();  // reload after insert
        genreToCreate = new Genre(); // reset form
        return "/myBatis/genres?faces-redirect=true";
    }

    // Update existing genre in DB
    @Transactional
    public String updateGenre() {
        genreMapper.updateByPrimaryKey(genreToEdit);
        loadAllGenres(); // reload list after update
        genreToEdit = null; // clear editing object
        return null;
    }

}
