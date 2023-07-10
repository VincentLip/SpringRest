package com.example.tpvendredi_07_juillet.service;

import com.example.tpvendredi_07_juillet.dto.DislikeDto;

import java.util.List;

public interface DislikeService {

    DislikeDto createDislike (Integer commentId, DislikeDto dislikeDto);

    List<DislikeDto> getPostsByDislikeId(Integer dislikeId);

    DislikeDto getDislikeById(Integer commentId , Integer dislikeId);

    DislikeDto updateDislike(Integer commentId , Integer dislikeId , DislikeDto dislikeRequest);

    void deleteDislike( Integer commentId, Integer dislikeId);
}
