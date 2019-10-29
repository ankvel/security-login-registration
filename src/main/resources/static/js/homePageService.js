(function(namespace) {

    window.addEventListener('load', (event) => {
        namespace.someService.sayHello();
    });

    namespace.homePageService = {
    };

})(window.someNamespace)
