/**
 * angular board controller
 */
(function(){
	'use strict';
	angular.module('boardApp')
		/**
		 * @Subject 게시물 리스트
		 * @Controller listCtrl
		 * @Service $scope, $window, $http, boardListSvc
		 * @Comment
		 *  - boardListSvc 추가 : 게시물 리스트 반환 처리
		 */
		.controller('listCtrl', function ($scope, $window, $http, boardListSvc) {
			$scope.nextPage    = 0;
			$scope.backPage    = 0;
			$scope.boardList   = [];

			
			//$scope.nextPage    = boardListSvc.getNextPage();
			//$scope.backPage    = boardListSvc.getBackPage();
			//$scope.boardList   = boardListSvc.getBoardList();
			//boardListSvc.getResource();
			
			
			
			
			$http.get('/rest/board/list')
			.then(
				function successCallBack(response){
					$scope.boardList = response.data.boardList;
					// 첫 페이지인 경우
					if(response.data.currentPage === 1){
						$scope.nextPage  = (response.data.currentPage) + 1;
						$scope.backPage  = 0;
					}else{
						$scope.nextPage  = (response.data.currentPage) + 1;
						$scope.backPage  = (response.data.currentPage) - 1;						
					}
				},
				function errorCallBack(response){
					$window.alert("Network Failure");
				}
			);				
			
			$scope.selectBoardDetail = function(boardNo){
				$window.location.href = "#/detail/" + boardNo;
			};
			
			$scope.pageMove = function(pageNo){
				$http.get('/rest/board/list?currentPage=' + pageNo)
				.then(
					function successCallBack(response){
						$scope.boardList = response.data.boardList;
						// 첫 페이지인 경우
						//$scope.currentPage = (response.data.currentPage);
						if(response.data.currentPage === 1){
							$scope.nextPage  = (response.data.currentPage) + 1;
							$scope.backPage  = 0;
						}else{
							$scope.nextPage  = (response.data.currentPage) + 1;
							$scope.backPage  = (response.data.currentPage) - 1;						
						}
					},
					function errorCallBack(response){
						$window.alert("Network Failure");
					}				
				)
			};
		})
		/**
		 * @Comment 게시물 작성
		 * @Controller writeCtrl
		 * @Service $scope, $window, $http
		 */
		.controller('writeCtrl', function($scope, $window, $http){
			var boardDt = $scope.boardDt;
			$scope.boardDt = {
				boardTitle : "",
				boardContent : "",
				boardInsertDate : "",
				boardModifyDate : ""
			}
			
			$scope.saveBoard = function(){
				$http.post("/rest/board/insert", $scope.boardDt)
				.then(
					function successCallBack(response){
						$window.location.href = "#/"
					},
					function errorCallBack(response){
						$window.alert("Failure");	
					}						
				
				)
				console.log(boardDt);
			};
		})
		/**
		 * @Comment 게시물 업데이트
		 * @Controller updateCtrl
		 * @Service $scope, $routeParams, $http, $window
		 */
		.controller('updateCtrl', function($scope, $routeParams, $http, $window){
			var boardDt = $scope.boardDt;
			$scope.boardNo = $routeParams.boardNo;
			$scope.boardDt = {};
			
			$http.get('/rest/board/detail/' + $scope.boardNo)
			.then(
				function successCallBack(response){
					$scope.boardDt = response.data;
				},
				function errorCallBack(response){
				}
			);
			
			
			$scope.updateBoardDt = function(boardNo){
				$http.post(	"/rest/board/update", this.boardDt)
				.then(
					function successCallBack(response){
						$window.location.href = "#/"
					},
					function errorCallBack(response){
						$window.alert("Failure");	
					}
				);
			}
		})
		/**
		 * @Comment 게시물 상세
		 * @Controller detailCtrl
		 * @Service $scope, $routeParams, $http, $window
		 */
		.controller('detailCtrl', function($scope, $routeParams, $http, $window){
			$scope.boardNo = $routeParams.boardNo;
			$scope.boardDt = {};
			
			$http.get('/rest/board/detail/' + $scope.boardNo)
			.then(
				function successCallBack(response){
					$scope.boardDt = response.data;
				},
				function errorCallBack(response){
				}
			);
			
			// 수정 이동
			$scope.boardUpdate = function(boardNo){
				$window.location.href = "#/update/" + boardNo;				
			}
			
			//삭제
			$scope.boardDelete = function(boardNo){
				if($window.confirm("delete?")){
					$http.delete('/rest/board/delete/' + $scope.boardNo)
					.then(
						function successCallBack(response){
							if(response.status !== 200){
								$window.alert("실패");
							}
							$window.location.href = "#/"								
							
						},
						function errorCallBack(response){
							console.log(response);
						}					
					);
				}
			}
		});
}());
