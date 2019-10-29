<#import "lib/utils.ftl" as u>
<#import "/spring.ftl" as spring/>

<#assign pageTitle><@spring.message "page.login.title"/></#assign>
<@u.page title="${pageTitle}">

    <@u.showMessagesData messagesData = loginInfo/>

    <form name="loginForm" role="form" action="/login" method="post"
            onsubmit="return window.someNamespace.loginPageService.validateForm()">

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="some-container some-border">
            <div class="row">
                <div class="col-label">
                    <label for="username"><@spring.message "page.login.email"/></label>
                </div>
                <div class="col-input">
                    <input type="text" class="some-input" name="username" id="username" autofocus/>
                </div>
            </div>
            <div class="row">
                <div class="col-label">
                    <label for="password"><@spring.message "page.login.password"/></label>
                </div>
                <div class="col-input">
                    <input type="password" class="some-input" name="password" id="password" />
                </div>
            </div>
            <div class="row">
                <div class="col-label">
                    <button type="submit" class="some-button"><@spring.message "page.login.submit"/></button>
                </div>
            </div>
        </div>
    </form>

    <#if Session.SPRING_SECURITY_LAST_EXCEPTION?? && Session.SPRING_SECURITY_LAST_EXCEPTION.message?has_content>
        <div class="some-block some-error"><@spring.message "error.login.bad.credentials"/></div>
    </#if>

    <script src="/js/loginPageService.js"></script>
</@u.page>
