/*package com.example.psk1.services;

import com.example.psk1.entities.Artist;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Specializes;
import java.util.ArrayList;
import java.util.List;

@Specializes
@RequestScoped
public class SpecialArtistService extends ArtistService {

    @Override
    public List<Artist> getAllArtists() {
        List<Artist> mockList = new ArrayList<>();

        Artist a = new Artist();
        a.setId(999L);
        a.setFirstName("Special");
        a.setLastName("Artist");

        mockList.add(a);
        return mockList;
    }
}*/

