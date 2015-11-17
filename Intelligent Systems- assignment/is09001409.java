/**
 *
 * @author Steve 09001409
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Random;        
import java.util.Collections;
import java.lang.*;
public class is09001409 
{  
	private static double [][] polarCoordinates;
	private static int popSize;
	private static int [][] matrix;
	private static int n;
	private static int [][] population;
	private static double [] fitnessValue;
    public static void main(String [] args)
    {
        if(args.length > 0  && args.length <= 2)
        {              
            File readIn = new File(args[0]);
            if(readIn.exists())
            {
				if(readIn.canRead())
				{	
					if(args.length == 2)
						popSize = Integer.parseInt(args[1]);
					else
						popSize = 100;
					readInput(readIn);
					n = matrix.length;
					population = new int[popSize][n];
					System.out.println("Matrix: ");
					for(int i = 0;i < matrix.length; i++)
					{	
						for(int j = 0; j < matrix.length; j++)
						{
							System.out.print(matrix[i][j] + " ");
							if(j == matrix.length - 1)
								System.out.println("");
						}
					}
					System.out.println();
					System.out.println("Population: ");
					generatePopulation();
					fitness_function();
                }  
				else System.out.println("Error file " + readIn + "cannot be read, check permissions and try again.");
            }
            else
                System.out.println("Error " + readIn + " cannot be found, please check the filename and try again");
        }
        else
            System.out.println("Error Usage - java is09001409 filename (optionally: +integer-populationSize)");
    }
	//read input method
    public static void readInput(File input)
	{
		FileReader fileRead;
		ArrayList<String> fileLines = new ArrayList<>();
		BufferedReader bfReader;	
		try{
			fileRead = new FileReader(input);
			bfReader = new BufferedReader(fileRead);
			String line = bfReader.readLine();
			//getting first line to give size, for matrix.adding to arraylist.
			fileLines.add(line);
			String [] stringArray = line.split(" ");	
			//initialising the matrix with the size from the first line after split
			matrix = new int [stringArray.length][stringArray.length];
			//reading the lines in from the file and adding to arraylist.
			while((line = bfReader.readLine()) != null)
			{	
				fileLines.add(line);
			}
			for(int i = 0;i < fileLines.size();i++)
			{				
				stringArray = fileLines.get(i).split(" ");
				//check to make sure matrix supplied is square
				try
				{
					for(int j = 0; j < fileLines.size() ; j++)
					{					
						matrix[i][j]= Integer.parseInt(stringArray[j]);	
					}
				}catch(IndexOutOfBoundsException indexExcept)
				{
					System.out.println("Error check the matrix supplied and make sure it is a square N * N matrix and restart the program.");
					System.exit(0);
				}
			}//close out the buffered reader
			bfReader.close();
			
		}catch(FileNotFoundException notFound)
		{
			System.out.println("Error : file "+ input + "not found, check directory and try again.");
		}catch(IOException anIOExcept)
		{
			System.out.println("Error :" + anIOExcept.getMessage());
		}
	}
	
	public static void generatePopulation()
	{
		ArrayList<String> popToTest = new ArrayList<>();
		String pop = "";
		ArrayList<Integer> popSeed = new ArrayList<Integer>();	
		int iterater = 0;
		int [] populationStorage = new int[n];
		for(int i = 0;i < populationStorage.length;i++)
			{
				popSeed.add(i);
				populationStorage[i] = popSeed.get(i);
				pop += i + ",";
			}
			popToTest.add(pop);
			pop = "";
		while(iterater < popSize)
		{
			Collections.shuffle(popSeed);
			
			for(int i = 0;i < populationStorage.length;i++)
			{
				populationStorage[i] = popSeed.get(i);
				pop += popSeed.get(i) + ",";
			}
			popToTest.add(pop);
			// if(isUnique(popToTest,pop))
			// {
				for(int j = 0; j < populationStorage.length ; j++)
				{
					population[iterater][j] = populationStorage[j];
				}
			
				for(int j = 0; j < n ;j++)
				{
					System.out.print(population[iterater][j] + " ");
					if(j == n - 1 )System.out.println();
				}
				iterater++;
			//}
			//else
			//{
				//iterater = iterater;
			//}
		}
	}  
	
	/* public static boolean isUnique(ArrayList<String> list, String test)
	{
		boolean unique = false;
		if((list.contains(test) == unique))
		{
			unique = true;
			System.out.println(unique);
			return unique;
		}
		else
		{
			return unique;
		}
		
	} */
	
	private static void fitness_function()
	{
		fitnessValue = new double[popSize];
		polarCoordinates = new double [popSize][n];
		double chunk = 2 * (Math.PI)/n;
		for(int i = 0; i < popSize;i++)
		{
			for(int j = 0; j < n; j++)
			{
				polarCoordinates[i][j] = chunk * j;
			}
		}
		double [][] xCoordinateArray = new double [popSize][n];
		double [][] yCoordinateArray = new double [popSize][n];
		for(int i = 0; i < popSize; i++)
		{
			for(int j = 0; j < n; j++)
			{
				double xCoordinate = Math.cos(polarCoordinates[i][j]);
				double yCoordinate = Math.sin(polarCoordinates[i][j]);
				xCoordinateArray[i][j] = xCoordinate;
				yCoordinateArray[i][j] = yCoordinate;
			}
		}
		double x1;
		double x2;
		double y1;
		double y2;
		double length = 0.0;
		double fitness = 0.0;
		for(int p = 0; p < popSize; p++)
		{
			for(int i = 0; i < n; i++)
			{
				for(int j = 0; j < n;j++)
				{
					fitness = 0.0;
					length = 0.0;
					if(matrix[i][j] == 1)
					{
						x1 = xCoordinateArray[p][i];
						x2 = xCoordinateArray[p][j];
						y1 = yCoordinateArray[p][i];
						y2 = yCoordinateArray[p][j];
						length = Math.sqrt((Math.pow((x1 - x2),2))+(Math.pow((y1 - y2),2)));
						//System.out.println("Length: " + length);
						//System.out.println("Fitness: "+ fitness);
						fitness += length;
						System.out.println("Fitness: " +fitness);
						//fitnessValue[p] =  fitness;
					}
				}
			}
		}
		
	}
}
