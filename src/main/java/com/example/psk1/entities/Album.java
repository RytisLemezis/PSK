package com.example.psk1.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String title;

    @Basic
    private Integer numberOfSongs;

    @ManyToOne
    private Artist artist;

    @ManyToMany
    @JoinTable(
            name = "album_genre",
            joinColumns = @JoinColumn(name = "albums_id"),
            inverseJoinColumns = @JoinColumn(name = "genres_id")
    )
    private List<Genre> genres = new ArrayList<>();

}
