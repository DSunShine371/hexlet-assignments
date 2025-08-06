package exercise.controller.users;

import java.util.List;

import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {
    @Setter
    private static  List<Post> posts = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> index(@PathVariable Integer id) {
        return posts.stream()
                .filter(p -> p.getUserId() == id)
                .toList();
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> create(@RequestBody Post post, @PathVariable Integer id) {
        Post responsePost = new Post();
        responsePost.setUserId(id);
        responsePost.setSlug(post.getSlug());
        responsePost.setTitle(post.getTitle());
        responsePost.setBody(post.getBody());

        posts.add(responsePost);

        return ResponseEntity.status(HttpStatus.CREATED).body(responsePost);
    }
}
// END
