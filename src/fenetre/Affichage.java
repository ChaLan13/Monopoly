package fenetre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Affichage {
	BufferedReader sc;

	public Affichage() {
		//scanner close a la fin du main
        sc = new BufferedReader(new InputStreamReader(System.in));
	}

	public void print(String message) {
		System.out.println(message);
	}
	
	public String getString(String message){
		try {
			this.print(message);
			String rep;
			rep = sc.readLine();
			return rep;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	//affiche le message puis demande un booleen
	//ne pas mettre de \n a la fin de message
	//plein de possibilite (o, O, oui, Oui, y, yes, Y, Yes ...)
	public boolean getBool(String message) {
		try {
			boolean suite = true;

			while (suite) {
				suite = false;

				this.print(message + "(o/n)");
				String rep = sc.readLine();
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
					return true;

				case "n":
				case "N":
				case "no":
				case "NO":
				case "non":
				case "NON":
				case "Non":
				case "No":
					return false;

				default:
					print("La saisie est incorrecte.\n");
					suite = true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int getInt(String message){
		return getInt(message, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	public int getInt(String message, int min){
		return getInt(message, min, Integer.MAX_VALUE);
	}
	
	public int getInt(String message, int min, int max){
		try{
			boolean suite = true;
			String rep;
			int repint;

			while (suite) {
				suite = false;

				this.print(message + "(entre " + min + " et " + max + ")");
				rep = sc.readLine();

				try {
					repint = Integer.parseInt(rep);
					if (repint < min || repint > max) {
						print("La saisie est incorrecte.\n");
						suite = true;
					} else
						return repint;
				} catch (NumberFormatException e) {
					print("La saisie est incorrecte.\n");
					suite = true;
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		return min;
	}
}
