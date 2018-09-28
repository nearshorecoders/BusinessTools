/**@author asr*/

var productos = (function() {

	var title = document.title;
	var favicon = "";
	var domain = window.location.pathname.replace("index", "");
	
	var currentWall;
	var pathArray = window.location.pathname;
	var stompClient = null;
	var stompClientChat = null;

	var wallType = {};		
	var intCurrenntWall = 1;
	
	var canvas = null;
	var chart1 = null;
	var mainDataSet;
	var i = 0;

	var initProperties = function() {
		events.slowNetworkDetection();	
	}
	
	var catchDom = function() {
		
	};
	
	var suscribeEvents = function() {
		
		$(".navbar-brand.pitch-logo").on("click", function(){ 
			events.loadMainPage();
        });
		
		$('.nav-link').on('click', function() {
			$("#navbarNavDropdown .nav-link").removeClass("current");
			$(".navbar-nav li").removeClass("active");
			$(this).parent().addClass('active');
			var navLinkDOM = $(this);
			var wall = navLinkDOM.attr('data-option');
			var wallint = navLinkDOM.attr('data-option-int');

			if(wallint == 0){
				wall = "dashboard";
			}
			
			if (wall != "null"){
				events.changeView(wall);
			}
		});
		
	};
	
	var events = {
			getAllProducts : function() {
				$.ajax({
				    url: "/getAllProducts",
				    type: "GET",
				    dataType : "json",
				    headers: { 
				        'Accept': 'application/json',
				        'Content-Type': 'application/json' 
				    },
				}).done(function( json ) {
					console.log("Getting main menu");
					console.log(json);
					
				}).fail(function( xhr, status, errorThrown ) {
					//console.log( "Sorry, there was a problem!" );
				    console.log( "Error: " + errorThrown );
				    console.log( "Status: " + status );
				    //console.dir( xhr );
				}).always(function( xhr, status ) {
				    //console.log( "The request is complete!" );
				});
				
			},
			createNewProduct : function() {
				
				unidadMedida="Pieza";
				prsentacionToSend="Caja";
				
				dataToSend={
					codigo : $("#inputCodigo").val(),
					descripcion : $("#inputDescripcion").val(),
					unidades : $("#inputCantidad").val(),
					precioCompra : $("#inputPrecioCompra").val(),
					precioVenta : $("#inputPrecioVenta").val(),
					cantidadMinima : $("#inputCantidadMinima").val(),
					cantidadAceptable : $("#inputCantidadAceptable").val(),
					unidadMedida : unidadMedida,
					presentacion : prsentacionToSend,
					idSucursal:1
				}
				$.ajax({
					
				    url: "/createProduct",
				    type: "POST",
				    dataType : "json",
				    data:JSON.stringify(dataToSend),
				    headers: { 
				        'Accept': 'application/json',
				        'Content-Type': 'application/json' 
				    },
				    
				}).done(function( json ) {
					console.log("creating product");
					console.log(json);
					
				}).fail(function( xhr, status, errorThrown ) {
					//console.log( "Sorry, there was a problem!" );
				    console.log( "Error: " + errorThrown );
				    console.log( "Status: " + status );
				    //console.dir( xhr );
				}).always(function( xhr, status ) {
				    //console.log( "The request is complete!" );
				});
				
			},
		loadMainPage : function() {
			events.changeView("index");
		},
		
		changeView : function (theView) {
			console.log("cambiando vista");
			currentView = theView;
			$("#btContent").load(pathArray + "/" + theView );
		},
		
		slowNetworkDetection : function() {
			// Add event listener offline to detect network loss.
			window.addEventListener("offline", function(e) {
			    showPopForOfflineConnection();
			});

			// Add event listener online to detect network recovery.
			window.addEventListener("online", function(e) {
			    hidePopAfterOnlineInternetConnection();
			});

			function hidePopAfterOnlineInternetConnection(){
				/*			
			    // Enable to text field input
			    $("#input-movie-name").prop('disabled', false);
			    // Enable the search button responsible for triggering the search activity
			    $("#search-button").prop('disabled', false);
			    // Hide the internet connection status pop up. This is shown only when connection if offline and hides itself after recovery.
			    $('#internet-connection-status-dialogue').trigger('close');*/
			}

			function showPopForOfflineConnection(){
				/*
			    // Disable the text field input
			    $("#input-movie-name").prop('disabled', true);
			    // Disable the search button responsible for triggering search activity.
			    $("#search-button").prop('disabled', true);
			    // Show the error with appropriate message title and description.
			    $(".main-error-message").html("Connection Error");
			    $(".main-error-resolution").html(" It seems that your Internet Connection if offline.Please verify and try again later.");
			    $(".extra-error-message").html("(This popup will automatically disappear once connection comes back to life)");
			    // Addition of extra design to improve user experience when connection goes off.
			    $('#internet-connection-status-dialogue').lightbox_me({
			        centered: true,
			        overlaySpeed:"slow",
			        closeClick:false,
			        onLoad: function() {
			        }
			    });*/
			}
		},
	
	}
	
	var initialize = function() {
		initProperties();
		suscribeEvents();
	};


	return {
		init : initialize,
		events: events
	}
	
})();

$(document).ready(function () {
	productos.init();
});

function navegacion(element){
	console.log("iniciando clientes-----------------------");
	productos.events.changeView(element.id);
}
