package common;

import java.security.InvalidParameterException;

public class De{
	private int max = 6;
	private int min = 1;
	
	public De(int min, int max) throws InvalidParameterException{
		if(min <= 0 || min >= max){
			throw new InvalidParameterException("Valeur impossibles pour un de");
		}
		this.max = max;
		this.min = min;
	}
	
	public De(){}
	
	public int jet(){
		return (int)(Math.random() * (max-min + 1)) + min;
	}
}