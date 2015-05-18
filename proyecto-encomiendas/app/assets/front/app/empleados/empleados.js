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

        dataservice.Empleados().query(onSuccessQuery, onFail);

        vm.edit = function (personaId) {
            $location.path('/admin/empleados/' + personaId + '/edit');
        };

        vm.cesar = function (personaId) {
            dataservice.EmpleadosCesados().update({id: parseInt(personaId)});
        };

        function onSuccessDelete(data) {
            console.log(data);
        }

        function onSuccessQuery(data) {
            vm.empleados = data;
            console.log(data);
        }

        /*vm.alta = function (personaId) {
            dataservice.EmpleadosCesados().update({id: parseInt(personaId)});
        };*/
    }


    EmpleadoCreateController.$inject = ['$routeParams', '$location', 'dataservice'];
    function EmpleadoCreateController($routeParams, $location, dataservice) {
        var vm = this;

        vm.inputType = 'password';
        vm.showPassText = "Mostrar";

        vm.togglePassword = function() {
            if (vm.inputType == 'password') {
                vm.inputType = 'text';
                vm.showPassText = "Ocultar";
            } else {
                vm.inputType = 'password';
                vm.showPassText = "Mostrar";
            }
        };

        dataservice.Localidades().query(onLocalidadesLoad, onFail);
        vm.empleado = null;

        if ($routeParams.id !== undefined) {
            var id = parseInt($routeParams.id);
            dataservice.Empleados().get({id: id}, onSuccessGet, onFail);
            vm.title = "Editar Empleado";
        } else {
            vm.title = "Nuevo Empleado";
        }

        vm.save = function () {

            if (vm.empleado.personaId === undefined) {
                dataservice.Empleados().save(vm.empleado, onSuccessSave, onFail);
            } else {
                dataservice.Empleados().update({id: vm.empleado.personaId}, vm.empleado, onSuccessUpdate, onFail);
            }
            $location.path('/admin/empleados');
        };

        function onLocalidadesLoad(data) {
            vm.localidades = data;
            vm.localidad = data[0].localidadId;
        }

        function onSuccessGet (data) {
            vm.empleado = data;
            vm.empleado.fechaNacimiento = new Date(vm.empleado.fechaNacimiento);
        }

        function onSuccessSave (data) {
            console.log(data);
        }

        function onSuccessUpdate(data) {
            console.log(data);
        }
    }

    function onFail (data) {
        console.log(data);
    }


    function searchById(id, array, idAttr) {
        return array.map(function getIds(entity) {
            return entity[idAttr];
        }).indexOf(id);
    }
}());
