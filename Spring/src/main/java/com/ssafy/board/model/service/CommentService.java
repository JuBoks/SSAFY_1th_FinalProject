package com.ssafy.board.model.service;

import java.util.List;

import com.ssafy.board.model.dto.Comment;

public interface CommentService {

	List<Comment> list(Integer articleNo);

	boolean create(Comment commentDto);

	boolean modify(Comment commentDto);

	boolean delete(int commentNo);

}
