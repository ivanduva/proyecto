/*global angular, console*/
(function () {
    'use strict';

    angular
        .module('app.encomiendas')
        .controller('EncomiendaEstadosController', EncomiendaEstadosController)
        .controller('EncomiendaOrdenController', EncomiendaOrdenController);

    EncomiendaEstadosController.$inject = ['$routeParams', '$location', 'dataservice'];
    function EncomiendaEstadosController($routeParams, $location, dataservice) {
        var vm = this;

        dataservice.PuntosVenta().query(onPuntosDeVentaLoad, onFail);

        vm.buscar = function(idEncomienda) {
            dataservice.Estados().query({id: idEncomienda}, onEstadosLoad, onFail);
            dataservice.Encomiendas().get({id: idEncomienda}, onEncomiendaLoad, onFail);
        }


        function onEstadosLoad (data) {
            vm.estados = data;
        }

        function onEncomiendaLoad (data) {
            vm.encomienda = data;
        }

        function onPuntosDeVentaLoad (data) {
            vm.puntos = data;
            vm.puntoDeVenta = data[0];
        }

        function onFail (error) {
            console.log(error);
        }



    }

    EncomiendaOrdenController.$inject = ['$routeParams', '$location', 'dataservice'];
    function EncomiendaOrdenController($routeParams, $location, dataservice) {
        var vm = this;

        vm.ordenes = [];

        dataservice.PuntosVenta().query(onPuntosDeVentaLoad, onFail);

        vm.buscar = function(idPunto) {
            dataservice.PuntoEncomiendas().query({id: idPunto}, onEncomiendasLoad, onFail);
        }



        function onPuntosDeVentaLoad (data) {
            vm.puntos = data;
            vm.puntoDeVenta = data[0];
        }

        function onEncomiendasLoad (data) {
            vm.encomiendas = data;
        }

        function onFail (error) {
            console.log(error);
        }

    }

}());
