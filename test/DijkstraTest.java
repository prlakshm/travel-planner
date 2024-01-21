package test;

import org.junit.Before;
import org.junit.Test;
import sol.City;
import sol.Dijkstra;
import sol.Transport;
import sol.TravelGraph;
import src.IDijkstra;
import src.TransportType;
import test.simple.SimpleEdge;
import test.simple.SimpleGraph;
import test.simple.SimpleVertex;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Your Dijkstra's tests should all go in this class!
 * The test we've given you will pass if you've implemented Dijkstra's correctly, but we still
 * expect you to write more tests using the City and Transport classes.
 * You are welcome to write more tests using the Simple classes, but you will not be graded on
 * those.
 *
 * TODO: Recreate the test below for the City and Transport classes
 * TODO: Expand on your tests, accounting for basic cases and edge cases
 */
public class DijkstraTest {

    private static final double DELTA = 0.001;

    private SimpleGraph graph;
    private SimpleVertex a;
    private SimpleVertex b;
    private SimpleVertex c;
    private SimpleVertex d;
    private SimpleVertex e;
    private TravelGraph graph1;
    private TravelGraph graph2;
    private City boston;
    private City providence;
    private City boston2;
    private City providence2;
    private City durham;
    private City chicago;
    private City washington;
    private City dc;
    private City ithaca;
    private City newyorkcity;
    private Transport bosProv;
    private Transport bosDur;
    private Transport bosWas;
    private Transport durProv;
    private Transport durChi;
    private Transport chiProv;
    private Transport wasChi;
    private Transport provBost;
    private Transport provBosb;
    private Transport bosProvt;
    private Transport bosProvB;
    private Transport bosProvP;
    private Transport bosNyct;
    private Transport bosNycb;
    private Transport provNyc;
    private Transport dcProv;
    private Transport dcNyc;
    private Transport nycIth;

    /**
     * Creates a simple graph.
     * You'll find a similar method in each of the Test files.
     * Normally, we'd like to use @Before, but because each test may require a different setup,
     * we manually call the setup method at the top of the test.
     *
     * TODO: create more setup methods!
     */
    private void createSimpleGraph() {
        this.graph = new SimpleGraph();

        this.a = new SimpleVertex("a");
        this.b = new SimpleVertex("b");
        this.c = new SimpleVertex("c");
        this.d = new SimpleVertex("d");
        this.e = new SimpleVertex("e");

        this.graph.addVertex(this.a);
        this.graph.addVertex(this.b);
        this.graph.addVertex(this.c);
        this.graph.addVertex(this.d);
        this.graph.addVertex(this.e);

        this.graph.addEdge(this.a, new SimpleEdge(100, this.a, this.b));
        this.graph.addEdge(this.a, new SimpleEdge(3, this.a, this.c));
        this.graph.addEdge(this.a, new SimpleEdge(1, this.a, this.e));
        this.graph.addEdge(this.c, new SimpleEdge(6, this.c, this.b));
        this.graph.addEdge(this.c, new SimpleEdge(2, this.c, this.d));
        this.graph.addEdge(this.d, new SimpleEdge(1, this.d, this.b));
        this.graph.addEdge(this.d, new SimpleEdge(5, this.e, this.d));
    }

