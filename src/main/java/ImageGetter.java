import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class ImageGetter {

    public static void main(String avg[]) throws IOException {
        BufferedImage img = ImageIO.read(new File("/home/jake/ReadingNumbers/src/main/Numbers/3/3.1.jpg"));
//        ImageIcon icon = new ImageIcon(img);
//        JFrame frame = new JFrame();
//        frame.setLayout(new FlowLayout());
//        frame.setSize(80, 120);
//        JLabel lbl = new JLabel();
//        lbl.setIcon(icon);
//        frame.add(lbl);
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //1 is black, 0 is white


        int[][] blackIndexes = getRandomFakeImage();
        for(int x = 0; x < 30; x++) {
            System.out.println(Arrays.toString(blackIndexes[x]));
        }

    }

    public static void printNicely(double[][] d) {
        for(int x = 0; x < d.length; x++) {
            System.out.println(Arrays.toString(d[x]));
        }
    }

    public static void printNicely(int[][] d) {
        for(int x = 0; x < d.length; x++) {
            System.out.println(Arrays.toString(d[x]));
        }
    }

    public static void printNicely(String[][] d) {
        for(int x = 0; x < d.length; x++) {
            System.out.println(Arrays.toString(d[x]));
        }
    }

    public static int[][] getRandomNumberImage(int number) throws IOException {
        String filePath = "/home/jake/ReadingNumbers/src/main/Numbers/";
        filePath += number + "/" + number + "." + (int) (Math.random()*11 + 1) + ".jpg";
        //System.out.println(filePath);
        BufferedImage img = ImageIO.read(new File(filePath));

        int[][] blackIndexes = new int[30][30];

        for(int x = 0; x < 30; x++) {
            for(int y = 0; y < 30; y++) {
                blackIndexes[x][y] = blackIndex(new Color(img.getRGB(y, x)));
            }
        }
        return blackIndexes;
    }


    public static int[][] getNumberImage(int number, int fileNumber) throws IOException {
        String filePath = "/home/jake/ReadingNumbers/src/main/Numbers/";
        filePath += number + "/" + number + "." + fileNumber + ".jpg";
        //System.out.println(filePath);
        BufferedImage img = ImageIO.read(new File(filePath));

        int[][] blackIndexes = new int[30][30];
        for(int x = 0; x < 30; x++) {
            for(int y = 0; y < 30; y++) {
                blackIndexes[x][y] = blackIndex(new Color(img.getRGB(y, x)));
            }
        }
        return blackIndexes;
    }



    public static int[][] getRandomFakeImage() throws IOException{
        String filePath = "/home/jake/ReadingNumbers/src/main/Numbers/Fakes/fake";
        filePath += (int) (Math.random()*15 + 1) + ".jpg";
        //System.out.println(filePath);
        BufferedImage fakeImage = ImageIO.read(new File(filePath));

        int[][] outPutIndices = new int[30][30];

        for(int a = 0; a < 30; a ++) {
            for(int b = 0; b < 30; b++) {
                outPutIndices[a][b] = blackIndex((new Color(fakeImage.getRGB(b,a))));
            }
        }
        return outPutIndices;
    }

    public static int[][] getFakeImage(int fileNumber) throws IOException{
        String filePath = "/home/jake/ReadingNumbers/src/main/Numbers/Fakes/fake" + fileNumber + ".jpg";

        //System.out.println(filePath);
        BufferedImage fakeImage = ImageIO.read(new File(filePath));

        int[][] outPutIndices = new int[30][30];
        for(int a = 0; a < 30; a ++) {
            for(int b = 0; b < 30; b++) {
                outPutIndices[a][b] = blackIndex((new Color(fakeImage.getRGB(b,a))));
            }
        }
        return outPutIndices;
    }



    public static int blackIndex(Color c) {
        int redIndex = c.getRed();
        int blueIndex = c.getBlue();
        int greenIndex = c.getGreen();
        int totalColor = redIndex + blueIndex + greenIndex;
        return totalColor > 382? 0 : 1;
    }

}
