package exercise.controller;

import java.util.Collections;
import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.dto.posts.BuildPostPage;
import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.validation.ValidationException;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    public static void build(Context ctx) {
        var page = new BuildPostPage();
        ctx.render("posts/build.jte", Collections.singletonMap("page", page));
    }

    // BEGIN
    public static void create(Context ctx) {
        try {
            var name = ctx.formParamAsClass("name", String.class)
                    .check(value -> value.length() > 1, "Too short!")
                    .get();
            var body = ctx.formParamAsClass("body", String.class).get();
            PostRepository.save(new Post(name, body));
            ctx.sessionAttribute("flash", "Course has been created!");
            ctx.redirect(NamedRoutes.postsPath());
        } catch (ValidationException e) {
            var name = ctx.formParam("name");
            var body = ctx.formParam("body");
            ctx.status(422)
                    .render("posts/build.jte", Collections.singletonMap("page",
                    new BuildPostPage(name, body, e.getErrors())));
        }
    }

    public static void index(Context ctx) {
        PostsPage pages = new PostsPage(PostRepository.getEntities());
        pages.setFlashMessage(ctx.consumeSessionAttribute("flash"));
        ctx.render("posts/index.jte", Collections.singletonMap("pages", pages));
    }
    // END

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
            .orElseThrow(() -> new NotFoundResponse("Post not found"));

        var page = new PostPage(post);
        ctx.render("posts/show.jte", Collections.singletonMap("page", page));
    }
}
