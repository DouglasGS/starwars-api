package br.com.b2w.infraestructure.client.impl;

import static java.util.Objects.nonNull;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.b2w.infraestructure.client.SwapiService;
import br.com.b2w.infraestructure.client.dto.PlanetSwapiResponse;
import br.com.b2w.infraestructure.client.dto.PlanetSwapiResponseList;

@Service
public class SwapiServiceImpl implements SwapiService {

    public static final String URL_BASE = "https://swapi.co/api/planets/";

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public PlanetSwapiResponse getPlanet(final String url) {

        final HttpEntity<String> entity = getHeaders();

        final URI uri = URI.create(url);

        try {
            final ResponseEntity<PlanetSwapiResponse> exchange =
                            restTemplate.exchange(uri, HttpMethod.GET, entity, PlanetSwapiResponse.class);
            if (!exchange.getStatusCode().equals(HttpStatus.OK)) {
                throw new HttpClientErrorException(exchange.getStatusCode(), "Erro ao obter um planeta");
            }
            return exchange.getBody();
        } catch (final HttpClientErrorException ex) {
            throw new HttpClientErrorException(ex.getStatusCode(), "Erro ao obter um planeta");
        }

    }

    @Override
    public List<PlanetSwapiResponse> getPlanetas() {
        String pagina = URL_BASE;
        final List<PlanetSwapiResponse> planetas = new ArrayList<>();
        do {
            final PlanetSwapiResponseList planets = getPlanets(pagina);
            if (nonNull(planets) && nonNull(planets.getResults())) {
                planets.getResults().stream().forEach(p -> planetas.add(p));
                pagina = planets.getNext();
            } else {
                pagina = null;
            }

        } while (nonNull(pagina));
        return planetas;
    }

    private PlanetSwapiResponseList getPlanets(final String path) {

        try {

            final HttpEntity<String> entity = getHeaders();

            final URI url = URI.create(path);

            final ResponseEntity<PlanetSwapiResponseList> exchange =
                            restTemplate.exchange(url, HttpMethod.GET, entity, PlanetSwapiResponseList.class);

            if (!exchange.getStatusCode().equals(HttpStatus.OK)) {
                throw new HttpClientErrorException(exchange.getStatusCode(), "Erro ao obter um planeta");
            }
            return exchange.getBody();
        } catch (final HttpClientErrorException ex) {
            throw new HttpClientErrorException(ex.getStatusCode(), "Erro ao obter a lista de planetas");
        }
    }

    private HttpEntity<String> getHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("User-Agent", "douglas");
        return new HttpEntity<>("parameters", headers);
    }

}
