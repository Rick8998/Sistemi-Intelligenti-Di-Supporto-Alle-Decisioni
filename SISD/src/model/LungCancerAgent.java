package model;

import java.util.Scanner;

public class LungCancerAgent extends Agent {

	private boolean CTscan;
	private boolean Mtest;
	private boolean testCTscanDone;
	private boolean testMtestDone;
	
	public LungCancerAgent(Scanner scan) {
		super(scan);
		scan.nextLine();
		getNetwork().readFile("Problema1.xdsl");
		CTscan = false;
		Mtest = false;
		testCTscanDone = false;
		testMtestDone = false;
	}
	
	public void decisionCTscan() {		
		do {
			System.out.println("Vuoi effettuare una Tomografia Computerizzata (CT scan)? [s][n]");
			//printExpectedUtility("CT_Scan");
			System.out.println("> ");
			setCheckStr(getScanner().nextLine());
			if(!getCheckStr().equalsIgnoreCase("s") && !getCheckStr().equalsIgnoreCase("n")) System.out.println("ERROE: scegli un valore valido");
			else {
				if(getCheckStr().equalsIgnoreCase("s")) {
					getNetwork().setEvidence("CT_Scan", "Si");
					testCTscanDone = true;
				}
				else {
					getNetwork().setEvidence("CT_Scan", "No");
					getNetwork().setEvidence("Res_CT_Scan", "Non_Definito");
					testCTscanDone = false;
				}
			} 
		} while (!getCheckStr().equalsIgnoreCase("s")  && !getCheckStr().equalsIgnoreCase("n"));
		
		getNetwork().updateBeliefs();
	}
	
	public void resultTestCTscan() {
		if(testCTscanDone) {
			do {
				System.out.println("Qual è stato l'esito del test? Positivo o Negativo? [p][n]");
				System.out.println("> ");
				setCheckStr(getScanner().nextLine());
				if(!getCheckStr().equalsIgnoreCase("p") && !getCheckStr().equalsIgnoreCase("n")) System.out.println("ERROE: scegli un valore valido");
				else {
					if(getCheckStr().equalsIgnoreCase("p")) getNetwork().setEvidence("Res_CT_Scan", "Test_Positivo");
					else getNetwork().setEvidence("Res_CT_Scan", "Test_Negativo");
				}
			} while (!getCheckStr().equalsIgnoreCase("p") && !getCheckStr().equalsIgnoreCase("n"));
			
			getNetwork().updateBeliefs();
		}
	}
	
	public void decisionMtest() {
		do {
			System.out.println("Vuoi effettuare una Mediastinoscopia (MTest)? [s][n]");
			printExpectedUtility("M_Test");
			System.out.println("> ");
			setCheckStr(getScanner().nextLine());
			if(!getCheckStr().equalsIgnoreCase("s") && !getCheckStr().equalsIgnoreCase("n")) System.out.println("ERROE: scegli un valore valido");
			else {
				if(getCheckStr().equalsIgnoreCase("s")) {
					getNetwork().setEvidence("M_Test", "Si");
					testMtestDone = true;
				}
				else {
					getNetwork().setEvidence("M_Test", "No");
					getNetwork().setEvidence("Res_M_Test", "Non_Definito");
					testMtestDone = false;
				}
			} 
		} while (!getCheckStr().equalsIgnoreCase("s")  && !getCheckStr().equalsIgnoreCase("n"));
		
		getNetwork().updateBeliefs();
	}
	
	public void resultMTest() {
		if(testMtestDone) {
			do {
				System.out.println("Qual è stato l'esito del test? Positivo o Negativo? [p][n]");
				System.out.println("> ");
				setCheckStr(getScanner().nextLine());
				if(!getCheckStr().equalsIgnoreCase("p") && !getCheckStr().equalsIgnoreCase("n")) System.out.println("ERROE: scegli un valore valido");
				else {
					if(getCheckStr().equalsIgnoreCase("p")) getNetwork().setEvidence("Res_M_Test", "Test_Positivo");
					else getNetwork().setEvidence("Res_M_Test", "Test_Negativo");
				}
			} while (!getCheckStr().equalsIgnoreCase("p") && !getCheckStr().equalsIgnoreCase("n"));
				
			getNetwork().updateBeliefs();
			
		}
	}
	
	public void treatmentDecision() {
		
		//if(testCTscanDone && testMtestDone) {
			do {
				System.out.println("Ora devi scegliere obbligatoriamente un trattamento:\nQuale trattamento vuoi effettuare? Tracheotomia o Radiazioni? [t][r]");
				printExpectedUtility("Trattamento");
				System.out.println("> ");
				setCheckStr(getScanner().nextLine());
				if(!getCheckStr().equalsIgnoreCase("t") && !getCheckStr().equalsIgnoreCase("r")) System.out.println("ERROE: scegli un valore valido");
				else {
					if(getCheckStr().equalsIgnoreCase("t")) {
						getNetwork().setEvidence("Trattamento", "Tracheotomia");
					}
					else {
						getNetwork().setEvidence("Trattamento", "Radiazioni");
					}
				} 
			} while (!getCheckStr().equalsIgnoreCase("t")  && !getCheckStr().equalsIgnoreCase("r"));
			getNetwork().updateBeliefs();
		/*}else {
			System.out.println("******ATTENZIONE!!******\nPer poter scegliere un trattamento è necessario avere i risultati dei due test CTscan e Mtest");
			System.out.println("Ora verrai ridiretto all'esecuzione del test mancante");
			if(!testCTscanDone) {
				decisionCTscan();
				resultTestCTscan();
			}else if(!testMtestDone) {
				decisionMtest();
				resultMTest();
			}
			treatmentDecision();
		}*/
		
	}
	
	public void finalExpectedUtility() {
		double[] utility = getNetwork().getNodeValue("Aspettativa_di_vita");
		System.out.println("Aspettativa di vita : " + String.format("%.2f", utility[0]) + " anni di vita");
	}
	
}
