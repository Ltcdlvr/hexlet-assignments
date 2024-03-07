package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    // BEGIN
    public static String postsPath() { return "/posts"; }
    public static String postsPath(Integer page) { return "/posts?page=" + page.toString(); }
    public static String postPath(Long id) {
        return "/posts/" + id.toString();
    }
    public static String postPath() { return "/posts/{id}"; }
    // END
}
