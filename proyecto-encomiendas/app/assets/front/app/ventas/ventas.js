/*global angular, console*/
(function () {
    'use strict';

    angular
        .module('app.clientes')
        .controller('VentaCreateController', VentaCreateController);

    VentaCreateController.$inject = ['$scope', '$routeParams', '$location', 'dataservice'];
    function VentaCreateController($scope, $routeParams, $location, dataservice) {
        var vm = this;

        vm.puntos = dataservice.PuntosVenta().query();
        vm.clientes = dataservice.Clientes().query();


        vm.title = "Nueva Venta";


        vm.save = function () {
            console.log(vm.venta);
            dataservice.Ventas().create(vm.venta);

            //$location.path('/emp/ventas/new/' + vm.venta.ventaId + '/encomiendas');
        };
    }
}());