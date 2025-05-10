package com.example.psk1.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
public class Artist {
    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String firstName;

    @Basic
    private String lastName;

    @OneToMany(mappedBy = "artist")
    private List<Album> albums;
}
