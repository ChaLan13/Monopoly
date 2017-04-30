package terrain;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class Gare extends Propriete {
	private ArrayList<Gare> autres = new ArrayList<Gare>();
	private int val[] = {25, 50, 100, 200};
	
	
	public Gare(String name) throws InvalidParameterException {
		super(name, 200);
	}
	
	public void add(ArrayList<Gare> autres) throws InvalidParameterException {
		if(autres == null)
			throw new InvalidParameterException("Gare.add() -> Param NULL");
		if(autres.size() != 3)
			throw new InvalidParameterException("Gare.add() -> param < 3");
		
		this.autres = autres;
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
