package com.org.pos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.org.pos.config.StorageProperties;
import com.org.pos.services.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class PosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PosApplication.class, args);
	}
	
	@Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            //storageService.deleteAll();
            storageService.init();
        };
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