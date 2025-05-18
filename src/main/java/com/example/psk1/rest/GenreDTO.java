package com.example.psk1.rest;

import com.example.psk1.entities.Genre;

public class GenreDTO {
    public Long id;
    public String name;
    public String description;

    public GenreDTO(Genre genre) {
        this.id = genre.getId();
        this.name = genre.getName();
        this.description = genre.getDescription();
    }
}
