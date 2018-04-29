
public class ArbolBinario {
	NodeArbol root;
	private int contador;

	public ArbolBinario() {
		root = null;
	}

	public Object getRoot() {
		return root.getInfo();
	}
	
	public Indice hasElement(String o) {
		return hasElem(root, o);
	}
	
	private Indice hasElem(NodeArbol raiz, String o) {
		contador++;
		Indice hasElement = null;
		if (raiz != null) {
			if (o.equals(raiz.getInfo().getGenero())) {
				return raiz.getInfo();
			} else if (raiz.getInfo().getGenero().compareTo(o) > 0) {
				hasElement = hasElem(raiz.getNodeIzq(), o);
			} else {
				hasElement = hasElem(raiz.getNodeDer(), o);
			}
		}
		return hasElement;
	}

	public boolean isEmpty() {
		return root == null;
	}
	
	public void insert(Indice o) {
		NodeArbol tmp = new NodeArbol(o);
		if (isEmpty()) {
			root = tmp;
		} else {
			insert(root, tmp);
		}
	}
	
	private void insert(NodeArbol raiz, NodeArbol tmp) {
		if ((raiz.getInfo().getGenero().compareTo(tmp.getInfo().getGenero())) > 0 ) {
			if (raiz.getNodeIzq() != null) {
				insert(raiz.getNodeIzq(), tmp);
			} else {
				raiz.setNodeIzq(tmp);
			}
		} else {
			if (raiz.getNodeDer() != null) {
				insert(raiz.getNodeDer(), tmp);
			} else {
				raiz.setNodeDer(tmp);
			}
		}
	}

	public int getHeight() {
		int aux = 0;
		return aux;
	}

	public void printPreOrder() {
		printPreOrder(this.root);
		System.out.println("");
	}

	private void printPreOrder(NodeArbol root2) {
		if (root2 != null) {
			System.out.print(root2.getInfo().getGenero() + " ");
			printPreOrder(root2.getNodeIzq());
			printPreOrder(root2.getNodeDer());
		} else {
			System.out.print("- ");
		}
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}
}
