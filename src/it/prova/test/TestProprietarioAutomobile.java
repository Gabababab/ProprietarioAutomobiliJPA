package it.prova.test;

import java.util.Date;
import java.util.List;

import it.prova.model.Automobile;
import it.prova.model.Proprietario;
import it.prova.service.MyServiceFactory;
import it.prova.service.automobile.AutomobileService;
import it.prova.service.proprietario.ProprietarioService;

public class TestProprietarioAutomobile {

	public static void main(String[] args) {

		ProprietarioService proprietarioService = MyServiceFactory.getProprietarioServiceInstance();
		AutomobileService automobileService = MyServiceFactory.getAutomobileServiceInstance();

		try {

			System.out.println("In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size()
					+ " elementi.");
			System.out.println(
					"In tabella Automobile ci sono " + automobileService.listAllAutomobili().size() + " elementi.");

			testInserisciProprietario(proprietarioService);
			System.out.println("In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size()
					+ " elementi.");

			testInserisciAutomobile(automobileService, proprietarioService);
			System.out.println(
					"In tabella Automobile ci sono " + automobileService.listAllAutomobili().size() + " elementi.");

			testRimozioneAuto(automobileService, proprietarioService);
			System.out.println(
					"In tabella Automobile ci sono " + automobileService.listAllAutomobili().size() + " elementi.");
			
//			testContaQuantiHannoAutomobiliImmatricolateDal2000(automobileService, proprietarioService);
//			System.out.println("In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size()
//					+ " elementi.");
			
			testListAllAutoConCFProprietarioCheIniziaCon(automobileService, proprietarioService);
			System.out.println(
					"In tabella Automobile ci sono " + automobileService.listAllAutomobili().size() + " elementi.");
			

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static void testInserisciProprietario(ProprietarioService proprietarioService) throws Exception {
		System.out.println(".......testInserisciMunicipio inizio.............");

		Proprietario nuovoProprietario = new Proprietario("Mario", "Rossi", "CJDHK", new Date());
		if (nuovoProprietario.getId() != null)
			throw new RuntimeException("testInserisciProprietario fallito: record già presente ");

		// salvo
		proprietarioService.inserisciNuovo(nuovoProprietario);
		// da questa riga in poi il record, se correttamente inserito, ha un nuovo id
		// (NOVITA' RISPETTO AL PASSATO!!!)
		if (nuovoProprietario.getId() == null)
			throw new RuntimeException("testInserisciProprietario fallito ");

		System.out.println(".......testInserisciProprietario fine: .............");
	}

	private static void testInserisciAutomobile(AutomobileService automobileService,
			ProprietarioService proprietarioService) throws Exception {
		System.out.println(".......testInserisciAutomobile inizio.............");

		List<Proprietario> listaProprietarioPresenti = proprietarioService.listAllProprietari();
		if (listaProprietarioPresenti.isEmpty())
			throw new RuntimeException("testInserisciAutomobile fallito: non ci sono proprietari a cui collegarci ");

		Automobile nuovaAuto = new Automobile("Skoda", "Octavia", "HJFKD89", new Date());
		if (nuovaAuto.getId() != null)
			throw new RuntimeException("testInserisciAutomobile fallito: record già presente ");
		nuovaAuto.setProprietario(listaProprietarioPresenti.get(0));

		// salvo
		automobileService.inserisciNuovo(nuovaAuto);
		// da questa riga in poi il record, se correttamente inserito, ha un nuovo id
		if (nuovaAuto.getId() == null)
			throw new RuntimeException("testInserisciAutomobile fallito ");

		if (nuovaAuto.getProprietario() == null)
			throw new RuntimeException("testInserisciAutomobile fallito ");

		System.out.println(".......testInserisciAutomobile: PASSATO.............");
	}

	public static void testRimozioneAuto(AutomobileService automobileService, ProprietarioService proprietarioService)
			throws Exception {

		System.out.println(".......testRimozioneAuto inizio.............");

		// inserisco un abitante che rimuoverò
		// creo nuovo abitante ma prima mi serve un municipio
		List<Proprietario> listaProprietarioPresenti = proprietarioService.listAllProprietari();
		if (listaProprietarioPresenti.isEmpty())
			throw new RuntimeException("testRimozioneAuto fallito: non ci sono proprietari a cui collegarci ");

		Automobile nuovaAuto = new Automobile("Ford", "Fiesta", "KAJOSL34", new Date());
		if (nuovaAuto.getId() != null)
			throw new RuntimeException("testInserisciAutomobile fallito: record già presente ");
		nuovaAuto.setProprietario(listaProprietarioPresenti.get(0));

		automobileService.inserisciNuovo(nuovaAuto);

		Long idAutoInserito = nuovaAuto.getId();
		automobileService.rimuovi(automobileService.caricaSingolaAuto(idAutoInserito));
		// proviamo a vedere se è stato rimosso
		if (automobileService.caricaSingolaAuto(idAutoInserito) != null)
			throw new RuntimeException("testRimozioneAuto fallito: record non cancellato ");

		System.out.println(".......testRimozioneAuto fine: PASSED.............");
	}

	public static void testContaQuantiHannoAutomobiliImmatricolateDal2000(AutomobileService automobileService, ProprietarioService proprietarioService) throws Exception {
		System.out.println(".......testContaQuantiHannoAutomobiliImmatricolateDal2000 inizio.............");
		
		int contatore=proprietarioService.contaQuantiHannoAutomobiliImmatricolateDal2000();
		if(contatore==0)
			throw new RuntimeException("testContaQuantiHannoAutomobiliImmatricolateDal2000 fallito ");
		
		System.out.println("ContaQuantiHannoAutomobiliImmatricolateDal2000:"+contatore);
		
		System.out.println(".......testContaQuantiHannoAutomobiliImmatricolateDal2000 fine: PASSED.............");
		
	}
	
	public static void testListAllAutoConCFProprietarioCheIniziaCon(AutomobileService automobileService, ProprietarioService proprietarioService) throws Exception{
		System.out.println(".......testListAllAutoConCFProprietarioCheIniziaCon inizio.............");
		
		Proprietario proprietarioRicercaConCf=new Proprietario("Mario","Rossi", "MRRSS89", new Date());
		proprietarioService.inserisciNuovo(proprietarioRicercaConCf);
		
		Automobile automobileRicercaConCf1=new Automobile("Kia", "Sport", "jsdhf78", new Date());
		Automobile automobileRicercaConCf2=new Automobile("Peugeot", "Sports", "psodk23", new Date());
		automobileRicercaConCf1.setProprietario(proprietarioRicercaConCf);
		automobileRicercaConCf2.setProprietario(proprietarioRicercaConCf);
		automobileService.inserisciNuovo(automobileRicercaConCf1);
		automobileService.inserisciNuovo(automobileRicercaConCf2);
		List<Automobile> listaRisultatiRicercaConCf=automobileService.listaAutoConCFProprietarioCheIniziaCon("MR");
		
		if(listaRisultatiRicercaConCf.size()!=2)
			throw new RuntimeException("testListAllAutoConCFProprietarioCheIniziaCon fallito: numero record inesatto ");
		
		for(Automobile item:listaRisultatiRicercaConCf) {
			automobileService.rimuovi(item);
		}
		
		System.out.println(".......testListAllAutoConCFProprietarioCheIniziaCon fine: PASSED.............");
	}
}
