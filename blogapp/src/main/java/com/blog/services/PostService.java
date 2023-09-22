package com.blog.services;

import java.util.List;

import com.blog.dto.PostDto;

import jakarta.validation.Valid;

public interface PostService {
	
	List<PostDto> findAllPosts();

	void createPost(PostDto postDto);

	PostDto findPostById(Long postId);

	void updatePost(@Valid PostDto postDto);

	void deletePost(Long postId);

	PostDto findPostByUrl(String postUrl);

	List<PostDto> searchPosts(String query);
}
