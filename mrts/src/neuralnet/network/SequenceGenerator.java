package neuralnet.network;

/**
 * Provide unique ID for arcs/nodes
 */

/**
 * Provide unique ID for arcs/nodes
 * 
 * @author gsc
 * @author cbarca
 */
public class SequenceGenerator {

    /**
     * Each arc/node has a unique identifier. 
     * This was to help w/development, but it doesn't hurt to retain.
     * @return unique identifier
     */
    public static synchronized int getId() {
    	return(_id++);
    }
    
    /**
     * Contains the next Arc/Node identifier.
     */
    private static int _id = 1;
}
