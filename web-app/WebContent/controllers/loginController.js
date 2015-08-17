angular
        .module('webApp')
        .controller('loginController', LoginController);
 
    LoginController.$inject = ['$location', '$rootScope','authenticationService'];
    function LoginController($location, $rootScope, authenticationService) {
    	    	    	
        var vm = this;
 
        vm.login = login;
        vm.error = false;
 
        authenticationService.ClearCredentials();
        
 
        function login() {
            vm.dataLoading = true;
            authenticationService.Login(vm.username, vm.password, function (response) {
                if (response.success) {
                	                	                	
                    authenticationService.SetCredentials(vm.username, vm.password);
                    $rootScope.loggedIn = true;
                    $location.path('#/').reload('true');
                    //$route.reload('#/');
                    //$state.reload();
                } else {
                	$rootScope.error = "Usuario o password incorrectos";
                    vm.dataLoading = false;
                }
            });
        };
    }