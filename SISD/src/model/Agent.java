package model;

import java.util.Scanner;

import smile.Network;

public class Agent {

	private Network network;
	private Scanner scanner;
	private String checkStr;
	
	public Agent(Scanner sc) {
		network = new Network();
		this.scanner = sc;
	}

	public Network getNetwork() {
		return network;
	}

	public Scanner getScanner() {
		return scanner;
	}

	public String getCheckStr() {
		return checkStr;
	}

	public void setCheckStr(String checkStr) {
		this.checkStr = checkStr;
	}
	
	public void printExpectedUtility(String nodeId) {
		double[] utilities = getNetwork().getNodeValue(nodeId);
		for(int i = 0; i < utilities.length; i++) {
			System.out.println("Utilità attesa di "+ nodeId + ":" + getNetwork().getOutcomeId(nodeId, i) + " : " +  String.format("%.2f", utilities[i]));
		}
	}
	
	public void printProbability(String nodeId) {
		double[] utilities = getNetwork().getNodeValue(nodeId);
		for(int i = 0; i < utilities.length; i++) {
			System.out.println("Probabilità  " + getNetwork().getOutcomeId(nodeId, i) + " : " +  String.format("%.2f", utilities[i]));
		}
	}
	
}
