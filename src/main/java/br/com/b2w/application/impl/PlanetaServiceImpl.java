package br.com.b2w.application.impl;

import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import br.com.b2w.application.PlanetaService;
import br.com.b2w.domain.Planeta;
import br.com.b2w.infraestructure.client.SwapiService;
import br.com.b2w.infraestructure.client.dto.PlanetSwapiResponse;
import br.com.b2w.infraestructure.repository.PlanetaMySQLRepository;

@Service
public class PlanetaServiceImpl implements PlanetaService {

    @Autowired
    private SwapiService swapiService;

    @Autowired
    private PlanetaMySQLRepository repository;

    @Override
    public Planeta adicionarPlaneta(final String url) {

        final PlanetSwapiResponse planet = swapiService.getPlanet(url);

        final Planeta planeta = construirPlaneta(planet);

        return repository.save(planeta);
    }

    @Override
    public List<Planeta> getPlanetasDaApiSwapi() {

        final List<PlanetSwapiResponse> planetas = swapiService.getPlanetas();
        final List<Planeta> responseList = new ArrayList<>();
        planetas.forEach(planet -> responseList.add(construirPlaneta(planet)));
        return responseList;
    }

    @Override
    public List<Planeta> getPlanetasDoBancoDeDados() {
        return repository.findAll();
    }

    @Override
    public List<Planeta> buscarPorNome(final String nome) {

        return repository.findAllByNomeContaining(nome);
    }

    @Override
    public Planeta buscarPorId(final Integer id) {

        final Planeta findOne = repository.findOne(id);
        if (isNull(findOne)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "planeta não encontrado");
        }

        return findOne;
    }

    @Override
    public void removerPlaneta(final Integer id) {
        final Planeta buscarPorId = buscarPorId(id);
        repository.delete(buscarPorId.getId());
    }

    private Planeta construirPlaneta(final PlanetSwapiResponse planet) {
        final Planeta planeta = new Planeta();
        planeta.setNome(planet.getName());
        planeta.setClima(planet.getClimate());
        planeta.setQuantidadeAparicoes(planet.getFilms().size());
        planeta.setTerreno(planet.getTerrain());
        return planeta;
    }

}
