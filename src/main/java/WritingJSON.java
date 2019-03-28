import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WritingJSON {

    public static void main(String[] args) {
        example1();
    }

    private static void example1() {
        double p1 = 60;

        // ObjectMapper is a jackson class that's used to read and write JSON data
        ObjectMapper mapper = new ObjectMapper();


        String path = "/home/jake/ReadingNumbers/src/main/java/Matrices/MultiplierMatrix3bias.json";


//
//        try {
//            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), p1);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }



}