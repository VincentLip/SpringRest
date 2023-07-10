package com.example.tpvendredi_07_juillet.service;

import com.example.tpvendredi_07_juillet.dto.LikeDto;

import java.util.List;

public interface LikeService {

    LikeDto createLike (Integer commentId, LikeDto likeDto);

    List<LikeDto> getPostsByLikeId(Integer likeId);

    LikeDto getLikeById(Integer commentId , Integer likeId);

    LikeDto updateLike(Integer commentId , Integer likeId , LikeDto likeRequest);

    void deleteLike( Integer commentId, Integer likeId);
}
