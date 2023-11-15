import java.util.*;

public class individual {
	
	public int geneLength = main.objmax;
	ArrayList<Integer> gene = new ArrayList<Integer>(); //public int[] gene = new int[geneLength];
	public int fitness = 0;
	public int mutation_rate = 0;
	
	public individual(int gen_rate, int population) {
		
		Random rand = new Random();
		
		for(int i=0; i<geneLength; i++) { //Add 0 according to gene length
			gene.add(0); //Initial Population 
		}
		
		int mod = 0;
		for( int i=0; i< (int)(gen_rate/population)*100 ; i++) { //Randomly add 1 based on gen_rate
			mod = Math.abs(rand.nextInt()%geneLength);
			gene.set(mod, 1);
		}
		fitness = 0; //Initial Fitness
	} 
	
	public void print_individual() {
		System.out.printf("{ ");
		for(int i=0; i<geneLength; i++)
			System.out.printf("%d ",gene.get(i));
		System.out.printf(" }\n");
	}
	
	public void print_individual_fit_weight() {
		System.out.printf("{ ");
		for(int i=0; i<geneLength; i++)
			System.out.printf("%d ",gene.get(i));
		System.out.printf(" } ");
		System.out.printf("Fitness: %d",calFit());
		System.out.printf(" Weight: %d\n",calWeight());
	}
	
	public int calFit() {
		fitness = 0;
		int count = 0;
		int weight = 0;
		int[] index = new int[geneLength];
		for(int i=0; i<geneLength; i++) {
			index[i] = -1; 
		}
	
		for(int i=0; i<geneLength; i++) { //Find gene of 1
			if(gene.get(i)==1) index[count++] = i;
		}
		for(int i=0; i<geneLength; i++) { //Calculate Fitness
			//if(index[i]>=0) System.out.printf("%d : %d\n",index[i],main.raw_list.get(index[i]).obj);
			//else System.out.printf("%d : 0\n",index[i]);
			if(index[i]>=0) { 
				fitness += main.raw_list.get(index[i]).obj; 
				weight += main.raw_list.get(index[i]).weight;
			}
		}
			
		if(weight>main.weightmax) fitness=-1; //Check for overweight
		
		//System.out.println("Fitness "+fitness);
		return fitness;
	} 
	
	public int calWeight() {
		int count = 0;
		int weight = 0;
		int[] index = new int[geneLength];
		for(int i=0; i<geneLength; i++) {
			index[i] = -1; 
		}
		
		for(int i=0; i<geneLength; i++) { //Find gene of 1
			if(gene.get(i)==1) index[count++] = i;
		}
		
		for(int i=0; i<geneLength; i++) { //Calculate Fitness
			//if(index[i]>=0) System.out.printf("%d : %d\n",index[i],main.raw_list.get(index[i]).obj);
			//else System.out.printf("%d : 0\n",index[i]);
			if(index[i]>=0) { 
				weight += main.raw_list.get(index[i]).weight;
			}
		}

		//System.out.println("Fitness "+fitness);
		return weight;
	}

	public void increaseMutation(int rate) {
		mutation_rate += rate;
	}
	
	public void resetMutation() {
		mutation_rate = 0;
	}
	
	public void ind_mutation1() { //Two-point mutation
		 Random rand = new Random();

	     //Select a random mutation point
	     int point = rand.nextInt(geneLength);

	     //Flip values at the mutation point
	     if(gene.get(point) == 1) gene.set(point, 0);
	     else gene.set(point, 1);

	     point = rand.nextInt(geneLength);

	     if(gene.get(point) == 1) gene.set(point, 0);
	     else  gene.set(point, 1);
	}
	
	public void ind_mutation2() { //Sub-Group mutation
		Random rand = new Random();
		 
		 int point = (rand.nextInt(geneLength))/2;
		 
		 for(int i=0; i<point; i++) {
			 if(gene.get(i) == 1) gene.set(i, 0);
		     else  gene.set(i, 1);
		 }
		 
	}
	
	public void ind_mutation3() { //Whole mutation
		 
		 for(int i=0; i<geneLength; i++) {
			 if(gene.get(i) == 1) gene.set(i, 0);
		     else  gene.set(i, 1);
		 }
	}
	
}
