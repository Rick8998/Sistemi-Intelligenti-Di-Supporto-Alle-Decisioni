package model;

import java.util.Scanner;

import smile.License;

public class Main {
	public static void main(String[] args) {
		/*Validazione licenza e benvenuto*/
		licenseInit();
		System.out.println("+++------------------- | BENVENUTO |-------------------+++");
		
		boolean endProgram = false; //variabile usata per far terminare il programma
		
		do {
			//INIZIALIZZAZIONE VARIABILI
			Agent agent = null;
			Scanner scan = new Scanner(System.in);
			int choice = 0;
			
			do {
				System.out.println("Scegli l'agente da utilizzare:");
				System.out.println("- Digita [1] per l'agente per il trattamento del carcinoma al polmone\n- Digita [2] per l'agente che valuta la posizione del robot dato il target");
				System.out.print("> ");
				choice = scan.nextInt();
				if(choice!=1 && choice!=2)
					System.out.println("ERRORE: Input non corretto, inserisci un carattere valido.");
				else
					System.out.print("La tua scelta --> ");
					switch (choice) {
					case 1:
						System.out.println("Agente per il trattamento del carcinoma al polmone");
						agent = new LungCancerAgent(scan);
						break;
	
					case 2:
						System.out.println("Agente che valuta la posizione del robot dato il target\n");
						agent = new RobotTargetPositionAgent(scan);
						break;
						
					default:
						System.out.println("ERRORE: Scelta non valida");
						break;
				}
			}while(choice != 1 && choice != 2);
			/* -------------------- PROBLEMA 1 --------------------*/
			if(agent instanceof LungCancerAgent) {
				
				LungCancerAgent lungCancerAgent = ((LungCancerAgent) agent);
				
				lungCancerAgent.decisionCTscan();
				lungCancerAgent.resultTestCTscan();
				
				lungCancerAgent.decisionMtest();
				lungCancerAgent.resultMTest();
				
				lungCancerAgent.treatmentDecision();
				
				lungCancerAgent.finalExpectedUtility();
			}
			
			/* -------------------- PROBLEMA 2 --------------------*/
			if(agent instanceof RobotTargetPositionAgent) {				
				RobotTargetPositionAgent robotAgent = ((RobotTargetPositionAgent) agent);
				
				//rilevazione dei sensori + scelta del movimento da effettuare
				for(int i = 0; i < robotAgent.TIME_SLICE; i++) {
					
					robotAgent.robotPositionSensor(i);
					
					robotAgent.targetPositionSensor(i);
					
					//all'ultimo passo non devo più fare lo spostamento
					if(i < robotAgent.TIME_SLICE-1) {
						robotAgent.spostamento(i);
					}
				}
				
				robotAgent.finalExpectedUtility(robotAgent.TIME_SLICE-1);
			}
			
			
			/* ---------------------------------------- TERMINATORE PROGRAMMA ---------------------------------------- */
			System.out.println("\nDigita un qualunque carattere per continuare o [Q] per terminare ");
			System.out.print("> ");
			String quit = scan.nextLine();
			if(quit.equalsIgnoreCase("q")) {
				System.out.println("Programma terminato");
				endProgram = true;
			}
			
		} while (endProgram != true);
		
	}
	
	private static License licenseInit() {
		   "here goes your personal jsmile license"
	}
}
