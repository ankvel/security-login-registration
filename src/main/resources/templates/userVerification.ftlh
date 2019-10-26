<#import "lib/utils.ftl" as u>
<#import "/spring.ftl" as spring/>

<#assign pageTitle><@spring.message "page.user.verification.title"/></#assign>
<@u.page title="${pageTitle}">
    <#if verificationResult?? && verificationResult.success>
        <div class="some-block some-success some-border">
            <@spring.message "page.user.verification.confirmed"/>
        </div>
    <#else>
        <div class="some-block some-error some-border">
            <@spring.message "page.user.verification.rejected"/>
        </div>
    </#if>
</@u.page>