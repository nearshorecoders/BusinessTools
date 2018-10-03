/**@author asr*/

var ventas = (function() {

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
		
		var timer = null;
		$('#inputCodigo').keydown(function(){
		       clearTimeout(timer); 
		       timer = setTimeout(doCallToGetProductByCode, 1000)
		});

		function doCallToGetProductByCode() {
		    console.log('get product by code executing');
		    events.getProductByCode();
		}
		
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
			addProductToSell : function() {
				console.log("producto agregado");
			},
			getProductByDescription : function() {
				console.log("buscando producto por descripcion");
				description=$("#inputDescripcion").val();
				$.ajax({
				    url: "/getProductByDescription/"+description,
				    type: "GET",
				}).done(function( json ) {
					console.log("Getting product by desc");
					console.log(json);
					$("#tableBusqueda").empty();
					var preTable=$('#tableBusquedaProductos').DataTable();
					preTable.destroy();
					var productsString="";
					for(i=0;i<json.listaProductosPorDescripcion.length;i++){
						currentProducto=json.listaProductosPorDescripcion[i];
						productsString=productsString + '<tr>'+
										                '<td>'+currentProducto.id+'</td>'+
														'<td>'+ currentProducto.descripcion+'</td>'+
										                '<td>'+currentProducto.unidadesEnCaja +'</td>'+
														'<td>'+currentProducto.precioVenta.toLocaleString('es-MX', { style: 'currency', currency: 'MXN' });+'</td>'+
										                '</tr>;'
					}	
					$("#tableBusqueda").append(productsString);
					 var table = $('#tableBusquedaProductos').DataTable({
				        'paging'      : true,
				        'lengthChange': false,
				        'searching'   : true,
				        'ordering'    : true,
				        'info'        : true,
				        'autoWidth'   : false
				    });
				    
				    $('#tableBusquedaProductos tbody').on( 'click', 'tr', function () {
						$row=$(this);
						$tds = $row.find("td");             // Finds all children <td> elements
						//clean the form
				    	$('#tableProductoAAgregar').html('');
						index=0;
						var productsString='<tr>';
						
						$.each($tds, function() {               // Visits every single <td> element
						    switch(index){
						    	case 0:
						    		productsString=productsString+'<td>' + $(this).text() + '</td>';
						    	break;	
						    	case 1:
						    		productsString=productsString+'<td>'+ $(this).text() + '</td>';
						    	break;	
						    	case 2:
						    		productsString=productsString+'<td id="avaliableQty">'+ $(this).text() + '</td>';
						    		productsString=productsString+'<td><input type="text" class="form-control" id="inputCantidadAgregar" placeholder="Introduce la cantidad que deseas agregar"></td>';
						    	break;	
						    }
						    index++;
						});
				    	
						
						productsString=productsString + '</tr>';    
		                  					
						
				    	$('#tableProductoAAgregar').html(productsString);
				    	
				    	
						var timerC = null;
						$('#inputCantidadAgregar').keydown(function(){
						       clearTimeout(timerC); 
						       timerC = setTimeout(doCallToValidate, 1000)
						});

						function doCallToValidate() {
							qtyAdd=parseFloat($('#inputCantidadAgregar').val());
							avaliableQty=parseFloat($('#avaliableQty').html());
						    if(qtyAdd>avaliableQty){
						    	console.log("NO se puede agregar no existen suficientes");
						    }else{
						    	console.log("Agregado sin problema")
						    }
						}
				    	
				    	
//				    	if ( $(this).hasClass('selected') ) {
//				            $(this).removeClass('selected');
//				        }
//				        else {
//				            table.$('tr.selected').removeClass('selected');
//				            $(this).addClass('selected');
//				        }
				    });
				 
//				    $('#button').click( function () {
//				        table.row('.selected').remove().draw( false );
//				    } );
				    
				}).fail(function( xhr, status, errorThrown ) {
					//console.log( "Sorry, there was a problem!" );
				    console.log( "Error: " + errorThrown );
				    console.log( "Status: " + status );
				    //console.dir( xhr );
				}).always(function( xhr, status ) {
				    //console.log( "The request is complete!" );
				});
				
			},
			getProductByCode : function() {

				codigo= $("#inputCodigo").val();
				
				$.ajax({
				    url: "/getProductByCode/"+codigo,
				    type: "GET",
				}).done(function( json ) {
					console.log("Get producto by code");
					console.log(json);
					var productsString="";
					for(i=0;i<json.listaProductosPorCodigo.length;i++){
						currentProducto=json.listaProductosPorCodigo[i];
						cantidadAgregar=$("#cantidadCodigo").val() != "" ? parseFloat($("#cantidadCodigo").val()) : 1.0;
						cantidadSubtotal=currentProducto.precioVenta*cantidadAgregar;
						
						productsString=productsString + '<tr>'+
										                '<td>'+currentProducto.id+'</td>'+
														'<td>'+ currentProducto.descripcion+'</td>'+
										                '<td>'+ cantidadAgregar +'</td>'+
														'<td>'+currentProducto.precioVenta.toLocaleString('es-MX', { style: 'currency', currency: 'MXN' });+'</td>'+
														'<td>'+cantidadSubtotal.toLocaleString('es-MX', { style: 'currency', currency: 'MXN' });+'</td>'+
														'<td><a idMod="'+fila.id+'" class="btn btn-block btn-primary" onclick="" type="button">Modificar</a></td>'+
														'<td><a idDel="'+fila.id+'" class="btn btn-block btn-danger" onclick="" type="button">Eliminar</a></td>'+
										                '</tr>;'
					}
					if(json.listaProductosPorCodigo.length>0){
						$("#tableSell").append(productsString);
					}else{
						///show notification not found product by code
					}
				}).fail(function( xhr, status, errorThrown ) {
					//console.log( "Sorry, there was a problem!" );
				    console.log( "Error: " + errorThrown );
				    console.log( "Status: " + status );
				    //console.dir( xhr );
				}).always(function( xhr, status ) {
				    //console.log( "The request is complete!" );
				});
				
			},
			listAllProducts : function() {
				$.ajax({
				    url: "/getAllProducts",
				    type: "GET",
				}).done(function( json ) {
					console.log("Get all products");
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
	ventas.init();
});

function navegacion(element){
	console.log("iniciando clientes-----------------------");
	ventas.events.changeView(element.id);
}
