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
            EmpleadosCesados: empleadosCesados,
            Ventas: ventas,
            Encomiendas: encomiendas,
            Estados: estados,
            PuntoEncomiendas: puntoEncomiendas,
            /*Login: login*/
        };

        function puntosVenta() {
            return $resource('http://localhost:9000/admin/puntos-de-venta/:id', {}, {
                update: {method: 'PUT', params: {id: '@id'}},
            });
        }

        /*function login() {
            return $resource('http://localhost:9000/login/:usuario/:pass', {}, {
                create: {method 'POST'}
            });
        }*/

        function tipoPuntoVenta() {
            return $resource('http://localhost:9000/admin/tipo-puntos-de-venta');
        }

        function localidades() {
            return $resource('http://localhost:9000/admin/localidad');
        }

        function clientes() {
            return $resource('http://localhost:9000/admin/clientes/:id', {}, {
                update: {method: 'PUT', params: {id: '@id'}}
            });
        }

        function empleados() {
            return $resource('http://localhost:9000/admin/empleados/:id', {}, {
                update: {method: 'PUT', params: {id: '@id'}}
            });
        }

        function empleadosCesados() {
            return $resource('http://localhost:9000/admin/empleados/cesar/:id', {}, {
                update: {method: 'PUT', params: {id: '@id'}}
            });
        }

        function ventas() {
            return $resource('http://localhost:9000/ventas/:id', {}, {
                update: {method: 'PUT', params: {id: '@id'}}
            });
        }

        function encomiendas() {
            return $resource('http://localhost:9000/ventas/encomiendas/:id', {}, {
                update: {method: 'PUT', params: {id: '@id'}}
            });
        }

        function estados() {
            return $resource('http://localhost:9000/ventas/encomiendas/estados/:id', {}, {
                update: {method: 'PUT', params: {id: '@id'}}
            });
        }

        function puntoEncomiendas() {
            return $resource('http://localhost:9000/ventas/encomiendas/punto-de-venta/:id', {}, {
                update: {method: 'PUT', params: {id: '@id'}}
            });
        }
    }
}());
