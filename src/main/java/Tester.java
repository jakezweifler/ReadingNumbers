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
        m3.genAndKill();
        ImageGetter.printNicely(m3.getMultiplierMatrix());




    }
}


