package sol;

import src.IEdge;
import src.ITravelController;
import src.TransportType;
import src.TravelCSVParser;
import test.simple.SimpleEdge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Implementation for TravelController
 */
public class TravelController implements ITravelController<City, Transport> {

    private TravelGraph graph;

    /**
     * Constructor for TravelController
     */
    public TravelController() {
        this.graph = new TravelGraph();
    }

    /**
     * loads the map
     * @param citiesFile    the filename of the cities csv
     * @param transportFile the filename of the transportations csv
     * @return String representation of the map
     */
    @Override
    public String load(String citiesFile, String transportFile) {
        TravelCSVParser parser = new TravelCSVParser();

        Function<Map<String, String>, Void> addVertex = map -> {
            this.graph.addVertex(new City(map.get("name")));
            return null; // need explicit return null to account for Void type
        };

        Function<Map<String, String>, Void> addEdge = map -> {
            this.graph.addEdge(this.graph.getCity(map.get("origin")),
                    new Transport(this.graph.getCity(map.get("origin")), this.graph.getCity(map.get("destination")), TransportType.fromString(map.get("type")), Double.parseDouble(map.get("price")), Double.parseDouble(map.get("duration"))));
            return null; // need explicit return null to account for Void type
        };


        try {
            parser.parseLocations(citiesFile, addVertex);
        } catch (IOException e) {
            return "Error parsing file: " + citiesFile;
        }

        try{
            parser.parseTransportation(transportFile, addEdge);
        } catch (IOException e) {
            return "Error parsing file:" + transportFile;
        }

        return "Successfully loaded cities and transportation files.";
    }

    /**
     * returns the fastest route
     * @param source      the name of the source city
     * @param destination the name of the destination city
     * @return the list of transport which is the fastest route
     */
    @Override
    public List<Transport> fastestRoute(String source, String destination) {
        Function<Transport, Double> edgeWeightCalc = e -> e.getMinutes();
        Dijkstra dijkstra = new Dijkstra();
        return dijkstra.getShortestPath(this.graph, this.graph.getCity(source), this.graph.getCity(destination), edgeWeightCalc);
    }

    /**
     * returns the cheapest route
     * @param source      the name of the source city
     * @param destination the name of the destination city
     * @return the list of transport which is the cheapest route
     */
    @Override
    public List<Transport> cheapestRoute(String source, String destination) {
        Function<Transport, Double> edgeWeightCalc = e -> e.getPrice();
        Dijkstra dijkstra = new Dijkstra();
        return dijkstra.getShortestPath(this.graph, this.graph.getCity(source), this.graph.getCity(destination), edgeWeightCalc);
    }

    /**
     * returns the most direct route
     * @param source      the name of the source city
     * @param destination the name of the destination city
     * @return the list of transport which is the most direct route
     */
    @Override
    public List<Transport> mostDirectRoute(String source, String destination) {
        BFS bfs = new BFS();
        return bfs.getPath(this.graph, this.graph.getCity(source), this.graph.getCity(destination));
    }

    /**
     * Returns the graph stored by the controller
     *
     * NOTE: You __should not__ be using this in your implementation, this is purely meant to be used for the visualizer
     *
     * @return The TravelGraph currently stored in the TravelController
     */
    public TravelGraph getGraph() {
        return this.graph;
    }
}
