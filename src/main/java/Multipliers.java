import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Multipliers {

    private double[][] multiplierMatrix;
    private double bias;
    private int number;
    private final String folderLocation = "/home/jake/ReadingNumbers/src/main/java/Matrices/MultiplierMatrix";
    private String fileLocation;

    public Multipliers(int number) {
        this.number = number;
        fileLocation = folderLocation + number + ".json";
        ObjectMapper mapper = new ObjectMapper();
        try {
            double[][] multiplierMatrix = mapper.readValue(new File(fileLocation), double[][].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double[][] getMultiplierMatrix() {
        return multiplierMatrix;
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
    public double[][] randomMutation(double[][] inputMatrix) {
        for (int x = 0; x < 10; x++) {
            int xVal = (int) (Math.random() * 30);
            int yVal = (int) (Math.random() * 30);
            inputMatrix[xVal][yVal] += Math.random() - 0.5;
        }
        return inputMatrix;
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

    public static double checkAccuracy(double[][] multipliers, double bias, int number) throws IOException{
        double productSum = 0;
        for(int x = 0; x < 20; x++) {
            productSum += Math.random() > 0.5? compareToNumber(multipliers, bias, number) : compareToFake(multipliers, bias);
        }
        return productSum;
    }


    public static double compareToNumber(double[][] multipliers, double bias, int number) throws IOException {
        double productSum = getProduct(multipliers, ImageGetter.getRandomNumberImage(number));
        productSum = sigmoidFunction(productSum - bias);
        return 1 - productSum;
    }

    public static double compareToFake(double[][] multipliers, double bias) throws IOException{
        double productSum = getProduct(multipliers, ImageGetter.getRandomFakeImage());
        productSum = sigmoidFunction(productSum - bias);
        return productSum - 0;
    }


    public double[][] gen100AndKill() {
        return multiplierMatrix;
    }

    public static double sigmoidFunction(double input) {
        return Math.pow(Math.E, input) / (1 + Math.pow(Math.E, input));
    }


}
