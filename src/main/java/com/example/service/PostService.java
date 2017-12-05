package com.example.service;

import com.example.model.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService extends DefaultService<Post> {
    int getPagesNumber();
    List<Post> getNewestPostsByPage(int page);
}
