<#import "lib/utils.ftl" as u>
<#import "/spring.ftl" as spring/>

<#assign pageTitle><@spring.message "page.user.verification.title"/></#assign>
<@u.page title="${pageTitle}">
    <#if verificationResult?? && verificationResult.success>
        <@spring.message "page.user.verification.confirmed"/>
    <#else>
        <@spring.message "page.user.verification.rejected"/>
    </#if>
</@u.page>