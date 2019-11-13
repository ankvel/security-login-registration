<#import "lib/utils.ftl" as u>
<#import "/spring.ftl" as spring/>

<#assign pageTitle><@spring.message "page.password.reset.title"/></#assign>
<@u.page title="${pageTitle}">

    <#if sendResult??>
        <div class="some-block some-success some-border">
            <@spring.message "page.password.reset.code.sent"/>
        </div>
    <#else>
        <div class="some-container some-border">
            <form name="passwordResetForm" role="form" action="" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="row">
                    <div class="col-label">
                        <label for="email"><@spring.message "page.password.reset.email"/></label>
                    </div>
                    <div class="col-input">
                        <@spring.formInput "passwordResetVerificationRequest.email" "class='some-input' autofocus" />
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
