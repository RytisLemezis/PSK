package com.example.psk1.services.mybatis;

import com.example.psk1.mybatis.dao.AlbumMapper;
import com.example.psk1.mybatis.model.Album;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class AlbumMyBatis {

    @Inject
    private AlbumMapper albumMapper;

    private List<Album> allAlbums;

    private Album albumToCreate = new Album();

    @PostConstruct
    public void init() {
        loadAllAlbums();
    }

    private void loadAllAlbums() {
        allAlbums = albumMapper.selectAll();
    }

    @Transactional
    public String createAlbum() {
        albumMapper.insert(albumToCreate);
        return "/myBatis/albums?faces-redirect=true";
    }

    public List<Album> getAllAlbums() {
        return allAlbums;
    }

    public Album getAlbumToCreate() {
        return albumToCreate;
    }

    public void setAlbumToCreate(Album albumToCreate) {
        this.albumToCreate = albumToCreate;
    }
}

