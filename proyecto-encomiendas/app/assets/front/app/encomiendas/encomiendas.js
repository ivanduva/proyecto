/*global angular, console*/
(function () {
    'use strict';

    angular
        .module('app.encomiendas')
        .controller('EncomiendaEstadosController', EncomiendaEstadosController)
        .controller('EncomiendaOrdenController', EncomiendaOrdenController);

    EncomiendaEstadosController.$inject = ['dataservice', '$scope'];
    function EncomiendaEstadosController(dataservice, $scope) {
        var vm = this;
        vm.display = 'recibir';
        vm.id = "";

        vm.makeReadable = makeReadable;

        dataservice.PuntosVenta().query(onPuntosDeVentaLoad, onFail);

        vm.buscar = function() {
            var idEncomienda = parseInt(vm.id);
            clearView();
            vm.load = true;
            dataservice.Encomiendas().get({id: idEncomienda}, onEncomiendaLoad, onFail);
            dataservice.Estados().query({id: idEncomienda}, onEstadosLoad, onFail);
        }

        vm.addNewState = function () {
            var siguienteEstado = {
                tipoCambio: vm.display,
            }
            if (vm.display === 'recibir') {
                siguienteEstado['puntoDeVenta'] = vm.puntoDeVenta
            }
            modificarPdv(siguienteEstado);
        };

        vm.active = function ($event, action) {
            toggleActiveTab($event.currentTarget);
            vm.display = action;
            clearView();
        };

        function modificarPdv(siguiente) {
            dataservice.Encomiendas()
                .update({id: vm.encomienda.encomiendaId},
                        {encomienda: vm.encomienda, siguienteEstado: siguiente},
                        onEncomiendaUpdate, onFail);
                        
        }

        function onEncomiendaUpdate(data) {
            if (vm.encomienda.encomiendaId === data.encomiendaId) {
                vm.estados.push(data.estados[data.estados.length - 1]);
            }
        }

        function clearView() {
            vm.error = "";
            vm.estados = [];
            vm.id = "";
        }

        function toggleActiveTab(currentTab) {
            var tabs = document.querySelectorAll('li.equal-tab'),
                i = 0;

            for (i; i < tabs.length; i++) {
                if (tabs[i] === currentTab) {
                    tabs[i].classList.add('active');
                } else {
                    tabs[i].classList.remove('active');
                }
            }
        }

        function onEstadosLoad (data) {
            vm.estados = data;
        }

        function onEncomiendaLoad (data) {
            vm.load = false;
            vm.encomienda = data;
        }

        function onPuntosDeVentaLoad (data) {
            vm.puntos = data;
            vm.puntoDeVenta = data[0];
        }

        function onFail (error) {
            vm.load = false;
            if (error.status === 404) {
                vm.error = "No existe la encomienda buscada";
            }
        }
    }

    EncomiendaOrdenController.$inject = ['dataservice', '$scope'];
    function EncomiendaOrdenController(dataservice, $scope) {
        var vm = this;

        vm.ordenes = [];
        vm.encomiendas = [];

        dataservice.PuntosVenta().query(onPuntosDeVentaLoad, onFail);

        vm.buscar = function(idPunto) {
            vm.load = true;
            vm.error = "";
            dataservice.PuntoEncomiendas().query({id: idPunto}, onEncomiendasLoad, onFail);
        };

        vm.generar = function () {
            vm.order = vm.encomiendas.filter(function (enc) {
                if (enc.isReadyToDispatch) {
                    return enc;
                }
            });
        };

        vm.print = function () {
            window.print();
        }

        function onPuntosDeVentaLoad (data) {
            vm.puntos = data;
            vm.puntoDeVenta = data[0];
        }

        function onEncomiendasLoad (data) {
            vm.load = false;
            vm.encomiendas = data;
            if (data.length === 0) {
                vm.error = "Sin Encomiendas en este punto de venta";
            }
        }

        function onFail (error) {
            vm.load = false;
        }

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
