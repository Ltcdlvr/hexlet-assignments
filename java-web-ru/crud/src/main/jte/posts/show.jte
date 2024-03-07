@import exercise.dto.posts.PostPage
@import exercise.util.NamedRoutes
@param PostPage page


@template.layout.page(
    content = @`
        <div class="mb-3">
            <p>${page.getPost().getName()}</p>
            <p></p>
            <p>${page.getPost().getBody()}</p>
            <p></p>
            <a href="${NamedRoutes.postsPath()}">All posts.</a>
        </div>
    `
)