    /**
     * creates a travel graph with city and transport objects
     */
    @Before
    public void setup(){
        this.graph1 = new TravelGraph();
        this.boston = new City("Boston");
        this.providence = new City("Providence");
        this.durham = new City("Durham");
        this.chicago = new City("Chicago");
        this.washington = new City("Washington");
        this.graph1.addVertex(this.boston);
        this.graph1.addVertex(this.providence);
        this.graph1.addVertex(this.durham);
        this.graph1.addVertex(this.chicago);
        this.graph1.addVertex(this.washington);
        this.bosProv = new Transport(this.boston, this.providence, TransportType.TRAIN, 100., 10.);
        this.bosDur = new Transport(this.boston, this.durham, TransportType.PLANE, 3., 50.);
        this.bosWas = new Transport(this.boston, this.washington, TransportType.TRAIN, 1., 80.);
        this.durProv = new Transport(this.durham, this.providence, TransportType.BUS, 6., 70.);
        this.durChi = new Transport(this.durham, this.chicago, TransportType.TRAIN, 2., 90.);
        this.chiProv = new Transport(this.chicago, this.providence, TransportType.BUS, 1., 20.);
        this.wasChi = new Transport(this.washington, this.chicago, TransportType.TRAIN, 5., 31.);
        this.graph1.addEdge(this.boston, this.bosProv);
        this.graph1.addEdge(this.boston, this.bosDur);
        this.graph1.addEdge(this.boston, this.bosWas);
        this.graph1.addEdge(this.durham, this.durProv);
        this.graph1.addEdge(this.durham, this.durChi);
        this.graph1.addEdge(this.chicago, this.chiProv);
        this.graph1.addEdge(this.washington, this.wasChi);
    }

    /**
     * creates a travel graph with city and transport objects
     */
    @Before
    public void setup2(){
        this.graph2 = new TravelGraph();
        this.boston2 = new City("Boston");
        this.providence2 = new City("Providence");
        this.dc = new City("DC");
        this.ithaca = new City("Ithaca");
        this.newyorkcity = new City("NewYorkCity");
        this.graph2.addVertex(this.boston2);
        this.graph2.addVertex(this.providence2);
        this.graph2.addVertex(this.dc);
        this.graph2.addVertex(this.ithaca);
        this.graph2.addVertex(this.newyorkcity);
        this.provBost = new Transport(this.providence2, this.boston2, TransportType.TRAIN, 13., 80.);
        this.provBosb = new Transport(this.providence2, this.boston2, TransportType.BUS, 7., 150.);
        this.bosProvt = new Transport(this.boston2, this.providence2, TransportType.TRAIN, 13., 80.);
        this.bosProvB = new Transport(this.boston2, this.providence2, TransportType.BUS, 7., 150.);
        this.bosProvP = new Transport(this.boston2, this.providence2, TransportType.PLANE, 3., 40.);
        this.bosNyct = new Transport(this.boston2, this.newyorkcity, TransportType.TRAIN, 40., 100.);
        this.bosNycb = new Transport(this.boston2, this.newyorkcity, TransportType.BUS, 20., 200.);
        this.provNyc = new Transport(this.providence2, this.newyorkcity, TransportType.BUS, 17., 60.);
        this.dcProv = new Transport(this.dc, this.providence2, TransportType.TRAIN, 20., 20.);
        this.dcNyc = new Transport(this.dc, this.newyorkcity, TransportType.PLANE, 37., 80.);
        this.nycIth = new Transport(this.newyorkcity, this.ithaca, TransportType.TRAIN, 80., 80.);

        this.graph2.addEdge(this.providence2, this.provBost);
        this.graph2.addEdge(this.providence2, this.provBosb);
        this.graph2.addEdge(this.boston2, this.bosProvt);
        this.graph2.addEdge(this.boston2, this.bosProvB);
        this.graph2.addEdge(this.boston2, this.bosNyct);
        this.graph2.addEdge(this.boston2, this.bosNycb);
        this.graph2.addEdge(this.providence2, this.provNyc);
        this.graph2.addEdge(this.dc, this.dcProv);
        this.graph2.addEdge(this.dc, this.dcNyc);
        this.graph2.addEdge(this.newyorkcity, this.nycIth);
        this.graph2.addEdge(this.boston2, this.bosProvP);
    }

