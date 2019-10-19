<#import "lib/utils.ftl" as u>
<#import "/spring.ftl" as spring/>
<@u.page title="Log in">

    <@u.showMessagesData messagesData = loginInfo/>

    <form name="loginForm" role="form" action="/login" method="post" onsubmit="return validate()">

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="container">
            <div class="row">
                <div class="col-label">
                    <label for="username">Username</label>
                </div>
                <div class="col-input">
                    <input type="text" name="username" id="username" autofocus/>
                </div>
            </div>
            <div class="row">
                <div class="col-label">
                    <label for="password">Password</label>
                </div>
                <div class="col-input">
                    <input type="password" name="password" id="password" />
                </div>
            </div>
            <div class="row">
                <div class="col-label">
                    <button type="submit">Submit</button>
                </div>
            </div>
        </div>
    </form>

    <#if Session.SPRING_SECURITY_LAST_EXCEPTION?? && Session.SPRING_SECURITY_LAST_EXCEPTION.message?has_content>
        <div class="some-block some-error"><@spring.message "login.bad.credentials"/></div>
    </#if>

    <script src="js/main.js"></script>
    <script src="js/login.js"></script>
</@u.page>
