/** Written by 
 * 1. Jatetanan 63070501013 
 * 2. Phuettipol 63070501046 */
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;
import java.io.*;

import reader.data_node;
import timer.stopwatch;

public class main {

	public static ArrayList<data_node> raw_list = new ArrayList<data_node>();
	public static ArrayList<Integer> repeat = new ArrayList<Integer>();
	
	public static int objmax=0, weightmax=0;
	
	public static population Pop1 = new population();
	public static int gen_count = 0, gen_limit = 500000; //Adjust Generation Limit Here !
	
	public static int maximum_fitness = Integer.MIN_VALUE;
	
	public static void main(String[] args) {
		
		/** Read File */
		reader.buffreader.read_file(raw_list,"src\\Set3.txt"); /** Change txt file here ! */
		//reader.buffreader.print_raw(raw_list); //Test print list
		
		/** Find Object/Weigth Max */
		data_node x = raw_list.get(0);
		objmax = x.obj; weightmax = x.weight;
		raw_list.remove(0); //Remove first line
		//reader.buffreader.print_raw(raw_list); //Test print list after remove
		
		/** Initialize Population
		 * Adjust population size in population.java*/
		Pop1.init_pop();
		//Pop1.print_pop();
		Pop1.cal_fitness();
		
		repeat.add(Pop1.getFittest().fitness);
		System.out.println("Generation : "+gen_count+" Highest Fitness : "+Pop1.getFittest().fitness+" ");
		
		timer.stopwatch.Timestart();
		while(gen_count<gen_limit) {
			++gen_count;

			Random rand = new Random();
			
			crossover();
			
			if(Math.abs(rand.nextInt())%100 < 20) mutation();
			
			Pop1.cal_fitness();
			
			System.out.println("* Generation : "+gen_count+" Highest Fittest : "+Pop1.getFittest().fitness+" *");
			
			//////////////////////////////////////////////////////////////////////////////////////
			
			if(Pop1.getFittest().fitness > maximum_fitness) maximum_fitness = Pop1.getFittest().fitness;
			
			if( Pop1.getFittest().fitness==repeat.get(0) ) repeat.add(Pop1.getFittest().fitness);
			else if( Pop1.getFittest().fitness != repeat.get(0) ) {
				repeat = new ArrayList<Integer>(); //Reset repeat list
				repeat.add(Pop1.getFittest().fitness);
			}
			
			System.out.println("* Repeats : "+repeat.size()+" ( "+(int)((2*(double)repeat.size()/(double)gen_count)*100)+"% ) *");
			
			if( 2*repeat.size() >= 0.8*gen_count && gen_count > 100000) {
				System.out.println("*** Terminated : Repeation over 80% of Current Generations Count***"); 
				break;
			}
			
			if(Pop1.getFittest().fitness == -1)  {
				System.out.println("*** Terminated : Genes Extinction ***"); 
				break;
			}
		}
		timer.stopwatch.TimeStop();
		if(gen_count == gen_limit)System.out.println("*** Terminated : Generation Limit Reached ***");
		System.out.println("Total Runtime : "+Math.floor( (timer.stopwatch.Time()/1E3) )+" sec(s)");
		System.out.println("Total Generations : "+gen_count+" Maximum Fitness : "+maximum_fitness);
	}

	public static void crossover() { //Double Crossover
		
		individual replace1 = Pop1.getLeastFittest();
		individual replace2 = Pop1.getSecondLeast();
		
		ArrayList<Integer> arr1 = new ArrayList<Integer>();
		ArrayList<Integer> arr2 = new ArrayList<Integer>();
		
		arr1.addAll(Pop1.getFittest().gene);
		arr2.addAll(Pop1.getSecondFittest().gene);
		
		Random rand = new Random();
		int temp;
		int point1 = rand.nextInt(Pop1.Ind[0].geneLength);
		int point2 = rand.nextInt(Pop1.Ind[0].geneLength);
		
		if(point1==point2) {
			for( int i=0; i<point1; i++) {
				temp = arr1.get(i);
				arr1.set(i, arr2.get(i));
				arr2.set(i, temp);
			}
		}
		else if(point2>point1) {
			for( int i=point1; i<point2; i++) {
				temp = arr2.get(i);
				arr2.set(i, arr1.get(i));
				arr1.set(i, temp);
			}
		}
		else if(point1>point2) {
			for( int i=point2; i<point1; i++) {
				temp = arr2.get(i);
				arr2.set(i, arr1.get(i));
				arr1.set(i, temp);
			}
		}
		
		for(int i=0; i<arr1.size(); i++) {
			replace1.gene.set(i, arr1.get(i));
			replace2.gene.set(i, arr2.get(i));
		}
		
		//replace1.print_individual_fit_weight();
		//replace2.print_individual_fit_weight(); 
		
	}
	
	public static void mutation() { //Multiple Random Mutation
		Pop1.getRandom().ind_mutation1();
		Pop1.getRandom().ind_mutation1();
		Pop1.getRandom().ind_mutation1();
		Pop1.getRandom().ind_mutation2();
		Pop1.getRandom().ind_mutation2();
		Pop1.getRandom().ind_mutation3(); 
	}
}
