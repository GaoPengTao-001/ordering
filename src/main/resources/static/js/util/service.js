var appFactorys = angular.module('service', [])
appFactorys.factory('service', function ($http, constant) {
    return {
        queryMenu: function () {
            return $http({
                method: 'POST',
                url: '../client/queryMenu',
                responseType: 'text',
                data: ''
            })
        },
        queryUser: function () {
            return $http({
                method: 'POST',
                url: '../client/queryUser',
                responseType: 'text',
                data: ''
            })
        },
        saveOrderMenu: function (orderMenus, userInfo) {
            let data = {}
            data.orderMenus = orderMenus
            data.userInfo = userInfo
            return $http({
                method: 'POST',
                url: '../client/saveOrderMenu',
                responseType: 'text',
                data: JSON.stringify(data)
            })
        },
        getOpenId: function (code) {
            return $http({
                method: 'POST',
                url: '../client/getOpenId',
                responseType: 'text',
                data: code
            })
        },
    }
})