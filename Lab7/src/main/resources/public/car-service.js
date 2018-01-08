angular.module('car', [])
    .controller('CarController', function($scope, $http) {

        function getCars() {
            $http
                .get('http://localhost:8080/api/get-cars')
                .then(function (response) {
                    $scope.cars = response.data;
                });
        }

        $scope.addCar = function() {
            var data = {
                method: 'POST',
                url: 'http://localhost:8080/api/add-car',
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                data: {
                    model: $scope.model,
                    subModel: $scope.subModel,
                    price: $scope.price,
                    year: new Date($scope.year).getTime()
                }
            };

            $http(data)
                .then(function (data) {
                    console.log("success");
                    getCars();
                }, function(data) {
                    console.log("error");
                });

        };

        getCars();
    });