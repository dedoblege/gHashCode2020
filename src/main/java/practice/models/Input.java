package practice.models;

import tools.Tools;

public class Input {

	private int goal;
	private int numChoices;
	private int[] slices;
	
	public Input (String fileName){
		goal = 0;
		numChoices = 0;
		
		load("src/main/resources/" + fileName);
	}
	
	public void load (String fileName) {
		int[][] data = Tools.loadFile(fileName, 2);
		
		goal = data[0][0];
		numChoices = data [0][1];
		slices = data [1];
	}

	public int getGoal() {
		return goal;
	}

	public void setGoal(int goal) {
		this.goal = goal;
	}

	public int getNumChoices() {
		return numChoices;
	}

	public void setNumChoices(int numChoices) {
		this.numChoices = numChoices;
	}

	public int[] getSlices() {
		return slices;
	}

	public void setSlices(int[] slices) {
		this.slices = slices;
	}
	
	
}
