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
            TipoPuntoVenta: tipoPuntoVenta,
            Localidades: localidades,
            Clientes: clientes,
            Empleados: empleados,
            Ventas: ventas,
            Encomiendas: encomiendas,
            Estados: estados,
            PuntoEncomiendas: puntoEncomiendas,
            /*Login: login*/
        };

        function puntosVenta() {
            return $resource('http://localhost:9000/api/v1.0/puntos-de-venta/:id', {}, {
                update: {method: 'PUT', params: {id: '@id'}}
            });
        }

        /*function login() {
            return $resource('http://localhost:9000/login/:usuario/:pass', {}, {
                create: {method 'POST'}
            });
        }*/

        function tipoPuntoVenta() {
            return $resource('http://localhost:9000/api/v1.0/tipo-puntos-de-venta');
        }

        function localidades() {
            return $resource('http://localhost:9000/api/v1.0/localidad');
        }

        function clientes() {
            return $resource('http://localhost:9000/api/v1.0/cliente/:id', {}, {
                update: {method: 'PUT', params: {id: '@id'}}
            });
        }

        function empleados() {
            return $resource('http://localhost:9000/api/v1.0/empleado/:id', {}, {
                update: {method: 'PUT', params: {id: '@id'}}
            });
        }

        function ventas() {
            return $resource('http://localhost:9000/api/v1.0/venta/:id', {}, {
                update: {method: 'PUT', params: {id: '@id'}}
            });
        }

        function encomiendas() {
            return $resource('http://localhost:9000/api/v1.0/encomienda/:id', {}, {
                update: {method: 'PUT', params: {id: '@id'}}
            });
        }

        function estados() {
            return $resource('http://localhost:9000/api/v1.0/encomienda/:id/estados', {}, {
                update: {method: 'PUT', params: {id: '@id'}}
            });
        }

        function puntoEncomiendas() {
            return $resource('http://localhost:9000/api/v1.0/puntos-de-venta/:id/encomienda', {}, {
                update: {method: 'PUT', params: {id: '@id'}}
            });
        }
    }
}());
