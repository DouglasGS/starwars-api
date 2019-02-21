package br.com.b2w.application;

import java.util.List;

import br.com.b2w.domain.Planeta;

public interface PlanetaService {

    Planeta adicionarPlaneta(String url);

    List<Planeta> getPlanetasDaApiSwapi();

    List<Planeta> getPlanetasDoBancoDeDados();

    List<Planeta> buscarPorNome(String nome);

    Planeta buscarPorId(Integer id);

    void removerPlaneta(Integer id);

}
