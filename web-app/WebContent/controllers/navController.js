angular.module('webApp').controller("navController", ["$scope", '$http', '$location', '$rootScope', '$cookieStore','authenticationService',
   function ($scope, $http, $location, $rootScope, $cookieStore, authenticationService) {
		
		var self = this
		
		self.userName = authenticationService.GetCurrentUserName();
		
		$rootScope.loggedIn = authenticationService.IsLoggedIn();
		$rootScope.userName = authenticationService.GetCurrentUserName();
	
}]);