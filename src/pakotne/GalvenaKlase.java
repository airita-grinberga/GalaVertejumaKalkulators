package pakotne;

import java.text.DecimalFormat;
import java.util.Scanner;

public class GalvenaKlase {
	static Scanner scan = new Scanner(System.in);
	static int studSk = 0, kritSk = 0;
	static String[] studenti = null;
	static String[] kriteriji = null;
	static int[] kriterijaSvars = null;
	static int[][] kriterijaVertejums = null;
	static double[] semestraVertejums = null;
	

	// Audzēkņu ievade
	static void SkolenuSaraksts () {
		do {
			System.out.println("Cik studentiem aprēķināsi gala vērtējumu?");
			while(!scan.hasNextInt()) {
				System.out.println("Cik studentiem aprēķināsi gala vērtējumu?");
				scan.next();
			}
			studSk = scan.nextInt();
		}while(studSk<1);
		studenti = new String[studSk]; 
		
		scan.nextLine();
		// Ievada audzēkņu vārdus, uzvārdus
		for(int i=0; i<studenti.length; i++) {
			do {
				System.out.println("Ievadi "+(i+1)+". studentu");
				studenti[i] = scan.nextLine().trim();
			} while(!studenti[i].matches("^[\\p{L} ]+$"));
		}
	}
	
	// Vērtēšanas kritēriju ievade
	static void VertesanasKriteriji() {
		do {
			System.out.println("Kāds būs kritēriju skaits?");
			while(!scan.hasNextInt()) {
				System.out.println("Kāds būs kritēriju skaits?");
				scan.next();
			}
			kritSk = scan.nextInt();
		}while(kritSk<1);
		
		kriteriji = new String[kritSk];
		kriterijaSvars = new int[kritSk];
		kriterijaVertejums = new int[studSk][kritSk];
		semestraVertejums = new double[studSk];
		
		scan.nextLine();
		// Definē kritērijus
		for(int i=0; i<kriteriji.length; i++) {
			do {
				System.out.println("Ievadi "+(i+1)+". kritēriju");
				kriteriji[i] = scan.nextLine().trim();
			} while(!kriteriji[i].matches("^[\\p{L} ]+$"));
		}
	}
	
	// Vērtēšanas kritēriju svara ievade
	static void VertKritSvars() {
		int maxSvars = 100, sk = 1;
		double atlSvars;
		
		for(int i=0; i<kriteriji.length; i++) {
			// Norāda katra kritērija svaru
			do {
				System.out.println("Ievadi "+(i+1)+". kritērija svaru (max: "+maxSvars+")");
				while(!scan.hasNextInt()) {
					System.out.println("Ievadi "+(i+1)+". kritērija svaru");
					scan.next();
				}
				kriterijaSvars[i] = scan.nextInt();
				/* Minimālā KATRA ATLIKUŠĀ kritērija svars ir 5
				 * kopējai svaru vērtībai ir jābūt 100 (ne mazāk, ne vairāk)
				*/
				atlSvars = (maxSvars - kriterijaSvars[i]) / (double)(kriteriji.length - sk);
			} while(kriterijaSvars[i]>maxSvars || kriterijaSvars[i]<5 || 
				  (i != kriteriji.length-1 && kriterijaSvars[i] == maxSvars) ||
				  (i == kriteriji.length-1 && (maxSvars - kriterijaSvars[i])  > 0) 
				  || atlSvars < 5);
			maxSvars -= kriterijaSvars[i];
			sk++;
			scan.nextLine();
		}
	}
	
	// Vērtējumu ievade
	static void VertejumuIevade() {
		// Norāda vērtējumu kādu ieguvis katrs audzēknis par katru kritēriju
		for(int i=0; i<kriterijaVertejums.length; i++) {
			for(int j=0; j<kriterijaVertejums[i].length; j++) {
				do {
					System.out.println("Ievadi "+studenti[i]+" vērtējumu par kritēriju "+kriteriji[j]);
					while(!scan.hasNextInt()) {
						System.out.println("Ievadi "+studenti[i]+" vērtējumu par kritēriju "+kriteriji[j]);
						scan.next();
					}
					kriterijaVertejums[i][j] = scan.nextInt();
				}while(kriterijaVertejums[i][j]<0 || kriterijaVertejums[i][j]>10);
			}
		}
	}
	
	// Gala vērtējuma aprēķināšana
	static void GalaVertAprekins() {
		double rezultats;
		for(int i=0; i<studenti.length; i++) {
			rezultats=0;
			for(int j=0; j<kriteriji.length; j++) {
				rezultats += ((double) kriterijaSvars[j]/100)*kriterijaVertejums[i][j];
			}
			semestraVertejums[i] = rezultats;
		}
		GalaVertIzvade();
	}
	
	// Gala vērtējumu izvade
	static void GalaVertIzvade() {
		DecimalFormat df = new DecimalFormat("0.#");
		for(int i=0; i<studenti.length; i++) {	
			for(int j=0; j<kriteriji.length; j++) {
				System.out.println("Studenta "+studenti[i]+" vērtējums par kritēriju "+kriteriji[j]+" ir "+kriterijaVertejums[i][j]+", kura svars ir "+kriterijaSvars[j]);
			}
			System.out.println("Semestra vērtējums ir "+df.format(semestraVertejums[i])+" balles"
					+ "\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
		}
	}
	public static void main(String[] args) {
		char izv;
		do {
			System.out.println("\nPieejamās darbības:\n" 
					+ "1 - Ievadīt audzēkņus\n" 
					+ "2 - Ievadīt kritērijus\n"
					+ "3 - Ievadīt kritēriju svaru\n" 
					+ "4 - Ievadīt vērtējumus\n"
					+ "5 - Labot kritēriju\n"
					+ "6 - Labot kritērija svaru\n"
					+ "7 - Labot iegūto vērtējumu\n"
					+ "8 - Aprēķināt gala vērtējumu\n"
					+ "9 - Saglabāt rezultātus failā\n"
					+ "0 - Nolasīt rezultātus no faila\n"
					+ "x - Apturēt programmu\n");
					
			System.out.print("Tava izvēle: ");
			izv = scan.next().charAt(0);
			izv = Character.toLowerCase(izv);
			
			switch(izv) {
			case '1': SkolenuSaraksts(); break;
			case '2': VertesanasKriteriji(); break;
			case '3': VertKritSvars(); break;
			case '4': VertejumuIevade(); break;
			case '5': ; break;
			case '6': ; break;
			case '7': ; break;
			case '8': GalaVertAprekins(); break;
			case '9': ; break;
			case '0': ; break;
			case 'x' :System.out.println("Programma apturēta!"); break;
			default: System.out.println("Darbība nepastāv!");
				
			}
		} while (izv != 'x');
		

		scan.close();
	}
}