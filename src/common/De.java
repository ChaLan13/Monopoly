package common;

public class De{
	private int max = 6;
	private int min = 1;
	
	public De(int min, int max){
		if(max <= 0 || min <= 0 || min >= max){
			System.exit(2);
		}
		this.max = max;
		this.min = min
	}
	
	public De(){}
	
	public int jet(){
		return (Math.random() * (max-min + 1)) + min;
	}
}