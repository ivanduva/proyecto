/*global angular, console*/
(function () {
    'use strict';

    angular
        .module('app.punto_venta')
        .controller('PuntoVentaListController', PuntoVentaListController)
        .controller('PuntoVentaCreateController', PuntoVentaCreateController);

    PuntoVentaListController.$inject = ['$location', 'dataservice', '$scope'];
    function PuntoVentaListController($location, dataservice, $scope) {
        var vm = this;

        vm.puntosDeVenta = [];
        vm.makeReadable = makeReadable;

        dataservice.PuntosVenta().query(onSuccessQuery, onFail);

        vm.edit = function (puntoId) {
            $location.path('/admin/punto_venta/' + puntoId + '/edit');
        };

        vm.delete = function (puntoId) {
            dataservice.PuntosVenta().delete({id: parseInt(puntoId)}, onSuccessDelete, onFail);
        };

        function onSuccessQuery(puntos) {
            vm.puntosDeVenta = puntos;
        }

        function onSuccessDelete(punto) {
            var index = searchById(punto.puntoId, vm.puntosDeVenta, 'puntoId');
            vm.puntosDeVenta.splice(index, 1);
        }
    }

    PuntoVentaCreateController.$inject = ['$routeParams', '$location', 'dataservice'];
    function PuntoVentaCreateController($routeParams, $location, dataservice) {
        var vm = this;

        vm.punto = {};

        vm.inputType = 'password';
        vm.showPassText = "Mostrar";
        vm.makeReadable = makeReadable;

        vm.togglePassword = function() {
            if (vm.inputType == 'password') {
                vm.inputType = 'text';
                vm.showPassText = "Ocultar";
            } else {
                vm.inputType = 'password';
                vm.showPassText = "Mostrar";
            }
        };

        activate();

        function activate () {
            if ($routeParams.id !== undefined) {
                var id = parseInt($routeParams.id);
                dataservice.PuntosVenta().get({id: id}, onPdvLoad, onFail);
                vm.title = "Editar Punto de Venta";
            } else {
                vm.title = "Nuevo Punto de Venta";
            }
            initialRequest();
        }

        vm.save = function () {
            var index = searchById(vm.localidad, vm.localidades, 'localidadId');
            vm.punto.localidad = vm.localidades[index];
            vm.punto.tipo = vm.tipo;

            if (vm.punto.puntoId === undefined) {
                dataservice.PuntosVenta().save(vm.punto, onPdvSave, onFail);
            } else {
                dataservice.PuntosVenta().update({id: vm.punto.puntoId}, vm.punto, onPdvUpdate, onFail);
            }
            $location.path('/admin/punto_venta');
        };

        function initialRequest () {
            dataservice.Localidades().query(onLocalidadesLoad, onFail);
            dataservice.TipoPuntoVenta().query(onTipoPdvLoad, onFail);
        }

        function onLocalidadesLoad (data) {
            vm.localidades = data;
            if (vm.punto.puntoId === undefined) {
                vm.localidad = data[0].localidadId;
            }
        }

        function onTipoPdvLoad (data) {
            vm.tipoPDV = data;
            if (vm.punto.puntoId === undefined) {
                vm.tipo = data[0];
            }
        }

        function onPdvLoad (data) {
            vm.punto = data;
            vm.localidad = data.localidad.localidadId;
            vm.tipo = data.tipo;
        }

        function onPdvSave (data) {
            console.log("PDV SAVE", data);
        }

        function onPdvUpdate (data) {
            console.log("PDV UPDATE", data);
        }

    }


    function onFail(error) {
        console.log(error)
    }

    function searchById(id, array, idAttr) {
        return array.map(function getIds(entity) {
            return entity[idAttr];
        }).indexOf(id);
    }

    function makeReadable(str) {
            return str
                    .toLowerCase()
                    .split('_')
                    .map(function makeTitle(str) {
                        return str.charAt(0).toUpperCase() + str.substring(1);
                    })
                    .join(" ");
        }

}());
