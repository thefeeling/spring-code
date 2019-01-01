/**
 * angular config
 * 
 * 
 * Constant : 공통으로 사용하는 환경값을 저장하는 경우, 사용함(상수를 지정)
 *            (사용자 정의 Directive에서 환경값을 가져다 쓰는 경우에 종종 쓴다고 함)           
 * 
 * directive : 지시자
 */
(function(){
	'use strict';
	angular
	.module('boardApp', 
			['ngCookies', 
			 'ngResource', 
			 'ngRoute', 
			 'ngSanitize'])
	.constant('boardList', [
		{
			boardNo    : 1,
			boardTitle : '타이틀입니다',
			boardContent : '컨텐츠입니다.',
			insertDate : new Date().toLocaleString()
		},
	])
	//Back Button
	.directive('backButton', ['$window', function($window) {
		return {
			restrict: 'A',
			link: function (scope, elem, attrs) {
				elem.bind('click', function () {
					window.history.back();
				});
			}
		};
	}]);
}());
