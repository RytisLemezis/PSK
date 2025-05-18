package com.example.psk1.services;

import com.example.psk1.entities.Artist;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.List;

@Alternative
@RequestScoped
public class MockArtistService extends ArtistService {

    @Override
    public List<Artist> getAllArtists() {
        Artist a1 = new Artist();
        a1.setId(1L);
        a1.setFirstName("Mock");
        a1.setLastName("ArtistOne");

        Artist a2 = new Artist();
        a2.setId(2L);
        a2.setFirstName("Fake");
        a2.setLastName("ArtistTwo");

        List<Artist> list = new ArrayList<>();
        list.add(a1);
        list.add(a2);
        return list;
    }

    @Override
    public void createArtist(Artist artist) {
        System.out.println("Mock createArtist(): " + artist.getFirstName() + " " + artist.getLastName());
    }

    @Override
    public Artist getArtistById(Long id) {
        Artist a = new Artist();
        a.setId(id);
        a.setFirstName("Mock");
        a.setLastName("Loaded");
        return a;
    }

    @Override
    public void updateArtist(Artist artist) {
        System.out.println("Mock updateArtist(): " + artist.getId());
    }
}

