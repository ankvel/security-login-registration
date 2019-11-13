<#import "lib/utils.ftl" as u>
<#import "/spring.ftl" as spring/>

<#assign pageTitle><@spring.message "page.user.registration.success.title"/></#assign>
<@u.page title="${pageTitle}">
    <div class="some-block some-success some-border">
        <@spring.message "page.user.verification.code.sent"/>
    </div>
</@u.page>



