package com.example.psk1.mybatis.dao;

import com.example.psk1.mybatis.model.Album;
import com.example.psk1.mybatis.model.Artist;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

@Mapper
public interface ArtistMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Artist row);

    Artist selectByPrimaryKey(Long id);

    List<Artist> selectAll();

    int updateByPrimaryKey(Artist row);

    List<Album> selectAlbumsByArtistId(@Param("artistId") Long artistId);


}