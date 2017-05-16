package common;

import java.security.InvalidParameterException;

public class De{
	private int max = 6;
	private int min = 1;
	
	//=======================
	//=====Constructeurs=====
	//=======================
	
	public De(int min, int max) throws InvalidParameterException{
		if(min <= 0 || min >= max){
			throw new InvalidParameterException("Valeur impossibles pour un de");
		}
		this.max = max;
		this.min = min;
	}
	
	//min = 1, max = 6
	public De(){}
	
	//=============================
	//=====Fonctions speciales=====
	//=============================
	
	//fait un jet de de
	public int jet(){
		return (int)(Math.random() * (max-min + 1)) + min;
	}
	
	//===================
	//=====Get & Set=====
	//===================
	
	public int getMin(){return min;}
	public int getMax(){return max;}
	
	//===========================
	//=====equals & toString=====
	//===========================
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(!(obj instanceof De))
			return false;
		
		De o = (De) obj;
		return this.max == o.max
				&& this.min == o.min;
	}

	@Override
	public String toString() {
		return "De[max="+max+"; min="+min+"]";
	}
	
}