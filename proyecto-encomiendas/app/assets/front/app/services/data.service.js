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
            /*Login: login*/
        };

        function puntosVenta() {
            return $resource('http://localhost:9000/admin/puntos-de-venta/:id', {}, {
                query: {method: 'GET', isArray: true},
                create: {method: 'POST'},
                show: {method: 'GET', params: {id: '@id'}},
                update: {method: 'PUT', params: {id: '@id'}},
                delete: {method: 'DELETE', params: {id: '@id'}}
            });
        }

        /*function login() {
            return $resource('http://localhost:9000/login/:usuario/:pass', {}, {
                create: {method 'POST'}
            });
        }*/

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

        function clientes() {
            return $resource('http://localhost:9000/admin/clientes/:id', {}, {
                query: {method: 'GET', isArray: true},
                create: {method: 'POST'},
                show: {method: 'GET', params: {id: '@id'}},
                update: {method: 'PUT', params: {id: '@id'}},
                delete: {method: 'DELETE', params: {id: '@id'}}
            });
        }

        function empleados() {
                    return $resource('http://localhost:9000/admin/empleados/:id', {}, {
                        query: {method: 'GET', isArray: true},
                        create: {method: 'POST'},
                        show: {method: 'GET', params: {id: '@id'}},
                        update: {method: 'PUT', params: {id: '@id'}},
                    });
                }
    }
}());
