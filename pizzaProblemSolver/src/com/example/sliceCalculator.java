package com.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class sliceCalculator {
    public static void main(String [] args) {

        //TODO Read file a_example

        //TODO convert string to int

        int maxSlices = 17;
        int numberOfTypes = 4;
        int[] inSlicesType = {2, 5, 6, 8};

        String outLine1 = "";
        String outLine2 = "";

        int resultNumberOfTypes = 0;
        ArrayList<Integer> outVectorTypes = new ArrayList<Integer>();

        int maxSlicesToGet = maxSlices;
        int totalNumberOfSlices = 0;

        //slice calculator
        for(int positionInSlicesType=numberOfTypes-1; positionInSlicesType>=0; positionInSlicesType--) {
            //number of pizzas of the bigger pizza
            int numberOfPizza = maxSlicesToGet / inSlicesType[positionInSlicesType];
            //number of slices not covered yet
            int slicesToCover = maxSlicesToGet % inSlicesType[positionInSlicesType];

            if(numberOfPizza > 0) {
                if (slicesToCover < inSlicesType[0] && slicesToCover > 0) {
                    numberOfPizza--;
                } else if (slicesToCover == 0) {
                    //maxSlices reached --> we stop here
                    break;
                }

                //save result
                resultNumberOfTypes++;
                outVectorTypes.add(positionInSlicesType);

                //set max to next iteration
                maxSlicesToGet = maxSlicesToGet - (numberOfPizza * inSlicesType[positionInSlicesType]);

                //save for verifying
                totalNumberOfSlices += numberOfPizza * inSlicesType[positionInSlicesType];
            }
        }

        //verifying
        if(totalNumberOfSlices <= maxSlices){
            //order vector ascending before showing solution
            Collections.sort(outVectorTypes);


            System.out.println("resultNumberOfTypes = " + resultNumberOfTypes);
            System.out.println("typesVector = " + outVectorTypes);

            //TODO write result in file
        }else{
            System.out.println("ERROR -  MAX exceeded! Your result is not correct!");
            System.out.println("You should repeat the calculation");
        }


//        // The name of the file to open.
//        String fileName = "a_example.txt";
//
//        // This will reference one line at a time
//        String line = null;
//
//        try {
//            // Use this for reading the data.
//            byte[] buffer = new byte[1000];
//
//            FileInputStream inputStream =
//                    new FileInputStream(fileName);
//
//            // read fills buffer with data and returns
//            // the number of bytes read (which of course
//            // may be less than the buffer size, but
//            // it will never be more).
//            int total = 0;
//            int nRead = 0;
//            while((nRead = inputStream.read(buffer)) != -1) {
//                // Convert to String so we can display it.
//                // Of course you wouldn't want to do this with
//                // a 'real' binary file.
//                System.out.println(new String(buffer));
//                total += nRead;
//            }
//
//            // Always close files.
//            inputStream.close();
//
//            System.out.println("Read " + total + " bytes");
//        } catch (FileNotFoundException ex) {
//            System.out.println(
//                    "Unable to open file '" +
//                            fileName + "'");
//        } catch (IOException ex) {
//            System.out.println(
//                    "Error reading file '"
//                            + fileName + "'");
//        }
    }
}
