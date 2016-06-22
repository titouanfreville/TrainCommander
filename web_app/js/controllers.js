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

var $module = angular.module('rtt', ["apiServices", "ngCookies"]);
$module.controller('LoginController', ['$scope', '$window', '$cookies', 'DbUser', function($scope, $window , $cookies, DbUser) {
  // $scope.list = [];
  // $scope.text = 'hello';
  $scope.mail = "marsu@pilami.palombia";
  $scope.password = "HoubaBahou";
  var user;
  $scope.submit = function() {
    if ($scope.mail && $scope.password) {
      
      DbUser.findOne(
        {filter: 
          {
            where: {email: $scope.mail} 
          } 
        }).$promise.then(function(results) {
          user = results.toJSON();
        });
      if ($scope.password == user.password) {
        $cookies.put('user', user.id);
        $window.location.href = '/index.html';
      }
    }
    else {
      $window.alert("Email ou Passowrd non renseigner. Merci de remplir ces champs afin de pouvoir accéder à votre espace.")
    }
  };
}]);
$module.controller('test',[], function($scope) {

})
