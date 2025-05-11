package com.example.psk1.ui;

import com.example.psk1.entities.Artist;
import com.example.psk1.entities.Album;
import com.example.psk1.entities.Genre;
import com.example.psk1.services.ArtistService;
import com.example.psk1.services.AlbumService;
import com.example.psk1.services.GenreService;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Named
@SessionScoped
public class AlbumsController implements Serializable {
    @Inject
    private AlbumService albumService;

    @Inject
    private ArtistService artistService;

    @Inject
    private GenreService genreService;

    // Getters and setters
    @Getter
    @Setter
    private Long artistId;

    @Getter
    @Setter
    private Long albumId;

    @Getter
    @Setter
    private Album selectedAlbum = new Album();

    @Getter
    private List<Album> albums = new ArrayList<>();

    @Getter
    private List<Genre> allGenres = new ArrayList<>();

    @Getter
    @Setter
    private Long[] selectedGenreIds;

    @PostConstruct
    public void init() {
        loadGenres();
    }

    private void loadGenres() {
        allGenres = genreService.getAllGenresWithAlbums();
    }
    public void loadArtistAlbums() {
        if (artistId != null) {
            Artist artist = artistService.getArtistById(artistId);
            if (artist != null && artist.getAlbums() != null) {
                albums = artist.getAlbums();
            }
        }

        if (albumId != null) {
            selectedAlbum = albumService.getAlbumById(albumId);

            // Pre-select the genres for this album
            if (selectedAlbum != null && selectedAlbum.getGenres() != null) {
                selectedGenreIds = selectedAlbum.getGenres().stream()
                        .map(Genre::getId)
                        .toArray(Long[]::new);
            }
        }

    }

    private void loadAlbums() {
        albums = albumService.getAllAlbums();
    }

    public String saveAlbum() {

        if (selectedGenreIds != null && selectedGenreIds.length > 0) {
            List<Genre> selectedGenres = Arrays.stream(selectedGenreIds)
                    .map(genreId -> genreService.getGenreById(genreId))
                    .collect(Collectors.toList());
            selectedAlbum.setGenres(selectedGenres);
        } else {
            selectedAlbum.setGenres(new ArrayList<>());
        }

        if (selectedAlbum.getId() == null) {
            Artist artist = artistService.getArtistById(artistId);
            selectedAlbum.setArtist(artist);
            albumService.createAlbum(selectedAlbum);
        } else {
            albumService.updateAlbum(selectedAlbum);
        }

        loadArtistAlbums();
        return "artist-albums?faces-redirect=true&artistId=" + artistId;
    }

    public String viewArtistAlbums(Long artistId) {
        this.artistId = artistId;
        return "artist-albums?faces-redirect=true&artistId=" + artistId;
    }

    public String getArtistName() {
        if (artistId != null) {
            Artist artist = artistService.getArtistById(artistId);
            if (artist != null) {
                return artist.getFirstName() + " " + artist.getLastName();
            }
        }
        return "";
    }

    public void prepareNewAlbum() {
        selectedAlbum = new Album();            // Create a fresh Album
        selectedGenreIds = new Long[0];         // Reset selected genre
        System.out.println("skrrrr");
    }


}
