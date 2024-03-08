package exercise.controller;

import java.util.Collections;
import java.util.List;

import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;
import exercise.model.User;

import io.javalin.http.Context;

public class SessionsController {

    // BEGIN
    public static void build(Context ctx) {
        ctx.render("build.jte");
    }
    public static void createSession(Context ctx) {
        var name = ctx.formParamAsClass("name", String.class).get();
        var password = ctx.formParamAsClass("password", String.class).get();

        List<User> users = UsersRepository.getEntities();
        var user = users.stream()
                .filter(entity -> entity.getName().equals(name))
                .filter(entity -> entity.getPassword().equals(encrypt(password)))
                .findFirst();

        if (user.isEmpty()) {
            ctx.render("build.jte", Collections.singletonMap("page", new LoginPage(name,
                    "Wrong username or password")));
        } else {
            ctx.sessionAttribute("auth", String.valueOf(true));
            ctx.render("index.jte", Collections.singletonMap("page", new MainPage(name)));
        }
    }

    public static void redirectDeleteSession(Context ctx) {
        deleteSession(ctx);
    }
    public static void deleteSession(Context ctx) {
        ctx.sessionAttribute("auth", String.valueOf(false));
        ctx.render("index.jte", Collections.singletonMap("page", new MainPage(null)));
    }

    // END
}
