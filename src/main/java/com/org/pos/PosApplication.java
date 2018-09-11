package com.org.pos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PosApplication.class, args);
	}
}


/////example mail sender

//MailSender mailSender=new MailSender();

///

//try {
//		mailSender.enviarCorreo("hotmail", "Bienvenido a Exchange!!!", email , null , "Bienvenido al selecto grupo de usuarios de exchange");
//}catch(Exception e) {
//	e.printStackTrace();
//}