package com.example.psk1.mybatis.model;

import java.util.List;

public class Album {
    private Long id;
    private Integer numberofsongs;
    private String title;
    private Long artistId;
    private Artist artist;

    private List<Genre> genres;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberofsongs() {
        return numberofsongs;
    }

    public void setNumberofsongs(Integer numberofsongs) {
        this.numberofsongs = numberofsongs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
