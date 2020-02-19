package practice;

import java.util.ArrayList;
import java.util.List;

import practice.models.Input;
import practice.models.Output;

public class Main {

	public static void main(String[] args) {

		Input dataIn = new Input(args[0]);
		Output dataOut = process(dataIn, new Output(), 0);
//		Output dataOut = new Output();
//		dataOut.setScore(10);
//		dataOut.setSelectedTypes(new int[]{10, 15, 20});
		dataOut.write(args[1]);
		
	}
	
	public static Output process (Input input, Output output, int pos) {
//		int goal = input.getGoal() - output.getScore();
//		int bestScore = output.getScore();
		Output bestOutput = new Output();
		
//		for (int i=pos; i<input.getNumChoices(); i++) {
//			Output out = process (input, output, pos+1);
//		}
		
		int numberOfTypes;
		int actualMaxForThisR;
		int maxSlices = 0;
        int actualMax;
        int[] inSlices = {};
        ArrayList<Integer> actualCombiForThisR = new ArrayList<>();
        ArrayList<Integer> actualCombi = new ArrayList<>();
        ArrayList<ArrayList<Integer>> combinationsForR = new ArrayList<>();
        String solution;
		
       
        //Data to variables conversion
        numberOfTypes = input.getNumChoices();
        maxSlices = input.getGoal();
        inSlices = input.getSlices();
		
		//Calculation
        actualMax = 0;

        for(int r=1;r<numberOfTypes;r++) {
            actualMaxForThisR = 0;
            actualCombiForThisR.clear();

            combinationsForR = printCombination(inSlices, numberOfTypes, r);

            for (ArrayList<Integer> eachCombi : combinationsForR) {
                if (sum(eachCombi) > actualMaxForThisR
                        && sum(eachCombi) <= maxSlices) {
                    actualMaxForThisR = sum(eachCombi);
                    actualCombiForThisR.clear();
                    actualCombiForThisR.addAll(eachCombi);
                }
            }
            //System.out.println("Best combi for this r(" + r +") : " + actualCombiForThisR);

            if(r==0){
                actualMax = actualMaxForThisR;
                actualCombi.addAll(actualCombiForThisR);
            }

            if(sum(actualCombiForThisR) > actualMax
                    && sum(actualCombiForThisR) <= maxSlices){
                actualMax = sum(actualCombiForThisR);
                actualCombi.clear();
                actualCombi.addAll(actualCombiForThisR);
            }

        }

        //Prepare data to save in file
        solution = String.valueOf(actualCombi.size()) + "\n";

        int[] outputElements = new int[actualCombi.size()]; 
               
        for(int i = 0; i< actualCombi.size(); i++){
            outputElements[i] = actualCombi.get(i);
        }
                
        //Mapping solution to output
        bestOutput.setScore(actualCombi.size());
        bestOutput.setSelectedTypes(outputElements);
		
        
		return bestOutput;
	}


/*  https://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
 */
 /** arr[]  ---> Input Array
     data[] ---> Temporary array to store current combination
     start & end ---> Staring and Ending indexes in arr[]
     index  ---> Current index in data[]
     r ---> Size of a combination to be printed */
 static void combinationUtil(int arr[], int n, int r, int index,
                             int data[], int i, ArrayList<ArrayList<Integer>> vectorOfVectors)
 {
     ArrayList<Integer> myVector = new ArrayList<>();

     // Current combination is ready to be printed, print it
     if (index == r)
     {
         for (int j=0; j<r; j++)
             myVector.add(data[j]);

         vectorOfVectors.add(myVector);

         return;
     }

     // When no more elements are there to put in data[]
     if (i >= n)
         return;

     // current is included, put next at next location
     data[index] = arr[i];
     combinationUtil(arr, n, r, index+1, data, i+1, vectorOfVectors);

     // current is excluded, replace it with next (Note that
     // i+1 is passed, but index is not changed)
     combinationUtil(arr, n, r, index, data, i+1,vectorOfVectors);
 }

 /** The main function that prints all combinations of size r
  in arr[] of size n. This function mainly uses combinationUtil() */
 static  ArrayList<ArrayList<Integer>> printCombination(int arr[], int n, int r)
 {
     ArrayList<ArrayList<Integer>> combinationsForR = new ArrayList<>();

     // A temporary array to store all combination one by one
     int data[]=new int[r];

     // Print all combination using temporary array 'data[]'
     combinationUtil(arr, n, r, 0, data, 0, combinationsForR);
     //System.out.println(combinationsForR); //

     return combinationsForR;
 }

 static int sum(List<Integer> list) {
     int sum = 0;

     if(!list.isEmpty()) {
         for (int i : list)
             sum = sum + i;
     }
     return sum;
 }


 }
