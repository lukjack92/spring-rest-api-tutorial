package rest_api_tutorial.rest_api_tutorial.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import rest_api_tutorial.rest_api_tutorial.controller.dto.PostDto;
import rest_api_tutorial.rest_api_tutorial.model.Post;

import java.util.List;
import java.util.stream.Collectors;

public class PostDtoMapper {

    //An empty builder/constructor so they don't have access
    private PostDtoMapper() {}

    public static List<PostDto> mapToPostDtos(List<Post> posts) {
        return posts.stream()
                .map(PostDtoMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

    private static PostDto mapToPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .created(post.getCreated())
                .build();
    }
}
