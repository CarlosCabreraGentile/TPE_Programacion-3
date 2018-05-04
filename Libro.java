package tpe;

public class Libro {
	protected String nombre;
	protected String autor;
	protected String cantHojas;
	protected String[] generos;
	
	public Libro(String nombre,String autor,String cantHojas,String[]generos) {
		this.nombre = nombre;
		this.autor = autor;
		this.cantHojas = cantHojas;
		this.generos = generos;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getAutor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public String getCantHojas() {
		return cantHojas;
	}
	
	public void setCantHojas(String cantHojas) {
		this.cantHojas = cantHojas;
	}
	
	public String[] getGeneros() {
		return generos;
	}
	
	public void setGeneros(String[] generos) {
		this.generos = generos;
	} 
	
}
