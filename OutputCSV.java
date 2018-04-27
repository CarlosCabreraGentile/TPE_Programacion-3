package tpe;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OutputCSV {
	
	public void salida(ArrayList<String> generosLibro) {
	BufferedWriter bw = null;
	try {
		File file = new File("C:\\Tudai\\2do año\\Programacion 3\\2018\\tpe/salida.csv");
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file);
		bw = new BufferedWriter(fw);

		for (int i = 0; i < generosLibro.size(); i++) {
			// Escribo la primer linea del archivo
			String contenidoLinea1 = generosLibro.get(i);
			bw.write(contenidoLinea1);
			bw.newLine();
		}
		// Escribo la primer linea del archivo
//		String contenidoLinea1 = "Usuario1,Tiempo1";
//		bw.write(contenidoLinea1);
//		bw.newLine();

//		// Escribo la segunda linea del archivo
//		String contenidoLinea2 = "Usuario2,Tiempo2";
//		bw.write(contenidoLinea2);
//		bw.newLine();
		
		/*
		 *
		 * ... 
		 * 
		*/

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
