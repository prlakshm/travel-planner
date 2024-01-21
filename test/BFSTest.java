package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sol.*;
import src.TransportType;
import src.TravelCSVParser;
import test.simple.SimpleEdge;
import test.simple.SimpleGraph;
import test.simple.SimpleVertex;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Your BFS tests should all go in this class!
 * The test we've given you will pass if you've implemented BFS correctly, but we still expect
 * you to write more tests using the City and Transport classes.
 * You are welcome to write more tests using the Simple classes, but you will not be graded on
 * those.
 *
 * TODO: Recreate the test below for the City and Transport classes
 * TODO: Expand on your tests, accounting for basic cases and edge cases
 */
public class BFSTest {

    private static final double DELTA = 0.001;

    private SimpleVertex a;
    private SimpleVertex b;
    private SimpleVertex c;
    private SimpleVertex d;
    private SimpleVertex e;
    private SimpleVertex f;
    private SimpleGraph graph;
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
    public void makeSimpleGraph() {
        this.graph = new SimpleGraph();

        this.a = new SimpleVertex("a");
        this.b = new SimpleVertex("b");
        this.c = new SimpleVertex("c");
        this.d = new SimpleVertex("d");
        this.e = new SimpleVertex("e");
        this.f = new SimpleVertex("f");

        this.graph.addVertex(this.a);
        this.graph.addVertex(this.b);
        this.graph.addVertex(this.c);
        this.graph.addVertex(this.d);
        this.graph.addVertex(this.e);
        this.graph.addVertex(this.f);

        this.graph.addEdge(this.a, new SimpleEdge(1, this.a, this.b));
        this.graph.addEdge(this.b, new SimpleEdge(1, this.b, this.c));
        this.graph.addEdge(this.c, new SimpleEdge(1, this.c, this.e));
        this.graph.addEdge(this.d, new SimpleEdge(1, this.d, this.e));
        this.graph.addEdge(this.a, new SimpleEdge(100, this.a, this.f));
        this.graph.addEdge(this.f, new SimpleEdge(100, this.f, this.e));
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
        this.bosNyct = new Transport(this.boston2, this.newyorkcity, TransportType.TRAIN, 40., 100.);
        this.bosNycb = new Transport(this.boston2, this.newyorkcity, TransportType.BUS, 20., 200.);
        this.provNyc = new Transport(this.providence2, this.newyorkcity, TransportType.BUS, 17., 225.);
        this.dcProv = new Transport(this.dc, this.providence2, TransportType.TRAIN, 20., 600.);
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
    }

    /**
     * A sample test that tests BFS on a simple graph. Checks that running BFS gives us the path we expect.
     */
    @Test
    public void testBasicBFS() {
        this.makeSimpleGraph();
        BFS<SimpleVertex, SimpleEdge> bfs = new BFS<>();
        List<SimpleEdge> path = bfs.getPath(this.graph, this.a, this.e);
        assertEquals(SimpleGraph.getTotalEdgeWeight(path), 200.0, DELTA);
        assertEquals(path.size(), 2);
    }

    /**
     * test for shortest path
     */
    @Test
    public void testShortestPath(){
        BFS bfs1 = new BFS();
        List<Transport> list1 = new LinkedList<Transport>();
        list1.add(this.bosDur);
        Assert.assertEquals(list1, bfs1.getPath(this.graph1, this.boston, this.durham));
        List<Transport> list2 = new LinkedList<Transport>();
        list2.add(this.dcNyc);
        Assert.assertEquals(list2, bfs1.getPath(this.graph2, this.dc, this.newyorkcity));
        List<Transport> list3 = new LinkedList<Transport>();
        list3.add(this.wasChi);
        list3.add(this.chiProv);
        Assert.assertEquals(list3, bfs1.getPath(this.graph1, this.washington, this.providence));

        //tie case where returned is first occurance of transport object
        List<Transport> list4 = new LinkedList<Transport>();
        list4.add(this.provBost);
        Assert.assertEquals(list4, bfs1.getPath(this.graph2, this.providence2, this.boston2));
    }

    /**
     * test for shortest path when source and destination are the same city
     */
    @Test
    public void testShortestPathSameCity(){
        BFS bfs1 = new BFS();
        Assert.assertEquals(new LinkedList<Transport>(), bfs1.getPath(this.graph2, this.dc, this.dc));
        Assert.assertEquals(new LinkedList<Transport>(), bfs1.getPath(this.graph1, this.boston, this.boston));
    }
    /**
     * test for shortest path when destination is not on the graph
     */
    @Test
    public void testShortestPathNoCity(){
        BFS bfs1 = new BFS();
        Assert.assertEquals(new LinkedList<Transport>(), bfs1.getPath(this.graph2, this.ithaca, this.dc));
        Assert.assertEquals(new LinkedList<Transport>(), bfs1.getPath(this.graph1, this.chicago, this.durham));
        City la = new City("LA");
        Assert.assertEquals(new LinkedList<Transport>(), bfs1.getPath(this.graph2, this.ithaca, la));
        Assert.assertEquals(new LinkedList<Transport>(), bfs1.getPath(this.graph1, this.boston, la));
    }
}
