package be.vdab.repositories;

import org.springframework.stereotype.Repository;

@Repository
public class DeprecatedJpaRepository {
	/*private EntityManager entityManager;

	@PersistenceContext
	void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void create(Filiaal filiaal) {
		entityManager.persist(filiaal);
	}

	@Override
	public Optional<Filiaal> read(long id) {
		return Optional.ofNullable(entityManager.find(Filiaal.class, id));
	}

	@Override
	public void update(Filiaal filiaal) {
		entityManager.merge(filiaal);
	}

	@Override
	public void delete(long id) {
		entityManager.remove(read(id));
	}

	@Override
	public List<Filiaal> findAll() {
		return entityManager.createNamedQuery("Filiaal.findAll", Filiaal.class).getResultList();
		// return entityManager.createQuery("select f from Filiaal f",
		// Filiaal.class).getResultList();
	}

	@Override
	public long findAantalFilialen() {
		return entityManager.createNamedQuery("Filiaal.findAantal", Number.class).getSingleResult().longValue();
		// return entityManager.createQuery("select count(f) from Filiaal f",
		// Number.class).getSingleResult().longValue();
	}

	@Override
	public List<Filiaal> findByPostcodeReeks(PostcodeReeks postcodeReeks) {
		return entityManager.createNamedQuery("Filiaal.findByPostcodeReeks", Filiaal.class)
				.setParameter("van", postcodeReeks.getVanpostcode()).setParameter("tot", postcodeReeks.getTotpostcode())
				.getResultList();
	}
*/
}
