public class ArbolBinario {
	NodeArbol root;

	public ArbolBinario() {
		root = null;
	}

	public Object getRoot() {
		return root.getInfo();
	}
	/*corresponde a la entrega*/
	public Genero hasElem(String o) {
		return hasElem(root, o);
	}
	/*corresponde a la entrega*/
	private Genero hasElem(NodeArbol raiz, String o) {
		Genero hasElement = null;
		if (raiz != null) {
			if (o.equals(raiz.getInfo().getGenero())) {
				return raiz.getInfo();
			} else if (raiz.getInfo().getGenero().compareTo(o) == -1) {
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
	/*corresponde a la entrega*/
	public void insert(Genero o) {
		NodeArbol tmp = new NodeArbol(o);
		if (isEmpty()) {
			root = tmp;
		} else {
			insert(root, tmp);
		}
	}
	/*corresponde a la entrega*/
	private void insert(NodeArbol raiz, NodeArbol tmp) {
		if (raiz.getInfo().getGenero().compareTo(tmp.getInfo().getGenero()) == -1  ) {
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

//	public boolean delete(int o) {
//		boolean delete = false;
//		NodeArbol aux = root;
//		if (root.getInfo() == o) {
//			if ((root.getNodeDer() != null)
//					&& ((root.getNodeDer().getNodeIzq() != null) || (root.getNodeDer().getNodeDer() != null))) {
//				root = realizarCorrimientos(root, o);
//				delete = true;
//			} else if ((root.getNodeDer() != null)
//					&& ((root.getNodeDer().getNodeIzq() == null) && (root.getNodeDer().getNodeDer() == null))) {
//				root = aux.getNodeDer();
//				root.setNodeIzq(aux.getNodeIzq());
//			} else {
//				root = root.getNodeIzq();
//			}
//
//		} else {
//			delete = delete(root, o);
//		}
//		return delete;
//	}
//
//	public boolean delete(NodeArbol raiz, int o) {
//		boolean delete = false;
//		if (raiz != null) {
//			if ((raiz.getInfo() > (int) o) && (raiz.getNodeIzq() != null)) {
//				if (raiz.getNodeIzq().getInfo()==o) {
//					raiz.setNodeIzq(realizarCorrimientos(raiz.getNodeIzq(), o));
//					delete = true;
//				} else {
//					delete = delete(raiz.getNodeIzq(), o);
//				}
//			} else if (( raiz.getInfo() <  o) && (raiz.getNodeDer() != null)) {
//				if (raiz.getNodeDer().getInfo()==o) {
//					raiz.setNodeDer(realizarCorrimientos(raiz.getNodeDer(), o));
//					delete = true;
//				} else {
//					delete = delete(raiz.getNodeDer(), o);
//				}
//			}
//		}
//		return delete;
//	}
//
//	private NodeArbol realizarCorrimientos(NodeArbol raiz, Object o) {
//		// TODO Auto-generated method stub
//		NodeArbol auxRaiz = raiz;
//		if ((raiz.getNodeDer() == null) && (raiz.getNodeIzq() == null)) {
//			raiz = null; // tengo que borrarme de mi padre (ojo si soy la raiz)
//		} else if ((raiz.getNodeDer() != null) && (raiz.getNodeIzq() == null)) {
//			raiz = raiz.getNodeDer(); // padre.sethijo(raiz.getNodoDer)
//		} else if ((raiz.getNodeDer() == null) && (raiz.getNodeIzq() != null)) {
//			raiz = raiz.getNodeIzq(); // padre.sethijo(raiz.getNodoDer)
//		} else {
//			if (raiz.getNodeDer().getNodeIzq() == null) {
//				raiz = raiz.getNodeDer();
//				raiz.setNodeIzq(auxRaiz.getNodeIzq());
//			} else {
//				NodeArbol auxPadre = buscarMasIzqDeSubarbol(raiz.getNodeDer());
//				NodeArbol auxRaizIzq = raiz.getNodeIzq();
//				if (auxPadre.getNodeIzq() == null) {
//					raiz = auxPadre;
//					raiz.setNodeDer(raiz.getNodeDer());
//					raiz.setNodeIzq(auxRaizIzq);
//				} else {
//					raiz = auxPadre.getNodeIzq();
//					auxPadre.setNodeIzq(/* auxPadre.getDerecho() */null);
//					raiz.setNodeDer(auxPadre);
//					raiz.setNodeIzq(auxRaizIzq);
//				}
//			}
//
//		}
//		return raiz;
//	}
//
//	public NodeArbol buscarMasIzqDeSubarbol(NodeArbol aux) {
//		if (aux.getNodeIzq() == null) {
//			return aux;
//		} else if ((aux.getNodeIzq().getNodeIzq() == null)) {
//			return aux;
//		} else {
//			return buscarMasIzqDeSubarbol(aux.getNodeIzq());
//		}
//
//	}

	public int getHeight() {
		int aux = 0;
		return aux;
	}

	public void printPosOrder() {
		printPosOrder(root);
		System.out.println("");
	}

	private void printPosOrder(NodeArbol raiz) {
		// TODO Auto-generated method stub
		if (raiz != null) {
			order(raiz.getNodeIzq());
			order(raiz.getNodeDer());
			System.out.print(raiz.getInfo() + " - ");
		} else {
			System.out.print(raiz.getInfo() + " - ");
		}
	}

	public void printPreOrder() {
		printPreOrder(this.root);
		System.out.println("");
	}

	private void printPreOrder(NodeArbol root2) {
		if (root2 != null) {
			System.out.print(root2.getInfo() + " ");
			printPreOrder(root2.getNodeIzq());
			printPreOrder(root2.getNodeDer());
		} else {
			System.out.print("- ");
		}
	}

	public void printOrder() {
		order(root);
	}

	private void order(NodeArbol raiz) {
		if (raiz != null) {
			printPreOrder(raiz.getNodeIzq());
			System.out.print(raiz.getInfo() + " ");
			printPreOrder(raiz.getNodeDer());
		} else {
			System.out.print("- ");
		}

	}
}