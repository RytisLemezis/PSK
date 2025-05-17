package com.example.psk1.services.mybatis;

import com.example.psk1.mybatis.dao.ArtistMapper;
import com.example.psk1.mybatis.dao.AlbumMapper;
import com.example.psk1.mybatis.dao.GenreMapper;
import com.example.psk1.mybatis.model.Artist;
import com.example.psk1.mybatis.model.Album;
import com.example.psk1.mybatis.model.Genre;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ViewScoped
@Named
public class AlbumDetailsMyBatis implements Serializable {
    @Inject
    private AlbumMapper albumMapper;

    @Inject
    private ArtistMapper artistMapper;

    @Inject
    private GenreMapper genreMapper;

    @Getter @Setter
    private Long albumId;

    @Getter @Setter
    private Long artistId;

    @Getter
    private Album album;

    @Getter
    private Artist artist;

    @Getter
    private List<Genre> albumGenres = new ArrayList<>();

    public void loadAlbum() {
        if (albumId == null) {
            System.out.println("albumId is null in loadAlbum()");
            return;
        }

        album = albumMapper.selectByPrimaryKey(albumId);
        if (album == null) {
            System.out.println("No album found for id: " + albumId);
            return;
        }

        if (album.getArtistId() != null) {
            artist = artistMapper.selectByPrimaryKey(album.getArtistId());
        } else {
            System.out.println("Album has no artistId.");
        }

        albumGenres = genreMapper.selectByAlbumId(albumId);
    }

    public String getArtistName() {
        if (artist != null) {
            return artist.getFirstname() + " " + artist.getLastname();
        }
        return "No artist assigned";
    }

    public boolean hasGenres() {
        return albumGenres != null && !albumGenres.isEmpty();
    }
}
