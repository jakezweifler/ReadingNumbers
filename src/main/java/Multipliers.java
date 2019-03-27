import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class Multipliers {

    private double[][] multiplierMatrix;
    private double bias;
    private final String folderLocation = "/home/jake/ReadingNumbers/src/main/java/Matrices/MultiplierMatrix";
    private String fileLocation;

    public Multipliers(int number) {
        fileLocation = folderLocation + number + ".json";
        ObjectMapper mapper = new ObjectMapper();
        try {
            double[][] multiplierMatrix = mapper.readValue(new File(fileLocation), double[][].class);
        }
        catch (IOException e) {
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
        for(int x = 0; x < 30; x++) {
            for(int y = 0; y < 30; y++) {
                multiplierMatrix[x][y] = Math.random();
            }
        }
    }


    //methods for optimization
    public double[][] randomMutation(double[][] inputMatrix) {
        for(int x = 0; x < 10; x++) {
            int xVal = (int) (Math.random() * 30);
            int yVal = (int) (Math.random() * 30);
            inputMatrix[xVal][yVal] = Math.random();
        }
        return inputMatrix;
    }

    public double getProduct(int[][] image) {
        double sum = 0;
        for(int x = 0; x < 30; x++) {
            for(int y = 0; y < 30; y++) {
                sum += multiplierMatrix[x][y]*image[x][y];
            }
        }
        return sum;
    }

    public double[][] gen100AndKill() {
        return multiplierMatrix;
    }

    public static double sigmoidFunction(int input) {
        return Math.E;
    }



}
