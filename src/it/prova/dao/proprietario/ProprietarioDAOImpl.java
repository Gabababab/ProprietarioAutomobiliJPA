package it.prova.dao.proprietario;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.prova.model.Proprietario;


public class ProprietarioDAOImpl implements ProprietarioDAO {

	private EntityManager entityManager;
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		// TODO Auto-generated method stub
		this.entityManager=entityManager;
		
	}

	@Override
	public List<Proprietario> list() throws Exception {

		return entityManager.createQuery("from Proprietario", Proprietario.class).getResultList();
	}

	@Override
	public Proprietario get(Long id) throws Exception {
		return entityManager.find(Proprietario.class, id);
	}

	@Override
	public void update(Proprietario proprietarioInstance) throws Exception {
		if (proprietarioInstance == null) {
			throw new Exception("Problema valore in input");
		}
		proprietarioInstance = entityManager.merge(proprietarioInstance);

	}

	@Override
	public void insert(Proprietario proprietarioInstance) throws Exception {
		if (proprietarioInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(proprietarioInstance);
	}

	@Override
	public void delete(Proprietario proprietarioInstance) throws Exception {
		
		if (proprietarioInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(proprietarioInstance));
	}

	@Override
	public int contaQuantiHannoAutomobiliImmatricolateDal2000() {
		Query q= entityManager
				.createQuery("SELECT COUNT (p.id) from Proprietario p left join fetch p.automobili where YEAR(p.automobili.annoImmatricolazione)>'2000'", Proprietario.class);
		int count = (int)q.getSingleResult();
		return count;
		
	}

	@Override
	public Proprietario getEagerAutomobili(Long id) throws Exception {
		
		TypedQuery<Proprietario> query = entityManager
				.createQuery("from Proprietario p left join fetch p.automobili where p.id = ?1", Proprietario.class);
		query.setParameter(1, id);

		return query.getResultStream().findFirst().orElse(null);
	}

}
