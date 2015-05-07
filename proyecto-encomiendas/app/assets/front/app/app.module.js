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
                templateUrl: 'assets/front/app/dashboard/dashboard.html'

            }).
            when('/punto_venta', {
                templateUrl: 'assets/front/app/punto_venta/punto_venta.html',
                controller:"PuntoVentaListController",
                controllerAs: "vm"
            }).

            when('/punto_venta/:id/edit', {
                templateUrl: 'assets/front/app/punto_venta/punto_venta_add.html',
                controller: "PuntoVentaCreateController",
                controllerAs: "vm"
            }).

            when('/login', {
                templateUrl: 'assets/front/app/login/login.html',
                /*controller: "LoginCreateController",
                controllerAs: "vm"*/
            }).

            when('/punto_venta/new', {
                templateUrl: 'assets/front/app/punto_venta/punto_venta_add.html',
                controller: "PuntoVentaCreateController",
                controllerAs: "vm"
                }).
            otherwise({ redirectTo: '/dashboard'});
    }

    function httpProviderConfiguration($httpProvider) {
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    }

}());
