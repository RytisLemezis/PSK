package com.example.psk1.services;

import com.example.psk1.entities.Artist;
import com.example.psk1.entities.Genre;
import com.example.psk1.persistence.GenresDAO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class GenreService {
    @Inject
    private GenresDAO genresDAO;

    @Transactional
    public void createGenre(Genre genre) {
        genresDAO.persist(genre);
    }

    public Genre getGenreById(Long id) {
        return genresDAO.findOne(id);
    }

    @Transactional
    public void updateGenre(Genre genre) {
        genresDAO.update(genre);
    }

    public List<Genre> getAllGenresWithAlbums() {
        return genresDAO.findAllWithAlbums();
    }

    public List<Genre> getAllGenres() {
        return genresDAO.loadAll();
    }
}
