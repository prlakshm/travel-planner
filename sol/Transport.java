package sol;

import src.IEdge;
import src.ITransport;
import src.IVertex;
import src.TransportType;

/**
 * A Transport class representing the edge of a travel graph
 */
public class Transport implements IEdge<City>, ITransport {
    private City source;
    private City destination;
    private TransportType type;
    private double price;
    private double minutes;

    /**
     * Constructor for Transport
     * @param source  Source city (for this edge)
     * @param destination Destination city (for this edge)
     * @param type Type/method of transport
     * @param price The price
     * @param minutes The time in minutes
     */
    public Transport(City source, City destination, TransportType type, double price,
                     double minutes) {
        this.source = source;
        this.destination = destination;
        this.type = type;
        this.price = price;
        this.minutes = minutes;
    }

    /**
     * getter for Source
     * @return the source of the Transport
     */
    @Override
    public City getSource() {
        return this.source;
    }

    /**
     * getter for Target
     * @return the target of the Transport
     */
    @Override
    public City getTarget() {
        return this.destination;
    }

    /**
     * getter for price
     * @return the price of the Transport
     */
    @Override
    public double getPrice() {
        return this.price;
    }

    /**
     * getter for Minutes
     * @return the time taken in minutes by the Transport
     */
    @Override
    public double getMinutes() {
        return this.minutes;
    }

    /**
     * getter for Type
     * @return the type of the Transport
     */
    @Override
    public String getType() {
        return this.type.getLabel();
    }

    /**
     * converts Transport class to String
     * @return String representation of Transport
     */
    @Override
    public String toString() {
        return this.getSource().toString() + " -> " + this.getTarget().toString() +
                ", Type: " + this.getType() +
                ", Cost: $" + this.getPrice() +
                ", Duration: " + this.getMinutes() + " minutes";
    }

}
