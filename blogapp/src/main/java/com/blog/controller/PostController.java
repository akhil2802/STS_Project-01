package com.blog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.dto.CommentDto;
import com.blog.dto.PostDto;
import com.blog.services.CommentService;
import com.blog.services.PostService;

import jakarta.validation.Valid;

@Controller
public class PostController {

	private PostService postService;
	private CommentService commentService;
	
	public PostController(PostService postService, CommentService commentService) {
		
		this.postService = postService;
		this.commentService = commentService;
	}
	
	@GetMapping("/admin/posts")
	public String posts(Model model) {

		List<PostDto> posts = postService.findAllPosts();
		model.addAttribute("posts", posts);

		return "/admin/posts";
	}

	// handler method to handle new post request
	@GetMapping("admin/posts/newpost")
	public String newPostForm(Model model) {

		PostDto postDto = new PostDto();
		model.addAttribute("post", postDto);

		return "admin/create_post";
	}

	@PostMapping("/admin/posts")
	public String createPost(@Valid @ModelAttribute("post") PostDto postDto, BindingResult result, Model model) {
		// Binding result takes error message fromcontroller layer to view:

		if (result.hasErrors()) {
			model.addAttribute("post", postDto);
			return "admin/create_post";
		}

		postDto.setUrl(getUrl(postDto.getTitle()));

		postService.createPost(postDto);

		return "redirect:/admin/posts";
	}

	private static String getUrl(String postTitle) {

		String title = postTitle.trim().toLowerCase();
		String url = title.replaceAll("\\s+", "-");
		url = url.replaceAll("[^A-Za-z0-9]", "-");

		return url;

	}

	@GetMapping("/admin/posts/{postId}/edit")
	public String editPostForm(@PathVariable("postId") Long postId, Model model) {

		PostDto postDto = postService.findPostById(postId);
		model.addAttribute("post", postDto);

		return "admin/edit_post";

	}

	/*
	handler method
	to handle
	edit post
	form submit request 
	*/
	@PostMapping("/admin/posts/{postId}")
	public String updatePost(@PathVariable("postId") Long postId,
	@Valid @ModelAttribute("post") PostDto postDto, BindingResult result, Model model){
	
		if(result.hasErrors()){
			model.addAttribute("post", postDto);
			return "admin/edit_post";
		}
		
		postDto.setId(postId);
		
		postService.updatePost(postDto);
		
		return "redirect:/admin/posts";
	
	}
	
	@GetMapping("/admin/posts/{postId}/delete")
	public String deletePost(@PathVariable Long postId) {
		
		postService.deletePost(postId);
		
		return "redirect:/admin/posts";
	}
	
	@GetMapping("/admin/posts/{postUrl}/view")
	public String viewPost(@PathVariable String postUrl, Model model) {
		
		PostDto postDto = postService.findPostByUrl(postUrl);
		model.addAttribute("post", postDto);
		
		return "admin/view_post";
	}
	
	@PostMapping("/admin/posts/search")
	public String searchPosts(@RequestParam(value = "query") String query, Model model) {

		List<PostDto> posts = postService.searchPosts(query);
		model.addAttribute("posts", posts);
		
		return "admin/posts";
	}
	
	@GetMapping("/admin/posts/comments")
	public String getComments(Model model) {
		
		List<CommentDto> comments = this.commentService.getComments();
		model.addAttribute("comments", comments);
		
		return "admin/comments";
	}
	
	@GetMapping("/admin/comments/{commentId}/delete")
	public String deleteComment(@PathVariable Long commentId) {
		
		this.commentService.deleteComment(commentId);
		
		return "redirect:/admin/posts";
	}
}
