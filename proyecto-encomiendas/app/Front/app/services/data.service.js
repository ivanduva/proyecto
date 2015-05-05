/*global angular,console*/
(function () {
    'use strict';

    angular
        .module('app')
        .factory('dataservice', dataservice);

    dataservice.$inject = ['$resource'];
    function dataservice($resource) {
        return {
            PuntosVenta: puntosVenta,
            PuntoVenta: puntoVenta,
            TipoPuntoVenta: tipoPuntoVenta,
            Localidades: localidades
        };

        function puntosVenta() {
            return $resource('http://localhost:9000/admin/puntos-de-venta', {}, {
                query: {method: 'GET', isArray: true},
                create: {method: 'POST'}
            });
        }

        function puntoVenta() {
            return $resource('http://localhost:9000/admin/puntos-de-venta/:id', {}, {
                show: {method: 'GET'},
                update: {method: 'PUT', params: {id: '@id'}},
                delete: {method: 'DELETE', params: {id: '@id'}}
            });
        }

        function tipoPuntoVenta() {
            return $resource('http://localhost:9000/admin/tipo-puntos-de-venta', {}, {
                query: {method: 'GET', isArray: true}
            });
        }

        function localidades() {
            return $resource('http://localhost:9000/admin/localidad', {}, {
                query: {method: 'GET', isArray: true}
            });
        }
    }
}());
