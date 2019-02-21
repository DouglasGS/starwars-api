package br.com.b2w;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import br.com.b2w.application.PlanetaService;
import br.com.b2w.application.impl.PlanetaServiceImpl;
import br.com.b2w.domain.Planeta;
import br.com.b2w.infraestructure.client.SwapiService;
import br.com.b2w.infraestructure.repository.PlanetaMySQLRepository;

public class PlanetaServiceTest {

    private static int QUANTIDADE_PLANETAS = 10;

    @InjectMocks
    PlanetaService service = new PlanetaServiceImpl();

    @Mock
    PlanetaMySQLRepository repository;

    @Mock
    SwapiService swapiservice;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(swapiservice.getPlanet(anyString())).thenReturn(PlanetaMock.getPlanetSwapi());
        when(swapiservice.getPlanetas()).thenReturn(PlanetaMock.getPlanetsSwapi(QUANTIDADE_PLANETAS));
        when(swapiservice.getPlanet("ERRO")).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        when(repository.save(any(Planeta.class))).thenReturn(PlanetaMock.getPlanetaRepository());
        when(repository.findAllByNomeContaining(anyString())).thenReturn(PlanetaMock.getPlanetasRepository(QUANTIDADE_PLANETAS));
        when(repository.findAll()).thenReturn(PlanetaMock.getPlanetasRepository(QUANTIDADE_PLANETAS));
        when(repository.findOne(anyInt())).thenReturn(PlanetaMock.getPlanetaRepository());
        when(repository.findOne(-1)).thenReturn(null);

    }

    @Test
    public void adicionarPlaneta() {

        final Planeta adicionarPlaneta = service.adicionarPlaneta("http://teste.teste/1");
        assertNotNull(adicionarPlaneta);
        assertEquals("teste", adicionarPlaneta.getNome());
    }

    @Test(expected = HttpClientErrorException.class)
    public void adicionarPlanetaComException() {

        try {
            service.adicionarPlaneta("ERRO");
            fail("era para ter ocorrido uma excessão");
        } catch (final HttpClientErrorException ex) {
            assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
            throw ex;
        }

    }

    @Test
    public void getPlanetasDaApiSwapi() {

        final List<Planeta> planetasDaApiSwapi = service.getPlanetasDaApiSwapi();
        assertEquals(QUANTIDADE_PLANETAS, planetasDaApiSwapi.size());
    }

    @Test
    public void getPlanetasDoBancoDeDados() {

        final List<Planeta> planetasDoBancoDeDados = service.getPlanetasDoBancoDeDados();
        assertEquals(QUANTIDADE_PLANETAS, planetasDoBancoDeDados.size());
    }

    @Test
    public void buscarPorNome() {
        final List<Planeta> planetasDoBancoDeDados = service.buscarPorNome("teste");
        assertEquals(QUANTIDADE_PLANETAS, planetasDoBancoDeDados.size());
    }

    @Test
    public void buscarPorId() {
        final Planeta planeta = service.buscarPorId(1);
        assertNotNull(planeta);
        assertEquals("teste1", planeta.getNome());
    }

    @Test(expected = HttpClientErrorException.class)
    public void buscarPorIdException() {

        try {

            service.buscarPorId(-1);
            fail("era para ter ocorrido uma excessão");
        } catch (final HttpClientErrorException ex) {
            assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
            throw ex;
        }

    }

    @Test
    public void removerPlaneta() {

        try {
            service.removerPlaneta(1);
        } catch (final HttpClientErrorException ex) {
            fail("ocorreu uma execessão");
        }

    }

    @Test(expected = HttpClientErrorException.class)
    public void removerPlanetaException() {

        try {
            service.removerPlaneta(-1);
            fail("era para ter ocorrido uma excessão");
        } catch (final HttpClientErrorException ex) {
            assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
            throw ex;
        }

    }

}
