package com.example.psk1.mybatis.dao;

import com.example.psk1.mybatis.model.Album;
import com.example.psk1.mybatis.model.Genre;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

@Mapper
public interface AlbumMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Album row);

    Album selectByPrimaryKey(Long id);

    List<Album> selectAll();

    int updateByPrimaryKey(Album row);

    List<Album> selectByGenreId(Long genreId);

    List<Genre> selectGenresByAlbumId(@Param("albumId")Long albumId);

    void deleteGenresForAlbum(@Param("albumId") Long albumId);

    void insertAlbumGenre(@Param("albumId") Long albumId, @Param("genreId") Long genreId);


}
