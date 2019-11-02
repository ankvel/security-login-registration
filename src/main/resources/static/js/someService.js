(function(){

    const sayHello = () => {
        console.log("Hello Some!");
    };

    const toggleElementShow = (elem) => {
        elem.classList.toggle("show");
    };

    window.someNamespace = {};

    window.someNamespace.someService = {
        sayHello: sayHello,
        toggleElementShow: toggleElementShow
    };

})();