package com.example.psk1.ui;

import com.example.psk1.entities.Album;
import com.example.psk1.services.AlbumService;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class AlbumDetailsController implements Serializable {
    @Inject
    private AlbumService albumService;

    @Getter
    @Setter
    private Long albumId;

    @Getter
    private Album album;

    public void loadAlbum() {
        if (albumId != null) {
            album = albumService.getAlbumById(albumId);
        }
    }

    public String getArtistName() {
        if (album != null && album.getArtist() != null) {
            return album.getArtist().getFirstName() + " " + album.getArtist().getLastName();
        }
        return "No artist assigned";
    }
}

