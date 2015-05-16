/*global angular, console*/
(function () {
    'use strict';

    angular
        .module('app.clientes')
        .controller('ClienteListController', ClienteListController)
        .controller('ClienteCreateController', ClienteCreateController);

    ClienteListController.$inject = ['$location', 'dataservice'];
    function ClienteListController($location, dataservice) {
        var vm = this;

        vm.clientes = dataservice.Clientes().query();

        vm.edit = function (personaId) {
            $location.path('/admin/clientes/' + personaId + '/edit');
        };

        vm.delete = function (personaId) {
            dataservice.Clientes().delete({id: parseInt(personaId)});

        };
    }

    ClienteCreateController.$inject = ['$scope', '$routeParams', '$location', 'dataservice'];
    function ClienteCreateController($scope, $routeParams, $location, dataservice) {
        var vm = this;

        $scope.inputType = 'password';

        $scope.hideShowPassword = function(){
            if ($scope.inputType == 'password')
              $scope.inputType = 'text';
            else
              $scope.inputType = 'password';
          };

        vm.localidades = dataservice.Localidades().query();
        vm.cliente = null;

        if ($routeParams.id !== undefined) {
            var id = parseInt($routeParams.id);
            vm.cliente = dataservice.Clientes().show({id: id});
            vm.title = "Editar Cliente";
        } else {
            vm.title = "Nuevo Cliente";
        }

        vm.save = function () {
            console.log(vm.cliente);
            if (vm.cliente.personaId === undefined) {
                dataservice.Clientes().create(vm.cliente);
            } else {
                dataservice.Clientes().update({id: vm.cliente.personaId}, vm.cliente);
            }
            $location.path('/admin/clientes');
        };
    }
}());
