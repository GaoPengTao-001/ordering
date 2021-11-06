var appFactorys = angular.module('constant', [])
appFactorys.factory('constant', function () {
    let data = {};
    data.appTitle = '清凉一夏';
    return {
        getValue: function (param) {
            return data[param]
        }
    };
});