/**
 * @ngdoc controller
 * @name IndexCtrl
 * @author Christophe Eble <ceble@nexway.com>
 * @author Titouan Freville <tfreville@nexway.com>
 * @module iab
 * @description
 * Main controller for InApp Billing
 *
 * @usage
 *
 * ```html
 * <body ng-controller="MainCtrl"></body>
 * ```
 */

/* jshint +W097 */
'use strict';
/* jshint -W097 */

 angular.module('rtt', ["apiServices"])
.controller('ExampleController', ['$scope', function($scope) {
  $scope.list = [];
  $scope.text = 'hello';
  $scope.submit = function() {
    if ($scope.text) {
      $scope.list.push(this.text);
      $scope.text = '';
    }
  };
}]);
