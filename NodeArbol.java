
public class NodeArbol {
	private Indice info;
	private NodeArbol nodeDer;
	private NodeArbol nodeIzq;

	public NodeArbol(Indice o) {
		info = o;
		nodeDer = null;
		nodeIzq = null;
	}

	public NodeArbol(Indice o, NodeArbol n , NodeArbol p) {
		setInfo(o);
		setNodeDer(n);
		setNodeIzq(p);
	}

	public void setNodeIzq(NodeArbol p) {
		// TODO Auto-generated method stub
		nodeIzq = p; 
	}

	public void setInfo(Indice o) {
		info = o;
	}

	public void setNodeDer(NodeArbol n) {
		nodeDer = n;
	}

	public Indice getInfo() {
		return info;
	}

	public NodeArbol getNodeDer() {
		return nodeDer;
	}
	public NodeArbol getNodeIzq() {
		return nodeIzq;
	}

	
}
