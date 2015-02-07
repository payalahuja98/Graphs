public class WeightedGraph<T> implements WeightedGraphInterface<T>{
    private T[] vertices;
	int numVertices;
	private int[][] edges;
	private static final int NULL_EDGE = 0;
	private boolean[] marks;
	
	
	public boolean isEmpty(){
		if(numVertices == 0){
			return true;
		}
		return false;
	}

	public boolean isFull(){
		if(numVertices == vertices.length){
			return true;
		}
		else{
			return false;
		}
	}

	public void addVertex(T vertex){
		if(!isFull()){
			vertices[numVertices] = vertex;
			for(int i = 0 ; i < numVertices; i++){
				addEdge(vertex,vertices[i],weightIs(vertex,vertices[i]));
			}
			numVertices++;
		}
	}

	public boolean hasVertex(T vertex) {
		if(indexIs(vertex) >= 0){
			return true;
		}
		return false;
	}

	public void addEdge(T fromVertex,T toVertex, int weight) {
		edges[indexIs(fromVertex)][indexIs(toVertex)] = weight;
	}
	
	private int indexIs(T vertex){
		for(int i = 0; i <= numVertices; i++){
			if(vertices[i] == vertex){
				return i;
			}
		}
		return -1;
	}

	public int weightIs(T fromVertex, T toVertex) {
		return edges[indexIs(fromVertex)][indexIs(toVertex)];
	}
	
	public UnboundedQueueInterface<T> getToVertices(T vertex) {
		UnboundedQueueInterface<T> adjacent = new LinkedUnboundedQueue<T>();
		int fromIndex;
		int toIndex;
		fromIndex = indexIs(vertex);
		for(toIndex = 0; toIndex < numVertices; toIndex++){
			if(edges[fromIndex][toIndex] != NULL_EDGE){
				adjacent.enqueue(vertices[toIndex]);
			}
		}
		return adjacent;
	}
	
	public void clearMarks(){
		for(int i = 0; i < marks.length; i++){
			marks[i] = false;
		}
	}

	public void markVertex(T vertex){
		for(int i = 0; i < marks.length; i++){
			marks[i] = true;
		}
	}

	public boolean isMarked(T vertex) {
		if(marks[indexIs(vertex)] == true){
			return true;
		}
		return false;
	}

	public T getUnmarked() {
		for(int i = 0; i < marks.length; i++){
			if(marks[i] == false){
				return vertices[i];
			}
		}
		return null; 
	}
	

}
