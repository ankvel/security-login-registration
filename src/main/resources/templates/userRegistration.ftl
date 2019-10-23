<#import "lib/utils.ftl" as u>
<#import "/spring.ftl" as spring/>

<@u.page title="${rc.getMessage('user.registration.title', .locale)}">
    <form name="loginForm" role="form" action="" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <div class="container">

            <div class="row">
                <div class="col-label">
                    <label for="username">Email</label>
                </div>
                <div class="col-input">
                    <input type="text" name="email" id="email" autofocus/>
                </div>
            </div>

            <div class="row">
                <div class="col-label">
                    <label for="name">Name</label>
                </div>
                <div class="col-input">
                    <input type="text" name="name" id="name" autofocus/>
                </div>
            </div>

            <div class="row">
                <div class="col-label">
                    <label for="password">Password</label>
                </div>
                <div class="col-input">
                    <input type="password" name="password" id="password" />
                </div>
            </div>

            <div class="row">
                <div class="col-label">
                    <label for="password">Repeat Password</label>
                </div>
                <div class="col-input">
                    <input type="password" name="passwordAgain" id="passwordAgain" />
                </div>
            </div>

            <div class="row">
                <div class="col-label">
                    <button type="submit">Register</button>
                </div>
            </div>
        </div>
    </form>

    <#if registrationErrors??>
        <@u.showMessagesData messagesData = registrationErrors/>
    </#if>
</@u.page>



