import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Biblioteca {

	public static void main(String[] args) {
		int numeroDataSet = obtenerNumero();
		ArrayList <Genero>indice ;
		ArbolBinario indiceArbolBinario;
		
//		/*ARRANCA EL TIMER*/ 		
// 		Timer timer = new Timer(); 
//		timer.start();
		indice = crearIndice(input(numeroDataSet));
//		timer.stop();
//		
//		double elapsedTime = timer.stop();
//		System.out.println("Tiempo ArrayList" + elapsedTime); 
//		/*TERMINA EL TIMER*/ 
//		
//		/*ARRANCA EL TIMER*/ 		
// 		Timer timer2 = new Timer();
//		timer2.start();
		indiceArbolBinario = crearIndiceArbol(input(numeroDataSet));
//		timer2.stop();
//		
//		double elapsedTime2 = timer2.stop();
//		System.out.println("Tiempo arbol: " + elapsedTime2); 
//		/*TERMINA EL TIMER*/ 
		 
		/*ARRANCA EL TIMER*/ 		
 		Timer timer = new Timer(); 
		timer.start();
		buscarGenero(indice, "terror");
		timer.stop();
		
		double elapsedTime = timer.stop();
		System.out.println("Tiempo ArrayList: " + elapsedTime); 
		/*TERMINA EL TIMER*/ 
		
		/*ARRANCA EL TIMER*/ 		
 		Timer timer2 = new Timer(); 
		timer2.start();
		buscarGeneroArbol(indiceArbolBinario, "terror");
		timer2.stop();
		
		double elapsedTime2 = timer2.stop();
		System.out.println("Tiempo arbol: " + elapsedTime2); 
		/*TERMINA EL TIMER*/ 
		 
//		output(buscarGenero(indice, "terror"));
		 
	}  
	
	public static int obtenerNumero() {
		boolean exit = false;
		int valor = 0;
		
		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
		
		do {
			try {
				System.out.println ("Ingrese numero");
				valor = new Integer(entrada.readLine());
				if((valor > 0) && (valor < 5)) {
					exit = true;
				}
				else {
					System.out.println("Numero incorrecto");
					exit = false;
				}
			}
			catch (NumberFormatException e){
				System.out.println("Numero invalido");
				exit = false;
			}
			catch (Exception exc ) {
				System.out.println( exc );
				exit = false;
			}
		} while (!exit);
		return valor; 
	}

	private static void printSearch(ArrayList<Libro> libros) {
		for (int i = 0; i < libros.size(); i++) {
			System.out.print(libros.get(i).getNombre() + " ");
			System.out.println(" ");
		}
	}

	public static ArrayList<Libro> buscarGenero(ArrayList<Genero> indice, String genero) {
		ArrayList<Libro> retorno = new ArrayList<Libro>();
		for (int i = 0; i < indice.size(); i++) {
			if(genero.equals(indice.get(i).getGenero())) {
				retorno = indice.get(i).getLibros();
			}
		}
		return retorno;
	}
	public static ArrayList<Libro> buscarGeneroArbol(ArbolBinario indice, String genero) {
		ArrayList<Libro> retorno;
		Genero aux;
		aux = indice.hasElem(genero);
		retorno = aux.getLibros();
		return retorno;
	}
	 
	public static ArrayList <Genero> crearIndice(ArrayList <Libro> libros) {
		ArrayList <Genero>retorno = new  ArrayList<Genero>();
		String [] arrGeneros ;
		
		for (int i = 0; i < libros.size(); i++) { 
			arrGeneros = libros.get(i).getGeneros();
		
			agregarGenero(retorno,arrGeneros);
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
	
	public static ArrayList<Libro> input(int numero) {
		String csvFile = "C:\\Tudai\\2do año\\Programacion 3\\2018\\tpe\\dataset" + numero + ".csv";
		String line = ""; 
		String cvsSplitBy = ","; 
		ArrayList <Libro>libros =new  ArrayList<Libro>();

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
		return libros;  
	}
	
	public static void output(ArrayList<Libro> generosLibro) {
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
				String contenidoLinea1 = generosLibro.get(i).nombre;
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

}
