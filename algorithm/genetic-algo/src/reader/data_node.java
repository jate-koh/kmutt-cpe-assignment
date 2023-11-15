package reader;

public class data_node {
	
	public int obj=0, weight=0; 
	/** obj & weight in line 0 is objmax and weightmax; otherwise, if n>0
	 *  obj[n] is value of object n
	 *  weight[n] is weight of object n*/
	
	public data_node()
	{ this.obj=0; this.weight=0; }
	
	public data_node(String buff)
	{
		String[] str = buff.split("\\s+");
		this.obj = Integer.parseInt(str[0]); this.weight = Integer.parseInt(str[1]);
		//System.out.printf("Test: %d %d\n",obj,weight);
	}

	public void print_one(int i)
	{
		System.out.printf("[%d] %d %d\n",i,obj,weight);
	}
}
