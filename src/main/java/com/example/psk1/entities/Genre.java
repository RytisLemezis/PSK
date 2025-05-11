package com.example.psk1.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genre_seq")
    @SequenceGenerator(name = "genre_seq", sequenceName = "genre_seq", allocationSize = 1)
    private Long id;

    @Basic
    private String name;

    @Basic
    private String description;

    @ManyToMany(mappedBy = "genres")
    private List<Album> albums = new ArrayList<>();

}
