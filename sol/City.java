package sol;

import src.IEdge;
import src.IVertex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * A City class representing the vertex of a travel graph
 */
public class City implements IVertex<Transport> {
    private String name;
    private Set<Transport> outgoingEdges;

    /**
     * Constructor for a City
     * @param name The name of the city
     */
    public City(String name) {
        this.name = name;
        this.outgoingEdges = new HashSet<>();
    }

    /**
     * getter for Outgoing Edges
     * @return the set of outgoing edges
     */
    @Override
    public Set<Transport> getOutgoing() {
        return this.outgoingEdges;
    }

    /**
     * adds an outgoing edge
     * */
    @Override
    public void addOut(Transport outEdge) {
        this.outgoingEdges.add(outEdge);
    }

    /**
     * converts the City class to a string
     * @return String representation of City
     * */
    @Override
    public String toString() {
        return this.name;
    }

}

