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
		return new smile.License(
		     	"SMILE LICENSE f4c4f6f3 abe0d681 5aecec1f " +
		     	"THIS IS AN ACADEMIC LICENSE AND CAN BE USED " +
		     	"SOLELY FOR ACADEMIC RESEARCH AND TEACHING, " +
		     	"AS DEFINED IN THE BAYESFUSION ACADEMIC " +
		     	"SOFTWARE LICENSING AGREEMENT. " +
		     	"Serial #: 8g0106hdbism9i2sxapeo99w0 " +
		     	"Issued for: RICCARDO CECCI (20023915@studenti.uniupo.it) " +
		     	"Academic institution: Universit\u00e0 del piemonte orientale " +
		     	"Valid until: 2022-08-02 " +
		     	"Issued by BayesFusion activation server",
		     	new byte[] {
		     	-63,87,-103,67,-12,-64,-78,111,-122,-108,-24,-62,-99,-71,-102,-96,
		     	27,-52,-114,-54,103,-83,-43,73,26,61,106,99,115,-14,56,-104,
		     	-33,-71,70,108,70,102,-57,46,-29,127,-123,36,-47,-92,0,-81,
		     	105,81,93,-70,-33,0,91,-51,112,37,-114,102,-37,-79,-115,67
		     	}
		     );
	}
}
