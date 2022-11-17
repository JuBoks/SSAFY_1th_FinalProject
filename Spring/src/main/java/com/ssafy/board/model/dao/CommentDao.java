package com.ssafy.board.model.dao;

import java.util.List;

import com.ssafy.board.model.dto.Comment;

public interface CommentDao {

	List<Comment> list(Integer articleNo);

	int create(Comment commentDto);

	int modify(Comment commentDto);

	int delete(int commentNo);
	
}
