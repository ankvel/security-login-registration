(function(namespace) {

    const submitLogout = () => {
        document.logoutForm.submit();
    }

    const addLangClickListener = () => {
        const popup = document.getElementById("lang");
        const popupContent = document.getElementById("langContent");
        popup.addEventListener("click", (event) => {
            namespace.someService.toggleElementShow(popupContent);
        });
    };

    window.addEventListener('load', (event) => {
        const logoutElem = document.getElementById('logout');
        if (logoutElem) {
            logoutElem.addEventListener('click', submitLogout);
        }
        addLangClickListener();
    });

})(window.someNamespace)