/*global angular*/
(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngResource', 'ngRoute', 'app.punto_venta', 'app.clientes', 'app.empleados'])
        .config(['$routeProvider', routeConfiguration])
        .config(['$httpProvider', httpProviderConfiguration]);

    function routeConfiguration($routeProvider) {
        $routeProvider.


            when('/admin/dashboard', {
                templateUrl: 'assets/front/app/dashboard/admin_dashboard.html'

            }).

            when('/admin/personas', {
                templateUrl: 'assets/front/app/dashboard/admin_personas_dashboard.html'
            }).

            when('/login', {
                templateUrl: 'assets/front/app/login/login.html',
                /*controller: "LoginCreateController",
                controllerAs: "vm"*/
            }).

            when('/admin/punto_venta', {
                templateUrl: 'assets/front/app/punto_venta/punto_venta.html',
                controller:"PuntoVentaListController",
                controllerAs: "vm"
            }).

            when('/admin/punto_venta/:id/edit', {
                templateUrl: 'assets/front/app/punto_venta/punto_venta_add.html',
                controller: "PuntoVentaCreateController",
                controllerAs: "vm"
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

            otherwise({ redirectTo: '/admin/dashboard'});
    }

    function httpProviderConfiguration($httpProvider) {
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    }

}());
