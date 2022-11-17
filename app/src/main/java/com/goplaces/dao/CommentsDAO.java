package com.goplaces.dao;

import android.content.Context;

import com.goplaces.model.Comment;

import java.util.ArrayList;

public class CommentsDAO implements CommentsDAOInterface {
    ArrayList<Comment> comments = new ArrayList<>();
    private static Context context;
    public static CommentsDAO commentsDAO = null;

    private CommentsDAO(Context contenxt) { CommentsDAO.context = context; }

    public static CommentsDAOInterface getInstance(Context context) {
        if(commentsDAO == null) {
            commentsDAO = new CommentsDAO(context);
        }
        return commentsDAO;
    }

    @Override
    public ArrayList<Comment> listComments() {
        return comments;
    }

    @Override
    public boolean addComment(Comment comment) {
        comments.add(comment);
        return true;
    }
}
