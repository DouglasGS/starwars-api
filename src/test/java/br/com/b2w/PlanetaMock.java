package br.com.b2w;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.b2w.domain.Planeta;
import br.com.b2w.infraestructure.client.dto.PlanetSwapiResponse;

public class PlanetaMock {

    public static PlanetSwapiResponse getPlanetSwapi() {
        return getPlanetSwapi(1);
    }

    public static List<PlanetSwapiResponse> getPlanetsSwapi(final int quantidadePlanetas) {

        final List<PlanetSwapiResponse> results = new ArrayList<>();
        for (int i = 0; i < quantidadePlanetas; i++) {
            results.add(getPlanetSwapi(i));

        }

        return results;
    }

    public static PlanetSwapiResponse getPlanetSwapi(final int id) {
        final PlanetSwapiResponse response = new PlanetSwapiResponse();
        response.setClimate("quente");
        response.setName("teste" + id);
        response.setTerrain("arido");
        response.setFilms(Arrays.asList(new String[] {"um", "dois", "tres"}));
        return response;
    }

    public static List<Planeta> getPlanetasRepository(final int quantidade) {
        final List<Planeta> planetas = new ArrayList<>();
        for (int i = 0; i < quantidade; i++) {
            planetas.add(getPlanetaRepository(i));
        }
        return planetas;
    }

    public static Planeta getPlanetaRepository(final int id) {
        final Planeta planeta = new Planeta();
        planeta.setNome("teste" + id);
        planeta.setClima("quente");
        planeta.setId(1);
        planeta.setQuantidadeAparicoes(3);
        planeta.setTerreno("arido");
        return planeta;
    }

    public static Planeta getPlanetaRepository() {
        return getPlanetaRepository(1);
    }

}
