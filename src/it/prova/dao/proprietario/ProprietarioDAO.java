package it.prova.dao.proprietario;

import it.prova.dao.IBaseDAO;
import it.prova.model.Proprietario;

public interface ProprietarioDAO extends IBaseDAO<Proprietario>{
	
	public Proprietario getEagerAutomobili(Long id) throws Exception;
	
	public int contaQuantiHannoAutomobiliImmatricolateDal2000();
	
}
