package br.org.ifpe.metafin.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.org.ifpe.metafin.dao.ItensDAO;
import br.org.ifpe.metafin.model.Home;
import br.org.ifpe.metafin.model.Itens;
import br.org.ifpe.metafin.model.Tipo;
import br.org.ifpe.metafin.model.Usuario;

@Controller
public class ItensController {
	
	@Autowired
	private ItensDAO itensDAO;

	@GetMapping("/historico")
	public String historico(Model model) {
		model.addAttribute("listarItens", itensDAO.findAll());
		return "historico";
	}
	
	@GetMapping("/venda")
	public String venda() {
		return "venda";
	}
	@GetMapping("/custo")
	public String custo() {
		return "custo";
	}
	
	@PostMapping("/cadastrarVenda")
	public String CadVenda(Itens item){
		item.setTipo(Tipo.Venda);
		itensDAO.save(item);
		return "historico";
	}
	@PostMapping("/cadastrarCusto")
	public String CadCusto(Itens item){
		item.setTipo(Tipo.Custo);
		item.setData(LocalDate.now());
		this.itensDAO.save(item);
		return "historico";
	}
	private String home(HttpServletRequest request) {
		Home home = new Home();
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		List<Itens> itens = usuario.getItens();
		List<Double> mesAtual = null;  
		for(Itens iten: itens) {
			home.setData(LocalDate.now());
			
			
//			if (iten.getData().getMonth().equals(LocalDate.now().getMonth())) {
//				mesAtual.add(iten.getPreco())
//			} else if (iten.getData().getMonth().equals(LocalDate.now().getMonthValue())
		}

		

	}
	
	
	

	
	
}
