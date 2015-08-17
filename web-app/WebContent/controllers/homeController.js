angular.module('webApp').controller("homeController", ["$scope", '$http', '$location', '$rootScope', '$cookieStore','authenticationService',
   function ($scope, $http, $location, $rootScope, $cookieStore, authenticationService) {
		
		var self = this
		
		self.username = authenticationService.GetCurrentUserName();
		self.logout = logout
						
		function logout(){
			authenticationService.ClearCredentials();
			$rootScope.loggedIn = false;
	    	$location.path('#/login');
	    	//$route.reload('#/login');
		}
	
	
}]);