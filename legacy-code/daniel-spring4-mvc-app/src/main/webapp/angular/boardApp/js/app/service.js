/**
 * @Comment Angular services are substitutable objects
 * Angular Service는 '객체'
 * 싱글턴으로 유지됨.
 */
(function() {
	'use strict';
	angular
	.module('boardApp')
	.service('reverseTxtService', function() {
		this.reverse = function(name) {
			return name.split("").reverse().join("");
		};
	})
	/**
	 * @Comment DI 적용이 되는듯?
	 */
	.service('boardListSvc', ["$http", function($http){
		var boardList = [];
		var backPage = 0;
		var nextPage = 0;
		
		this.getBoardList = function(){
			$http.get('/rest/board/list')
			.then(
				function successCallBack(response){
					boardList = response.data.boardList;
					// 첫 페이지인 경우
					if(response.data.currentPage === 1){
						nextPage  = (response.data.currentPage) + 1;
						backPage  = 0;
					}else{
						nextPage  = (response.data.currentPage) + 1;
						backPage  = (response.data.currentPage) - 1;						
					}
				},
				function errorCallBack(response){
					$window.alert("Network Failure");
				}
			);			
			
			return boardList;
		}
		
		this.getBackPage = function(){
			return backPage;
		}
		
		this.getNextPage = function(){
			return nextPage;
		}
		
		this.getResource = function(){
			$http.get('/rest/board/list')
			.then(
				function successCallBack(response){
					boardList = response.data.boardList;
					// 첫 페이지인 경우
					if(response.data.currentPage === 1){
						nextPage  = (response.data.currentPage) + 1;
						backPage  = 0;
					}else{
						nextPage  = (response.data.currentPage) + 1;
						backPage  = (response.data.currentPage) - 1;						
					}
				},
				function errorCallBack(response){
					$window.alert("Network Failure");
				}
			);						
		}
	}]);
}());
