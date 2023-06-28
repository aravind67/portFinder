package com.test.portFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class FreePortFinder {
    private int rangeOfPorts;

    public int threads;

    public FreePortFinder(int rangeOfPorts, int threads) {
        this.rangeOfPorts = rangeOfPorts;
        this.threads = threads;
    }

    public List<Integer> getFreePorts(int numOfFreePorts) {
        Vector<Integer> freePorts = new Vector<>();
        int startPort = 10000;
        int endPort = rangeOfPorts;
        List<Thread> l = new ArrayList<>();
        for(int i=0; i<threads; i++) {
            FreePortRunnable ffp = new FreePortRunnable(startPort, endPort, freePorts, numOfFreePorts);
            Thread t = new Thread(ffp);
            t.start();
            l.add(t);
        }
        return freePorts;
    }


}

