<#macro page title>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>${title}</title>
        <link rel="stylesheet" type="text/css" href="/css/main.css">
        <script src="/js/someService.js"></script>
        <script src="/js/navigationPanelService.js"></script>
    </head>
    <body>
        <@navigation/>
        <div class="content">
            <h1>${title}</h1>
            <#nested>
        </div>
        <@pageBottom/>
    </body>
</html>
</#macro>

<#macro navigation>
    <nav role="navigation">
        <ul>
            <li><a href="/"><img src="/img/some.svg" alt="SOME LOGO" /></a></li>
            <li><a href="/home"><@spring.message "nav.item.home"/></a></li>
            <#if currentUser??>
                <#if currentUser.isAnonymous()>
                    <li><a href="/login"><@spring.message "nav.item.login"/></a></li>
                    <li><a href="/user/registration"><@spring.message "nav.item.registration"/></a></li>
                <#else>
                    <li><a href="/user/info">${currentUser.name}</a></li>
                    <li>
                        <form name="logoutForm" role="form" action="/logout" method="post">
                            <a id="logout" href="#"><@spring.message "nav.item.logout"/></a>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </li>
                </#if>
            </#if>
            <li>
                <div class="some-popup" id="lang">
                    <@spring.message "nav.item.lang"/>
                    <div class="some-popup-content some-container some-border" id="langContent">
                        <div class="row">
                            <a href="?lang=en"><@spring.message "nav.item.lang.en"/></a>
                        </div>
                        <div class="row">
                            <a href="?lang=ru"><@spring.message "nav.item.lang.ru"/></a>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
    </nav>
</#macro>

<#macro pageBottom>
    <div class="bottom">

    </div>
</#macro>

<#macro showMessagesData messagesData>
    <#switch messagesData.type>
        <#case "INFO">
            <div class="some-block some-info some-border">
            <#break>
        <#case "WARN">
            <div class="some-block some-warn some-border">
            <#break>
        <#case "ERROR">
            <div class="some-block some-error some-border">
            <#break>
        <#default>
            <div class="some-block some-border">
    </#switch>
        <#if messagesData.message??>${messagesData.message}</#if>
        <#if messagesData.messages??>
            <ul>
                <#list messagesData.messages as message>
                    <li>${message}</li>
                </#list>
            </ul>
        </#if>
    </div>
</#macro>

<#macro showErrors>
    <#if spring.status.errorMessages?has_content>
        <div class="some-block some-error some-border">
            <#list spring.status.errorMessages as error>
                ${error}<br/>
            </#list>
        </div>
    </#if>
</#macro>