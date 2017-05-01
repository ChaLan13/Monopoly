package fenetre;

import java.security.InvalidParameterException;
import java.util.Scanner;

public class Affichage {
	Scanner sc;

	public Affichage() {
		//scanner close a la fin du main
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		sc.useDelimiter(System.getProperty("line.separator"));
	}

	public void print(String message) {
		System.out.print(message);
	}
	
	public void close(){//Attention rend la classe inutilisable
		sc.close();
	}
	
	public String getString(String message){
		this.print(message + "\n");
		String rep = sc.nextLine();
		return rep;
	}
	
	//affiche le message puis demande un booleen
	//ne pas mettre de \n a la fin de message
	//plein de possibilité (o, O, oui, Oui, y, yes, Y, Yes ...)
	public boolean getBool(String message) {
		boolean suite = true;
		
		while(suite){
			suite= false;
			
			this.print(message + "(o/n)\n");
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
					
				default : 
					print("La saisie est incorrecte.\n"); 
					suite=true; 
			}	
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
		boolean suite = true;
		String rep;
		int repint;
		
		while(suite){
			suite= false;
			
			this.print(message + "(o/n)\n");
			rep = sc.nextLine();
			
			try{
				repint = Affichage.toInt(rep);
				if(repint < min || repint > max){
					print("La saisie est incorrecte.\n");
					suite = true;
				}
				else
					return repint;
			}
			catch(InvalidParameterException e){
				print("La saisie est incorrecte.\n");
				suite = true;
			}
		}
		
		return min;
	}
	
	public static int toInt(String m)throws InvalidParameterException{
		if(m == null)
			throw new InvalidParameterException("Affichage.toInt() -> m null");
		if(m == "")
			throw new InvalidParameterException("Affichage.toInt() -> m vide");
		
		char[] entry = m.toCharArray();
		int tmp = 0;
		boolean neg = entry[0] == '-';
		
		for(int i = neg?1:0; i < entry.length; i++){
			if(entry[i] < '0' || entry[i] > '9')
				throw new InvalidParameterException("Affichage.toInt() -> m invalide");
			
			tmp*=10;
			
			if(neg)
				tmp-=entry[i] - '0';
			else
				tmp+=entry[i] - '0';
		}
		
		return tmp;
	}
}
