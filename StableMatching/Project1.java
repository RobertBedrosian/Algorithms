/*Robert Bedrosian
*COMP 482
*Professor Noga
*This program will determine whether a matching that has been made is stable *or not. This program requires an input file titled "input1.txt" to read *matching data from.
*/

import java.io.*;

public class Project1
{
	public static void main( String[] args)
	{

		String fileName="input1.txt";
		String line =null;
		int size=0;
		int counter=1;
		/*Attempt to read input1.txt containing problem*/
		try
		{
			FileReader fileReader= new FileReader("input1.txt");
			BufferedReader bufferedReader = new BufferedReader (fileReader);
			size=Integer.parseInt(bufferedReader.readLine());
			Person[] men= new Person[size];
			Person[] women= new Person[size];
			int[] matching= new int[size];
			int[] matchindex= new int[size];

			while ((line = bufferedReader.readLine()) != null)
			{
				/*If Lines that are read are from 1->size, count as a male. otherwise, from size+1-> 2*size will be counted as a female. The last line of the input file, or 2size+1, is stored as the matching that is being tested if stable or not. Each person will have an array of preferences and an array to hold the indecies of each preference in the preference array*/
				if (counter <= size)
				{
					String[] pref=line.split(" ");
					int [] preference=new int[size];

					for (int i=0;i <pref.length;i++)
					{
						preference[i]=Integer.parseInt(pref[i]);


					}

					Person p = new Person(preference);
					men[counter-1]=p;

				}
				else if(counter > size && counter <= size*2)
				{
					String[] pref=line.split(" ");
					int [] preference=new int[size];


					for (int i=0;i <pref.length;i++)
					{
						preference[i]=Integer.parseInt(pref[i]);

					}

					Person p = new Person(preference);
					women[counter-(size+1)]=p;


				}
				else if (counter > size*2)
				{
					String[] match= line.split(" ");

					for (int i=0;i <match.length;i++)
					{
						matching[i]=Integer.parseInt(match[i]);

					}
					for (int i=0; i< match.length;i++)
					{
						matchindex[matching[i]-1]=i;

					}
				}				


			counter++;
			}
			//End of file read and data storing.
			bufferedReader.close();
			boolean stable=true;

			//Begin logic to determine stable matching.
			//For every man
			int currentMan;
			int matchedMan;
			for (int i=0; i<size; i++)
			{	
				//If there are no instablities
				if (stable)
				{
					//If the mans first choice is not equal to who he matched
					if (men[i].preference[0]!=matching[i])
					{
						//Then check for every preference that is higher prioritized than who he was matched with
						for (int j=0; j< men[i].index[matching[i]-1] && stable; j++)
						{
							//If the currentMan is ranked higher on the womans list than who she matched with, there is an unstable pair with man i and woman j, j being man i's j'th preference.
							currentMan=women[men[i].preference[j]-1].index[i];
							matchedMan=women[men[i].preference[j]-1].index[matchindex[men[i].preference[j]-1]];
							if (currentMan<matchedMan)
							{
								System.out.println("No (" + (i+1) + " , " + (men[i].preference[j]) + ")");
									stable=false;
							}
						}
					
					}

				}

			}
			//If no instabilities were found, then it is a stable matching.
			if (stable)
				System.out.println("Yes");

			
			
		}

		
		catch(IOException ex)
		{
			System.out.println("Unable to open file " + fileName);

		}
	}

}