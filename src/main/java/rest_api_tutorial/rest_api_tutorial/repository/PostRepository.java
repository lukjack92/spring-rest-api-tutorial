package rest_api_tutorial.rest_api_tutorial.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rest_api_tutorial.rest_api_tutorial.model.Post;


import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    //@Query("Select p From Post p left join fetch p.comment")
    @Query("Select p From Post p")
    List<Post> findAllPosts(Pageable page);
}
