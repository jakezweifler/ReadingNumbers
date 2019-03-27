import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WritingJSON {

    public static void main(String[] args) {
        example1();
    }

    private static void example1() {
        String[] sTest = new String[3];
        double[][] p1 = new double[30][30];

        // ObjectMapper is a jackson class that's used to read and write JSON data
        ObjectMapper mapper = new ObjectMapper();


        String someData = "The capital of New York is Albany";
        String fileName = "NewYorkCapital.txt";
        String path = "/home/jake/ReadingNumbers/src/main/java/Matrices/MultiplierMatrix0.json";

        try {

            // writes p1 as json to the specified file
            //mapper.writeValue(new File(path), p1);

            // writes p1 as json to the specified file in a more readable way
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), p1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}