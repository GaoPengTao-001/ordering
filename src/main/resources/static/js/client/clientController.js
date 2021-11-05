app.controller("clientCtrl", function ($scope, service, constant, $ionicPopup) {
    $scope.message = "";
    $scope.appTitle = constant.getValue('appTitle');
    $scope.selectOpt = {'opts': [], 'price': 0};
    $scope.orderInfo = {};
    $scope.left = function () {
        return 100 - $scope.message.length;
    };
    service.queryMenu().then(function (data) {
        // 初始化数量
        if (null != data.data && data.data.length > 0) {
            for (let i = 0; i < data.data.length; i++) {
                data.data[i].num = 0;
            }
        }
        $scope.menuList = data.data;
    });

    service.getOpenId(getUrlParams('code'))

    function getUrlParams(name) {
        let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        let param = window.location.search.substr(1).match(reg);
        return param && param[2];
    }

    $scope.removeMenu = function (menu) {
        if (menu.num > 0) {
            for (let i in $scope.selectOpt.opts) {
                let len = $scope.selectOpt.opts.length
                if ($scope.selectOpt.opts[len - i - 1].menu.id == menu.id) {
                    $scope.selectOpt.opts.splice(len - i - 1, 1)
                    menu.num--
                    $scope.clacPrice()
                    break
                }
            }
        }
    }

    $scope.addMenu = function (menu) {
        $scope.selectMenu = menu
        // 默认选择第一个
        $scope.selectOpt.optId = menu.options[0].id
        $ionicPopup.show({
            template: '<div class="list">\n' +
                '<label class="item item-radio" ng-repeat="opt in selectMenu.options">\n' +
                '  <input ng-if="$index == 0" type="radio" name="group" checked="checked" ng-model="selectOpt.optId" value="{{opt.id}}">\n' +
                '  <input ng-if="$index != 0" type="radio" name="group" ng-model="selectOpt.optId" value="{{opt.id}}">\n' +
                '   <div class="radio-content">\n' +
                '   <div class="item-content">\n' +
                '    {{opt.name}}\n' +
                '   </div>\n' +
                '   <i class="radio-icon ion-checkmark"></i>\n' +
                '  </div>\n' +
                '</label>\n' +
                '</div>',
            title: '规格',
            subTitle: '请选择：',
            scope: $scope,
            buttons: [
                {text: '取消'},
                {
                    text: '<b>确认</b>',
                    type: 'button-positive',
                    onTap: function (e) {
                        for (let optId in menu.options) {
                            if (menu.options[optId].id == $scope.selectOpt.optId) {
                                // 构造选择的对象
                                $scope.selectOpt.opts.push({'menu': menu, 'opt': menu.options[optId]})
                                menu.num++;
                                $scope.clacPrice()
                            }
                        }
                    }
                },
            ]
        });
    }

    $scope.showSelect = function () {
        console.log($scope.selectOpt.opts)
    }

    $scope.clacPrice = function () {
        let price = 0
        if ($scope.selectOpt.opts.length > 0) {
            let opts = $scope.selectOpt.opts
            for (let i in opts) {
                price += opts[i].menu.price
                price += opts[i].opt.price
            }
        }
        $scope.selectOpt.price = price
    }

    // 下单页
    $scope.settlement = function () {
        if ($scope.selectOpt.opts.length < 1) {
            return
        }
        // 查询手机号，地址等
        service.queryUser().then(function (data) {
            console.log(data)
            $scope.orderInfo.phoneNum = data.data.phoneNum
            $scope.orderInfo.address = data.data.address
        })

        // 显示弹出框
        $ionicPopup.show({
            template: '<div class="row" ng-repeat="opt in selectOpt.opts"\n' +
                '         style="height:20px;font-size: 15px;margin-bottom: 10px;">\n' +
                '        <div>{{opt.menu.name}}/{{opt.opt.name}}</div>\n' +
                '        <span style="font-family: \'MicrosoftYaHei\';color: #ff5c3a;">￥{{opt.menu.price + opt.opt.price}}</span>\n' +
                '    </div>' +
                '<div>总价：{{selectOpt.price}}元</div>' +
                '手机号：<input type="text" ng-model="orderInfo.phoneNum">' +
                '地址：<input type="text" ng-model="orderInfo.address">',
            title: '确认订单',
            subTitle: '内容',
            scope: $scope,
            buttons: [
                {text: '取消'},
                {
                    text: '<b>下单</b>',
                    type: 'button-positive',
                    onTap: function (e) {
                        if (!$scope.orderInfo.phoneNum) {
                            $ionicPopup.alert({
                                title: '提示',
                                template: '请填写正确的手机号'
                            });
                            e.preventDefault();
                        } else if (!$scope.orderInfo.address) {
                            $ionicPopup.alert({
                                title: '提示',
                                template: '请填写正确的地址'
                            });
                            e.preventDefault();
                        } else {
                            service.saveOrderMenu($scope.selectOpt.opts, $scope.orderInfo).then(function (data) {
                                if (200 == data.status) {
                                    $ionicPopup.alert({
                                        title: '提示',
                                        template: '下单成功'
                                    });
                                }
                            }, function err(data) {
                                $ionicPopup.alert({
                                    title: '提示',
                                    template: '下单失败'
                                });
                            })

                        }
                    }
                },
            ]
        });

    }
});