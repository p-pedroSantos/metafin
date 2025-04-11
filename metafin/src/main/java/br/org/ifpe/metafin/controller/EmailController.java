package br.org.ifpe.metafin.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;

import br.org.ifpe.metafin.model.Usuario;

public class EmailController {
	
	@Autowired 
	 private JavaMailSender mailSender;

	    //@RequestMapping(path = "/email-send", method = RequestMethod.GET)
		//@GetMapping("/email")
	    public void sendMail(Usuario usuario) throws MessagingException {
	            MimeMessage mail = mailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper( mail );
	            helper.setTo(usuario.getEmail());
	            helper.setSubject( "Senha Nova" );
	            helper.setText("<p>email test</p></br> ");
	            mailSender.send(mail);
	            System.out.println("email-enviado");
	       
	    }
	
}

