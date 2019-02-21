package br.com.b2w.infraestructure.client;

import java.util.List;

import br.com.b2w.infraestructure.client.dto.PlanetSwapiResponse;

public interface SwapiService {

    PlanetSwapiResponse getPlanet(String url);

    List<PlanetSwapiResponse> getPlanetas();

}
