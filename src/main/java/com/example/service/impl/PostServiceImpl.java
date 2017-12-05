package com.example.service.impl;

import com.example.config.Constants;
import com.example.model.Post;
import com.example.repository.MessageRepository;
import com.example.repository.PostRepository;
import com.example.service.PostService;
import com.example.util.DateStringFormatter;
import com.example.util.PaginationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private PaginationModel paginationModel;

    @Autowired
    private DateStringFormatter dateStringFormatter;

    @Override
    @Transactional
    public void save(Post post) {
        messageRepository.save(post.getMessage());
        postRepository.save(post);
    }

    @Override
    public Post findOne(Long id) {
         return dateStringFormatter.updateDisplayDateFormat(postRepository.findOne(id));
    }

    @Override
    public int getPagesNumber() {
        return paginationModel.getPageNumberRoundedUp(postRepository.count(), Constants.PAGE_ELEMENTS);
    }

    @Override
    public List<Post> getNewestPostsByPage(int page) {
        PageRequest pageRequest = new PageRequest(page - 1, Constants.PAGE_ELEMENTS, Sort.Direction.DESC, "id");
        return dateStringFormatter.updateDisplayPostsDateFormat(postRepository.findAll(pageRequest).getContent());
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        postRepository.delete(id);
    }

}
