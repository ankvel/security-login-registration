<#import "lib/utils.ftl" as u>
<#import "/spring.ftl" as spring/>

<#assign pageTitle><@spring.message "page.home.title"/></#assign>
<@u.page title="${pageTitle}">
    <script src="/js/homePageService.js"></script>
</@u.page>