package exercise;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN

// END

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/companies/{id}", ctx -> {
            String id = ctx.pathParamAsClass("id", String.class).get();
            List<Map<String, String>> companies = Data.getCompanies();
            Map<String, String> res = new HashMap<>();

            for (Map<String, String> company: companies) {
                String curId = company.get("id");
                if (id.equals(curId)) {
                    res = company;
                    break;
                }
            }

            if (res.isEmpty()) {
                throw new NotFoundResponse("Company not found");
            }

            ctx.json(res);
        });
        // END

        app.get("/companies", ctx -> {
            ctx.json(COMPANIES);
        });

        app.get("/", ctx -> {
            ctx.result("open something like (you can change id): /companies/5");
        });

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
