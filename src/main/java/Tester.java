import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.io.File;
import java.io.IOException;

class Tester {
    public static void main(String args[]) throws IOException  {
        MultiplierMatrix m3 = new MultiplierMatrix(3);
        System.out.println(m3.getMultiplierMatrix().length);

        ImageGetter.printNicely(m3.getMultiplierMatrix());

        for(int x = 0; x < 10000; x++) {
            m3.genAndKill();
            if(x%100 == 0) {
                System.out.println((x/100) + "% done.");
            }

        }
        m3.saveToDrive();

        ImageGetter.printNicely(m3.getMultiplierMatrix());




    }
}


