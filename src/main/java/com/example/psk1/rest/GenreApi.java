package com.example.psk1.rest;

import com.example.psk1.entities.Genre;
import com.example.psk1.services.GenreService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/genres")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GenreApi {

    @Inject
    private GenreService genreService;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Long id) {
        Genre genre = genreService.getGenreById(id);
        if (genre == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        GenreDTO genreDTO = new GenreDTO(genre);

        return Response.ok(genreDTO).build();
    }


    @POST
    @Transactional
    public Response createGenre(Genre genre) {
        genreService.createGenre(genre);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateGenre(@PathParam("id") Long id, Genre updatedGenre) {
        Genre existing = genreService.getGenreById(id);
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        existing.setName(updatedGenre.getName());
        existing.setDescription(updatedGenre.getDescription());
        genreService.updateGenre(existing);

        return Response.ok().build();
    }
}

