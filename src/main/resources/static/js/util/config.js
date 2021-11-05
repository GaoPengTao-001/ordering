var appFactorys = angular.module('constant', [])
appFactorys.factory('constant', function () {
    var data = {};
    data.appTitle = '清凉一夏';
    return {
        getValue: function (param) {
            return data[param]
        }
    };
});