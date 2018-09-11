package com.org.pos.services;

public class AccountServices {
//	package com.cis.exchange.router.handler;
//
//	import com.cis.exchange.ExchangeUtils;
//	import com.cis.exchange.config.security.UserDetailsAdapter;
//	import com.cis.exchange.domain.Cliente;
//	import com.cis.exchange.domain.ClientePin;
//	import com.cis.exchange.domain.request.json.ParamsLiteCoinRequest;
//	import com.cis.exchange.repository.CatalogosRepository;
//	import com.cis.exchange.repository.RegistroRepository;
//	import com.cis.exchange.utils.Encryption;
//	import com.cis.exchange.utils.HttpWebClient;
//	import com.cis.exchange.utils.MailSender;
//	import com.google.gson.Gson;
//	import com.google.gson.JsonObject;
//	import com.google.gson.JsonParser;
//
//	import org.apache.http.HttpResponse;
//	import org.apache.http.client.HttpClient;
//	import org.apache.http.client.methods.HttpPost;
//	import org.apache.http.entity.StringEntity;
//	import org.apache.http.impl.client.HttpClientBuilder;
//	import org.springframework.beans.factory.annotation.Autowired;
//	import org.springframework.beans.factory.annotation.Value;
//	import org.springframework.http.MediaType;
//	import org.springframework.stereotype.Component;
//	import org.springframework.util.MultiValueMap;
//	import org.springframework.web.reactive.function.server.ServerRequest;
//	import org.springframework.web.reactive.function.server.ServerResponse;
//	import reactor.core.publisher.Mono;
//
//	import java.io.BufferedReader;
//	import java.io.InputStreamReader;
//	import java.net.URL;
//	import java.security.SecureRandom;
//	import java.util.*;
//	import java.util.Base64.Encoder;
//	import java.util.stream.IntStream;
//
//	@Component
//	public class RegistroHandler {
//		@Autowired
//		private RegistroRepository registroRepository;
//		
//		@Autowired
//		private CatalogosRepository catRepository;
//		
//		@Autowired
//		HttpWebClient httpWebClientRequester;
//		
//		ExchangeUtils utils=new ExchangeUtils();
//		MailSender mailSender=new MailSender();
//		
//		@Value("${litcoin.backend.url}")
//		private String backendUrlLiteCoin;
//		
//		@Value("${ethereum.backend.url}")
//		private String backendUrlEthereum;
//		
//		@Value("${recover.pass.url}")
//		private String recoverPassUrl;
//		
//		@Value("${apiwallet.user}")
//		private String userapi;
//		
//		@Value("${apiwallet.password}")
//		private String passwordapi;
//		
//		public Mono<ServerResponse> nuevoRegistro(ServerRequest request) {
//			request.queryParams();
//			String nombre=request.queryParam("nombre").get();
//			String paterno=request.queryParam("paterno").get();
//			String materno=request.queryParam("materno").get();
//			String email=request.queryParam("email").get();
//			Integer sexo=Integer.parseInt(request.queryParam("sexo").get());
//			String dia=request.queryParam("dia").get();
//			String mes=request.queryParam("mes").get();
//			String anio=request.queryParam("anio").get();
//			String contrasenia=request.queryParam("contrasenia").get();
//			String telefono=request.queryParam("telefono").get();
//			String smsPin=request.queryParam("smsPin").get();
//			telefono=telefono.replaceAll("-", "");
//			//dd-MM-yyyy
//			String fecha=dia+"-"+mes+"-"+anio;
//			Date fechaDate=utils.getDateFromString(fecha);
//
//			contrasenia = Encryption.buildHashPassword(contrasenia);
//					
//			Cliente cliente=new Cliente(telefono,nombre,paterno,materno,fechaDate,sexo,email,contrasenia,new Date(),1,1,smsPin);
//			cliente.setNumeroCuenta(this.getNumCta(this.catRepository.getNumCtaSeq()));
//			Integer result=registroRepository.nuevoRegistro(cliente);
//			
//			if(result>0){
//				
//				try {
//						mailSender.enviarCorreo("hotmail", "Bienvenido a Exchange!!!", email , null , "Bienvenido al selecto grupo de usuarios de exchange");
//				}catch(Exception e) {
//					e.printStackTrace();
//				}
//				return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON)
//						.body(Mono.just("User created ok"), String.class).switchIfEmpty(ServerResponse.notFound().build());			
//			}else{
//				return ServerResponse.badRequest().contentType(MediaType.APPLICATION_STREAM_JSON)
//						.body(Mono.just("User NOT created"), String.class).switchIfEmpty(ServerResponse.notFound().build());
//			}
//			
//
//		}
//		
//		public Mono<ServerResponse> reenviarMailRegistro(ServerRequest request) {
//			request.queryParams();
//			String email=request.queryParam("email").get();
//			
//				try {
//						mailSender.enviarCorreo("hotmail", "Bienvenido a Exchange!!!", email , null , "Bienvenido al selecto grupo de usuarios de exchange");
//				}catch(Exception e) {
//					e.printStackTrace();
//				}
//				return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON)
//						.body(Mono.just("Resend Email ok"), String.class).switchIfEmpty(ServerResponse.notFound().build());			
//
//		}
//		
//		public Mono<ServerResponse> guardarRegistro(ServerRequest request) {
//			
//			Mono<String> result = request.principal().map(UserDetailsAdapter::mapClientDetail).flatMap((principal) ->
//			registroRepository.guardarRegistro(principal.getId()));
//			
//			return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON)
//					.body(Mono.just("ok"), String.class).switchIfEmpty(ServerResponse.notFound().build());
//		}
//		
//		public Mono<ServerResponse> obtenerPin(ServerRequest request) {
//			
//			String tel=request.queryParam("telefono").get().trim();
//			tel=tel.replace("-", "");
//			ClientePin clientePin=new ClientePin(tel,0);
//			List<Map<String, Object>> resultado=registroRepository.getPinPorUsuario(clientePin);
//			
//			if(resultado.isEmpty()) {
//				return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON)
//						.body(Mono.just("NO ok "), String.class).switchIfEmpty(ServerResponse.notFound().build());			
//			}else{
//				
//				Map<String,Object> mapResult=resultado.get(0);
//				String valorPin=(String)mapResult.get("pin");
//				
//				return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON)
//						.body(Mono.just(valorPin), String.class).switchIfEmpty(ServerResponse.notFound().build());
//			}
//			
//
//		}
//		
//		public Mono<ServerResponse> obtenerCorreo(ServerRequest request) {
//			
//			String correo=request.queryParam("correo").get();
//			List<Map<String, Object>> resultado=registroRepository.getClientePorCorreo(correo);
//			
//			if(resultado.isEmpty()) {
//				return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON)
//						.body(Mono.just("false"), String.class).switchIfEmpty(ServerResponse.notFound().build());			
//			}else{
//				
//				return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON)
//						.body(Mono.just("true"), String.class).switchIfEmpty(ServerResponse.notFound().build());
//			}
//			
//
//		}
//		
//		public Mono<ServerResponse> obtenerNumero(ServerRequest request) {
//			
//			String numero=request.queryParam("numero").get();
//			numero=numero.replaceAll("-", "");
//			List<Map<String, Object>> resultado=registroRepository.getClientePorNumero(numero);
//			
//			if(resultado.isEmpty()) {
//				return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON)
//						.body(Mono.just("false"), String.class).switchIfEmpty(ServerResponse.notFound().build());			
//			}else{
//				
//				return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON)
//						.body(Mono.just("true"), String.class).switchIfEmpty(ServerResponse.notFound().build());
//			}
//			
//		}
//		
//		public Mono<ServerResponse> requerirNuevoPassword(ServerRequest request) {
//			
//			String correo=request.queryParam("email").get();
//			String token=generateRandomToken();
//			Integer resultado=0;
//			List<Map<String, Object>> yaExiste=registroRepository.getEmailPorToken(correo,token);
//			if(yaExiste.size()>0) {
//				resultado=registroRepository.actualizarTokenPassword(correo,token, new Date());
//			}else {
//				resultado=registroRepository.guardarTokenPassword(correo,token, new Date());
//			}
//			
//			if(resultado>0) {
//				try {
//						
//						String url=recoverPassUrl+"/recover/verificarToken?token="+token+"&correo="+correo;
//						mailSender.enviarCorreo("hotmail", "Recuperar contraseña Exchange", correo , null , "Da click en el siguiente link para recuperar tu contraseña:\n"+url);
//				}catch(Exception e) {
//					e.printStackTrace();
//				}
//				return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON)
//						.body(Mono.just("true"), String.class).switchIfEmpty(ServerResponse.notFound().build());			
//			}else{
//				
//				return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON)
//						.body(Mono.just("false"), String.class).switchIfEmpty(ServerResponse.notFound().build());
//			}
//			
//		}
//		
//		public Mono<ServerResponse> guardarNuevoPassword(ServerRequest request) {
//			
//			String correo=request.queryParam("correo").get();
//			String token=request.queryParam("token").get();
//			String contrasena=request.queryParam("contrasena").get();
//			contrasena=Encryption.buildHashPassword(contrasena);
//			Integer resultado=registroRepository.updatePassword(correo,token, contrasena);
//			
//			if(resultado==1) {
//				
//				return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON)
//						.body(Mono.just("1"), String.class).switchIfEmpty(ServerResponse.notFound().build());			
//			}else if(resultado==2){
//				
//				return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON)
//						.body(Mono.just("2"), String.class).switchIfEmpty(ServerResponse.notFound().build());
//			}else {
//				return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON)
//						.body(Mono.just("0"), String.class).switchIfEmpty(ServerResponse.notFound().build());
//			}
//			
//		}
//		
//		public Mono<ServerResponse> validarToken(ServerRequest request) {
//			
//			String token=request.queryParam("token").get();
//			String correo=request.queryParam("correo").get();
//			
//			List<Map<String, Object>> resultado=registroRepository.getEmailPorToken(correo,token);
//			
//			if(resultado.isEmpty()) {
//				return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON)
//						.body(Mono.just("false"), String.class).switchIfEmpty(ServerResponse.notFound().build());		
//			}else{
//				return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON)
//						.body(Mono.just("true"), String.class).switchIfEmpty(ServerResponse.notFound().build());
//			}
//			
//		}
//		
//		
//		public String generateRandomToken() {
//			SecureRandom random = new SecureRandom();
//		    byte bytes[] = new byte[128];
//		    random.nextBytes(bytes);
//		    Encoder encoder = Base64.getUrlEncoder().withoutPadding();
//		    String token = encoder.encodeToString(bytes);
//		    return token;
//		}
//		
//		public Mono<ServerResponse> enviarMensajeRegistro(ServerRequest request){
//
//				enviarMensajeRegistroRequest(request);
//				
//				return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON)
//						.body(Mono.just("ok"), String.class).switchIfEmpty(ServerResponse.notFound().build());
//		}
//		
//		private String rpHash(String value) {
//			int hash = 5381;
//			value = value.toUpperCase();
//			for(int i = 0; i < value.length(); i++) {
//				hash = ((hash << 5) + hash) + value.charAt(i);
//			}
//			return String.valueOf(hash);
//		}
//		
//		public void enviarMensajeRegistroRequest(ServerRequest request) {
//		    try {
//				URL url = new URL("http://www.paychat-services.com/paychat-sms/secured/sms/sendSMS");
//				MultiValueMap<String,String> paramsGet=request.queryParams();
//				
//				HttpClient httpClient = HttpClientBuilder.create().build();
//				
//				String destinatario=paramsGet.get("numero").toString();
//				destinatario=destinatario.substring(1,destinatario.length()-1);
//				destinatario=destinatario.replaceAll("-", "");
//				//obtener el codigo de verificacion de exchange
//				//String mensaje=" Codigo de verificacion Exchange 12345";//+paramsGet.get("mensajes").toString();
//				//String mensaje=paramsGet.get("mensajes").toString();
//				//mensaje=mensaje.substring(1,mensaje.length()-1);
//				String mensaje=getRandomNumberInRange(1,9)+""+getRandomNumberInRange(1,9)+""+getRandomNumberInRange(1,9)+""+getRandomNumberInRange(1,9)+""+getRandomNumberInRange(1,9)+""+getRandomNumberInRange(1,9);
//				
//				String paramsRequest="[{\"numero\":\""+destinatario+"\",\"mensajes\":[\""+mensaje+"\"]}]";
//				String listParam="{\"list\":"+paramsRequest+"}";
//			    HttpPost requestPost = new HttpPost("http://www.paychat-services.com/paychat-sms/secured/sms/sendSMS");
//			    StringEntity params =new StringEntity(listParam);
//			    requestPost.addHeader("content-type", "application/json");
//			    requestPost.setEntity(params);
//			    HttpResponse response = httpClient.execute(requestPost);
//			    
//			    System.out.println("Response Code : " + response.getStatusLine().getStatusCode() +" envio de mensaje OK");
//		
//			    if(response.getStatusLine().getStatusCode()==200) {
//			    	//se envio correctamente el mensaje
//			    	//guardamos el pin generado y el numero de telefono asociado	
//			    	ClientePin clientePin=new ClientePin(destinatario,Integer.parseInt(mensaje));
//			    	
//			    	List<Map<String, Object>> resultado=registroRepository.getPinPorUsuario(clientePin);
//			    	if(resultado.size()>0) {
//			    		registroRepository.updatePinRegistro(clientePin);
//			    	}else {
//			    		registroRepository.guardarPinRegistro(clientePin);
//			    	}
//			    	
//			    }
//			    
//				BufferedReader rd = new BufferedReader(
//				        new InputStreamReader(response.getEntity().getContent()));
//		
//				StringBuffer result = new StringBuffer();
//				String line = "";
//				while ((line = rd.readLine()) != null) {
//					result.append(line);
//				}
//			    
//		    }catch(Exception ex) {
//		    	 System.out.println(ex.getMessage());
//		    }
//		}
//		
//		private static int getRandomNumberInRange(int min, int max) {
//
//			if (min >= max) {
//				throw new IllegalArgumentException("max must be greater than min");
//			}
//
//			Random r = new Random();
//			return r.nextInt((max - min) + 1) + min;
//		}
//		
//		
//		/**
//		 * Se calcula el num de cuanta y se valida usando el algoritmo Luhn
//		 * @param seq
//		 * @return
//		 */
//		private String getNumCta(Long seq) {
//			boolean isValid = false;
//			String numAcc = "";
//			while (!isValid) {
//				String typeDig = "45";
//				String random1 = getRandomNumberInRange(1, 9) + "" + getRandomNumberInRange(1, 9) + ""
//						+ getRandomNumberInRange(1, 9) + "" + getRandomNumberInRange(1, 9);
//				
//				//Se agregan ceros a la secuencia
//				String seqStr = "";
//				if(seq.toString().length() < 9) {
//					for(int i=0; i<(9-seq.toString().length());i++) {
//						seqStr += "0";
//					}
//				}
//				
//				seqStr += seq.toString();
//				
//				numAcc = typeDig + random1 + seqStr + getRandomNumberInRange(1, 9);
//				int[] numbers = new int[numAcc.length()];
//				for (int i = 0; i < numAcc.length(); i++) {
//					numbers[i] = numAcc.charAt(i) - '0';
//				}
//				isValid = IntStream.range(0, numbers.length)
//						.map(i -> (((i % 2) ^ (numbers.length % 2)) == 0) ? ((2 * numbers[i]) / 10 + (2 * numbers[i]) % 10)
//								: numbers[i]).sum() % 10 == 0;
//			}
//			return numAcc;
//		}
//		
//		public Mono<ServerResponse> createWalletType(ServerRequest request) {
//			
//			return request.principal().map(UserDetailsAdapter::mapClientDetail).flatMap((principal) -> createWalletTypePrincipal(principal.getId(), request));
//		}
//		
//		private Mono<ServerResponse> createWalletTypePrincipal(Long idUser, ServerRequest request) {
//			
//			Map<String, String> authorization = new HashMap<String, String>();
//			authorization.put("user", userapi);
//			authorization.put("pass", passwordapi);
//			//verificar que exista el usuario
//			List<Map<String, Object>> list_cliente = registroRepository.getClienteById(((Long) idUser).intValue());
//			
//			//verificar que exista la billetera activa
//			Map<String, Object> walletByCrypto = catRepository.getDivisaConBilleteraActiva(Integer.parseInt(request.queryParam("idCrypto").get()));
//			
//			if(!list_cliente.isEmpty() && !walletByCrypto.isEmpty()) {
//				
//				//verificar si existe la billetera del ciente con ese tipo de cripto
//				//list_cliente.get(0).get("num_cuenta").toString()
//				String wallet = registroRepository.getBilleteraByIdclientAndIdcrypto(list_cliente.get(0).get("num_cuenta").toString(), Integer.parseInt(request.queryParam("idCrypto").get()));
//				
//				if(wallet.equals("")) {
//					
//					ParamsLiteCoinRequest params=new ParamsLiteCoinRequest();
//					params.setNum_cuenta(list_cliente.get(0).get("num_cuenta").toString());
//					params.setNombreTipoCripto(walletByCrypto.get("nombre").toString());
//					params.setAddres("");
//					params.setTotalRetirar("0");
//					
//						if(walletByCrypto.get("nombre").toString().equals("Ethereum")) {
//							
//							Mono<String> ETHRest = httpWebClientRequester.makePostMonoRequestWithJsonObject(backendUrlEthereum,params, authorization);
//							return ServerResponse.ok().body(ETHRest.map(stringObject -> {
//								System.out.println("stringObject: "+ stringObject);
//								String billetera = "", account = "";
//								JsonObject gsonObject = new JsonObject();
//								try {
//									gsonObject = new JsonParser().parse(stringObject).getAsJsonObject();
//									if(gsonObject.has("collection")) {
//										gsonObject = gsonObject.get("collection").getAsJsonObject().get("EthereumWallet").getAsJsonObject();
//										billetera = gsonObject.get("wallet").getAsJsonObject().get("address").getAsString();
//										account = gsonObject.get("accountNumber").getAsString();
//										registroRepository.nuevaBilletera(account, billetera, Integer.valueOf(walletByCrypto.get("id").toString()));
//									}
//								} catch(Exception e) {
//									System.out.println(e.getMessage());
//								}
//								//Map<String, Object> objectMap = new Gson().fromJson(stringObject, Map.class);
//					            return billetera;
//							}), String.class);
//						}else {
//							Mono<String> LTCRest = httpWebClientRequester.makePostMonoRequestWithJsonObject(backendUrlLiteCoin, params, authorization);
//							
//							return ServerResponse.ok().body(
//									LTCRest.map( stringObject -> {
//										String billetera = "";
//										JsonObject gsonObject = new JsonObject();
//										System.out.println("stringObject: "+  stringObject);
//										try {
//											if(stringObject.startsWith("[")) {
//												gsonObject = new JsonParser().parse(stringObject).getAsJsonArray().get(0).getAsJsonObject();
//											}else {
//												gsonObject = new JsonParser().parse(stringObject).getAsJsonObject();
//											}
//											billetera = gsonObject.get("addres").getAsString().replaceAll("bitcoincash:", "");
//											registroRepository.nuevaBilletera(gsonObject.get("num_cuenta").getAsString(), billetera, Integer.valueOf(walletByCrypto.get("id").toString()));
//										} catch (Exception e) {
//											System.out.println(e.getMessage());
//										}
//										return billetera;
//									}), String.class).switchIfEmpty(ServerResponse.notFound().build());
//						}
//				}else {
//					return ServerResponse.ok().body(Mono.just(wallet), String.class).switchIfEmpty(ServerResponse.notFound().build());
//				}
//			}
//			return ServerResponse.ok().body(Mono.just("No existe el usuario ó es indenfinida la moneda"), String.class).switchIfEmpty(ServerResponse.notFound().build());
//		}
//	}

}
