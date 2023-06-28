// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import com.test.portFinder.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        int rangeOfPorts = 65535;
        int numOfFreePorts = 10;

        FreePortFinder portFinder = new FreePortFinder(rangeOfPorts, 50);
        List<Integer> freePorts = portFinder.getFreePorts(numOfFreePorts);
        System.out.println("Free Ports:");
        for (int port : freePorts) {
            System.out.println(port);
        }
        System.out.println(freePorts.size());
    }
}