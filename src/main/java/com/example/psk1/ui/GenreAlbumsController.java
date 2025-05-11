package com.example.psk1.ui;

import com.example.psk1.entities.Album;
import com.example.psk1.entities.Genre;
import com.example.psk1.services.AlbumService;
import com.example.psk1.services.GenreService;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class GenreAlbumsController implements Serializable {
    @Inject
    private GenreService genreService;
    @Inject
    private AlbumService albumService;

    @Getter
    @Setter
    private Long genreId;

    @Getter
    private List<Album> albums = new ArrayList<>();

    @Getter
    private String genreName = "";

    public void loadGenreAlbums() {
        if (genreId != null) {
            Genre genre = genreService.getGenreById(genreId);
            if (genre != null) {
                genreName = genre.getName();
                albums = albumService.getAlbumsByGenreId(genreId);
            }
        }
    }
}
