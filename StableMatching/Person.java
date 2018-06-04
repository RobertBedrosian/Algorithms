public class Person
{
	int[] preference;
	int [] index;

	public Person(int[] preference)
	{
		this.preference=preference;
		index = new int[preference.length];
		for (int i=0;i< preference.length ; i++)
		{
			index[preference[i]-1]=i;
		}
	}
}