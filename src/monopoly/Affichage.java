package monopoly;

public interface Affichage {
	
	public void premierLancer(int lancer, int i);
	public void joueurQuiCommence(int i, int nb);
	public String intToString(int n);
	public boolean demandeCarteSortiePrison();
	void print(String message);
	boolean getBool();
	int getInt(String message);

}
