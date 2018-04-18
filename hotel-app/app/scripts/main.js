var myApp = angular.module('my-app', ['smart-table']);
const SUNDAY = 7;
const SATURDAY = 6;
myApp.controller('my-controller', function ($http, $timeout) {
    'ngInject';

    const vm = this;
    init();

    vm.addNew = () => {
        vm.isInserting = true;
    }

    vm.onChangeFilter = () => {
        vm.records = vm.checkIn.filter(record => {
            return ((vm.filter.type == 2 && moment(record.dataSaida).isBefore(moment(), 'minute'))
                || (vm.filter.type == 1 && moment(record.dataSaida).isSameOrAfter(moment(), 'minute')))
        });
    }

    vm.addNewRecord = () => {
        vm.record.pessoa.documento = Math.round(Math.random() * 1000000);
        vm.checkIn.push(angular.copy(vm.record));
        vm.record = {};
        vm.onChangeFilter();
        showAlert('Registro adicionado com sucesso!', 'alert-success');
        vm.isInserting = false;
    }

    vm.onChangeEndDate = () => {
        if (moment(vm.record.dataEntrada).isAfter(moment(vm.record.dataSaida))) {
            showAlert('A data de saída não pode ser menor que a data de entrada.', 'alert-danger');
            vm.record.dataSaida = null;
        }
    }

    vm.onChangeStartDate = () => {
        if (moment().isAfter(moment(vm.record.dataEntrada))) {
            showAlert('A data de entrada não pode ser menor que a data atual.', 'alert-danger');
            vm.record.dataEntrada = null;
        }
    }

    function showAlert(msg, className) {
        vm.alert = {
            message: msg,
            class: className
        }
        $timeout(() => vm.alert = {}, 5000);
    }

    function init() {
        vm.person = {};
        vm.filter = { 'type': 1 };
        vm.record = {}
        vm.checkIn = [];
        vm.isInserting = false;
        vm.prices = {};

        initData();
    }

    vm.bills = record => {
        const aux = moment(record.dataEntrada);
        const endDateAsMoment = moment(record.dataSaida);
        let bills = 0;
        const sum = () => {
            if (![SUNDAY, SATURDAY].includes(aux.isoWeekday())) {
                bills += vm.prices.daily;
                if (record.adicionalVeiculo) {
                    bills += vm.prices.car;
                }
            } else {
                bills += vm.prices.weekend;
                if (record.adicionalVeiculo) {
                    bills += vm.prices.car_weekend;
                }
            }
        }
        if ((endDateAsMoment.hours() + (endDateAsMoment.minutes()  / 60)) >= 16.5
            && !endDateAsMoment.isSame(aux, 'day')) {
            endDateAsMoment.add(1, 'day');
        }
        if (endDateAsMoment.isSame(aux, 'day')) {
            sum();
        }
        while (endDateAsMoment.isAfter(aux, 'day')) {
            sum();
            aux.add(1, 'day');
        }
        return bills;
    }

    function initData() {
        $http.get('json/fake.json').then(rec => {
            vm.checkIn = rec.data;
            vm.onChangeFilter();
        });
        $http.get('json/values.json').then(rec => {
            vm.prices = rec.data;
        });
    }
});