    /**
     * A sample test that tests Dijkstra's on a simple graph. Checks that the fastest path using Dijkstra's is what we
     * expect.
     */
    @Test
    public void testSimple() {
        this.createSimpleGraph();

        IDijkstra<SimpleVertex, SimpleEdge> dijkstra = new Dijkstra<>();
        Function<SimpleEdge, Double> edgeWeightCalculation = e -> e.weight;
        // a -> c -> d -> b
        List<SimpleEdge> path =
            dijkstra.getShortestPath(this.graph, this.a, this.b, edgeWeightCalculation);
        assertEquals(6, SimpleGraph.getTotalEdgeWeight(path), DELTA);
        assertEquals(3, path.size());

        // c -> d -> b
        path = dijkstra.getShortestPath(this.graph, this.c, this.b, edgeWeightCalculation);
        assertEquals(3, SimpleGraph.getTotalEdgeWeight(path), DELTA);
        assertEquals(2, path.size());
    }

    /**
     * A sample test that tests Dijkstra's on a simple graph. Checks that the fastest path using Dijkstra's is what we
     * expect.
     */
    @Test
    public void testFastestRoute() {
        IDijkstra<City, Transport> dijkstra = new Dijkstra<>();
        Function<Transport, Double> edgeWeightCalc = e -> e.getMinutes();
        List<Transport> path =
                dijkstra.getShortestPath(this.graph1, this.boston, this.providence, edgeWeightCalc);
        List<Transport> list1 = new LinkedList<>();
        list1.add(this.bosProv);
        assertEquals(list1, path);
        assertEquals(1, path.size());
        List<Transport> path2 =
                dijkstra.getShortestPath(this.graph2, this.dc, this.newyorkcity, edgeWeightCalc);
        List<Transport> list2 = new LinkedList<>();
        list2.add(this.dcNyc);
        assertEquals(list2, path2);
        assertEquals(1, path2.size());
        List<Transport> path3 =
                dijkstra.getShortestPath(this.graph2, this.providence2, this.newyorkcity, edgeWeightCalc);
        List<Transport> list3 = new LinkedList<>();
        list3.add(this.provNyc);
        assertEquals(list3, path3);
        assertEquals(1, path3.size());
    }

    /**
     * fastest path when the source and destination are the same city.
     */
    @Test
    public void testFastestRouteSameCity() {
        IDijkstra<City, Transport> dijkstra = new Dijkstra<>();
        Function<Transport, Double> edgeWeightCalc = e -> e.getMinutes();
        List<Transport> path =
                dijkstra.getShortestPath(this.graph1, this.boston, this.boston, edgeWeightCalc);
        assertEquals(new LinkedList<>(), path);
        assertEquals(0, path.size());
        List<Transport> path1 =
                dijkstra.getShortestPath(this.graph1, this.providence, this.providence, edgeWeightCalc);
        assertEquals(new LinkedList<>(), path1);
        assertEquals(0, path1.size());
    }

    /**
     * fastest route when the city is not there on the graph.
     */
    @Test
    public void testFastestRouteNoCity() {
        IDijkstra<City, Transport> dijkstra = new Dijkstra<>();
        Function<Transport, Double> edgeWeightCalc = e -> e.getMinutes();
        List<Transport> path =
                dijkstra.getShortestPath(this.graph1, this.boston, this.ithaca, edgeWeightCalc);
        assertEquals(new LinkedList<>(), path);
        assertEquals(0, path.size());
        List<Transport> path1 =
                dijkstra.getShortestPath(this.graph2, this.providence, this.providence2, edgeWeightCalc);
        assertEquals(new LinkedList<>(), path1);
        assertEquals(0, path1.size());
    }

