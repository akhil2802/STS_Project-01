package com.blog.services;

import java.util.List;

import com.blog.dto.CommentDto;

public interface CommentService {
	
	void createComment(String postUrl, CommentDto commentDto);

	List<CommentDto> getComments();

	void deleteComment(Long commentId);
}
