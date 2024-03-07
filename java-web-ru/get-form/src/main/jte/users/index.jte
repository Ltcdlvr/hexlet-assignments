@import exercise.dto.users.UsersPage
@param UsersPage page

<html>
    <head>
        <title>Хекслет</title>
    </head>
    <body>
        <form action="/users" method="get">
        <input type="search" name="term" value="${page.getTerm()}" />
        <input type="submit" value="Search" />
        </form>

        @if(!page.getUsers().isEmpty())
            @for(var user : page.getUsers())
               <div>
                   <p>${user.getFirstName()} ${user.getLastName()}</p>
               </div>
            @endfor
        @endif
    </body>
</html>