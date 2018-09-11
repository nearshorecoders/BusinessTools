package com.org.pos.utils;

import org.apache.commons.fileupload.FileItem;
import com.sun.mail.smtp.SMTPTransport;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

public class MailSender{

	public void enviarCorreo(String tipoServidor,String asunto, String destinatarios, List<FileItem> adjuntos, String contenido) throws Exception {
		if(tipoServidor==null){
			tipoServidor="smtp";
		}
		//objeto donde almacenamos la configuración para conectarnos al servidor
        Properties properties = new Properties();
        //cargamos el archivo de configuracion
        properties.load(new MailSender().getClass().getResourceAsStream("/com/cis/exchange/utils/mail/"+tipoServidor+".properties"));
        //creamos una sesión
        Session session = Session.getInstance(properties, null);
		//creamos el mensaje a enviar
        Message mensaje = new MimeMessage(session);
        //agregamos la dirección que envía el email
        mensaje.setFrom(new InternetAddress(properties.getProperty("mail.from")));
        List<InternetAddress> emailsDestino = new ArrayList<InternetAddress>();
        int i = 0;
        StringTokenizer emailsSt = new StringTokenizer(destinatarios,";,");
        while (emailsSt.hasMoreTokens()) {
        	String email=emailsSt.nextToken();
        	try{
        		//agregamos las direcciones de email que reciben el email, en el primer parametro envíamos el tipo de receptor
        		mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
        		//Message.RecipientType.TO;  para
        		//Message.RecipientType.CC;  con copia
        		//Message.RecipientType.BCC; con copia oculta
        	}catch(Exception ex){
        		//en caso que el email esté mal formado lanzará una exception y la ignoramos
        	}
        }
        mensaje.setSubject(asunto);
        //agregamos una fecha al email
        mensaje.setSentDate(new Date(1,1,1));
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(contenido);
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        if (adjuntos != null) {
            for (FileItem adjunto : adjuntos) {
            	//agregamos los adjuntos
                messageBodyPart = new MimeBodyPart();
                DataSource source = new ByteArrayDataSource(adjunto.getInputStream(), adjunto.getContentType());
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(adjunto.getName());
                multipart.addBodyPart(messageBodyPart);
            }
        }
        mensaje.setContent(multipart);
        SMTPTransport transport = (SMTPTransport) session.getTransport("smtp");
        try {
        	//conectar al servidor
            transport.connect(properties.getProperty("mail.user"), properties.getProperty("mail.password"));
            //enviar el mensaje
            transport.sendMessage(mensaje, mensaje.getAllRecipients());
        } finally {
        	//cerrar la conexión
            transport.close();
        }
    }
}