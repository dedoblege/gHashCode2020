package practice.models;

import tools.Tools;

public class Output {

	private int score;
	private int[] selectedTypes;
	
	public Output() {
		this.score = 0;
		this.selectedTypes = new int[0];
	}
	
	public void write (String fileName) {
		int [][] data = new int[2][];
		data[0] = new int[] {selectedTypes.length};
		data[1] = selectedTypes;
		
		Tools.writeToFile("src/main/resources/" + fileName, data);
	}

	public int[] getSelectedTypes() {
		return selectedTypes;
	}

	public void setSelectedTypes(int[] selectedTypes) {
		this.selectedTypes = selectedTypes;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
}
