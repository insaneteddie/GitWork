/**
 *
 * @author Steve 09001409
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Random;        
import java.util.Collections;
import java.lang.*;
public class is09001409MK2
{
	
	public static void main(String [] args)
    {
		int n;
		int popSize;
		ArrayList<String> popList = new ArrayList<>();
		
		if(args.length > 0 && args.length <= 2)
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
				}
			}
		}
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
}