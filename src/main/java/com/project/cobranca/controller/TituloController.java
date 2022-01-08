package com.project.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.cobranca.model.StatusTitulo;
import com.project.cobranca.model.Titulo;
import com.project.cobranca.repository.filter.TituloFilter;
import com.project.cobranca.service.CadastroTituloService;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	@Autowired
	private CadastroTituloService tituloService;
	
	private static final String CADASTRO_VIEW = "CadastroTitulo"; 
	
	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") TituloFilter filtro) {
		ModelAndView view = new ModelAndView("PesquisaTitulos");
		
		view.addObject("titulos", tituloService.filtrar(filtro));	
		return view;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView view = new ModelAndView(CADASTRO_VIEW);
		
		view.addObject(new Titulo());
		return view;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {
		
		if(errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		tituloService.salvar(titulo);
		
		attributes.addFlashAttribute("mensagem", "Título salvo com sucesso");
	
		return "redirect:/titulos/novo";
	}
	
	//passando o codigo no @PathVariable("codigo") você pode receber um título ao invés de um Long
	//O jpa/hibernate entende que tem de fazer um findById e já converte no objeto Titulo
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Titulo titulo) {
		ModelAndView view = new ModelAndView(CADASTRO_VIEW);
		view.addObject(titulo);
		return view;
	}
	
	@RequestMapping(value="{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		tituloService.excluir(codigo);
		
		attributes.addFlashAttribute("mensagem", "Título excluído com sucesso!");
		return "redirect:/titulos";
	}
	
	@RequestMapping(value = "/{codigo}/receber", method = RequestMethod.PUT)
	public @ResponseBody String receber(@PathVariable Long codigo) {
		tituloService.receber(codigo);
		return "";
	}
	
	@ModelAttribute
	public List<StatusTitulo> statusTitulos(){
		return Arrays.asList(StatusTitulo.values());
	}
	
}
