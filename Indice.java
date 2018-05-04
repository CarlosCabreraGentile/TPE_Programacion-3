

public class Indice {
	protected String genero;
	protected MySimpleLinkedList libros;
	
	public Indice(String genero) {
		this.genero = genero;
		libros = new MySimpleLinkedList();
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public MySimpleLinkedList getLibros() {
		return libros;
	}
	

	
	public void addLibro(Libro l) {
		libros.insertAlFinal(l);
	}
}
