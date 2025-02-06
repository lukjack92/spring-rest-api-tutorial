package rest_api_tutorial.rest_api_tutorial.controller;

import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rest_api_tutorial.rest_api_tutorial.controller.dto.PostDto;
import rest_api_tutorial.rest_api_tutorial.model.Post;
import rest_api_tutorial.rest_api_tutorial.service.PostService;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public List<PostDto> getPosts(@RequestParam(defaultValue = "0") int page, Sort.Direction sort) {
        int pageNumber = page >= 0 ? page : 0;
        //throw new IllegalArgumentException("Not implemented yet!");
        return PostDtoMapper.mapToPostDtos(postService.getPosts(pageNumber, sort));
    }

    @GetMapping("/posts/comments")
    public List<Post> getPostsWithComments(@RequestParam(defaultValue = "0") int page, Sort.Direction sort) {
        int pageNumber = page >= 0 ? page : 0;
        //throw new IllegalArgumentException("Not implemented yet!");
        return postService.getPostsWithComments(pageNumber, sort);
    }

    @GetMapping("/posts/{id}")
    public Post getSinglePost(@PathVariable long id) {
        //throw new IllegalArgumentException("Not implemented yet!");
        return postService.getSinglePost(id);
    }
}
