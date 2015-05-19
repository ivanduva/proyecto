/*global angular*/
(function () {
    'use strict';

    angular
        .module('app', [
            'ngRoute',
            'ngResource',
            'ngRoute',
            'app.punto_venta',
            'app.clientes',
            'app.empleados',
            'app.encomiendas',
            'app.ventas'
        ])
        .config(['$routeProvider', routeConfiguration])
        .config(['$httpProvider', httpProviderConfiguration]);

    function routeConfiguration($routeProvider) {
        $routeProvider.

            when('/dashboard', {
                templateUrl: 'assets/front/app/dashboard/dashboard.html'
            }).

            when('/admin/dashboard', {
                templateUrl: 'assets/front/app/dashboard/admin_dashboard.html'
            }).

            when('/public/dashboard', {
                templateUrl: 'assets/front/app/dashboard/public_dashboard.html'
            }).

            when('/empleado/dashboard', {
                templateUrl: 'assets/front/app/dashboard/emp_dashboard.html',
                controller: "EncomiendaEstadosController",
                controllerAs: "vm"
            }).

            when('/login', {
                templateUrl: 'assets/front/app/login/login.html',
                /*controller: "LoginCreateController",
                controllerAs: "vm"*/
            }).

            /*PDV*/

            when('/admin/punto_venta', {
                templateUrl: 'assets/front/app/punto_venta/punto_venta.html',
                controller:"PuntoVentaListController",
                controllerAs: "vm"
            }).

            when('/admin/punto_venta/:id/edit', {
                templateUrl: 'assets/front/app/punto_venta/punto_venta_add.html',
                controller: "PuntoVentaCreateController",
                controllerAs: "vm",
            }).

            when('/admin/punto_venta/new', {
                templateUrl: 'assets/front/app/punto_venta/punto_venta_add.html',
                controller: "PuntoVentaCreateController",
                controllerAs: "vm"
            }).

            /*CLIENTES*/

            when('/admin/clientes', {
                templateUrl: '/assets/front/app/clientes/admin_clientes.html',
                controller:"ClienteListController",
                controllerAs: "vm"
            }).

            when('/admin/clientes/:id/edit', {
                templateUrl: '/assets/front/app/clientes/clientes_add.html',
                controller: "ClienteCreateController",
                controllerAs: "vm"
            }).

            when('/admin/clientes/new', {
                templateUrl: '/assets/front/app/clientes/clientes_add.html',
                controller: "ClienteCreateController",
                controllerAs: "vm"
            }).

            when('/public/clientes/new', {
                templateUrl: '/assets/front/app/clientes/clientes_add.html',
                controller: "ClienteCreateController",
                controllerAs: "vm"
            }).

            /*EMPLEADOS*/

            when('/admin/empleados', {
                templateUrl: '/assets/front/app/empleados/admin_empleados.html',
                controller:"EmpleadoListController",
                controllerAs: "vm"
            }).

            when('/admin/empleados/:id/edit', {
                templateUrl: '/assets/front/app/empleados/empleados_add.html',
                controller: "EmpleadoCreateController",
                controllerAs: "vm"
            }).

            when('/admin/empleados/new', {
                templateUrl: '/assets/front/app/empleados/empleados_add.html',
                controller: "EmpleadoCreateController",
                controllerAs: "vm"
            }).

            /*VENTAS*/

            when('/empleado/ventas/new', {
                templateUrl: '/assets/front/app/ventas/ventas_add.html',
                controller: "VentaCreateController",
                controllerAs: "vm"
            }).

            /*ENCOMIENDAS*/

            when('/public/estado-encomienda', {
                templateUrl: '/assets/front/app/encomiendas/ver_estados.html',
                controller: "EncomiendaEstadosController",
                controllerAs: "vm"
            }).

            when('/empleado/estado-encomienda', {
                templateUrl: '/assets/front/app/encomiendas/ver_estados.html',
                controller: "EncomiendaEstadosController",
                controllerAs: "vm"
            }).

            when('/empleado/cambiar-estado-encomienda', {
                templateUrl: '/assets/front/app/encomiendas/cambiar_estados.html',
                controller: "EncomiendaEstadosController",
                controllerAs: "vm"
            }).

            /*when('/empleado/recibir-encomienda', {
                templateUrl: '/assets/front/app/encomiendas/ver_estados.html',
                controller: "EncomiendaEstadosController",
                controllerAs: "vm"
            }).

            when('/empleado/entregar-encomienda', {
                templateUrl: '/assets/front/app/encomiendas/ver_estados.html',
                controller: "EncomiendaEstadosController",
                controllerAs: "vm"
            }).*/

            when('/empleado/generar-orden', {
                templateUrl: '/assets/front/app/encomiendas/orden_reparto.html',
                controller: "EncomiendaOrdenController",
                controllerAs: "vm"
            }).

            otherwise({ redirectTo: '/dashboard'});
    }

    function httpProviderConfiguration($httpProvider) {
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    }

}());
