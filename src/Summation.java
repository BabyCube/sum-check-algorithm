
public class Summation {
/* this is the class that stores the sum of the two numbers*/
	
	
	int result = 0;
	int _firstInt = 0;
	int _secondInt = 0;
	
	public Summation(int firstInt, int secondInt){
		result = firstInt + secondInt;
		
		_firstInt = firstInt;
		_secondInt = secondInt;
	}
	
	public int getSum(){ /* get the sum of two numbers*/
		return result;
	}
	
	public int getFirstNumber(){
		return _firstInt;
	}
	
	public int getSecondNumber(){
		return _secondInt;
	}
}
