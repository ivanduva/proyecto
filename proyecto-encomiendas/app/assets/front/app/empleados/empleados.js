/*global angular, console*/
(function () {
    'use strict';

    angular
        .module('app.empleados')
        .controller('EmpleadoListController', EmpleadoListController)
        .controller('EmpleadoCreateController', EmpleadoCreateController);

    EmpleadoListController.$inject = ['$location', 'dataservice'];
    function EmpleadoListController($location, dataservice) {
        var vm = this;

        vm.empleados = dataservice.Empleados().query();

        vm.edit = function (personaId) {
            $location.path('/admin/empleados/' + personaId + '/edit');
        };

        vm.cesar = function (personaId) {
            dataservice.Empleados().update({id: parseInt(personaId)});

        };
    }

    EmpleadoCesadoListController.$inject = ['$location', 'dataservice'];
    function EmpleadoCesadoListController($location, dataservice) {
        var vm = this

        vm.empleados = dataservice.Empleados().query();

        vm.alta = function (personaId) {
          dataservice.Empleados().update({id: parseInt(personaId)});
        };


    }

    EmpleadoCreateController.$inject = ['$scope', '$routeParams', '$location', 'dataservice'];
    function EmpleadoCreateController($scope, $routeParams, $location, dataservice) {
        var vm = this;

        $scope.inputType = 'password';

        $scope.hideShowPassword = function(){
            if ($scope.inputType == 'password')
              $scope.inputType = 'text';
            else
              $scope.inputType = 'password';
          };

        vm.localidades = dataservice.Localidades().query();
        vm.empleado = null;

        if ($routeParams.id !== undefined) {
            var id = parseInt($routeParams.id);
            vm.empleado = dataservice.Empleados().show({id: id});
            vm.title = "Editar Empleado";
        } else {
            vm.title = "Nuevo Empleado";
        }

        vm.save = function () {
            console.log(vm.empleado);
            if (vm.empleado.personaId === undefined) {
                dataservice.Empleados().create(vm.empleado);
            } else {
                dataservice.Empleados().update({id: vm.empleado.personaId}, vm.empleado);
            }
            $location.path('/admin/empleados');
        };
    }
}());