package main.java.qa.framework.temp;

public class VolatileTest3 extends Thread{
	 boolean keepRunning = true;

	    public void run() {
	        long count=0;
	        while (keepRunning) {
	            count++;
	        }

	        System.out.println("Thread terminated."+count);
	    }

	    public static void main(String[] args) throws InterruptedException {
	        VolatileTest3 t = new VolatileTest3();
	        t.start();
	        Thread.sleep(1000);
	        t.keepRunning = false;
	        System.out.println("keepRunning set to false.");
	    }
	}