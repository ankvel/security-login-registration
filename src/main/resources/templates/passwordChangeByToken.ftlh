<#import "lib/utils.ftl" as u>
<#import "/spring.ftl" as spring/>

<#assign pageTitle><@spring.message "page.password.reset.title"/></#assign>
<@u.page title="${pageTitle}">
    <#if passwordChanged??>
        <div class="some-block some-success some-border">
            <@spring.message "page.password.reset.success"/>
        </div>
    <#else>
        <div class="some-container some-border">
            <form name="passwordChangeForm" role="form" action="" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <@spring.formHiddenInput "passwordChangeByTokenRequest.token" "class='some-input'" />

                <div class="row">
                    <div class="col-label">
                        <label for="password"><@spring.message "page.password.reset.password"/></label>
                    </div>
                    <div class="col-input">
                        <@spring.formPasswordInput "passwordChangeByTokenRequest.password" "class='some-input'" />
                        <@u.showErrors />
                    </div>
                </div>

                <div class="row">
                    <div class="col-label">
                        <label for="password"><@spring.message "page.password.reset.repeat.password"/></label>
                    </div>
                    <div class="col-input">
                        <@spring.formPasswordInput "passwordChangeByTokenRequest.passwordAgain" "class='some-input'" />
                        <@u.showErrors />
                    </div>
                </div>


                <div class="row">
                    <div class="col-label">
                        <button type="submit" class="some-button"><@spring.message "page.password.reset.submit"/></button>
                    </div>
                </div>
            </form>
        </div>
    </#if>
</@u.page>
