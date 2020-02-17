package com.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class sliceCalculator {
    public static void main(String [] args) throws IOException {

        //Initialisation
        String inputPath = "C:\\Users\\dedob\\Documents\\GoogleHashCode\\gHashCode2020\\pizzaExampleStatement\\a_example.txt";
        String outputPath = "C:\\Users\\dedob\\Documents\\GoogleHashCode\\gHashCode2020\\pizzaExampleStatement\\a_solution.txt";
        int maxSlices = 0;
        int numberOfTypes = 0;
        int actualMaxForThisR;
        int actualMax;
        int[] inSlices = {};
        ArrayList<Integer> actualCombiForThisR = new ArrayList<>();
        ArrayList<Integer> actualCombi = new ArrayList<>();
        ArrayList<ArrayList<Integer>> combinationsForR = new ArrayList<>();
        String solution;

        //Reading file
        String[] myFile = readMyFile(inputPath);

        //Data to variables conversion
        String[] line0 = myFile[0].split(" ");
        maxSlices = Integer.valueOf(line0[0]);
        numberOfTypes = Integer.valueOf(line0[1]);

        String[] line1 = myFile[1].split(" ");

        inSlices = new int[line1.length];
        for(int i = 0; i<line1.length;i++){
            inSlices[i] = Integer.parseInt(line1[i]);
        }

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
            System.out.println("Best combi for this r(" + r +") : " + actualCombiForThisR);

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

        for(Integer element:actualCombi){
            solution += String.valueOf(element) + " ";
        }

        //Write solution in output file
        System.out.println("solution : " + solution);
        writeMySolution(solution, outputPath);
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
        System.out.println(combinationsForR); //

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

    static String[] readMyFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner sc = new Scanner(file);

        // we just need to use \\Z as delimiter
        sc.useDelimiter("\\Z");

        String fileContent = sc.next().toString();
        System.out.println("fileContent : "+fileContent);

        String[] line = fileContent.split("\n");
        return line;

//        System.out.println("line " + line);
//        for(int i=0;i<line.length;i++) {
//            System.out.println("line "+ i + " : " + line[i]);
//            String[] valueOfLine = line[i].split(" ");
//            for (int j = 0; j < valueOfLine.length; j++) {
//                System.out.println("value " + j + " : " + valueOfLine[j]);
//
//            }
//        }
    }

    static void writeMySolution(String wrString, String path) throws IOException {
        // attach a file to FileWriter
        FileWriter fw = new FileWriter(path);

        // read character wise from string and write
        // into FileWriter
        for (int i = 0; i < wrString.length(); i++)
            fw.write(wrString.charAt(i));

        System.out.println("Writing successful");
        //close the file
        fw.close();
    }


}
