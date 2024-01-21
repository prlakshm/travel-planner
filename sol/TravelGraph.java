package sol;

import src.IGraph;
import src.TransportType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation for TravelGraph
 */
public class TravelGraph implements IGraph<City, Transport> {
    private HashMap<String, City> vertices;

    /**
     * Constructor for TravelGraph
     */
    public TravelGraph(){
        this.vertices = new HashMap<>();
    }

    @Override
    /**
     * adds a Vertex
     * @param City the vertex that needs to be added
     * */
    public void addVertex(City vertex) {
        this.vertices.put(vertex.toString(), vertex);
    }

    @Override
    /**
     * adds an Edge
     * @param City the city that the edge needs to be added to
     * @param Transport the edge that needs to be added
     * */
    public void addEdge(City origin, Transport edge) {
        origin.addOut(edge);
    }

    @Override
    /**
     * getter for vertices
     * @return Set of City
     * */
    public Set<City> getVertices() {
        Set<City> cities = new HashSet<>(this.vertices.values());
        return cities;
    }

    @Override
    /**
     * getter for source of an edge
     * @param Transport the edge that is being looked at
     * @return Source of the edge
     * */
    public City getEdgeSource(Transport edge) {
        return edge.getSource();
    }

    @Override
    /**
     * getter for edge of the target
     * @param Transport the transport that is being looked at
     * @return the target City of the edge
     * */
    public City getEdgeTarget(Transport edge) {
        return edge.getTarget();
    }

    @Override
    /**
     * getter for OutgoingEdges
     * @param fromVertex the City that is being looked at
     * @return The set of transport which represents the outgoing edges at fromVertex
     * */
    public Set<Transport> getOutgoingEdges(City fromVertex) {
        return fromVertex.getOutgoing();
    }

    /**
     * getter for City
     * @param cityName Name of the City
     * @return City that has the same name as cityName
     */
    public City getCity(String cityName){
        return this.vertices.get(cityName);
    }
}