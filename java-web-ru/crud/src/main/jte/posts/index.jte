@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page


@template.layout.page(
    content = @`
        @for(var post : page.getPosts())
            <div><a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a></div>
        @endfor

        <div>
            <a href="${NamedRoutes.postsPath(page.getPagination() - 1)}">Prev</a>
            <a href="${NamedRoutes.postsPath(page.getPagination() + 1)}">Next</a>
        </div>
    `
)