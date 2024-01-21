package test;

import org.junit.Before;
import org.junit.Test;
import sol.City;
import sol.Transport;
import sol.TravelGraph;
import src.TransportType;
import test.simple.SimpleEdge;
import test.simple.SimpleGraph;
import test.simple.SimpleVertex;

import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Your Graph method tests should all go in this class!
 * The test we've given you will pass, but we still expect you to write more tests using the
 * City and Transport classes.
 * You are welcome to write more tests using the Simple classes, but you will not be graded on
 * those.
 *
 * TODO: Recreate the test below for the City and Transport classes
 * TODO: Expand on your tests, accounting for basic cases and edge cases
 */
public class GraphTest {
    private SimpleGraph graph;

    private SimpleVertex a;
    private SimpleVertex b;
    private SimpleVertex c;
    private SimpleVertex d;

    private SimpleEdge edgeAB;
    private SimpleEdge edgeBC;
    private SimpleEdge edgeCA;
    private SimpleEdge edgeAC;
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
        this.bosProvP = new Transport(this.boston2, this.providence2, TransportType.PLANE, 3., 10.);
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
        this.graph2.addEdge(this.boston, this.bosProvP);
        this.graph2.addEdge(this.boston2, this.bosNyct);
        this.graph2.addEdge(this.boston2, this.bosNycb);
        this.graph2.addEdge(this.providence2, this.provNyc);
        this.graph2.addEdge(this.dc, this.dcProv);
        this.graph2.addEdge(this.dc, this.dcNyc);
        this.graph2.addEdge(this.newyorkcity, this.nycIth);

    }
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
        this.a = new SimpleVertex("A");
        this.b = new SimpleVertex("B");
        this.c = new SimpleVertex("C");
        this.d = new SimpleVertex("D");

        this.graph.addVertex(this.a);
        this.graph.addVertex(this.b);
        this.graph.addVertex(this.c);
        this.graph.addVertex(this.d);

        // create and insert edges
        this.edgeAB = new SimpleEdge(1, this.a, this.b);
        this.edgeBC = new SimpleEdge(1, this.b, this.c);
        this.edgeCA = new SimpleEdge(1, this.c, this.a);
        this.edgeAC = new SimpleEdge(1, this.a, this.c);

        this.graph.addEdge(this.a, this.edgeAB);
        this.graph.addEdge(this.b, this.edgeBC);
        this.graph.addEdge(this.c, this.edgeCA);
        this.graph.addEdge(this.a, this.edgeAC);
    }

    /**
     * Sample test for the graph. Tests that the number of vertices and the vertices in the graph are what we expect.
     */
    @Test
    public void testGetVertices() {
        this.createSimpleGraph();
        System.out.println(this.graph.getVertices());
        System.out.println(this.graph.getOutgoingEdges(this.a));
        // test getVertices to check this method AND insertVertex
        assertEquals(4,this.graph.getVertices().size());
        assertTrue(this.graph.getVertices().contains(this.a));
        assertTrue(this.graph.getVertices().contains(this.b));
    }

    /**
     * Sample test for the city/transport graph. Tests that the number of vertices and the vertices in the graph are what we expect.
     */
    @Test
    public void testGetVertices2() {
        System.out.println(this.graph1.getOutgoingEdges(this.boston));
        System.out.println(this.graph1.getOutgoingEdges(this.providence));
        // test getVertices to check this method AND insertVertex
        assertEquals(5, this.graph1.getVertices().size());
        assertTrue(this.graph1.getVertices().contains(this.washington));
        assertTrue(this.graph1.getVertices().contains(this.chicago));
        assertFalse(this.graph1.getVertices().contains(this.c));
    }

    /**
     * tests graph edge getter methods getEdgeSource and getEdgeTarget
     */
    @Test
    public void testGetEdges() {
        this.createSimpleGraph();
        System.out.println(this.graph.getEdgeSource(this.edgeAB));
        System.out.println(this.graph.getEdgeTarget(this.edgeAB));
        System.out.println(this.graph.getOutgoingEdges(this.b));
        // test getEdgeSource and getEdgeTarget to check this method AND addEdge
        this.createSimpleGraph();
        assertEquals(this.c, this.graph.getEdgeSource(this.edgeCA));
        assertEquals(this.c, this.graph.getEdgeTarget(this.edgeBC));
        assertEquals(this.a, this.graph.getEdgeTarget(this.edgeCA));
    }

    /**
     * tests graph edge getter methods getEdgeSource and getEdgeTarget
     */
    @Test
    public void testGetEdges2() {
        System.out.println(this.graph2.getEdgeSource(this.bosNycb));
        System.out.println(this.graph2.getEdgeTarget(this.bosNycb));
        System.out.println(this.graph2.getOutgoingEdges(this.boston));
        // test getEdgeSource and getEdgeTarget to check this method AND addEdge
        assertEquals(this.boston2, this.graph2.getEdgeSource(this.bosProvB));
        assertEquals(this.newyorkcity, this.graph2.getEdgeTarget(this.provNyc));
        assertEquals(this.providence2, this.graph2.getEdgeTarget(this.dcProv));
    }

    /**
     * tests getOutgoingEdges to see if returns correct hashset
     */
    @Test
    public void testOutgoingEdges(){
        this.createSimpleGraph();

        HashSet<SimpleEdge> outgoing = new HashSet<>();
        outgoing.add(this.edgeAB);
        outgoing.add(this.edgeAC);
        assertEquals(outgoing, this.graph.getOutgoingEdges(this.a));

        HashSet<SimpleEdge> outgoing2 = new HashSet<>();
        outgoing2.add(this.edgeCA);
        assertEquals(outgoing2, this.graph.getOutgoingEdges(this.c));

        assertEquals(new HashSet<SimpleEdge>(), this.graph.getOutgoingEdges(this.d));
    }

    /**
     * tests getOutgoingEdges to see if returns correct hashset
     */
    @Test
    public void testOutgoingEdges2(){
        HashSet<Transport> outgoing = new HashSet<>();
        outgoing.add(this.dcProv);
        outgoing.add(this.dcNyc);
        assertEquals(outgoing, this.graph2.getOutgoingEdges(this.dc));

        HashSet<Transport> outgoing2 = new HashSet<>();
        outgoing2.add(this.provBosb);
        outgoing2.add(this.provBost);
        outgoing2.add(this.provNyc);
        assertEquals(outgoing2, this.graph2.getOutgoingEdges(this.providence2));

        assertEquals(new HashSet<SimpleEdge>(), this.graph2.getOutgoingEdges(this.ithaca));
    }

    /**
     * test getCity to see if it returns the city
     * */
    @Test
    public void testGetCity(){
        assertEquals("Providence", this.graph1.getCity("Providence").toString());
        assertEquals("NewYorkCity", this.graph2.getCity("NewYorkCity").toString());
    }
}
