package exercise;

import io.javalin.Javalin;
import io.javalin.validation.ValidationException;
import java.util.List;
import exercise.model.Article;
import exercise.dto.articles.ArticlesPage;
import exercise.dto.articles.BuildArticlePage;
import java.util.Collections;
import java.util.Optional;

import exercise.repository.ArticleRepository;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/articles", ctx -> {
            List<Article> articles = ArticleRepository.getEntities();
            var page = new ArticlesPage(articles);
            ctx.render("articles/index.jte", Collections.singletonMap("page", page));
        });

        // BEGIN
        app.get("/articles/build", ctx -> {
            BuildArticlePage page = new BuildArticlePage();
            ctx.render("articles/build.jte", Collections.singletonMap("page", page));
        });

        app.post("/articles", ctx -> {
            String title = ctx.formParam("title");
            String content = ctx.formParam("content");

            try {
                title = ctx.formParamAsClass("title", String.class)
                        .check(value -> value.length() > 1, "Название не должно быть короче двух символов")
                        .check(value -> {
                                    Optional<Article> existedArticle = ArticleRepository.findByTitle(value);
                                    return existedArticle.isEmpty();
                                },
                                "Статья с таким названием уже существует")
                        .get();
                content = ctx.formParamAsClass("content", String.class)
                        .check(value -> value.length() > 9, "Статья должна быть не короче 10 символов")
                        .get();
                ArticleRepository.save(new Article(title, content));
                ctx.redirect("/articles");
            } catch (ValidationException e) {
                var page = new BuildArticlePage(title, content, e.getErrors());
                ctx.status(422)
                        .render("articles/build.jte", Collections.singletonMap("page", page));
            }
        });
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
