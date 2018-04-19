var myApp = angular.module('my-app', ['smart-table']);
const SUNDAY = 7;
const SATURDAY = 6;
myApp.controller('my-controller', function ($http, $timeout) {
    'ngInject';

    const vm = this;
    init();


    vm.addNew = () => {
        vm.isInserting = !vm.isInserting;
        vm.newPerson = {
          person: {
            name:'',
            document:'',
            fone:''
          }
        };
    };

    vm.saveNewPerson = () => {
      vm.listPerson.push(angular.copy(vm.newPerson));
    };

    vm.voltar = () => {
      vm.isInserting = false;
    }

    vm.onChangeFilter = () => {
        vm.records = vm.checkIn.filter(record => {
            return ((vm.filter.type == 2 && moment(record.exitDate).isBefore(moment(), 'minute'))
                || (vm.filter.type == 1 && moment(record.exitDate).isSameOrAfter(moment(), 'minute')))
        });
    }

    vm.addNewRecord = () => {
        vm.checkIn.push(angular.copy(vm.record));
        vm.record = {};
        vm.onChangeFilter();
        showAlert('Registro adicionado com sucesso!', 'alert-success');
        vm.isInserting = false;
    }

    vm.onChangeEndDate = () => {
        if (moment(vm.record.entryDate).isAfter(moment(vm.record.exitDate))) {
            showAlert('A data de saída não pode ser menor que a data de entrada.', 'alert-danger');
            vm.record.exitDate = null;
        }
    }

    vm.onChangeStartDate = () => {
        if (moment().isAfter(moment(vm.record.entryDate))) {
            showAlert('A data de entrada não pode ser menor que a data atual.', 'alert-danger');
            vm.record.entryDate = null;
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
        vm.listPerson = [];

        initData();
    }

    vm.bills = record => {
        const aux = moment(record.entryDate);
        const endDateAsMoment = moment(record.entryDate);
        let bills = 0;
        const sum = () => {
            if (![SUNDAY, SATURDAY].includes(aux.isoWeekday())) {
                bills += vm.prices.daily;
                if (record.haveVehicle) {
                    bills += vm.prices.car;
                }
            } else {
                bills += vm.prices.weekend;
                if (record.haveVehicle) {
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
        $http.get('json/mock.json').then(rec => {
            vm.checkIn = rec.data;
            vm.onChangeFilter();
        });
        $http.get('json/prices.json').then(rec => {
            vm.prices = rec.data;
        });
        $http.get('json/persons.json').then(rec => {
          vm.listPerson = rec.data;
        });
    }
});
