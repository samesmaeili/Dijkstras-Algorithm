import java.util.ArrayList;
import java.util.List;

public class Vertex {

	//Each vertex will have an adjaceny matrix, known, distance, and a vertex path
	public List<Integer> adj;  
	public boolean known;     
	public int dist;           
	public Vertex path;

	public Vertex() {
		adj = new ArrayList<>();
	}
	
	public void setPath(Vertex p) {
		path = p;
	}
	
	public Vertex getPath() {
		return path;
	}
	
	public void setDistance(int distance) {
		dist = distance;
	}
	
	public int getDistance() {
		return dist;
	}
	
	public void setKnown(boolean value) {
		known = value;
	}
	
	public boolean getKnown() {
		return known;
	}
	
	
}
