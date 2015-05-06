/*global angular, console*/
(function () {
    'use strict';

    angular
        .module('app.punto_venta')
        .controller('PuntoVentaListController', PuntoVentaListController)
        .controller('PuntoVentaCreateController', PuntoVentaCreateController);

    PuntoVentaListController.$inject = ['$location', 'dataservice'];
    function PuntoVentaListController($location, dataservice) {
        var vm = this;

        vm.puntosDeVenta = dataservice.PuntosVenta().query();

        vm.edit = function (puntoId) {
            $location.path('/punto_venta/' + puntoId + '/edit');
        };

        vm.delete = function (puntoId) {
            dataservice.PuntoVenta().delete(puntoId);
        };
    }

    PuntoVentaCreateController.$inject = ['$scope', '$routeParams', '$location', 'dataservice'];
    function PuntoVentaCreateController($scope, $routeParams, $location, dataservice) {
        var vm = this;

        vm.localidades = dataservice.Localidades().query();
        vm.tipoPDV = dataservice.TipoPuntoVenta().query();

        if ($routeParams.id !== undefined) {
            var id = parseInt($routeParams.id);
            vm.punto = dataservice.PuntoVenta().show(id);
            vm.title = "Editar Punto de Venta";
        } else {
            vm.title = "Nuevo Punto de Venta";
        }


        vm.save = function () {
            if (vm.punto.puntoId === undefined) {
                dataservice.PuntosVenta().create(vm.punto);
            } else {
                dataservice.PuntoVenta().update(vm.punto);
            }
            $location.path('/punto_venta');
        };
    }
}());
