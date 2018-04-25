import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CSVReader { 
 
	public static void main(String[] args) {
		String csvFile = "C:\\Tudai\\2do año\\Programacion 3\\2018\\tpe\\dataset4.csv";
		String line = ""; 
		String cvsSplitBy = ","; 
		ArrayList <Libro>libros =new  ArrayList<Libro>();
		ArrayList <Genero>indice ;
		ArbolBinario indi;
		String [] aux;
		
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			while ((line = br.readLine()) != null) {
				String[] items = line.split(cvsSplitBy);
				if(!items[3].equals("Generos")) {
					aux = generos(items[3]);
					Libro a = new Libro(items[0],items[1],items[2],aux);
					libros.add(a);
				}
				// ---------------------------------------------
				// Poner el codigo para cargar los datos
				// ---------------------------------------------
			}
		} catch (IOException e) {
			e.printStackTrace(); 
		}  
		
//		indice=crearIndice(libros);
//		buscarGenero(indice,"infantil");
//		System.out.println();
//		indi=crearIndiceArbol(libros);
//		buscarGeneroArbol(indi,"infantil");		
		
		printSearch(libros);
		salida(libros);
		
		
	}  
	
	public static void salida(ArrayList<Libro> generosLibro) {
		BufferedWriter bw = null;
		try {
			File file = new File("C:\\Tudai\\2do año\\Programacion 3\\2018\\tpe/salida.csv");
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write("Lista de libros encontrada: \n");
			
			for (int i = 0; i < generosLibro.size(); i++) {
				// Escribo la primer linea del archivo
				String contenidoLinea1 = generosLibro.get(i).autor;
				bw.write(contenidoLinea1);
				bw.newLine();
			}
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		} 
		finally {
			try {
				if (bw != null)
					bw.close();
			} catch (Exception ex) {
				System.out.println("Error cerrando el BufferedWriter" + ex);
			}
		}
		}
	
