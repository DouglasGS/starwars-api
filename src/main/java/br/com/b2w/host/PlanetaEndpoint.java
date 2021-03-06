package br.com.b2w.host;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.b2w.application.PlanetaService;
import br.com.b2w.domain.Planeta;
import br.com.b2w.host.dto.PlanetaResponse;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/planetas")
public class PlanetaEndpoint {

    @Autowired
    private PlanetaService planetaService;

    @PostMapping
    @ApiOperation(value = "Adiciona um novo planeta", response = PlanetaResponse.class)
    public ResponseEntity<PlanetaResponse> adicionarPlaneta(@RequestParam("url") final String url) {

        final Planeta planeta = planetaService.adicionarPlaneta(url);
        final PlanetaResponse construiResponse = construiResponse(planeta);
        return ResponseEntity.ok().body(construiResponse);
    }

    @GetMapping("/swapi")
    @ApiOperation(value = "Retorna os planetas da api swap.co", response = PlanetaResponse[].class)
    public ResponseEntity<List<PlanetaResponse>> getPlanetasDaApiSwapi() {
        final List<Planeta> planetasDaApiSwapi = planetaService.getPlanetasDaApiSwapi();

        final List<PlanetaResponse> responseList = getResponseList(planetasDaApiSwapi);
        return ResponseEntity.ok().body(responseList);
    }

    @GetMapping("/")
    @ApiOperation(value = "Retorna os planetas cadastrados no banco de dados.", response = PlanetaResponse[].class)
    public ResponseEntity<List<PlanetaResponse>> getPlanetasDoBancoDeDados() {

        final List<Planeta> planetasDoBancoDeDados = planetaService.getPlanetasDoBancoDeDados();
        final List<PlanetaResponse> responseList = getResponseList(planetasDoBancoDeDados);

        return ResponseEntity.ok().body(responseList);
    }

    @GetMapping("/nome/")
    @ApiOperation(value = "Busca os planetas por nome", response = PlanetaResponse[].class)
    public ResponseEntity<List<PlanetaResponse>> buscarPorNome(@RequestParam(name = "nome", defaultValue = "") final String nome) {

        final List<Planeta> buscarPorNome = planetaService.buscarPorNome(nome);
        final List<PlanetaResponse> responseList = getResponseList(buscarPorNome);

        return ResponseEntity.ok().body(responseList);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retorna um planeta pelo id cadastrado no banco de dados", response = PlanetaResponse.class)
    public ResponseEntity<PlanetaResponse> buscarPorId(@PathVariable("id") final Integer id) {
        final Planeta planeta = planetaService.buscarPorId(id);
        final PlanetaResponse response = construiResponse(planeta);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Remove um planeta cadastrado pelo id", response = Void.class)
    public ResponseEntity<Void> removerPlaneta(@PathVariable("id") final Integer id) {
        planetaService.removerPlaneta(id);
        return ResponseEntity.noContent().build();

    }

    private List<PlanetaResponse> getResponseList(final List<Planeta> planetasDaApiSwapi) {
        final List<PlanetaResponse> responseList = new ArrayList<>();
        planetasDaApiSwapi.forEach(p -> responseList.add(construiResponse(p)));
        return responseList;
    }

    public PlanetaResponse construiResponse(final Planeta planeta) {
        final PlanetaResponse response = new PlanetaResponse();
        response.setClima(planeta.getClima());
        response.setId(planeta.getId());
        response.setNome(planeta.getNome());
        response.setQuantidadeAparicoes(planeta.getQuantidadeAparicoes());
        response.setTerreno(planeta.getTerreno());
        response.setUrl(planeta.getUrl());
        return response;
    }

}
