package com.project.cobranca.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cobranca.model.StatusTitulo;
import com.project.cobranca.model.Titulo;
import com.project.cobranca.repository.TituloRepository;
import com.project.cobranca.repository.filter.TituloFilter;

@Service
public class CadastroTituloService {
	
	@Autowired
	private TituloRepository tituloRepository;
	
	public void salvar(Titulo titulo) {
		tituloRepository.save(titulo);
	}
	
	public void excluir(Long codigo) {
		tituloRepository.deleteById(codigo);
	}
	
	@Transactional
	public void receber(Long codigo) {
		Titulo titulo = tituloRepository.findById(codigo).get();
		titulo.setStatus(StatusTitulo.RECEBIDO);
	}
	
	public List<Titulo> filtrar(TituloFilter filtro){
		List<Titulo> titulos = new ArrayList<>();
		
		if(filtro.getDescricao() != null) {
			titulos = tituloRepository.findByDescricaoContaining(filtro.getDescricao());
		}else {
			titulos = tituloRepository.findAll();
		}
		
		titulos.sort((a1,a2) -> a1.getCodigo().compareTo(a2.getCodigo()));
		
		return titulos;
	}
	
}
