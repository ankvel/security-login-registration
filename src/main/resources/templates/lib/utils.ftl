<#macro page title>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>${title}</title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
    </head>
    <body>
        <@header/>
        <h1>${title}</h1>
        <#nested>
    </body>
</html>
</#macro>

<#macro header>
    <div class="header">
        <nav role="navigation">
            <ul>
                <li><a href="/"><img src="img/some.svg" alt="SOME LOGO" /></a></li>
                <li><a href="/home">Home</a></li>
                <#if currentUser.isAnonymous()>
                    <li><a href="/login">Login</a></li>
                <#else>
                    <li><a href="/user-info">${currentUser.name}</a></li>
                    <li>
                        <form name="logoutForm" role="form" action="/logout" method="post">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button type="submit">Logout</button>
                        </form>
                    </li>
                </#if>
            </ul>
        </nav>
    </div>
</#macro>

