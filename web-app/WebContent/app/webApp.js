var app = angular.module('webApp', ['ngRoute','ui.bootstrap','objectEditor','angular-loading-bar','ngCookies', 'ngMap']);

app.config(['$routeProvider', '$locationProvider', 
   function ($routeProvider, $locationProvider) {
	    $routeProvider
          .when('/', 
          {
               templateUrl: '/views/home.html'
          })
          .when('/login', 
          {
             templateUrl: '/views/login.html'
          })
    	     .otherwise({redirectTo: '/'});
	}]);



app.run(['$rootScope', '$location', '$cookieStore', '$http','$window', function ($rootScope, $location, $cookieStore, $http) {


    // keep user logged in after page refresh
    $rootScope.globals = $cookieStore.get('globals') || {};
    if ($rootScope.globals.currentUser) {
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
    }

    $rootScope.$on('$locationChangeStart', function (event, next, current) {
        // redirect to login page if not logged in
        if ($location.path() !== '/login' && !$rootScope.globals.currentUser) {
            $location.path('/login');
        }
    });

}]);