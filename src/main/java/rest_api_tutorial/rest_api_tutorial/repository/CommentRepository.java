package rest_api_tutorial.rest_api_tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rest_api_tutorial.rest_api_tutorial.model.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostIdIn(List<Long> ids);
}