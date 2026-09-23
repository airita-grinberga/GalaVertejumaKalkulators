package pakotne;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class FailuApstradesKlase {
	// Rezultātu saglabāšana failā
	static void SaglabatFaila(int studSk, String[] studenti, double[] semestraVertejums) {
		DecimalFormat df = new DecimalFormat("0.#");
		try (BufferedWriter raksta = new BufferedWriter(new FileWriter("Rezultati.txt"))) {
			for (int i=0; i<studSk; i++) {
		    	  raksta.write((i+1) + ". skolēna (" + studenti[i] + ") galīgais vērtējums: " + df.format(semestraVertejums[i])+"#");
		      }
		      System.out.println("Ierakstīts failā!");
		    }catch (IOException e) {
		      System.out.println("Problēma rakstot failā!");
		    }
	}
	
	// Rezultātu nolasīšana no faila
	static void NolasitNoFaila() {
		try{BufferedReader lasa = 
					new BufferedReader(new FileReader("Rezultati.txt"));
					String dati = lasa.readLine();
					String[] s = dati.split("#");
					if (s == null)
						System.out.println("Rezultātu fails ir tukšs!");
					else
						for (int i=0; i<s.length; i++) {
							System.out.println(s[i]);
						}
					lasa.close();
				}catch(IOException e){ 
					System.out.println("Problēmas ar datni!"); 
				}

	}
}
