/**
 * Gestion de l'inventaire 
 * 
 */


 	//Initialisation d'une application AngulaJS
 	console.log("Initialisation du l'app inventaireApp");
 	//1er parametre : l'id de l'app
 	//2eme : la liste des dependances
 	var inventaireApp = angular.module("inventaireApp",[])
 	//Ajouter un controleur de gestion des medias
 	// un controleur est une fonction JS
 	var mediaCtrl = inventaireApp.controller("mediaCtrl", function($scope,$http){
 		console.log("Initialisation du Ctrl mediaCtrl");
 		// $scope.medias = [
 		//					{'identifiant':1,'titre':'Maven','auteur':'Orsys'},
		//	 				{'identifiant':2,'titre':'AngularJS','auteur':'Orsys'}
 		//1				];
 		listerMedias($scope, $http);	
 		//init form 
 		$scope.media = {'titre':'','auteur':'Orsys'};
 		//event ajout
 		$scope.ajouter = function(){
 			console.log("ajouter" + $scope.media.titre);
 			ajouterMedia($http, $scope.media);
 			listerMedias($scope, $http);	
 		}
 		
 		//event suppression
 		$scope.supprimer = function(id){
 			console.log("supprimer media avec" + id);
 			supprimerMedia($scope,$http, id); 
 		}
 		
 	})
 	
 	function ajouterMedia($http,media){
 			//Appel du web service rest : mediars
 	 		$http({
 	 			  method: 'PUT',
 	 			  url: 'rest/mediars',
 	 			  data: media
 	 			}).then(function success(reponse) {
 	 			   	console.log("success : ");//+ JSON.stringify(reponse));
 	 			  }, function error(reponse) {
 	 				 console.log("error : "+  JSON.stringify(reponse));
 	 			});
 	}
 	
 	
 	function supprimerMedia($scope,$http,id){
			//Appel du web service rest : mediars
 			console.log("supprimer id : "+id);
	 		$http({
	 			  method: 'DELETE',
	 			  url: 'rest/mediars/'+id
	 			}).then(function success(reponse) {
	 			   	console.log("success delete : ");//+ JSON.stringify(reponse));
	 			    listerMedias($scope, $http);	 			   	
	 			  }, function error(reponse) {
	 				 console.log("error : "+  JSON.stringify(reponse));
	 			});
	}
 	
 	/**
 	 * 
 	 * @param $scope
 	 * @param $http
 	 * @returns
 	 */
 	function listerMedias($scope,$http){
 		console.log("lister les medias");
 		$http({
			  method: 'GET',
			  url: 'rest/mediars'
			}).then(function success(reponse) {
			   	console.log("success : ");//+ JSON.stringify(reponse));
			   	$scope.medias = reponse.data;
			  }, function error(reponse) {
				 console.log("error : "+  JSON.stringify(reponse));
			  });
 	}
