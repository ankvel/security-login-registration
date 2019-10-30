(function(namespace) {

    const submitLogout = () => {
        document.logoutForm.submit();
    }

    window.addEventListener('load', (event) => {
        const logoutElem = document.getElementById('logout');
        if (logoutElem) {
            logoutElem.addEventListener('click', submitLogout);
        }
    });

})(window.someNamespace)