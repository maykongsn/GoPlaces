package com.goplaces.dao;

import android.content.Context;

import com.goplaces.model.Comment;

import java.util.ArrayList;

public interface CommentsDAOInterface {
    static CommentsDAOInterface getInstance(Context context) { return null; }

    ArrayList<Comment> listComments();
    boolean addComment(Comment comment);
}
