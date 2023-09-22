package com.blog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.blog.dto.CommentDto;
import com.blog.dto.PostDto;
import com.blog.services.CommentService;
import com.blog.services.PostService;

@Controller
public class BlogController {

	private PostService postService;

	public BlogController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping("/")
	public String viewBlogPosts(Model model) {

		List<PostDto> posts = postService.findAllPosts();
		model.addAttribute("posts", posts);

		return "blog/view_posts";
	}

	// handler method to handle view post request
	@GetMapping("/post/{postUrl}")
	private String showPost(@PathVariable("postUrl") String postUrl, Model model) {
		
		PostDto postDto = postService.findPostByUrl(postUrl);
		CommentDto commentDto = new CommentDto();
		
		model.addAttribute("post", postDto);
		model.addAttribute("comment", commentDto);
		
		return "blog/blog_post";
	}
}
