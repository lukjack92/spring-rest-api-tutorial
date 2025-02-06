package rest_api_tutorial.rest_api_tutorial.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.SortableValue;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rest_api_tutorial.rest_api_tutorial.model.Comment;
import rest_api_tutorial.rest_api_tutorial.model.Post;
import rest_api_tutorial.rest_api_tutorial.repository.CommentRepository;
import rest_api_tutorial.rest_api_tutorial.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private static final int PAGE_SIZE = 20;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public List<Post> getPosts(int page, Sort.Direction sort) {
        return postRepository.findAllPosts(PageRequest.of(page,PAGE_SIZE, Sort.by(sort, "id")));
    }

    public Post getSinglePost(long id) {
        return postRepository.findById(id)
                .orElseThrow();
    }

    public List<Post> getPostsWithComments(int page, Sort.Direction sort) {
        List<Post> allPosts = postRepository.findAllPosts(PageRequest.of(page,PAGE_SIZE, Sort.by(sort, "id")));
        List<Long> ids = allPosts.stream()
                .map(Post::getId)
                .collect(Collectors.toList());
        List<Comment> comments = commentRepository.findAllByPostIdIn(ids);
        allPosts.forEach(post -> post.setComment(extractComments(comments, post.getId())));
        return allPosts;
    }

    private List<Comment> extractComments(List<Comment> comments, long id) {
        return comments.stream()
                .filter(comment -> comment.getPostId() == id)
                .collect(Collectors.toList());
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    @Transactional
    public Post editPost(Post post) {
        Post postToEdit = postRepository.findById(post.getId()).orElseThrow();
        postToEdit.setTitle(post.getTitle());
        postToEdit.setContent(post.getContent());
        return postToEdit;
    }

    public void deletePost(long id) {
        postRepository.deleteById(id);
    }
}
