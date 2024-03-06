@import exercise.dto.users.UsersPage
@param UsersPage page

<html>
    <head>
        <title>Хекслет</title>
    </head>
    <body>
        @if(page.getUsers().isEmpty())
            <p>Пока нет юзеров.</p>
        @else
            @for(var user : page.getUsers())
               <div>
                   <p>${user.toString()}</p>
               </div>
            @endfor
        @endif
    </body>
</html>