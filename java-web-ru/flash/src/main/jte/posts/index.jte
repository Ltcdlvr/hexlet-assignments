@import exercise.dto.posts.PostsPage
@param PostsPage pages

@template.layout.page(
    page = pages,
    content = @`
      @for(var post : pages.getPosts())
        <div> ${post.getName()}" </div>
      @endfor
    `
)