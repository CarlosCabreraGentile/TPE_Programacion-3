import java.util.ArrayList;

public class Genero {
	protected String genero;
	protected ArrayList <Libro> libros;
	public Genero(String genero) {
		this.genero = genero;
		libros = new ArrayList<Libro>();
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public ArrayList<Libro> getLibros() {
		return libros;
	}
	public void setLibros(ArrayList<Libro> libros) {
		this.libros = libros;
	}
	public void addLibro(Libro l) {
		libros.add(l);
	}
}