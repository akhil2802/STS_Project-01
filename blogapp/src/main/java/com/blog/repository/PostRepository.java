package com.blog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blog.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	Optional<Post> findByUrl(String url);
	
	@Query("SELECT p from Post p WHERE " + "p.title LIKE CONCAT('%', :qquery, '%') OR" + " p.shortDescription LIKE CONCAT('%', :query, '%')")
	List<Post> searchPosts(@Param("query") String query);

}
