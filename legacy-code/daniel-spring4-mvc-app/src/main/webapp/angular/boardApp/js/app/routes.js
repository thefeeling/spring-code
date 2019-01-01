/**
 * angular board route
 */
(function(){
	'use strict';
	angular
	.module('boardApp')
	.config(function($routeProvider) {
		$routeProvider
		.when('/', {
			templateUrl : 'views/boardList.html',
			controller : 'listCtrl',
		})
		.when('/write', {
			templateUrl : 'views/boardWrite.html',
			controller : 'writeCtrl',
		})
		.when('/update/:boardNo', {
			templateUrl : 'views/boardUpdate.html',
			controller : 'updateCtrl',
		})
		.when('/detail/:boardNo', {
			templateUrl : 'views/boardDetail.html',
			controller : 'detailCtrl',
		})
		.otherwise({
			redirectTo : '/'
		});
	});
}());