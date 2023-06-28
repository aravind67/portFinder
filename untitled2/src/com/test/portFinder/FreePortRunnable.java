package com.test.portFinder;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;

public class FreePortRunnable implements Runnable {

    int startPort;
    int endPort;
    List<Integer> freePorts;

    int port = -1;

    public int getPort() {
        return port;
    }

    public int getStartPort() {
        return startPort;
    }

    public FreePortRunnable(int startPort, int endPort, List<Integer> freePorts, int numOfFreePorts) {
        this.startPort = startPort;
        this.endPort = endPort;
        this.freePorts = freePorts;
        this.numOfFreePorts = numOfFreePorts;
    }

    public int numOfFreePorts;

    @Override
    public void run() {
        synchronized (freePorts) {
            while (freePorts.size() < numOfFreePorts) {
                port = this.findNextFreePort(startPort, endPort);
                if (port != -1) {
                    freePorts.add(port);
                    startPort += 1;
                }
            }
        }
    }

    public int findNextFreePort(int startPort, int endPort) {
        for (int i= startPort; i < endPort; i++) {
            int port= getRandomPort(startPort, endPort);
            if (isPortAvailable(port)) {
                return port;
            }
        }
        return -1;
    }

    private static int getRandomPort(int low, int high) {
        return (int)(Math.random() * (high-low)) + low;
    }

    public boolean isPortAvailable(int port) {
        ServerSocket serverSocket = null;
        try  {
            serverSocket = new ServerSocket(port);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if(serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}