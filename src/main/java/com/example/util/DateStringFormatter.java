package com.example.util;

import com.example.model.Comment;
import com.example.model.Post;
import com.example.service.impl.PostServiceImpl;
import javafx.geometry.Pos;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class DateStringFormatter {

    private static final long MINUTES_IN_HOUR = Duration.ofHours(1).toMinutes();
    private static final long HOURS_IN_DAY = Duration.ofDays(1).toHours();

    public Comment updateDisplayDateFormat(Comment comment){
        if(comment == null) return comment;
        comment.getMessage().setFormattedDate(convert(comment.getMessage().getLocalDateTime()));
        comment.getPost().getMessage().setFormattedDate(convert(comment.getPost().getMessage().getLocalDateTime()));
        return comment;
    }

    public Post updateDisplayDateFormat(Post post){
        if(post == null) return post;
        post.getMessage().setFormattedDate(convert(post.getMessage().getLocalDateTime()));
        post.getComments().forEach(
                x -> x.getMessage().setFormattedDate(convert(x.getMessage().getLocalDateTime())));
        return post;
    }

    public List<Comment> updateDisplayCommentsDateFormat(List<Comment> comments){
        comments.forEach(x -> x = updateDisplayDateFormat(x));
        return comments;
    }

    public List<Post> updateDisplayPostsDateFormat(List<Post> posts){
        posts.forEach(x -> x = updateDisplayDateFormat(x));
        return posts;
    }

    private String convert(LocalDateTime localDateTime){
        String formattedDate = checkMinutes(localDateTime);
        if(!formattedDate.isEmpty()){
            return formattedDate;
        }
        formattedDate = checkHours(localDateTime);
        if(!formattedDate.isEmpty()){
            return formattedDate;
        }
        formattedDate = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return formattedDate;
    }

    private String checkHours(LocalDateTime localDateTime) {
        LocalDateTime currentDate = LocalDateTime.now();
        long hoursDuration = Duration.between(localDateTime, currentDate).toHours();
        StringBuilder ret = new StringBuilder();
        if(hoursDuration < HOURS_IN_DAY){
            ret.append(hoursDuration == 1
                    ? "an hour ago"
                    : "an " + String.valueOf(hoursDuration) + " hours ago");
        }
        return ret.toString();
    }

    private String checkMinutes(LocalDateTime localDateTime){
        LocalDateTime currentDate = LocalDateTime.now();
        long minuteDuration = Duration.between(localDateTime, currentDate).toMinutes();
        StringBuilder ret = new StringBuilder();
        if(minuteDuration < MINUTES_IN_HOUR) {
            if(minuteDuration == 0) return "now";
            ret.append(minuteDuration == 1
                    ? "a minute ago"
                    : "a " + String.valueOf(minuteDuration) + " minutes ago");
            return ret.toString();
        }
        return ret.toString();
    }
}
