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

        dataservice.Clientes().query(onSuccessQuery, onFail);

        vm.edit = function (personaId) {
            $location.path('/admin/clientes/' + personaId + '/edit');
        };

        vm.delete = function (personaId) {
            dataservice.Clientes().delete({id: parseInt(personaId)}, onSuccessDelete, onFail);
        };

        function onSuccessDelete(data) {
            console.log(data);
        }

        function onSuccessQuery(data) {
            vm.clientes = data;
            console.log(data);
        }

    }

    ClienteCreateController.$inject = ['$routeParams', '$location', 'dataservice'];
    function ClienteCreateController($routeParams, $location, dataservice) {
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
        vm.cliente = {};

        if ($routeParams.id !== undefined) {
            var id = parseInt($routeParams.id);
            dataservice.Clientes().get({id: id}, onSuccessGet, onFail);
            vm.title = "Editar Cliente";
            vm.ocultarUsuario=1;
        } else {
            vm.title = "Nuevo Cliente";
            vm.ocultarUsuario=0;
        }

        vm.save = function () {
            var index = searchById(vm.localidad, vm.localidades, 'localidadId');
            vm.cliente.localidad = vm.localidades[index];
            vm.cliente.puedeReservar = vm.cliente.puedeReservar ? "SI" : "NO";

            if (vm.cliente.personaId === undefined) {
                dataservice.Clientes().save(vm.cliente, onSuccessSave, onFail);
            } else {
                dataservice.Clientes().update({id: vm.cliente.personaId}, vm.cliente, onSuccessUpdate, onFail);
            }
            $location.path('/admin/clientes');
        };

        function onLocalidadesLoad(data) {
            vm.localidades = data;
            vm.localidad = data[0].localidadId;
        }

        function onSuccessGet (data) {
            vm.cliente = data;
            vm.cliente.puedeReservar = vm.cliente.puedeReservar === "SI";
            vm.cliente.fechaNacimiento = new Date(vm.cliente.fechaNacimiento);
        }

        function onSuccessSave (data) {
            console.log(data);
        }

        function onSuccessUpdate(data) {
            console.log(data);
        }
    }

    function onFail(data) {
        console.log(data);
    }

    function searchById(id, array, idAttr) {
        return array.map(function getIds(entity) {
            return entity[idAttr];
        }).indexOf(id);
    }
}());
