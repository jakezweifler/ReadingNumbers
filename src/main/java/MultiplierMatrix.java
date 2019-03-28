import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class MultiplierMatrix {

    private double[][] multiplierMatrix;
    private double bias;
    private int number;
    private final String folderLocation = "/home/jake/ReadingNumbers/src/main/java/Matrices/MultiplierMatrix";
    private String multipliersFileLocation;
    private String biasFileLocation;

    public MultiplierMatrix(int number) {
        this.number = number;
        multipliersFileLocation = folderLocation + number + ".json";
        biasFileLocation = folderLocation + number + "bias.json";

        ObjectMapper mapper = new ObjectMapper();
        try {
            multiplierMatrix = mapper.readValue(new File(multipliersFileLocation), double[][].class);
            bias = mapper.readValue(new File(biasFileLocation), double.class);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public double[][] getMultiplierMatrix() {
        return multiplierMatrix;
    }

    public double getBias() {
        return bias;
    }

    public void clear() {
        multiplierMatrix = new double[30][30];
    }

    public void randomClear() {
        for (int x = 0; x < 30; x++) {
            for (int y = 0; y < 30; y++) {
                multiplierMatrix[x][y] = Math.random();
            }
        }
    }


    //methods for optimization
    public static void randomMutation(double[][] inputMatrix) {
        for (int x = 0; x < 100; x++) {
            int xVal = (int) (Math.random() * 30);
            int yVal = (int) (Math.random() * 30);
            if(Math.abs(inputMatrix[xVal][yVal]) > 10) {
                x--;
            }
            else {
                inputMatrix[xVal][yVal] = (Math.random() - 0.5);
            }
        }
    }


    public static double getProduct(double[][] multipliers, int[][] image) {
        double sum = 0;
        for (int x = 0; x < 30; x++) {
            for (int y = 0; y < 30; y++) {
                sum += multipliers[x][y] * image[x][y];
            }
        }
        return sum;
    }

    public static double checkTrueAccuracy(double[][] multipliers, double bias, int number) throws IOException{
        double productSum = 0;
        for(int x = 0; x < 5; x++) {
            productSum += compareToNumber(multipliers, bias, number);
        }
        return productSum;
    }

    public static double checkFalsePositiveAccuracy(double[][] multipliers, double bias) throws IOException{
        double productSum = 0;
        for(int x = 0; x < 10; x++) {
            productSum += compareToFake(multipliers, bias);
        }
        return productSum;
    }


    public static double compareToNumber(double[][] multipliers, double bias, int number) throws IOException {
        double productSum = getProduct(multipliers, ImageGetter.getRandomNumberImage(number));
        //System.out.println(productSum);
        productSum = sigmoidFunction(productSum - bias);
        return 1 - productSum;
    }

    public static double compareToFake(double[][] multipliers, double bias) throws IOException{
        double productSum = getProduct(multipliers, ImageGetter.getRandomFakeImage());
        //System.out.println(productSum);
        productSum = sigmoidFunction(productSum - bias);
        return productSum - 0;
    }

    public static double getNumberIndex(int[][] image, double[][] multipliers, double bias) throws IOException{
        double productSum = getProduct(multipliers, image);
        productSum = sigmoidFunction(productSum - bias);
        return productSum;
    }


    public void genAndKill() throws IOException{
        double[][] originalMatrix = copyArray(multiplierMatrix);
        double[][] mutationMatrix = copyArray(multiplierMatrix);
        double originalBias = bias;
        double mutationBias = bias + Math.random() - 0.5;
        randomMutation(mutationMatrix);

        if((checkTrueAccuracy(mutationMatrix, mutationBias, number) < checkTrueAccuracy(originalMatrix, originalBias, number)) &&
                (checkFalsePositiveAccuracy(mutationMatrix, mutationBias) < checkFalsePositiveAccuracy(originalMatrix, originalBias))){
            this.multiplierMatrix = mutationMatrix;
            this.bias = mutationBias;
            //System.out.println("hooray!");
        }
    }

    public static double sigmoidFunction(double input) {
        return Math.pow(Math.E, input) / (1 + Math.pow(Math.E, input));
    }

    public static double[][] copyArray(double[][] b) {
        double[][] copy = new double[b.length][b[0].length];
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                copy[i][j] = b[i][j];
            }
        }
        return copy;
    }

    public void saveToDrive() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(multipliersFileLocation), multiplierMatrix);
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(biasFileLocation), bias);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


