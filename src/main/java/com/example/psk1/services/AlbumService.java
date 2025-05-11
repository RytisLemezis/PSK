package com.example.psk1.services;

import com.example.psk1.entities.Album;
import com.example.psk1.persistence.AlbumsDAO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class AlbumService {
    @Inject
    private AlbumsDAO albumsDAO;

    @Transactional
    public void createAlbum(Album album) {
        albumsDAO.persist(album);
    }

    public Album getAlbumById(Long id) {
        return albumsDAO.findOne(id);
    }

    @Transactional
    public void updateAlbum(Album album) {
        albumsDAO.update(album);
    }

    public List<Album> getAlbumsByGenreId(Long genreId) {
        return albumsDAO.findAlbumsByGenreId(genreId);
    }

    public List<Album> getAllAlbums() {return albumsDAO.loadAll();}
}
