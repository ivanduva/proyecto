/*global angular*/
(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngResource', 'ngRoute', 'app.punto_venta'])
        .config(['$routeProvider', routeConfiguration])
        .config(['$httpProvider', httpProviderConfiguration]);

    function routeConfiguration($routeProvider) {
        $routeProvider.
            when('/dashboard', {
                templateUrl: 'assets/Front/app/dashboard/dashboard.html'

            }).
            when('/punto_venta', {
                templateUrl: 'assets/Front/app/punto_venta/punto_venta.html',
                controller:"PuntoVentaListController",
                controllerAs: "vm"
            }).

            when('/punto_venta/:id/edit', {
                templateUrl: 'assets/Front/app/punto_venta/punto_venta_add.html',
                controller: "PuntoVentaCreateController",
                controllerAs: "vm"
            }).
            when('/punto_venta/new', {
                templateUrl: 'assets/Front/app/punto_venta/punto_venta_add.html',
                controller: "PuntoVentaCreateController",
                controllerAs: "vm"
                }).
            otherwise({ redirectTo: '/puntos_venta'});
    }

    function httpProviderConfiguration($httpProvider) {
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    }

}());
