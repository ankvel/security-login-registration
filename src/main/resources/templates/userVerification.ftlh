<#import "lib/utils.ftl" as u>
<#import "/spring.ftl" as spring/>

<#assign pageTitle><@spring.message "page.user.verification.title"/></#assign>
<@u.page title="${pageTitle}">
    <form name="loginForm" role="form" action="" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <div class="some-container some-border">

            <div class="row">
                <div class="col-label">
                    <label for="email"><@spring.message "page.user.verification.email"/></label>
                </div>
                <div class="col-input">
                    <input type="text" class="some-input" name="email" id="email" autofocus/>
                </div>
            </div>

            <div class="row">
                <div class="col-label">
                    <button type="submit" class="some-button"><@spring.message "page.user.verification.submit"/></button>
                </div>
            </div>
            <#if sendResult??>
                <#if sendResult>
                    <div class="some-block some-success some-border">
                        <@spring.message "info.user.verification.request"/>
                    </div>
                <#else>
                    <div class="some-block some-error some-border">
                        <@spring.message "error.user.verification.request.fail"/>
                    </div>
                </#if>
            </#if>
        </div>
    </form>
</@u.page>