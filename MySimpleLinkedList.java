
public class MySimpleLinkedList {
	protected Node first;
	protected int size;
	protected Node cursor;
	protected Node last;
	public MySimpleLinkedList() {
		first = null;
	}

	public void insert(Libro o) {
		Node tmp = new Node(o, null);
		tmp.setNext(first);
		first = tmp;
		cursor = first;
		size++;
	}
	public void insertAlFinal(Libro o) {
		Node tmp = new Node(o, null);
		if(first==null) {
			first=tmp;
			last=tmp;
			cursor = first;
		}else {
			last.setNext(tmp);
			last=tmp;
		}
		size++;
	}
	
	public Libro extract() {
		Node aux = first;
		first = first.getNext();
		return aux.getInfo();
	}

	public void print() {
		Node aux = first;
		for (int i = 0; i < this.size(); i++) {
			System.out.println(aux.getInfo());
			aux = aux.getNext();
		}
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public Libro getNodo() {
		Node tmp = cursor;
		cursor = cursor.getNext();
		return tmp.getInfo();
	}
	public void resetCursor() {
		cursor = first;
	}

	public boolean hasNext() {
		return cursor != null;
	}
	private class Node {
		private Libro info;
		private Node next;
		private Node previous;
		public Node(Libro o, Node n) {
			info = o;
			next = n;
		}
		public void setPrevious(Node p) {
			// TODO Auto-generated method stub
			previous = p; 
		}

		public void setInfo(Libro o) {
			info = o;
		}

		public void setNext(Node n) {
			next = n;
		}

		public Libro getInfo() {
			return info;
		}

		public Node getNext() {
			return next;
		}
		public Node getPrevious() {
			return previous;
		}
	}

}