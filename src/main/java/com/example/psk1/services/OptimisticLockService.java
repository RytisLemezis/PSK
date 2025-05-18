package com.example.psk1.services;

import com.example.psk1.entities.Album;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;

@Stateless
public class OptimisticLockService {
    @Inject
    private EntityManager em;

    public Album getAlbumReference(Long albumId) {
        return em.find(Album.class, albumId);
    }

    @Transactional
    public void updateAlbumAsAnotherUser(Long albumId) {
        Album album = em.find(Album.class, albumId);
        album.setNumberOfSongs(album.getNumberOfSongs() + 5);
        em.merge(album);
    }

    @Transactional
    public boolean updateAlbumWithStaleReference(Album staleAlbum, int newNumberOfSongs) {
        try {
            staleAlbum.setNumberOfSongs(newNumberOfSongs);
            em.merge(staleAlbum);
            em.flush();
            return true;
        } catch (OptimisticLockException e) {
            return false;
        }
    }

    @Transactional
    public void updateAlbumWithFreshReference(Long albumId, int newNumberOfSongs) {
        Album freshAlbum = em.find(Album.class, albumId);
        freshAlbum.setNumberOfSongs(newNumberOfSongs);
        em.merge(freshAlbum);
    }
}
