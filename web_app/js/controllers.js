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
  // $scope.mail = "marsu@pilami.palombia";
  // $scope.password = "HoubaBahou";
  $scope.submit = function() {
    var user;
    var CredError = "Les identifiants renseigner sont incorrect. Vérifier que vous avez bien saisie votre mot de passe et votre email utiliser lors de l'enregistrement.";
    var CredNotProvided = "Email ou Passowrd non renseigner. Merci de remplir ces champs afin de pouvoir accéder à votre espace.";
    if ($scope.mail && $scope.password) {
      DbUser.findOne(
        {filter:
          {
          where: {email: $scope.mail}
          }
        },
        function(value, responseHeaders) {
          user=value.toJSON();
          if ( angular.isUndefined(user) ) { }
          else {
            if ($scope.password == user.password) {
              $cookies.put('user', user.id);
              $window.location.href = '/index.html';
            }
            else {
              $window.alert(CredError);
            }
          }
        },
        function error(httpResponse) {
          var status = httpResponse.status;
          if (status == 404) {
            $window.alert(CredError);
          }
        });
    }
    else {
      $window.alert(CredNotProvided);
    }
  };
}]);
$module.controller('ReservationFormController', ['$scope', '$window', '$cookies', 'Station', '$http', function($scope, $window , $cookies, Station, $http) {
  // $scope.mail = "marsu@pilami.palombia";
  // $scope.password = "HoubaBahou";

    var stations;
    var CredError = "Les identifiants renseigner sont incorrect. Vérifier que vous avez bien saisie votre mot de passe et votre email utiliser lors de l'enregistrement.";
    var NoTripProvided = "Vous n'avez pas choisi d'itinéraire. Merci de sélectionner un départ et une arrivée pour pouvoir continuer";
    $scope.date= Date.now();
    console.log();
    $scope.slist = Station.find();
    $scope.submit = function() {
      if ($scope.departure && $scope.arrival)  {
        console.log($http.get('http://37.187.3.178:4000/iaapi/getshortest/'+$scope.departure+'/'+$scope.arrival));
      }
      else {
        $window.alert(NoTripProvided);
      }
    };
}]);
