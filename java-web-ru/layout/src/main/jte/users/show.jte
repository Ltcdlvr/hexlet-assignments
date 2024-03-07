@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
    content = @`
        <div>${page.getUser().toString()}</div>
    `
)