package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Collections;
import java.util.Objects;

import exercise.model.User;
import exercise.dto.users.UsersPage;
import exercise.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/users", ctx -> {
            List<User> users = UserRepository.getEntities();
            var page = new UsersPage(users);
            ctx.render("users/index.jte", Collections.singletonMap("page", page));
        });

        // BEGIN
        app.get("/users/build", ctx -> {
            ctx.render("users/build.jte");
        });

        app.post("/users", ctx -> {
            var name = ctx.formParamAsClass("firstName", String.class).getOrDefault("").trim();
            var surname = ctx.formParamAsClass("lastName", String.class).getOrDefault("").trim();
            var email = ctx.formParamAsClass("email", String.class).getOrDefault("").trim().toLowerCase();
            var password = Security.encrypt(ctx.formParam("password"));
            UserRepository.save(new User(StringUtils.capitalize(name), StringUtils.capitalize(surname), email, password));
            ctx.redirect("/users");
        });
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
