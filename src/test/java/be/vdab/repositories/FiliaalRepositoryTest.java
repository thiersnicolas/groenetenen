package be.vdab.repositories;

import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.datasource.TestDataSourceConfig;
import be.vdab.entities.Filiaal;
import be.vdab.entities.Werknemer;
import be.vdab.valueobjects.Adres;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {TestDataSourceConfig.class, RepositoriesConfig.class})
@Transactional
public class FiliaalRepositoryTest {
	@Autowired
	private FiliaalRepository filiaalRepository;
	private List<Filiaal> filialen;
	
	@Before
	public void before() {
		filialen = Collections.emptyList();
	}
	
	@Test
	public void create() {
		Filiaal filiaal = new Filiaal("TestNaam", true, BigDecimal.ONE, LocalDate.now(), new Adres("Straat", "HuisNr", 1000, "Gemeente"), new TreeSet<Werknemer>());
		filiaalRepository.save(filiaal);
		assertNotEquals(0, filiaal.getId());
	}
	
	@Test
	public void findAllNotEmptyList(){
		assertNotEquals(filialen, filiaalRepository.findAll());
	}

}
