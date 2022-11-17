package com.ssafy.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.board.model.dao.CommentDao;
import com.ssafy.board.model.dto.Comment;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Override
	public List<Comment> list(Integer articleNo) {
		System.out.println("서비스까지 들어왔다.");
		return commentDao.list(articleNo);
	}

	@Override
	public boolean create(Comment commentDto) {
		return commentDao.create(commentDto) == 1;
	}

	@Override
	public boolean modify(Comment commentDto) {
		return commentDao.modify(commentDto) == 1;
	}

	@Override
	public boolean delete(int commentNo) {
		return commentDao.delete(commentNo) == 1;
	}

}
