<#import "lib/utils.ftl" as u>
<#import "/spring.ftl" as spring/>

<#assign pageTitle><@spring.message "page.user.info.title"/></#assign>
<@u.page title="${pageTitle}">
    <p>User Name: ${currentUser.name}</p>
    <p>User Email: ${currentUser.email}</p>
    <p>Roles:
        <ul>
            <#list currentUser.roles as role>
                <li>${role.name}</li>
            </#list>
        </ul>
    </p>
</@u.page>