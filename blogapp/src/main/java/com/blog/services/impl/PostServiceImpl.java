package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dto.PostDto;
import com.blog.entities.Post;
import com.blog.mapper.PostMapper;
import com.blog.repository.PostRepository;
import com.blog.services.PostService;

import jakarta.validation.Valid;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepo;
	
	@Override
	public List<PostDto> findAllPosts() {

		List<Post> posts = this.postRepo.findAll();
		
//		return posts.stream().map(post -> PostMapper.mapToPostDto(post)).collect(Collectors.toList());
		return posts.stream().map(PostMapper::mapToPostDto).collect(Collectors.toList());
	}

	@Override
	public void createPost(PostDto postDto) {
		
		Post post = PostMapper.mapToPost(postDto);
		
		postRepo.save(post);
	}

	@Override
	public PostDto findPostById(Long postId) {
		
		Post post = postRepo.findById(postId).get();
		
		return PostMapper.mapToPostDto(post);
	}

	@Override
	public void updatePost(@Valid PostDto postDto) {
		
		postRepo.save(PostMapper.mapToPost(postDto));
	}

	@Override
	public void deletePost(Long postId) {
		
		postRepo.deleteById(postId);
		
	}

	@Override
	public PostDto findPostByUrl(String postUrl) {
		
		Post post = postRepo.findByUrl(postUrl).get();
		return PostMapper.mapToPostDto(post);
	}

	@Override
	public List<PostDto> searchPosts(String query) {
		
		List<Post> posts = postRepo.searchPosts(query);
		
		return posts.stream().map(PostMapper::mapToPostDto).collect(Collectors.toList());
	}

}
