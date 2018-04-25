public class NodeArbol {
	private Genero info;
	private NodeArbol nodeDer;
	private NodeArbol nodeIzq;

	public NodeArbol(Genero o) {
		info = o;
		nodeDer = null;
		nodeIzq = null;
	}

	public NodeArbol(Genero o, NodeArbol n , NodeArbol p) {
		setInfo(o);
		setNodeDer(n);
		setNodeIzq(p);
	}

	public void setNodeIzq(NodeArbol p) {
		// TODO Auto-generated method stub
		nodeIzq = p; 
	}

	public void setInfo(Genero o) {
		info = o;
	}

	public void setNodeDer(NodeArbol n) {
		nodeDer = n;
	}

	public Genero getInfo() {
		return info;
	}

	public NodeArbol getNodeDer() {
		return nodeDer;
	}
	public NodeArbol getNodeIzq() {
		return nodeIzq;
	}

	
}