//	public static String obtenerGenero() {
//		boolean exit = false;
//		String genero = "";
//		
//		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
//		
//		
//			try {
//				System.out.println ("Ingrese genero: o exit para salir");
//				genero = new String(entrada.readLine());
////				exit = true;
//			} 
//			catch (Exception exc ) {
//				System.out.println(exc);
//				exit = false;
//			}
//			
//			while(!exit) {
//			switch (genero) {
//			case "humor": exit = true;
//			case "infantil": exit = true;
//			case "thriller": exit = true;;
//			case "investigacion": exit = true;
//			case "ensayo": exit = true;
//			case "cine": exit = true;
//			case "fantasia": exit = true;
//			case "biografia": exit = true;
//			case "poesia": exit = true;
//			case "politica": exit = true;
//			case "fotografia": exit = true;
//			case "gastronomia": exit = true;
//			case "religion": exit = true;
//			case "drama": exit = true;
//			case "juegos": exit = true;
//			case "leyendas": exit = true;
//			case "filosofia": exit = true;
//			case "economia": exit = true;
//			case "arte": exit = true;
//			case "historia": exit = true;
//			case "musica": exit = true;
//			case "ciencia": exit = true;
//			case "negocios": exit = true;
//			case "psicologia": exit = true;
//			case "educacion": exit = true;
//			case "policial": exit = true;
//			case "terror": exit = true;
//			case "viajes": exit = true;
//			case "tecnologia": exit = true;
//			case "informatica": exit = true;
//			case "novela": exit = true;
//			case "relatos": exit = true;
//			case "romance": exit = true;
//			case "ficcion": exit = true;
//			case "moda": exit = true;
//			case "periodismo": exit = true;
//			case "exit": exit = true;
//			default : System.out.println("Ingrese un genero correcto");
//			}
//		
//		
//		}
//			return genero;
//	}
	

	public static void printMatriz(ArrayList <String[]>libros) {
		for (int i = 0; i < libros.size(); i++) {
			for (int j = 0; j < libros.get(i).length; j++) {
				System.out.print(libros.get(i)[j]+" ");
				
			}
			System.out.println();
		} 
	}

	private static void printSearch(ArrayList<Libro> libros) {
		for (int i = 0; i < libros.size(); i++) {
			System.out.print(libros.get(i).getNombre() + " ");
			System.out.println(" ");
		}
		
	}

	public static void buscarGenero(ArrayList<Genero> indice, String genero) {
		ArrayList<Libro> retorno = new ArrayList<Libro>();
		for (int i = 0; i < indice.size(); i++) {
			if(genero.equals(indice.get(i).getGenero())) {
				retorno = indice.get(i).getLibros();
			}
		}
		if(!retorno.isEmpty()) {
			printSearch(retorno);
		}
	}
	public static void buscarGeneroArbol(ArbolBinario indice, String genero) {
		ArrayList<Libro> retorno;
		Genero aux;
		aux = indice.hasElem(genero);
		retorno = aux.getLibros();
		if(!retorno.isEmpty()) {
			printSearch(retorno);
		}
	}
	public static ArrayList <Genero> crearIndice(ArrayList <Libro> libros) {
		ArrayList <Genero>retorno =new  ArrayList<Genero>();
		String [] arrGeneros ;
		for (int i = 0; i < libros.size(); i++) {
			arrGeneros = libros.get(i).getGeneros();
			if(retorno.isEmpty()) {
				retorno=(crearGenero(arrGeneros));
			}else {
				agregarGenero(retorno,arrGeneros);
			}
			agregarLibroAlGenero(retorno,arrGeneros,libros.get(i));
		}
		return retorno;
	}
	public static ArbolBinario crearIndiceArbol(ArrayList <Libro> libros) {
		ArbolBinario retorno =new  ArbolBinario();
		String [] arrGeneros ;
		for (int i = 0; i < libros.size(); i++) {
			arrGeneros = libros.get(i).getGeneros();
			if(retorno.isEmpty()) {
				retorno=(crearGeneroArbol(arrGeneros));
			}else {
				agregarGeneroArbol(retorno,arrGeneros);
			}
			agregarLibroAlGeneroArbol(retorno,arrGeneros,libros.get(i));
		}
		return retorno;
	}

	private static void agregarLibroAlGenero(ArrayList<Genero> retorno, String[] arrGeneros, Libro libro) {
		// TODO Auto-generated method stub
		for (int i = 0; i < arrGeneros.length; i++) {
			for (int j = 0; j < retorno.size(); j++) {
				if(retorno.get(j).getGenero().equals(arrGeneros[i])) {
					retorno.get(j).addLibro(libro);
				}
			}
		}
	}
	private static void agregarLibroAlGeneroArbol(ArbolBinario retorno, String[] arrGeneros, Libro libro) {
		// TODO Auto-generated method stub
		Genero aux;
		for (int i = 0; i < arrGeneros.length; i++) {
			aux=retorno.hasElem(arrGeneros[i]);
			aux.addLibro(libro);
		}
	}

	private static void agregarGenero(ArrayList<Genero> retorno, String[] arrGeneros) {
		// TODO Auto-generated method stub
		int tamAux=retorno.size();
		for (int i = 0; i < arrGeneros.length; i++) {
			int j = 0;
			boolean hasGenero = false;
			while((j < tamAux)&&(!hasGenero)) {
				if((retorno.get(j).getGenero().equals(arrGeneros[i]))) {
					hasGenero=true;
				}
				j++;
			}
			if(!hasGenero) {
				Genero g = new Genero(arrGeneros[i]);
				retorno.add(g);
			}
		}
	}
	private static void agregarGeneroArbol(ArbolBinario retorno, String[] arrGeneros) {
		// TODO Auto-generated method stub
		for (int i = 0; i < arrGeneros.length; i++) {
			if(retorno.hasElem(arrGeneros[i])==null) {
				Genero g = new Genero(arrGeneros[i]);
				retorno.insert(g);
			}
		}
	}


	private static ArrayList <Genero> crearGenero(String[] arrGeneros) {
		// TODO Auto-generated method stub
		ArrayList <Genero>retorno= new ArrayList <Genero>();
		for (int i = 0; i < arrGeneros.length; i++) {
			Genero g= new Genero(arrGeneros[i]);
			retorno.add(g);
		}
		return retorno;
	}
	private static ArbolBinario crearGeneroArbol(String[] arrGeneros) {
		// TODO Auto-generated method stub
		ArbolBinario retorno= new ArbolBinario();
		for (int i = 0; i < arrGeneros.length; i++) {
			Genero g= new Genero(arrGeneros[i]);
			retorno.insert(g);;
		}
		return retorno;
	}

	private static String[] generos(String generos) {
		// TODO Auto-generated method stub
		String [] arrGeneros = generos.split(" ");
		return arrGeneros;
	}
	
}  
