package br.com.b2w.infraestructure.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.b2w.domain.Planeta;

public interface PlanetaMySQLRepository extends CrudRepository<Planeta, Integer> {

    List<Planeta> findAllByNomeContaining(String nome);

    @Override
    List<Planeta> findAll();

}
