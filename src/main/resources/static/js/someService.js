(function(){

    const sayHello = () => {
        console.log("Hello Some!");
    }

    window.someNamespace = {};

    window.someNamespace.someService = {
        sayHello: sayHello
    }

})();