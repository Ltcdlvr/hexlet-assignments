package exercise.controller;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    // BEGIN
    public static void getPost(Context ctx) {

        Long id = ctx.pathParamAsClass("id", Long.class).getOrDefault((long) -1);
        Post post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Page not found"));

        ctx.render("posts/show.jte", Collections.singletonMap("page", new PostPage(post)));
    }

    public static void getPosts(Context ctx) {
        Integer page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        System.out.println(page);
        List<Post> posts = new ArrayList<>();
        List<Post> allPosts = PostRepository.getEntities();
        for (int index = 0; index < allPosts.size(); index++) {
            if ((page - 1) * 5 <= index && index < page * 5) {
                posts.add(allPosts.get(index));
            }
        }

        ctx.render("posts/index.jte", Collections.singletonMap("page", new PostsPage(posts, page)));
    }
    // END
}
