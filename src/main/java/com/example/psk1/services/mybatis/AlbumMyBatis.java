package com.example.psk1.services.mybatis;

import com.example.psk1.mybatis.dao.AlbumMapper;
import com.example.psk1.mybatis.dao.ArtistMapper;
import com.example.psk1.mybatis.dao.GenreMapper;
import com.example.psk1.mybatis.model.Album;
import com.example.psk1.mybatis.model.Artist;
import com.example.psk1.mybatis.model.Genre;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class AlbumMyBatis implements Serializable {

    @Inject
    private AlbumMapper albumMapper;

    @Inject
    private ArtistMapper artistMapper;

    @Inject
    private GenreMapper genreMapper;

    @Getter
    @Setter
    private Long artistId;

    @Getter
    @Setter
    private Long albumId;

    @Getter
    @Setter
    private Album selectedAlbum;

    @Getter
    @Setter
    private Long[] selectedGenreIds;

    @Getter
    private List<Album> albums = new ArrayList<>();

    @Getter
    private List<Genre> allGenres = new ArrayList<>();

    @PostConstruct
    public void init() {
        loadGenres();
    }

    private void loadGenres() {
        allGenres.clear();
        allGenres = genreMapper.selectAll();
    }

    public void loadArtistAlbums() {
        if (artistId != null) {
            albums = artistMapper.selectAlbumsByArtistId(artistId);
        }

        if (albumId != null) {
            selectedAlbum = albumMapper.selectByPrimaryKey(albumId);

            if (selectedAlbum != null && selectedAlbum.getGenres() != null) {
                selectedGenreIds = selectedAlbum.getGenres().stream()
                        .map(Genre::getId)
                        .toArray(Long[]::new);
            }
        }
    }

    public void prepareNewAlbum() {
        selectedAlbum = new Album();
        selectedGenreIds = new Long[0];
    }

    @Transactional
    public String saveAlbum() {

        selectedAlbum.setArtistId(artistId);

        if (artistId != null) {
            Artist artist = artistMapper.selectByPrimaryKey(artistId);
            selectedAlbum.setArtist(artist); // If using object reference
            selectedAlbum.setArtistId(artistId); // Also safe to set ID directly
        }

        if (selectedAlbum.getId() == null) {
            albumMapper.insert(selectedAlbum);
        } else {
            albumMapper.updateByPrimaryKey(selectedAlbum);
            albumMapper.deleteGenresForAlbum(selectedAlbum.getId());
        }

        if (selectedGenreIds != null && selectedGenreIds.length > 0) {
            for (Long genreId : selectedGenreIds) {
                albumMapper.insertAlbumGenre(selectedAlbum.getId(), genreId);
            }
        }

        loadArtistAlbums();

        return "/myBatis/artist-albums.xhtml?faces-redirect=true&artistId=" + artistId;
    }

    public String viewArtistAlbums(long artistId) {
        this.artistId = artistId;
        return "/mybatis/artist-albums?faces-redirect=true&artistId=" + artistId;
    }


    public String getArtistName() {
        if (artistId != null) {
            Artist artist = artistMapper.selectByPrimaryKey(artistId);
            if (artist != null) {
                return artist.getFirstname() + " " + artist.getLastname();
            }
        }
        return "";
    }
}
