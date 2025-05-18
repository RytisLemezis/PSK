package com.example.psk1.decorators;

import com.example.psk1.entities.Genre;
import com.example.psk1.services.GenreFetcher;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;
import java.util.List;

@Decorator
public class GenreFetcherDecorator implements GenreFetcher {

    @Inject
    @Delegate
    private GenreFetcher delegate;

    @Override
    public List<Genre> getAllGenres() {
        System.out.println("[Decorator] Before fetching all genres...");
        List<Genre> genres = delegate.getAllGenres();
        System.out.println("[Decorator] Fetched " + genres.size() + " genres.");
        return genres;
    }
}

