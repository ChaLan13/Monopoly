package terrain;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class Gare extends Propriete {
	private ArrayList<Gare> autres = new ArrayList<Gare>();
	private int val[] = {25, 50, 100, 200};
	
	
	public Gare(int prix) throws InvalidParameterException {
		super(prix);
	}
	
	public void add(Gare gare) throws InvalidParameterException {
		if(gare == null)
			throw new InvalidParameterException("Gare.add() -> Param NULL");
		autres.add(gare);
	}

	@Override
	int valeur(int scoreDe) {
		int i=0;
		for(Gare e: autres){
			if( getPossesseur() == e.getPossesseur())
				i++;
		}
		return val[i];
	}

}
