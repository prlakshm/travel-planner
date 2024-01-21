package sol;
import java.util.*;

import src.IDijkstra;
import src.IEdge;
import src.IGraph;
import src.IVertex;

import java.util.function.Function;

/**
 * Implementation for Dijkstra's algorithm
 *
 * @param <V> the type of the vertices
 * @param <E> the type of the edges
 */
public class Dijkstra<V extends IVertex<E>, E extends IEdge<V>> implements IDijkstra<V, E> {
    private HashMap<V, Double> cityVal;
    private HashMap<V, E> cameFrom;
    private Comparator<V> sortVal;
    private PriorityQueue<V> toCheckQueue;

    /**
     * Constructor for Dijkstra
     */
    public Dijkstra(){
        this.cityVal = new HashMap<>();
        this.cameFrom = new HashMap<>();

        // when you get to using a PriorityQueue, remember to remove and re-add a vertex to the
        // PriorityQueue when its priority changes!
         this.sortVal = (v1, v2) -> {
            return Double.compare(this.cityVal.get(v1), this.cityVal.get(v2));
        };
        this.toCheckQueue = new PriorityQueue<V>(this.sortVal);

    }

    /**
     * backtrack method
     * @param start the City that is the source
     * @param end the City that is the destination
     * @return List of edges
     */
    public List<E> backtrack(V start, V end) {
        LinkedList<E> path = new LinkedList<>();
        V current = end;
        while (!current.equals(start)) {
            E edge = this.cameFrom.get(current);
            path.addFirst(edge);
             current = edge.getSource();

        }
        return path;
    }

    /**
     * returns the ShortestPath
     * @param graph the HashMap that maps the City to it's edge
     * @param source the City that is the source
     * @param destination the City that is the destination
     * @param edgeWeight a function that returns the edgeWeight of an edge
     * @return List of edges
    **/
    @Override
    public List<E> getShortestPath(IGraph<V, E> graph, V source, V destination,
                                   Function<E, Double> edgeWeight) {
        for(V v : graph.getVertices()){
            this.cityVal.put(v, Double.MAX_VALUE);
        //    this.cameFrom.put(v, null);
        }
        this.cityVal.put(source, 0.);
        this.toCheckQueue.add(source);

        while (!this.toCheckQueue.isEmpty()) {
            V checkingV = this.toCheckQueue.poll();
            if(source != destination) {
            for (E edge : checkingV.getOutgoing()) {
                V edgeTarget = edge.getTarget();
                    Double newWeight = this.cityVal.get(edge.getSource()) + edgeWeight.apply(edge);
                    if (newWeight < this.cityVal.get(edgeTarget)) {
                        this.cityVal.put(edgeTarget, newWeight);
                        this.cameFrom.put(edgeTarget, edge);
                        if (this.toCheckQueue.contains(edgeTarget)) {
                            this.toCheckQueue.remove(edgeTarget);
                        }
                        this.toCheckQueue.offer(edgeTarget);
                    }
                }
            }
        }
        if (!this.cameFrom.containsKey(destination)){
            return new LinkedList<E>();
        }

        return this.backtrack(source, destination);
    }
}
