package be.vdab.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import be.vdab.entities.Filiaal;
import be.vdab.entities.Werknemer;
import be.vdab.services.FiliaalService;
import be.vdab.valueobjects.Adres;

@RunWith(MockitoJUnitRunner.class)
public class FiliaalControllerTest {
	private Filiaal filiaal;
	private FiliaalController filiaalController;
	private List<Filiaal> filialen;
	@Mock
	private FiliaalService filiaalService;
	@Before
	public void before() {
		filialen = Collections.emptyList();
		when(filiaalService.findAll()).thenReturn(filialen);
		filiaalController = new FiliaalController(filiaalService);
		filiaal = new Filiaal("naam1", true, BigDecimal.ONE, LocalDate.now(),
				new Adres("straat1", "huisnr1", 1, "gemeente1"), new TreeSet<Werknemer>());
				when(filiaalService.read(1)).thenReturn(Optional.of(filiaal));
				when(filiaalService.read(666)).thenReturn(Optional.empty());
	}
	@Test
	public void findAllActiveertJuisteView() {
		assertEquals("filialen/filialen", filiaalController.findAll().getViewName());
	}
	@Test
	public void findAllMaaktRequestAttribuutFilialen() {
		assertSame(filialen, filiaalController.findAll().getModelMap().get("filialen"));
	}
	@Test
	public void readActiveertJuisteView() {
	assertEquals("filialen/filiaal", filiaalController.read(filiaal).getViewName());
	}
	@Test
	public void readMetBestaandeIDGeeftFiliaalTerug() {
	assertSame(filiaal,
	filiaalController.read(filiaal).getModelMap().get("filiaal"));
	}
}
