<#import "lib/utils.ftl" as u>
<#import "/spring.ftl" as spring/>

<#assign pageTitle><@spring.message "page.user.verification.title"/></#assign>
<@u.page title="${pageTitle}">


    <#if sendResult??>
        <#if sendResult>
            <div class="some-block some-success some-border">
                <@spring.message "page.user.verification.code.sent"/>
            </div>
        <#else>
            <div class="some-block some-error some-border">
                <@spring.message "error.user.verification.request.fail"/>
            </div>
        </#if>
    <#else>
        <form name="userVerificationForm" role="form" action="" method="post">
           <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <div class="some-container some-border">

                <div class="row">
                    <div class="col-label">
                        <label for="email"><@spring.message "page.user.verification.email"/></label>
                    </div>
                    <div class="col-input">
                        <@spring.formInput "userVerificationRequest.email" "class='some-input' autofocus" />
                        <@u.showErrors />
                    </div>
                </div>

                <div class="row">
                    <div class="col-label">
                        <button type="submit" class="some-button"><@spring.message "page.user.verification.submit"/></button>
                    </div>
                </div>

            </div>
        </form>
    </#if>

</@u.page>
