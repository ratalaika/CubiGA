package es.unileon.rnag.operator.crossover;

import java.util.Random;

import es.unileon.rnag.chromosome.Chromosome;
import es.unileon.rnag.gen.Gen;

/**
 * Class that make the Two Point chrossover
 * @author Adrian Casimiro Alvarez
 * @author Javier de Pedro Lopez
 */
public class TwoPoint extends CrossoverStrategy{

	@Override
	public CrossoverElement doCrossover(CrossoverElement crossoverElement) {
		Random ran = new Random();
		double probability = ran.nextDouble();
		
		if(probability < this.getCrossoverProbability()){
			Chromosome firstChromosome = crossoverElement.getFirstChromosome();
			Chromosome secondChromosome = crossoverElement.getSecondChromosome();
			
			int length = firstChromosome.length();
			
			int firstPosition =  ran.nextInt(length);
			int secondPosition = ran.nextInt(length);
			if(firstPosition > secondPosition){
				int aux = firstPosition;
				firstPosition = secondPosition;
				secondPosition = aux;
			}
			
			Gen[] sliceFirstChromosome = firstChromosome.getChromosomeSlice(firstPosition, secondPosition);
			Gen[] sliceSecondChromosome = secondChromosome.getChromosomeSlice(firstPosition, secondPosition);
			
			firstChromosome.setChromosomeSlice(sliceSecondChromosome, firstPosition);
			secondChromosome.setChromosomeSlice(sliceFirstChromosome, firstPosition);
			
			CrossoverElement result = new CrossoverElement(firstChromosome, secondChromosome);
			
			return result;
		}
		else{
			return crossoverElement;
		}
	}

}