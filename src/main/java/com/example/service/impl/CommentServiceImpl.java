package com.example.service.impl;

import com.example.model.Comment;
import com.example.repository.CommentRepository;
import com.example.repository.MessageRepository;
import com.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    @Transactional
    public void save(Comment comment) {
        messageRepository.save(comment.getMessage());
        commentRepository.save(comment);
    }

    @Override
    public Comment findOne(Long id) {
        return commentRepository.findOne(id);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        commentRepository.delete(id);
    }

}
