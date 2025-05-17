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
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class GenreAlbumMyBatis {

    @Inject
    private GenreMapper genreMapper;

    @Inject
    private AlbumMapper albumMapper;

    @Inject
    private ArtistMapper artistMapper;

    @Getter @Setter
    private Long genreId;

    @Getter
    private Genre genre;

    @Getter
    private List<Album> genreAlbums = new ArrayList<>();

    @Getter
    private List<Artist> genreArtists = new ArrayList<>();

    @PostConstruct
    public void init() {
        loadGenreAlbums();
    }

    public void loadGenreAlbums() {
        if (genreId != null) {
            genre = genreMapper.selectByPrimaryKey(genreId);

            // Load albums for the genre
            genreAlbums = albumMapper.selectByGenreId(genreId);

            // Extract unique artist IDs from albums
            Set<Long> artistIds = genreAlbums.stream()
                    .map(Album::getArtistId)
                    .filter(id -> id != null)
                    .collect(Collectors.toSet());

            // Load artists by their IDs
            genreArtists.clear();
            for (Long artistId : artistIds) {
                Artist artist = artistMapper.selectByPrimaryKey(artistId);
                if (artist != null) {
                    genreArtists.add(artist);
                }
            }
        }
    }

    public String getGenreName() {
        return genre != null ? genre.getName() : "";
    }

    public String getArtistNameById(Long artistId) {
        if (artistId == null) {
            return "Unknown Artist";
        }

        for (Artist artist : genreArtists) {
            if (artist.getId().equals(artistId)) {
                return artist.getFirstname() + " " + artist.getLastname();
            }
        }
        return "Unknown Artist";
    }
}
