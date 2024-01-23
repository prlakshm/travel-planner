# Travel Planner

## Project Details:

**Project name:** Travel Planner

**Team members:** Pranavi Lakshminarayanan and Nayani Modugula

**Project Description:** Given a network of cities and transport routes, this program helps identify the cheapest, 
fastest, and most direct route. The cheapest route is found using Dijkstra's algorithm. The fastest route (smallest 
travel time) and most direct route (least amount of layover stops) is found using BFS. 

**Estimated Completion Time:** 20 hours

## How-to:

Run Main.java in the "src" directory. 
Enter the command: 
>"load [cities.csv filename] [transport.csv filname]"

> Ex. "load cities1.csv transport1.csv"

This commands loads the city and transport network. The city 
and transport csv file set MUST MATCH (i.e. have the same number after them).

Then, enter any of the following:
> "cheap [origin] [destination]" -- Ex. "cheap Providence NewYorkCity"

> "direct [origin] [destination]" -- Ex. "direct Boston Providence"

> "fast [origin] [destination]" -- Ex. "direct NewYorkCity Providence"

The program will return the computed route (if any). 

Run Visualizer.java in the "src" directory to create a visual graph of a city transport network.
Within the file, there is a Main method. In the main method, you can change the cities.csv filename and transport.csv 
filename to graph the desired network. You can also change the output file path if needed. 
Right now the code reads:

> String citiesFilepath = "cities4.csv";
<br> String transportFilepath = "transport4.csv";
<br> String outputFilepath = "data/graphs/graph2.dot";

The graph will be written as a dotfile. To view this file, the DOT Language plugin must be installed in Intellij. This 
plugin allows you to view the file contents and the graph preview side-by-side. Graphviz must also be installed on the 
computer. If a user prefers another method of to view dotfiles, that is okay as well. 

## File Structure:

**"Sol" directory:** Contains classes that set up data structures and implement algorithms.

**"Src" directory:** Contains all interfaces. Also includes classes that provide with user interaction.

**"Test" directory:** Contains all test suites.


## Tests:

In the "test" directory, BFSTest.java tests the BFS algorithm. DijkstraTest.java tests the Dijkstra algorithm.
GraphTest.java tests that when edges and vertices are added to a graph, that the graph is connected properly. All tests
All suites are tested on SimpleGraphs, which are created using SimpleVertex and SimpleEdge objects. These classes 
are simple implementations of the IGraph, IVertex, and IEdge interfaces. These classes allow us to test our program and 
see that our methods manipulate and operate on the data structures correctly. 


## Errors and Bugs: 

There are currently no bugs that we know of in the code. We fixed all the ones we found, but there might still be more 
lurking. Below are some bugs we encountered and worked through that might help someone reviewing our code:

Previously, when Visualizer.java wrote the dotfile, there was an error that was logged describing a problem during
rasterization. The graph was still getting created properly, it was just that an error was also getting logged. After 
shutting down and restarting Intellij, the error went away.

The BFS getPath method computed the wrong route due to a busy waiting bug in the while loop checking if the toCheck
queue was empty. In order to prevent this, we had to sleep the tread for 1 millisecond to allow the CPU processes to 
synchronize. Though we fixed this bug, we acknowledge that this line might throw off other looking at our code.

