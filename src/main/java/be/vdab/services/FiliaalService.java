package be.vdab.services;

import java.util.List;
import java.util.Optional;

import be.vdab.entities.Filiaal;
import be.vdab.valueobjects.PostcodeReeks;

public interface FiliaalService {
	void create(Filiaal filiaal);
	Optional<Filiaal> read(long id);
	void update(Filiaal filiaal);
	void delete(long id);
	List<Filiaal> findAll();
	long findAantalFilialen();
	List<Filiaal> findByPostcodeReeks(PostcodeReeks postcodeReeks);
}
