package br.org.ifpe.metafin.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.ifpe.metafin.model.Itens;

public interface ItensDAO extends JpaRepository<Itens, Integer>{

}
