

angular
        .module('webApp')
        .factory('userService', userService);
 
    userService.$inject = ['$http'];
    function userService($http) {
        var service = {};
 
        service.getAll = getAll;
        service.getByUsername = getByUsername;
        service.authenticate = authenticate;
       
        return service;
 
        function getAll() {
            return $http.get('http://localhost:8080/web-app/user/all').then(handleSuccess, handleError('Error getting all users'));
        }
 
        function getByUsername(username) {
            return $http.get('http://localhost:8080/web-app/user/' + username).then(handleSuccess, handleError('Error getting user by username'));
        }
        
        function authenticate(username, password, callback) {
            $http.post('http://localhost:8080/web-app/user/authenticate', 
            		{userName: username, password: password})
            		.success(function (response){
                        callback({ success : true});
            		})
            		.error(function (response){
                        callback({ success : false});
            		});
        }
 
        // private functions
 
        function handleSuccess(data) {
            return data;
        }
 
        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
    }
 
