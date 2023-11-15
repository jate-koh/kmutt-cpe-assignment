import java.util.Random;

public class population {
	public int popSize = 50; //Adjust population size here !
    public int fittest = 0, fittest2 = 0, min1 = 0, min2 = 0;
    individual Ind[] = new individual[popSize];
    
    public void init_pop() { //Initialize Population
		for(int i=0; i<popSize; i++) {
			Ind[i] = new individual(1,popSize); //1% generation
        }
    }
    
    public void print_pop() { //Test Print
    	for(int i=0; i<Ind.length; i++) {
    		System.out.printf("[%d] ",i+1);
    		Ind[i].print_individual_fit_weight();
    	}
    }
    
    public void cal_fitness() { //Calculate Fitness of entire generation
        for(int i=0; i<Ind.length; i++) {
        	//System.out.printf("[%d] ",i+1);
        	//Ind[i].print_individual_fit_weight();
        	Ind[i].calFit();
        }
    }

    public individual getRandom() {
    	Random rand = new Random();
    	return Ind[Math.abs(rand.nextInt()%popSize)];
    }
    
    public individual getFittest() {
        int maxFit = Integer.MIN_VALUE;
        int maxFitIndex = 0;
        for(int i=0; i<Ind.length; i++) {
            if(maxFit<=Ind[i].fitness) {
                maxFit = Ind[i].fitness;
                maxFitIndex = i;
            }
        }
        fittest = maxFitIndex;
        return Ind[maxFitIndex];
    }
    
    public individual getSecondFittest() {
        int maxFit1 = 0;
        int maxFit2 = 0;
        for (int i=0; i<Ind.length; i++) {
            if(Ind[i].fitness>Ind[maxFit1].fitness) {
                maxFit2 = maxFit1;
                maxFit1 = i;
            } else if(Ind[i].fitness>Ind[maxFit2].fitness) {
                maxFit2 = i;
            }
        }
        fittest2 = maxFit2;
        return Ind[maxFit2];
    }

    public individual getLeastFittest() {
        int minFitVal = Integer.MAX_VALUE;
        int minFitIndex = 0;
        for(int i=0; i<Ind.length; i++) {
            if (minFitVal>=Ind[i].fitness) {
                minFitVal = Ind[i].fitness;
                minFitIndex = i;
            }
        }
        min1 = minFitIndex;
        return Ind[minFitIndex];
    }
    
    public individual getSecondLeast() {
        int minFit1 = 0;
        int minFit2 = 0;
        for (int i=0; i<Ind.length; i++) {
            if(Ind[i].fitness<=Ind[minFit1].fitness) {
                minFit2 = minFit1;
                minFit1 = i;
            } else if(Ind[i].fitness>Ind[minFit2].fitness) {
                minFit2 = i;
            }
        }
        min2 = minFit2;
        return Ind[minFit2];
    }
}
