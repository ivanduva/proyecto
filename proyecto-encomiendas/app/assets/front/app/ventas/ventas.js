/*global angular, console*/
(function () {
    'use strict';

    angular
        .module('app.ventas')
        .controller('VentaCreateController', VentaCreateController);

    VentaCreateController.$inject = ['$routeParams', '$location', 'dataservice'];
    function VentaCreateController($routeParams, $location, dataservice) {
        var vm = this,
            encomiendaIdx;

        vm.ocultarImprimir = false;

        dataservice.PuntosVenta().query(onPuntosDeVentaLoad, onFail);
        dataservice.Clientes().query(onClientesLoad, onFail);
        dataservice.Localidades().query(onLocalidadesLoad, onFail);

        vm.title = "Nueva Venta";
        vm.saveText = "Agregar Encomienda";
        vm.venta = {
            encomiendas: []
        };

        vm.save = function () {
            dataservice.Ventas().save(vm.venta);
            $location.path('/empleado/dashboard');
        };

        vm.volver = function () {
            $location.path('/empleado/dashboard');
        };

        vm.addEncomienda = function () {
            if (vm.isEdit) {
                vm.venta.encomiendas[encomiendaIdx] = vm.encomienda;
                vm.isEdit = false;
                vm.saveText = "Agregar Encomienda";
            } else {
                var index = searchById(vm.localidad, vm.localidades, 'localidadId');

                vm.encomienda.localidad = vm.localidades[index];
                vm.venta.encomiendas.push(vm.encomienda);
            }
            vm.encomienda = {};
        }

        vm.editEncomienda = function (index) {
            vm.encomienda = vm.venta.encomiendas[index];
            vm.isEdit = true;
            vm.saveText = "Guardar";
            encomiendaIdx = index;
        }

        vm.deleteEncomienda = function (index) {
            vm.encomienda = vm.venta.encomiendas.splice(index, 1);
        }

        vm.getTotal = function () {
            if (vm.venta.encomiendas.length === 0) {
                return 0;
            }
            return vm.venta.encomiendas
                .map(function (encomienda) {
                    return encomienda.tarifa;
                })
                .reduce(function (prev, current, index, array) {
                    return prev + current;
                });
        }

        vm.print = function () {
            window.print();

        }

        vm.finalizar = function () {
            vm.ocultarImprimir = true;
        }

        vm.habilitar = function () {
            vm.ocultarImprimir = false;
        }

        function onPuntosDeVentaLoad (data) {
            vm.puntos = data;
            vm.venta.puntoDeVenta = data[0];
        }

        function onClientesLoad (data) {
            vm.clientes = data;
            vm.venta.cliente = data[0];
        }

        function onLocalidadesLoad (data) {
            vm.localidades = data;
            vm.localidad = data[0].localidadId;
        }

        function onFail (data) {
            console.log(data)
        }
    }

    function searchById(id, array, idAttr) {
        return array.map(function getIds(entity) {
            return entity[idAttr];
        }).indexOf(id);
    }

}());
