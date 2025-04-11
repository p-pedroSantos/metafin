package br.org.ifpe.metafin.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.org.ifpe.metafin.dao.UsuarioDAO;
import br.org.ifpe.metafin.model.Usuario;

@Controller
public class UsuarioController {	
	
	@Autowired
	private UsuarioDAO usuarioDAO;

	
	@PostMapping("/login")
	public String efetuarLogin(String email, String senha,  HttpSession session, RedirectAttributes ra) {
		
		Usuario usuarioLogado = this.usuarioDAO.findByEmailAndSenha(email, senha);
		session.setMaxInactiveInterval(120);
		if (usuarioLogado == null) {
			
			ra.addFlashAttribute("mensagem", "email ou senha invalidos");
			return "redirect:/";
			} else {
				session.setAttribute("usuarioLogado", usuarioLogado);
				return "redirect:/adm/home";
			}
		}
	
//	@GetMapping("/")
//	public String inicio() {
//		return "inicio";
//	}
		
	@GetMapping("/")
	public String login() {
		return "login";
	}
	
	@GetMapping("/cadastro")
	public String cadastro() {
		return "cadastro";
	}
	
	@PostMapping("/salvarusuario")
	public String salvarcadastro(Usuario usuario , String confirmarSenha) {
		this.usuarioDAO.save(usuario);
		return "redirect:/";
	}
	
	@GetMapping("/adm/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("/sair")
	public String sair(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/acessoNegado")
	public String acessoNegado() {
		return "redirect:/";
	}
	@GetMapping("/esqueceuSenha")
	public String esqueceuSenha() {
		return "esqueceusenha";
	}
	@GetMapping("/adm/mudarSenha")
	public String mudarSenha() {
		return "mudarsenha";
	}
	
	@PostMapping("/adm/mudarSenha")
	public String perfil(RedirectAttributes ra, HttpServletRequest request, String senha, String confirmasenha) {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		if(senha.equals(confirmasenha)) {
			usuario.setSenha(confirmasenha);
			this.usuarioDAO.save(usuario);
			ra.addFlashAttribute("mensagem", "senha alterada");
		}else {
			ra.addFlashAttribute("mensagem", "senhas diferentes");
			return "redirect:/adm/perfil";
		}
		return "redirect:/adm/perfil";
	}
	@GetMapping("/adm/perfil")
	public String perfil(HttpServletRequest request, Model model) {
		//Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		model.addAttribute("usuario", (Usuario) request.getSession().getAttribute("usuarioLogado"));
		return "perfil";
	}
	
	@Autowired 
	 private JavaMailSender mailSender;
	
	@PostMapping("/NovaSenha")
	public String enviarSenha(RedirectAttributes ra, String email) throws MessagingException {
		
		Usuario usuario = this.usuarioDAO.findByEmail(email);
		if(usuario!= null) {
			MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper( mail );
            helper.setTo(usuario.getEmail());
            helper.setSubject( "Senha do Metafin" );
            helper.setText("Sua senha e: "+usuario.getSenha());
            mailSender.send(mail);
            
			ra.addFlashAttribute("mensagem", "Senha enviada para o email");
			return "redirect:/adm/esqueceuSenha";
		}else {
			ra.addFlashAttribute("mensagem", "email nao encontrado");
			return "redirect:/adm/esqueceuSenha";
		}

	}


}