    /**
     * fastest route when there are two routes which take the same time.
     */
    @Test
    public void testFastestTwoRoute() {
        IDijkstra<City, Transport> dijkstra = new Dijkstra<>();
        Function<Transport, Double> edgeWeightCalc = e -> e.getMinutes();
        List<Transport> path =
                dijkstra.getShortestPath(this.graph2, this.dc, this.newyorkcity, edgeWeightCalc);
        LinkedList<Transport> route = new LinkedList<>();
        route.add(this.dcNyc);
        assertEquals(route, path);
        assertEquals(1, path.size());

        //tie case -- use first occurance transport path
        List<Transport> path2 =
                dijkstra.getShortestPath(this.graph2, this.boston2, this.newyorkcity, edgeWeightCalc);
        LinkedList<Transport> route2 = new LinkedList<>();
        route2.add(this.bosNyct);
        assertEquals(route2, path2);

    }
    /**
     * A sample test that tests Dijkstra's on a simple graph. Checks that the cheapest path using Dijkstra's is what we
     * expect.
     */
    @Test
    public void testCheapestRoute() {
        IDijkstra<City, Transport> dijkstra = new Dijkstra<>();
        Function<Transport, Double> edgeWeightCalc = e -> e.getPrice();
        List<Transport> path =
                dijkstra.getShortestPath(this.graph1, this.boston, this.providence, edgeWeightCalc);
        List<Transport> list1 = new LinkedList<>();
        list1.add(this.bosDur);
        list1.add(this.durChi);
        list1.add(this.chiProv);
        assertEquals(list1, path);
        assertEquals(3, path.size());
        List<Transport> path2 =
                dijkstra.getShortestPath(this.graph2, this.dc, this.newyorkcity, edgeWeightCalc);
        List<Transport> list2 = new LinkedList<>();
        list2.add(this.dcNyc);
        assertEquals(list2, path2);
        assertEquals(1, path2.size());
        List<Transport> path3 =
                dijkstra.getShortestPath(this.graph2, this.providence2, this.newyorkcity, edgeWeightCalc);
        List<Transport> list3 = new LinkedList<>();
        list3.add(this.provNyc);
        assertEquals(list3, path3);
        assertEquals(1, path3.size());
    }

    /**
     * cheapest route when the source and destination are the same city.
     */
    @Test
    public void testCheapestRouteSameCity() {
        IDijkstra<City, Transport> dijkstra = new Dijkstra<>();
        Function<Transport, Double> edgeWeightCalc = e -> e.getPrice();
        List<Transport> path =
                dijkstra.getShortestPath(this.graph1, this.boston, this.boston, edgeWeightCalc);
        assertEquals(new LinkedList<>(), path);
        assertEquals(0, path.size());
        List<Transport> path1 =
                dijkstra.getShortestPath(this.graph1, this.providence, this.providence, edgeWeightCalc);
        assertEquals(new LinkedList<>(), path1);
        assertEquals(0, path1.size());
    }

    /**
     * cheapest route when the city is not there on the graph.
     */
    @Test
    public void testCheapestRouteNoCity() {
        IDijkstra<City, Transport> dijkstra = new Dijkstra<>();
        Function<Transport, Double> edgeWeightCalc = e -> e.getPrice();
        List<Transport> path =
                dijkstra.getShortestPath(this.graph1, this.boston, this.ithaca, edgeWeightCalc);
        assertEquals(new LinkedList<>(), path);
        assertEquals(0, path.size());
        List<Transport> path1 =
                dijkstra.getShortestPath(this.graph2, this.providence, this.providence2, edgeWeightCalc);
        assertEquals(new LinkedList<>(), path1);
        assertEquals(0, path1.size());
    }

    /**
     * cheapest route when there are two routes which take the same time.
     */
    @Test
    public void testCheapestTwoRoute() {
        IDijkstra<City, Transport> dijkstra = new Dijkstra<>();
        Function<Transport, Double> edgeWeightCalc = e -> e.getPrice();
        List<Transport> path =
                dijkstra.getShortestPath(this.graph2, this.dc, this.newyorkcity, edgeWeightCalc);
        LinkedList<Transport> route = new LinkedList<>();
        route.add(this.dcNyc);
        assertEquals(route, path);
        assertEquals(1, path.size());

        //tie case -- use first occurance transport path
        List<Transport> path2 =
                dijkstra.getShortestPath(this.graph2, this.boston2, this.newyorkcity, edgeWeightCalc);
        LinkedList<Transport> route2 = new LinkedList<>();
        route2.add(this.bosNycb);
        assertEquals(route2, path2);

    }
}
