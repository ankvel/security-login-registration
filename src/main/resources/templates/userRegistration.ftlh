<#import "lib/utils.ftl" as u/>
<#import "/spring.ftl" as spring/>

<#assign pageTitle><@spring.message "page.user.registration.title"/></#assign>
<@u.page title="${pageTitle}">
    <form name="userRegistrationForm" role="form" action="" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <div class="some-container some-border">

            <div class="row">
                <div class="col-label">
                    <label for="username"><@spring.message "page.user.registration.email"/></label>
                </div>
                <div class="col-input">
                    <@spring.formInput "userRegistrationRequest.email" "class='some-input' autofocus" />
                    <@u.showErrors />
                </div>
            </div>

            <div class="row">
                <div class="col-label">
                    <label for="name"><@spring.message "page.user.registration.name"/></label>
                </div>
                <div class="col-input">
                    <@spring.formInput "userRegistrationRequest.name" "class='some-input'" />
                    <@u.showErrors />
                </div>
            </div>

            <div class="row">
                <div class="col-label">
                    <label for="password"><@spring.message "page.user.registration.password"/></label>
                </div>
                <div class="col-input">
                    <@spring.formPasswordInput "userRegistrationRequest.password" "class='some-input'" />
                    <@u.showErrors />
                </div>
            </div>

            <div class="row">
                <div class="col-label">
                    <label for="password"><@spring.message "page.user.registration.repeat.password"/></label>
                </div>
                <div class="col-input">
                    <@spring.formPasswordInput "userRegistrationRequest.passwordAgain" "class='some-input'" />
                    <@u.showErrors />
                </div>
            </div>

            <div class="row">
                <div class="col-label">
                    <button type="submit" class="some-button"><@spring.message "page.user.registration.submit"/></button>
                </div>
            </div>
        </div>
    </form>
</@u.page>



