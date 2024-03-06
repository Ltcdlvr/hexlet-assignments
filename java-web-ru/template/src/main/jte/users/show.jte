@import exercise.dto.users.UserPage
@param UserPage page

<html>
    <head>
        <title>Хекслет</title>
    </head>
    <body>
        <div>
            <p>${page.getUser().toString()}</p>
        </div>
    </body>
</html>