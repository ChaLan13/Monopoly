package fenetre;

import java.util.Scanner;

public abstract class Affichage {

	public static void print(String message) {
		System.out.println(message);
		
	}
	
	public static boolean getBool() {
		Scanner sc = new Scanner(System.in);
		sc.useDelimiter(System.getProperty("line.separator"));
		
		boolean suite = true;
		
		while(suite){
			suite= false;
			String rep = sc.nextLine();
			switch(rep){
				case "o":
				case "O":
				case "y":
				case "Y":
				case "yes":
				case "YES":
				case "oui":
				case "OUI":
				case "Oui":
				case "Yes":
					sc.close();
					return true;
				
				case "n":
				case "N":
				case "no":
				case "NO":
				case "non":
				case "NON":
				case "Non":
				case "No":
					sc.close();
					return false;
					
				default : 
					print("La saisie est incorrecte."); 
					suite=true; 
			}	
		}
		sc.close();
		return false;
	}
	
}
