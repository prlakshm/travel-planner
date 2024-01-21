package sol;

import src.IBFS;
import src.IEdge;
import src.IGraph;
import src.IVertex;
import java.util.*;

/**
 * Implementation for BFS, implements IBFS interface
 * @param <V> The type of the vertices
 * @param <E> The type of the edges
 */
public class BFS<V extends IVertex<E>, E extends IEdge<V>> implements IBFS<V, E> {

    private HashSet<E> visited;
    private HashMap<V, E> cameFrom;

    /**
     * Constructor for BFS
     */
    public BFS(){
        this.visited = new HashSet<E>();
        this.cameFrom = new HashMap<V, E>();

    }

    /**
     * backtrack method
     * @param cameFrom the HashMap that maps the City to it's edge
     * @param start the City that is the source
     * @param end the City that is the destination
     * @return List of edges
     */
    public List<E> backtrack(HashMap<V, E> cameFrom, V start, V end) {
        LinkedList<E> path = new LinkedList<>();
        V current = end;
        while (current != start) {
            E edge = cameFrom.get(current);
            path.addFirst(edge);
            current = edge.getSource();
        }
        return path;
    }

    /**
     * returns the most direct path
     * @param graph the HashMap that maps the City to it's edge
     * @param start the City that is the source
     * @param end the City that is the destination
     * @return List of edges
     * */
    @Override
    public List<E> getPath(IGraph<V, E> graph, V start, V end) {
        LinkedList<E> toCheck = new LinkedList<E>(start.getOutgoing());
        while (!toCheck.isEmpty()) {
            E checkingEdge = toCheck.remove();
            V target = checkingEdge.getTarget();
            if (!this.cameFrom.containsKey(target)) {
                this.cameFrom.put(target, checkingEdge);
            }
            if (end.equals(target)) {
                return this.backtrack(this.cameFrom, start, end);
            }
            this.visited.add(checkingEdge);
            for (E e : graph.getOutgoingEdges(target)) {
                if (!this.visited.contains(e)) {
                    toCheck.addLast(e);
                }
            }
        }
        return new LinkedList<E>();
    }
}
