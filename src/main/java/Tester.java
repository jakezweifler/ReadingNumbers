import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.io.File;
import java.io.IOException;

class Tester {
    public static void main(String args[]) throws IOException  {
        MultiplierMatrix m3 = new MultiplierMatrix(3);
        //System.out.println(m3.getMultiplierMatrix().length);
        //m3.randomClear();

        //ImageGetter.printNicely(m3.getMultiplierMatrix());
        System.out.println(MultiplierMatrix.getNumberIndex(ImageGetter.getRandomNumberImage(3), m3.getMultiplierMatrix(),m3.getBias()));
        System.out.println(MultiplierMatrix.getNumberIndex(ImageGetter.getRandomFakeImage(), m3.getMultiplierMatrix(), m3.getBias()));


        //ImageGetter.printNicely(polarize(m3.getMultiplierMatrix()));

        for(int x = 0; x < 1000; x++) {
            m3.genAndKill();
//            if(x%100 == 0) {
//                m3.saveToDrive();
//            }
            if(x%100 == 0) {
                int percent = x/100;
                System.out.println(percent + "% done.");
            }

        }
        m3.saveToDrive();

        //ImageGetter.printNicely(m3.getMultiplierMatrix());




    }

    public static int[][] polarize(double[][] d) {
        int[][] simpleInt = new int[d.length][d[0].length];
        for (int x = 0; x < d.length; x++) {
            for (int y = 0; y < d[0].length; y++) {
                simpleInt[x][y] = d[x][y] > 0? 1:0;
            }
        }
        return simpleInt;
    }

}


