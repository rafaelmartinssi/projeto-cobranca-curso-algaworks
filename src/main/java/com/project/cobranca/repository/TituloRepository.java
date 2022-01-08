package com.project.cobranca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.cobranca.model.Titulo;
import java.lang.String;
import java.util.List;

@Repository
public interface TituloRepository extends JpaRepository<Titulo, Long>{
	
	List<Titulo> findByDescricaoContaining(String descricao);

}
