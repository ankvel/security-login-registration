<#import "lib/utils.ftl" as u>
<#import "/spring.ftl" as spring/>
<@u.page title="User Info">
    <label><@spring.message code="some"/></label>
    <input type="text" value="${rc.getMessage('some', .locale)}" />
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