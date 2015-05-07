(function () {
    'use strict';

    angular
        .module('app.login')
        .controller('LoginController', LoginController)
        .controller('LoginCreateController', LoginCreateController);

    LoginController.$inject = ['$location', 'dataservice'];
    function LoginController ($location, dataservice) {
        var vm = this;


        vm.opciones=true;

        /* Esta función era para hacer un ng-show en el index asi se ocultan las barras */
        vm.show = function(){
            if (vm.opciones === true){
                vm.opciones = false;
            } else {
                vm.opciones = true;
            }
        };


    }

    LoginCreateController.$inject = ['$scope', '$routeParams', '$location', 'dataservice'];
    function LoginCreateController ($scope, $routeParams, $location, dataservice) {
        var vm = this;
        vm.ingresar = function (nombreUsuario, password) {
            dataservice.Login().create(nombreUsuario, password);

        };
    }

}());