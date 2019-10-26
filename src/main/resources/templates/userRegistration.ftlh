<#import "lib/utils.ftl" as u>
<#import "/spring.ftl" as spring/>

<#assign pageTitle><@spring.message "page.user.registration.title"/></#assign>
<@u.page title="${pageTitle}">
    <form name="loginForm" role="form" action="" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <div class="some-container some-border">

            <div class="row">
                <div class="col-label">
                    <label for="username"><@spring.message "page.user.registration.email"/></label>
                </div>
                <div class="col-input">
                    <input type="text" class="some-input" name="email" id="email" autofocus/>
                </div>
            </div>

            <div class="row">
                <div class="col-label">
                    <label for="name"><@spring.message "page.user.registration.name"/></label>
                </div>
                <div class="col-input">
                    <input type="text" class="some-input" name="name" id="name" autofocus/>
                </div>
            </div>

            <div class="row">
                <div class="col-label">
                    <label for="password"><@spring.message "page.user.registration.password"/></label>
                </div>
                <div class="col-input">
                    <input type="password" class="some-input" name="password" id="password" />
                </div>
            </div>

            <div class="row">
                <div class="col-label">
                    <label for="password"><@spring.message "page.user.registration.repeat.password"/></label>
                </div>
                <div class="col-input">
                    <input type="password" class="some-input" name="passwordAgain" id="passwordAgain" />
                </div>
            </div>

            <div class="row">
                <div class="col-label">
                    <button type="submit" class="some-button"><@spring.message "page.user.registration.submit"/></button>
                </div>
            </div>
        </div>
    </form>

    <#if registrationErrors??>
        <@u.showMessagesData messagesData = registrationErrors/>
    </#if>
</@u.page>



