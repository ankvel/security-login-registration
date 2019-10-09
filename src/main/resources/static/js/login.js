function validate() {
    if (document.loginForm.username.value == "" && document.loginForm.password.value == "") {
        alert("Username and password are required");
        document.loginForm.username.focus();
        return false;
    }
    if (document.loginForm.username.value == "") {
        alert("Username is required");
        document.loginForm.username.focus();
        return false;
    }
    if (document.loginForm.password.value == "") {
    alert("Password is required");
    document.loginForm.password.focus();
        return false;
    }
}