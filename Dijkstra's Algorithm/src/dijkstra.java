import java.util.ArrayList;
import java.util.List;


public class dijkstra {

	public static void main(String[]args) {

		// Assumption: Finding shortest path from vertex A to other vertices
		// -1 Represents Infinity
		
		// Create vertices
		Vertex a = new Vertex();
		Vertex b = new Vertex();
		Vertex c = new Vertex();
		Vertex d = new Vertex();
		Vertex e = new Vertex();
		Vertex f = new Vertex();
		
		//Create a list that will contain all the vertices
		List<Vertex>vertexList = new ArrayList<>();
		vertexList.add(a);
		vertexList.add(b);
		vertexList.add(c);
		vertexList.add(d);
		vertexList.add(e);
		vertexList.add(f);
		
		//Add values to the adjaceny matrices of each vertex
		//An adjaceny matrix of a vertex holds the distance from one vertex to another vertex
		a.adj.add(-1); 
		a.adj.add(80); 
		a.adj.add(-1); 
		a.adj.add(10); 
		a.adj.add(-1); 
		a.adj.add(-1); 
		
		b.adj.add(-1); 
		b.adj.add(-1); 
		b.adj.add(20); 
		b.adj.add(-1); 
		b.adj.add(-1); 
		b.adj.add(-1); 
		
		c.adj.add(-1); 
		c.adj.add(-1); 
		c.adj.add(-1); 
		c.adj.add(-1); 
		c.adj.add(-1); 
		c.adj.add(-1); 
		
		d.adj.add(-1); 
		d.adj.add(-1); 
		d.adj.add(50); 
		d.adj.add(-1); 
		d.adj.add(100); 
		d.adj.add(200);
		
		e.adj.add(5); 
		e.adj.add(-1); 
		e.adj.add(-1); 
		e.adj.add(-1); 
		e.adj.add(-1); 
		e.adj.add(40); 
	
		f.adj.add(-1); 
		f.adj.add(-1); 
		f.adj.add(10); 
		f.adj.add(-1); 
		f.adj.add(-1); 
		f.adj.add(-1); 

		
		//Set distance value and knowns for each vertex
		for(int i = 0; i < vertexList.size(); i++) {
			vertexList.get(i).dist = -1;       //All vertices will begin with a -1 distance
			vertexList.get(i).known = false;   //All vertices will have begin with a false known
		}
		
		//A will be the source vertex and its distance will be set to 0
		a.dist = 0;
		
		while(unknownD(vertexList) == true) {   //While there exists a vertex with a known that is false
			Vertex v = vertexList.get(smallestUknownDistance(vertexList));	 //Find the vertex with smallest distance that has a false known
			v.known = true;   //Set the known of the vertex to true
			
			//create adjaceny arraylist
			List<Vertex>adjVertex = new ArrayList<>();        //Each Vertex v will have an adjVertex list
			List<Integer>adjVertexIndex = new ArrayList<>();  //Each Vertex v will have an adjVertexIndex list
			
			for(int i = 0; i < vertexList.size(); i++) {   //Loop through all vertices
				if(v.adj.get(i)!= -1) {                    //If Vertex v contains a vertex in its adjaceny matrix whose value is not equal to -1
					adjVertex.add(vertexList.get(i));      //Then add the found vertex to the new adjVertex list
					adjVertexIndex.add(i);				   //Also add the index of found vertex to the adjVertexIndex list 
				}
			}
			 
			for(int i = 0; i < adjVertex.size(); i++) {          //Loop through the adjVertex list 
				if(!adjVertex.get(i).known) {                    //If there is a vertex in the adjVertex list that contains a false known
					Vertex w = adjVertex.get(i);                 //Then create a Vertex w that will hold the found vertex
					int cvw = v.adj.get(adjVertexIndex.get(i));  //Also get the distance from v to w and set it to the variable cvw 
					if(v.dist + cvw < w.dist || w.dist == -1) {  //If the sum of cvw and Vertex v distance is less than Vertex w distance or w is equal to -1
						w.dist = v.dist + cvw;                   //Then set the Vertex w distance equal to the sum of the Vertex v distance and cvw
						w.path = v;                              //Also set the parent of Vertex w to Vertex v
					}
				}
			}
		}
		
		System.out.println("0 = A, 1 = B, 2 = C, 3 = D, 4 = E, 5 = F");  //The vertices are represented by numbers. For example, Vertex A = 0
		System.out.println();
		
		//Create Final Table of the Results
		System.out.println("Vertex  " + "Distance   " + "Parent   " + "Known");
		for(int i = 0; i < vertexList.size(); i++) {
			System.out.print(i + "          " + vertexList.get(i).dist + "       ");
			for(int j = 0; j < vertexList.size(); j++) {
				if(vertexList.get(i).path == null) {
					System.out.print("   -");
					break;
				}
				if(vertexList.get(i).path == vertexList.get(j)) {
					System.out.print("  " + j);
					break;
				}
			}
			System.out.print("      " + vertexList.get(i).known);
			System.out.println();
		}
		System.out.println();
		
	}
	

	//Find the vertex that has a known that is false and that has the smallest distance out of all of them that is not -1
	//Return the index of that vertex
	public static int smallestUknownDistance(List<Vertex>a) {  

		List<Integer>indexL = new ArrayList<>();
		List<Vertex>minimums = new ArrayList<>();
		
		for(int i = 0; i < a.size(); i++) {   //Loop through the vertex list
			if(a.get(i).known == false && a.get(i).dist!= -1) {  //If a vertex has a false known and its distance is not equal to -1 (infinity)
				indexL.add(i);            //Add the index of the vertex to the indexL list that meets the if condition 
				minimums.add(a.get(i));   //Add the vertex to the minimums list that meets the if condition
			}
		}
		
		//Now, must find the vertex that has the smallest distance
		int minimum = minimums.get(0).dist;  //minimum holds the distance of the vertex
		int index = indexL.get(0);           //index holds the index of minimum distance vertex
		for(int i =0; i < minimums.size(); i++) {  //Loop through minimums list
			if(minimum > minimums.get(i).dist) {   //Compare the minimum distance of one vertex to another vertex in the minimum list 
				minimum = minimums.get(i).dist;
				index = indexL.get(i);
			}
		}
		
		return index;
	}
	
	//Loop through list containing vertices and return true if there is a vertex with a false known
	//Else, return false if there is no vertex has a false known 
	public static boolean unknownD(List<Vertex>a) {  
		
		for(int i = 0; i <a.size(); i++ ) {
			if(a.get(i).known == false) {
				return true;
			}
		}
		return false; 
	}
	
}
