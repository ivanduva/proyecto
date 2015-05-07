(function () {
    'use strict';

    angular
        .module('app.login')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$location', 'dataservice'];
    function LoginController ($location, dataservice) {
        var vm = this;

        vm.barra=true;

        vm.show = function(){
            if (vm.barra === true){
                vm.barra = false;
            } else {
                vm.barra = true;
            }
        };

        vm.ingresar = function (nombreUsuario) {
            $location.path('/login/' + nombreUsuario);
        };
    }