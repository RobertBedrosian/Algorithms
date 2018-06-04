/*Robert Bedrosian
*COMP 482
*Professor Noga
*This program determines the LCS, Longest Common Substring, among 3 Strings.
*Method used: Dynamic programming, where an n dimensional array keeps track of
*n strings and holds the length of any substring found to be shared among all 3
*strings.
*Runtime: O(n^3)
*/

import java.io.*;

public class Project2
{
	static int lcs(char[] firstString, char[] secondString, char[] thirdString)
	{
		int x=firstString.length;
		int y=secondString.length;
		int z=thirdString.length;
		int [][][] lCs=new int[x+1][y+1][z+1];
		for (int i=0;i<=x;i++)
		{
			for (int j=0;j<=y;j++)
			{
				for (int k=0;k<=z;k++)
				{
					//If all strings share a substring, increment the length by 1 to count that substring. Base case is when i, j, or k equal 0, where we look at 0 characters from any of the three strings, then set the longest common substring to 0.
					if ((i != 0) && (j!= 0) && firstString[i-1]==secondString[j-1])
					{
						if((k!=0) && firstString[i-1] == thirdString[k-1])
						{
							lCs[i][j][k]=lCs[i-1][j-1][k-1] + 1;
						}
						else
						{
							lCs[i][j][k] =0;
						}
					}
					else
					{
						if ((i!=0) && (j!=0) && (k!=0))
						{
							lCs[i][j][k] =max(lCs[i-1][j][k],lCs[i][j-1][k],lCs[i][j][k-1]);
						}
						else
						{
							lCs[i][j][k]=0;
						}
					}
				}
			}
		}
		return lCs[x][y][z];
	}

	static int max(int a, int b, int c)
	{
		if (a>b)
		{
			if (a>c)
			{
				return a;
			}
			else
			{
				return c;
			}
		}
		else
			if(b>c)
			{
				return b;
			}
			else
			{
				return c;
			}
	}

	public static void main(String [] args)
	{
		String fileName="input2.txt";
		String line =null;
		int fileSize=3;
		int counter=0;
		String[] inputStrings=new String[fileSize];

		try
		{
			FileReader fileReader= new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader (fileReader);
			while ((line = bufferedReader.readLine()) != null)
			{
				inputStrings[counter]=line;
				counter++;
			}
		}
		catch(IOException ex)
		{
			System.out.println("Unable to open file " + fileName);
		}
		char[] firstString=inputStrings[0].toCharArray();
		char[] secondString=inputStrings[1].toCharArray();
		char[] thirdString=inputStrings[2].toCharArray();
		System.out.println("Length of LCS is " + lcs(firstString,secondString,thirdString));
	}
}
