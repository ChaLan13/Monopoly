package terrain;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class Terrain extends Propriete {
	private int nbrMaison;
	private int[] valeur;
	private int prixMaison;
	private ArrayList<Terrain> groupe = new ArrayList<Terrain>();
	
	//========================
	//===== Constructeur =====
	//========================
	public Terrain(String name, int prix, int[] valeur, int prixMaison) throws InvalidParameterException {
		super(name, prix);
		if(valeur.length != 6)
			throw new InvalidParameterException("Terrain Constructeur -> valeur[] de mauvaise taille");
		if(prixMaison < 0)
			throw new InvalidParameterException("Terrain Constructeur -> prixMaison negatif");
		
		this.valeur = valeur;
		this.prixMaison = prixMaison;
	}
	
	//===============================
	//===== Fonctions Speciales =====
	//===============================
	@Override
	public void clear() {
		super.clear();
		nbrMaison = 0;
		for (Terrain e : groupe) {
			int nbrMaison = e.getNbrMaison();
			while (nbrMaison > 0) {
				try {
					for (Terrain t : groupe)
						if (e.getNbrMaison() > nbrMaison)
							e.deconstruire();
					this.deconstruire();
				} catch (Exception x) {}
			}
		}
	}
	
	@Override
	int valeur(int scoreDe) {
		if(nbrMaison == 0){
			boolean tmp = true;
			for(Terrain e : groupe)
				if(! (e.getPossesseur() == this.getPossesseur()))
					tmp = false;
			
			if(tmp)
				return valeur[0] * 2;
			else
				return valeur[0];
		}
		else
			return valeur[nbrMaison];
	}
	
	public void construire() throws Exception{
		if(this.getPossesseur() == null)
			throw new Exception("Pas de possesseur");
		
		if(this.estHypo())
			throw new Exception("Terrain hypotheque");
		
		for(Terrain e : groupe)
			if(e.estHypo())
				throw new Exception("Un terrain du groupe est hypotheque");
		
		if(nbrMaison == 5)
			throw new Exception("Nombre max de maison atteint sur ce terrain");
		
		//On ne peut pas construire une 2e maison sur un terrain
		//Si tous le groupe ne possede pas deja au moins 1 maison
		for(Terrain e : groupe)
			if(e.getNbrMaison() < nbrMaison)
				throw new Exception("Pas assez de maisons sur les autres terrains de ce groupe\n(difference de 1maison max entre terrains d'un meme groupe)");
		
		nbrMaison++;
		this.getPossesseur().subMoney(prixMaison);
		
	}
	
	public void deconstruire() throws Exception{
		if(this.getPossesseur() == null)
			throw new Exception("Pas de possesseur");
		
		if(nbrMaison == 0)
			throw new Exception("Pas de maison sur ce terrain");
		
		//On ne peut pas enlever la 2e maison sur un terrain
		//Si un terrain du groupe en possede 3
		for(Terrain e : groupe)
			if(e.getNbrMaison() > nbrMaison)
				throw new Exception("Un terrain du groupe possede trop de maison pour deconstruire ici\n(difference de 1maison max entre terrains d'un meme groupe)");
		
		nbrMaison--;
		this.getPossesseur().addMoney(prixMaison/2);
	}
	
	@Override
	public void hypothequer(){
		while(nbrMaison > 0){
			try{
				for(Terrain e : groupe)
					if(e.getNbrMaison() > nbrMaison)
						e.deconstruire();
				this.deconstruire();
			}
			catch(Exception e){
			}
		}
		
		super.hypothequer();
	}
	//=====================
	//===== Get & Set =====
	//=====================
	public int getNbrMaison() {
		return nbrMaison;
	}
	
	public void add(Terrain terr1, Terrain terr2) throws InvalidParameterException{
		this.add(terr1);
		this.add(terr2);
	}
	
	public void add(Terrain terr) throws InvalidParameterException {
		if(terr == null)
			throw new InvalidParameterException("Terrain.add() -> param null");
		
		this.groupe.add(terr);
	}
	//=============================
	//===== equals & toString =====
	//=============================
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(!(obj instanceof Terrain))
			return false;
		
		Terrain o = (Terrain) obj;
		return this.getName().equals(o.getName())
				&& this.getPossesseur().equals(o.getPossesseur())
				&& this.getPrix() == o.getPrix()
				&& this.nbrMaison == o.nbrMaison
				&& this.prixMaison == o.prixMaison
				&& this.valeur == o.valeur;
	}

	@Override
	public String toString(){
		return super.toString() + "[" + (this.getNbrMaison() == 5 ? "1 hotel" : this.getNbrMaison() + " maison(s)") + "]";
	}
	
	
}
