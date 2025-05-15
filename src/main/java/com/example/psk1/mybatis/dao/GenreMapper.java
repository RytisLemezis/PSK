package com.example.psk1.mybatis.dao;

import com.example.psk1.mybatis.model.Album;
import com.example.psk1.mybatis.model.Genre;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

@Mapper
public interface GenreMapper {

    List<Album> selectAlbumsByGenreId(@Param("genreId") Long genreId);

    int deleteByPrimaryKey(Long id);

    int insert(Genre row);

    Genre selectByPrimaryKey(Long id);

    List<Genre> selectAll();

    int updateByPrimaryKey(Genre row);
}