package it.prova.service;

import it.prova.dao.MyDaoFactory;
import it.prova.service.automobile.AutomobileService;
import it.prova.service.automobile.AutomobileServiceImpl;
import it.prova.service.proprietario.ProprietarioService;
import it.prova.service.proprietario.ProprietarioServiceImpl;

public class MyServiceFactory {

	// rendiamo le istanze restituite SINGLETON
	private static ProprietarioService proprietarioServiceInstance = null;
	private static AutomobileService automobileServiceInstance = null;

	public static ProprietarioService getProprietarioServiceInstance() {
		if (proprietarioServiceInstance == null) {
			proprietarioServiceInstance = new ProprietarioServiceImpl();
			proprietarioServiceInstance.setProprietarioDAO(MyDaoFactory.getProprietarioDAOInstance());
		}
		return proprietarioServiceInstance;
	}

	public static AutomobileService getAutomobileServiceInstance() {
		if (automobileServiceInstance == null) {
			automobileServiceInstance = new AutomobileServiceImpl();
			automobileServiceInstance.setAutomobileDAO(MyDaoFactory.getAutomobileDAOInstance());
		}
		return automobileServiceInstance;
	}

}
