package reader;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class buffreader {
	
	//public static ArrayList<data_node> raw_list = new ArrayList<data_node>();
	
	/** Raw Data Processing */
	public static void read_file(ArrayList<data_node> arr, String name)
	{
		File f = null;
		BufferedReader fbuff = null;
		int count=0;
		System.out.println(" **** Reading File **** ");
		try { 
			f = new File(name); /** Change File Here !! */
			fbuff = new BufferedReader(new FileReader(f));
		} catch(FileNotFoundException err) { System.out.println("File not found."); }

		String buff;
		try {
			while( (buff = fbuff.readLine()) != null ) {
				//System.out.printf("Line %d : %s\n",count++,buff);
				data_node x = new data_node(buff);
				arr.add(x);
			}
		} catch (IOException err) { System.out.println("Read file error."); }
		System.out.println(" **** Read File Completed **** ");
	}
	public static void print_raw(ArrayList<data_node> arr)
	{
		//System.out.printf("%d %d\n",raw_list.get(2).obj,raw_list.get(2).weight);
		for(int i=0; i<arr.size(); i++) {
			System.out.printf("%d %d\n",arr.get(i).obj,arr.get(i).weight);
		}
	}

}
