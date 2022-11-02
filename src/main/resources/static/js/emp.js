var app = angular.module('empApp', []);
app.controller('Appcontroller', function($scope, $http) {
	console.log("In controller");

	//$scope.emp={};

	$http.get('http://localhost:8080/api/emp')
		.then(
			function(response) {
				console.log("starting to fetch accounts...");
				$scope.emplist = response.data;
			},
			function(errResponse){
				 console.error('Error while retrieving accounts');
			})





//create Employee
 $scope.createEmployee = function() {

    //$http POST function
    $http({

      method: 'POST',
      url: 'http://localhost:8080/api/emp',
      data: $scope.emp

    }).then(function successCallback(response) {

      alert("User has created Successfully")
      $scope.emp={}

    }, function errorCallback(response) {

      alert("Error. while created user Try Again!");

    });

  } 
 
 
 //Edit Function 
$scope.edit = function(emp) {
	
	console.log("In edit function");

    $scope.emp = emp;
    $scope.id = false;

  };
  
  
  
  //update Employee
  
  $scope.updateEmployee = function() {

    //$http PUT function
    $http({

      method: 'PUT',
      url: 'http://localhost:8080/api/emp/' + $scope.emp.id,
      data: $scope.emp
    }).then(function successCallback(response) {

      alert("User has updated Successfully")

    }, function errorCallback(response) {

      alert("Error. while updating user Try Again!");

    });

  };

//Delete User
	$scope.delete = function(emp) {

		//$http DELETE function
		$http({

			method: 'DELETE',
			url: 'http://localhost:8080/api/emp/' + emp.id

		}).then(function successCallback(response) {

			alert("User has deleted Successfully");
			var index = $scope.emplist.indexOf(emp);
			$scope.emplist.splice(index, 1);

		}, function errorCallback(response) {

			alert("Error. while deleting user Try Again!");

		});

	};

 
		  
 
		


});




/*app.controller('Savecontroller', function($scope, $http) {
//Create New User
 /* $scope.createEmployee = function() {

    //$http POST function
    $http({

      method: 'POST',
      url: 'http://localhost:8080/api/emp',
      data: $scope.emp

    }).then(function successCallback(response) {

      alert("User has created Successfully")
      $scope.emp={}

    }, function errorCallback(response) {

      alert("Error. while created user Try Again!");

    });

  } 
  */
  //Update User

 /* $scope.updateEmployee = function() {

    //$http PUT function
    $http({

      method: 'PUT',
      url: 'http://localhost:8080/api/emp' + $scope.emp.id,
      data: $scope.emp
    }).then(function successCallback(response) {

      alert("User has updated Successfully")

    }, function errorCallback(response) {

      alert("Error. while updating user Try Again!");

    });

  };*/
  
  