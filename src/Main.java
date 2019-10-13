import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		/* the address of the file will be entered by the user in the console*/
		/* the type of algorithm: if enter 1 - brute force; 2 - the optimized algorithm*/
		
		Scanner inputAddress = new Scanner(System.in); /* the scanner for user to enter the address of the file */
		
		System.out.println(" Please enter the file name for the source number: "); /* used later to determine if two of the numbers are the sum of the random number*/
		String inputNum = inputAddress.nextLine();
		
		System.out.println(" Please enter the file name for the random number list: "); /* the random number to test if the given number list can generate the sum*/
		String randomNum = inputAddress.nextLine();
		
		System.out.println(" Which algorithm? 1 for brute force; 2 for the optimized algorithm"); /* ask the user the algorithm to implement*/
		int algorithmNum = inputAddress.nextInt();
		
		long startTime = System.currentTimeMillis(); /* the start time of the program*/
		
		if(algorithmNum == 1){ /* call the method of the desired algorithm*/
			originalAlgorithm(inputNum, randomNum);
		}else if(algorithmNum == 2){
			optimizedAlgorithm(inputNum, randomNum);
		}
		
		long endTime = System.currentTimeMillis(); /* the end time of the program*/
		long runTime = endTime - startTime;
		System.out.println(" the running time for the given number list is (in milliseconds): " + runTime + " ms");
	}

	private static void originalAlgorithm(String inputNum, String randomNum) throws FileNotFoundException {
		
		try{ /* make sure each file address is valid*/
			File file = new File(inputNum);
			Scanner numberList = new Scanner(file);
		}catch(FileNotFoundException e){
			System.out.println(" The scource number list cannot be found!");
		}
		
		try{ /* make sure each file address is valid*/
			File file2 = new File(randomNum);
			Scanner randomList = new Scanner(file2);
		}catch(FileNotFoundException e){
			System.out.println(" The random number list cannot be found!");
			System.exit(0);
		}
		
		File file = new File(inputNum);
		Scanner numberList = new Scanner(file);
		File file2 = new File(randomNum);
		Scanner randomList = new Scanner(file2);
		
		
		ArrayList sourceArray = new ArrayList();
		
		while(numberList.hasNextInt()){ /* add all the number from the source file into the sourceArray*/
			sourceArray.add(numberList.nextInt());
		}
		

		int sourceSize = sourceArray.size();
		
		
		/* now all the possible sum has been added to the possibilityArray*/
		/* check if the random list number is the summation of the numbers in the source list*/
		
		while(randomList.hasNextInt()){
			int currentCheck = randomList.nextInt(); /* load the number to check*/
			boolean found = false; /* by default, the current check number is not in the array*/
			int firstNum = 0;
			int secondNum = 0;
			
			for(int n = 0; n < sourceSize; n++){
				for(int m = n; m < sourceSize; m++){
					if((int)sourceArray.get(n) + (int)sourceArray.get(m) == currentCheck){
						found = true;
						firstNum = n;
						secondNum = m;
						break; /* no need to check since it is already found*/
					}
				}
			}
			
			if(found){
				System.out.println(currentCheck + " is the sum of two numbers in the list: " + (int)sourceArray.get(firstNum) + " " + (int)sourceArray.get(secondNum));
			}else{
				System.out.println(currentCheck + " is not the sum of two numbers in the list");
			}
		}
		
		return;
		
	}

	private static void optimizedAlgorithm(String inputNum, String randomNum) throws FileNotFoundException{
		
		try{ /* make sure each file address is valid*/
			File file = new File(inputNum);
			Scanner numberList = new Scanner(file);
		}catch(FileNotFoundException e){
			System.out.println(" The scource number list cannot be found!");
		}
		
		try{ /* make sure each file address is valid*/
			File file2 = new File(randomNum);
			Scanner randomList = new Scanner(file2);
		}catch(FileNotFoundException e){
			System.out.println(" The random number list cannot be found!");
			System.exit(0);
		}
		
		File file = new File(inputNum);
		Scanner numberList = new Scanner(file);
		File file2 = new File(randomNum);
		Scanner randomList = new Scanner(file2);	
		
		 int[] sourceArray = new int[1000000000];
		ArrayList<Integer> sourceArray2 = new ArrayList<Integer>();
		 
		
		while(numberList.hasNextInt()){ /* add all the number from the source file into the sourceArray*/
			int toInsert = numberList.nextInt();
			sourceArray[toInsert] = -1;
			sourceArray2.add(toInsert);
		}
		
		while(randomList.hasNextInt()){
			int currentCheck = randomList.nextInt(); /* load the number to check*/
			boolean found = false; /* by default, the current check number is not in the array*/
			int firstNum = 0;
			int secondNum = 0;
			
			for(int k: sourceArray2){
				int targetPosition = currentCheck - k;
				
				if(targetPosition < 0){ /* the given k cannot be the right answer since it is bigger than the sum*/
					break;
				}
				
				try{ /* the sum may be big! if it is 11 digits, the out of bound exception can be thrown! In this case, there is no chance it can be found in the array! */
					if(sourceArray[targetPosition] == -1){
						found = true; /* the solution has been found*/
						firstNum = k;
						secondNum = targetPosition;
						break;
					}
				}catch(ArrayIndexOutOfBoundsException e){
					found = false;
					}
			}
			
			if(found){
				System.out.println(currentCheck + " is the sum of two numbers in the list: " + firstNum + " " + secondNum);
			}else{
				System.out.println(currentCheck + " is not the sum of two numbers in the list");
			}
			
			}
		
		
		return;
		
	}

}
