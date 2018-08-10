package be.vdab.services;

import java.util.List;
import java.util.Optional;

import be.vdab.entities.Filiaal;
import be.vdab.exceptions.FiliaalHeeftNogWerknemersException;
import be.vdab.repositories.FiliaalRepository;
import be.vdab.valueobjects.PostcodeReeks;

@ReadOnlyTransactionalService
public class DefaultFiliaalService implements FiliaalService {
	private FiliaalRepository filiaalRepository;

	DefaultFiliaalService(FiliaalRepository filiaalRepository) {
		this.filiaalRepository = filiaalRepository;
	}

	@Override
	@ModifyingTransactionalMethod
	public void create(Filiaal filiaal) {
		filiaalRepository.save(filiaal);
	}

	@Override
	public Optional<Filiaal> read(long id) {
		return filiaalRepository.findById(id);
	}

	@Override
	@ModifyingTransactionalMethod
	public void update(Filiaal filiaal) {
		filiaalRepository.save(filiaal);
	}

	@ModifyingTransactionalMethod
	@Override
	public void delete(long id) {
		Optional<Filiaal> optionalFiliaal = filiaalRepository.findById(id);
		if (optionalFiliaal.isPresent()) {
			if (!optionalFiliaal.get().getWerknemers().isEmpty()) {
				throw new FiliaalHeeftNogWerknemersException();
			}
		} else {
			filiaalRepository.deleteById(id);
		}

	}

	@Override
	public List<Filiaal> findAll() {
		return filiaalRepository.findAll();
	}

	@Override
	public long findAantalFilialen() {
		return filiaalRepository.count();
	}

	@Override
	public List<Filiaal> findByPostcodeReeks(PostcodeReeks postcodeReeks) {
		return filiaalRepository.findByAdresPostcodeBetweenOrderByNaam(postcodeReeks.getVanpostcode(),
				postcodeReeks.getTotpostcode());
	}

}
