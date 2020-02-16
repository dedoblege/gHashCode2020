package com.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class sliceCalculator {
    public static void main(String [] args) throws IOException {


        //TODO Read file a_example

       

        //TODO convert string to int

        int maxSlices = 100;
        int numberOfTypes = 10;
        int[] inSlices = {4, 14, 15, 18, 29, 32, 36, 82, 95, 95};

//        int maxSlices = 17;
//        int numberOfTypes = 4;
//        int [] inSlices = {2,5,6,8};
//        ArrayList<Integer> inSlices = new ArrayList<Integer>(numberOfTypes);
//        inSlices.add(2);
//        inSlices.add(5);
//        inSlices.add(6);
//        inSlices.add(8);

        int resultNumberOfTypes = 0;

        int actualMaxForThisR;
        int actualMax;
        ArrayList<Integer> actualCombiForThisR = new ArrayList<>();
        ArrayList<Integer> actualCombi = new ArrayList<>();

        ArrayList<ArrayList<Integer>> combinationsForR = new ArrayList<>();

        int maxSlicesToGet = maxSlices;
        int totalNumberOfSlices = 0;

        actualMax = 0;
        for(int r=0;r<numberOfTypes;r++) {
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
            System.out.println("best combi for this r(" + r +") : " + actualCombiForThisR);

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

        System.out.println("final decision : " + actualCombi);

            //TODO write result in file

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



}
