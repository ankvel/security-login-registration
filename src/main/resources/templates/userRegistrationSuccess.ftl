<#import "lib/utils.ftl" as u>
<#import "/spring.ftl" as spring/>

<@u.page title="${rc.getMessage('user.registration.success', .locale)}">
    <p>
        <@spring.message "user.registration.verify.request"/>
    </p>
</@u.page>



