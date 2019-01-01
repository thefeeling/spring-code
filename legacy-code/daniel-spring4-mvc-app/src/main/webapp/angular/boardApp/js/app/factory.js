/**
 * angular board factory
 * [module].factory 는 공통된 것을 function으로 이미 만들어 놓은 것을 사용할 때 사용한다.
 * 공통 모듈의 성격으로 사용함.(팩토리는 어떤 형태의 데이터타입이라도 리턴할 수 있다. 리턴된 오브젝트를 가지고 작업함)
 */
(function(){
	'use strict';
	angular
	.module('boardApp')
	.factory('reverseTxtFactory', function() {
		return {
			reverse : function(name){
				return name.split("").reverse().join("");
			}
		}
	});
}());
