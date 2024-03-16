package exercise;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getPosts() {
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(posts.size()))
                .body(posts);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Optional<Post>> getPost(@PathVariable String id) {
        Optional<Post> res = posts.stream().filter(v -> v.getId().equals(id)).findFirst();
        if (res.isPresent()) {
            return ResponseEntity.ok()
                    .body(res);
        } else {
            return ResponseEntity.status(404).body(res);
        }
    }

    @PostMapping("/posts")
    public ResponseEntity<List<Post>> createNew(@RequestBody Post post) {
        posts.add(post);
        return ResponseEntity.status(201)
                .body(posts);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@RequestBody Post post, @PathVariable String id) {
        var existingPost = posts.stream().filter(v -> v.getId().equals(id)).findFirst();
        if (existingPost.isPresent()) {
            Post curPost = existingPost.get();
            curPost.setBody(post.getBody());
            curPost.setTitle(post.getTitle());
            return ResponseEntity.status(200)
                    .body(curPost);
        }
        return ResponseEntity.status(204)
                .body(post);
    }

    
    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
