<#import "lib/utils.ftl" as u>
<#import "/spring.ftl" as spring/>

<#assign pageTitle><@spring.message "page.user.registration.success.title"/></#assign>
<@u.page title="${pageTitle}">
    <p>
        <@spring.message "user.registration.verify.request"/>
    </p>
</@u.page>



