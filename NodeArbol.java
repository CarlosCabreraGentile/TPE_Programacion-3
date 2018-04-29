

public class NodeArbol {
	private Indice info;
	private NodeArbol nodeDerecha;
	private NodeArbol nodeIzquierda;

	public NodeArbol(Indice o) {
		info = o;
		nodeDerecha = null;
		nodeIzquierda = null;
	}

	public NodeArbol(Indice o, NodeArbol n , NodeArbol p) {
		setInfo(o);
		setNodeDer(n);
		setNodeIzq(p);
	}

	public void setNodeIzq(NodeArbol p) {
		// TODO Auto-generated method stub
		nodeIzquierda = p; 
	}

	public void setInfo(Indice o) {
		info = o;
	}

	public void setNodeDer(NodeArbol n) {
		nodeDerecha = n;
	}

	public Indice getInfo() {
		return info;
	}

	public NodeArbol getNodeDer() {
		return nodeDerecha;
	}
	
	public NodeArbol getNodeIzq() {
		return nodeIzquierda;
	}
	
}
