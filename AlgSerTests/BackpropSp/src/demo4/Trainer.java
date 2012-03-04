package demo4;

import java.io.File;
import java.io.IOException; 
import java.io.FileNotFoundException;

import network.PatternList;


/**
 * Train the demo4 backpropagation network.
 *
 * @author G.S. Cole (gsc@digiburo.com)
 * @version $Id: Trainer.java,v 1.5 2002/02/02 20:53:53 gsc Exp $
 */

/*
 * Development Environment:
 *   Linux 2.2.14-5.0 (Red Hat 6.2)
 *   Java Developers Kit 1.3.1
 *
 * Legalise:  
 *   Copyright (C) 2002 Digital Burro, INC.
 *
 * Maintenance History:
 *   $Log: Trainer.java,v $
 *   Revision 1.5  2002/02/02 20:53:53  gsc
 *   More testing tweaks
 *
 *   Revision 1.4  2002/02/02 08:27:27  gsc
 *   Work In Progress
 *
 *   Revision 1.3  2002/02/01 05:09:59  gsc
 *   Tweaks from Unit Testing
 *
 *   Revision 1.2  2002/02/01 02:48:08  gsc
 *   Work In Progress
 *
 *   Revision 1.1  2002/01/22 08:19:35  gsc
 *   Initial Check In
 */

public class Trainer {
    private static final String TRAIN_FILENAME = "demo4.trn";
    private static final String NETWORK_FILENAME = "demo4.serial";

    private BpDemo4 bp;
    private PatternList pl;

    /**
     * Create network
     */
    public Trainer() {
    	bp = new BpDemo4(301, 10, 1, 0.25, 0.9);
    }

    /**
     * Load training datum
     * @param datum training file
     */
    public int loadTraining(File datum) throws IOException, FileNotFoundException, ClassNotFoundException {
    	pl = new PatternList();
    	pl.reader(datum);
    	return(pl.size());
    }

	/**
	 * Train the network on these patterns
	 */
	public void performTraining() {	
		bp.trainNetwork(pl, (int)(pl.size() - 0.01 * pl.size()), -1, 0.0001, true);
	}

    /**
     * Save this network for later use.
     * @param datum file to save as
     */
    public void saveTraining(File datum) throws IOException, FileNotFoundException {
    	bp.saveNetwork(datum);
    }

	/**
	 * Driver.
	 */
	public static void main(String args[]) throws Exception {
		System.out.println("begin");

		int population = 0;
		Trainer tr = new Trainer();

		if (args.length == 0) {
			population = tr.loadTraining(new File(TRAIN_FILENAME));
		} else {
			population = tr.loadTraining(new File(args[0]));
		}

		System.out.println("PatternList loaded w/" + population + " patterns");
		
		Long t1, t2;
		
		t1 = System.currentTimeMillis();
		
		tr.performTraining();
		
		t2 = System.currentTimeMillis();
		
		System.out.println("Execution time was " + (t2 - t1) + " ms.");
		
		tr.saveTraining(new File(NETWORK_FILENAME));

		System.out.println("end");
	}
}
