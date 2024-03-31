package exercise.controller.users;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
class PostController {
    private List<Post> posts = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    public List<Post> index(@PathVariable Integer id) {
        return posts.stream().filter(a -> id.equals(a.getUserId())).toList();
    }

    @PostMapping("/users/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@PathVariable Integer id, @RequestParam String slug,
                       @RequestParam String title, @RequestParam String body) {
        Post newPost = new Post(id, slug, title, body);
        posts.add(newPost);
        return newPost;
    }
}
// END
