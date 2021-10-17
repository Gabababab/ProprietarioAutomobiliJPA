package it.prova.service.proprietario;

import java.util.List;

import it.prova.dao.proprietario.ProprietarioDAO;
import it.prova.model.Proprietario;



public interface ProprietarioService {

	public List<Proprietario> listAllProprietari() throws Exception;

	public Proprietario caricaSingoloProprietario(Long id) throws Exception;
	
	public Proprietario caricaSingoloProprietarioConAutomobili(Long id) throws Exception;

	public void aggiorna(Proprietario proprietarioInstance) throws Exception;

	public void inserisciNuovo(Proprietario proprietarioInstance) throws Exception;

	public void rimuovi(Proprietario proprietarioInstance) throws Exception;

	public int contaQuantiHannoAutomobiliImmatricolateDal2000() throws Exception;

	//per injection
	public void setProprietarioDAO(ProprietarioDAO proprietarioDAO);
}
