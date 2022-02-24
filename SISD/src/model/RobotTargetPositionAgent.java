package model;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class RobotTargetPositionAgent extends Agent{
	
	public final int TIME_SLICE = 4;

	public RobotTargetPositionAgent(Scanner scan) {
		super(scan);
		System.out.println("Elaborando...");
		getNetwork().readFile("problema2 unrolled.xdsl");
		getNetwork().updateBeliefs();
	}
	
	/*public void robotStartingPosition(String pos) {
		getNetwork().setEvidence("RPos_0", pos);
		//il robot conosce la sua posizione con certezza
		getNetwork().setEvidence("SensorRPos_0", pos);
		System.out.println("Posizione iniziale robot: ["+getNetwork().getEvidenceId("RPos_0")+"]");
		getNetwork().updateBeliefs();
	}
	
	public void targetStartingPosition() {
		int randomNum = ThreadLocalRandom.current().nextInt(1, 4 + 1);
		getNetwork().setEvidence("TPos_0", ("R"+randomNum).toString());
		System.out.println("Posizione iniziale target: ["+getNetwork().getEvidenceId("TPos_0")+"]");
		//getNetwork().updateBeliefs();
	}*/
	
	public void robotPositionSensor(int timeSlice) {
		do {
			System.out.println("[Istante "+timeSlice+"] Qual è la posizione rilevata dal sensore del ROBOT?\n>Scrivi [1][2][3][4] per ogni rispettiva regione in cui si trova il ROBOT [R1][R2][R3][R4]");
			if(timeSlice==0) getScanner().nextLine();
			setCheckStr(getScanner().nextLine());
			System.out.println("Elaborando...");
			
			if(!getCheckStr().equalsIgnoreCase("1") && !getCheckStr().equalsIgnoreCase("2") && !getCheckStr().equalsIgnoreCase("3") &&!getCheckStr().equalsIgnoreCase("4")) System.out.println("ERROE: scegli un valore valido");
			
			else if(getCheckStr().equalsIgnoreCase("1")) {
				getNetwork().setEvidence("sensore_posizione_robot_"+timeSlice, "R1");
			} else if(getCheckStr().equalsIgnoreCase("2")) {
				getNetwork().setEvidence("sensore_posizione_robot_"+timeSlice, "R2");
			} else if(getCheckStr().equalsIgnoreCase("3")) {
				getNetwork().setEvidence("sensore_posizione_robot_"+timeSlice, "R3");
			} else {
				getNetwork().setEvidence("sensore_posizione_robot_"+timeSlice, "R4");
			}
		} while (!getCheckStr().equalsIgnoreCase("1") && !getCheckStr().equalsIgnoreCase("2") && !getCheckStr().equalsIgnoreCase("3") &&!getCheckStr().equalsIgnoreCase("4"));
		getNetwork().updateBeliefs();
	}
	
	public void targetPositionSensor(int timeSlice) {
		do {
			System.out.println("[Istante "+timeSlice+"] Qual è la posizione rilevata dal sensore del TARGET?\n>Scrivi [1][2][3][4] per ogni rispettiva regione in cui si trova il TARGET [R1][R2][R3][R4]");
			setCheckStr(getScanner().nextLine());
			System.out.println("Elaborando...");
			if(!getCheckStr().equalsIgnoreCase("1") && !getCheckStr().equalsIgnoreCase("2") && !getCheckStr().equalsIgnoreCase("3") &&!getCheckStr().equalsIgnoreCase("4")) System.out.println("ERROE: scegli un valore valido");
			
			else if(getCheckStr().equalsIgnoreCase("1")) {
				getNetwork().setEvidence("sensore_posizione_target_"+timeSlice, "R1");
			} else if(getCheckStr().equalsIgnoreCase("2")) {
				getNetwork().setEvidence("sensore_posizione_target_"+timeSlice, "R2");
			} else if(getCheckStr().equalsIgnoreCase("3")) {
				getNetwork().setEvidence("sensore_posizione_target_"+timeSlice, "R3");
			} else {
				getNetwork().setEvidence("sensore_posizione_target_"+timeSlice, "R4");
			}
		} while (!getCheckStr().equalsIgnoreCase("1") && !getCheckStr().equalsIgnoreCase("2") && !getCheckStr().equalsIgnoreCase("3") &&!getCheckStr().equalsIgnoreCase("4"));
		getNetwork().updateBeliefs();
	}
	
	public void spostamento(int timeSlice) {
		
		do {
		
			System.out.println("Che spostamento deve fare il robot? [L][R][S]");
			System.out.println(">Di seguito troverai le utilità dei diversi spostamenti");
			printExpectedUtility("Spostamento_"+timeSlice);
			finalExpectedUtility(timeSlice);
			setCheckStr(getScanner().nextLine());
			System.out.println("Elaborando...");
			if(!getCheckStr().equalsIgnoreCase("L") && !getCheckStr().equalsIgnoreCase("R") && !getCheckStr().equalsIgnoreCase("S")) System.out.println("ERROE: scegli un valore valido");
			
			else if(getCheckStr().equalsIgnoreCase("L")) {
				getNetwork().setEvidence("Spostamento_"+timeSlice, "L");
			}else if(getCheckStr().equalsIgnoreCase("R")) {
				getNetwork().setEvidence("Spostamento_"+timeSlice, "R");
			} else {
				getNetwork().setEvidence("Spostamento_"+timeSlice, "S");
			}
		} while (!getCheckStr().equalsIgnoreCase("L") && !getCheckStr().equalsIgnoreCase("R") && !getCheckStr().equalsIgnoreCase("S"));	
		getNetwork().updateBeliefs();
	}
	
	public void finalExpectedUtility(int timeSlice) {
		double[] utility = getNetwork().getNodeValue("Utilita_"+timeSlice);
		System.out.println("Utilità in base alla distanza dal target : " + String.format("%.2f", utility[0]));
		System.out.println("NB: Utilità MAX = [1] - Utilità MIN = [-2]");
	}
	
}
