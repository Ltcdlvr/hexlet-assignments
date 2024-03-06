package exercise;

import io.javalin.Javalin;
import java.util.List;
import io.javalin.http.NotFoundResponse;
import exercise.model.User;
import exercise.dto.users.UserPage;
import exercise.dto.users.UsersPage;
import io.javalin.validation.Validator;

import java.util.Collections;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", ctx -> {
            List<User> users = Data.getUsers();
            ctx.render("users/index.jte", Collections.singletonMap("page", new UsersPage(users)));
        });

        app.get("/users/{id}", ctx -> {
            Long curId = ctx.pathParamAsClass("id", Long.class).getOrDefault((long) -1);
            List<User> users = Data.getUsers();
            boolean flag = true;
            for (User user: users) {
                if (curId.equals(user.getId())) {
                    ctx.render("users/show.jte", Collections.singletonMap("page", new UserPage(user)));
                    flag = false;
                    break;
                }
            }
            if (flag) {
                throw new NotFoundResponse("User not found");
            }
        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
