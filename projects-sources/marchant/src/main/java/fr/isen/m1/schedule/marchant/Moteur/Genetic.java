package fr.isen.m1.schedule.marchant.moteur;

import fr.isen.m1.schedule.utilities.Diagnosis;
import fr.isen.m1.schedule.utilities.Doctor;

public abstract class Genetic
{
	public enum SelectionMode
	{
		Uniform,
		Probabilistic;
	}
	// Searching the worst path, i.e the one with higher total length:
	private static int indexWorstPath(double pathLength_array[])
	{
		double worst_pathLength = pathLength_array[0];
		int indexWorst = 0;

		for (int index = 1; index < pathLength_array.length; ++index)
		{
			if (worst_pathLength < pathLength_array[index])
			{
				worst_pathLength = pathLength_array[index];
				indexWorst = index;
			}
		}

		return indexWorst;
	}


	// Searching the best path, i.e the one with lower total length:
	private static int indexBestPath(double pathLength_array[])
	{
		double best_pathLength = pathLength_array[0];
		int indexBest = 0;

		for (int index = 1; index < pathLength_array.length; ++index)
		{
			if (pathLength_array[index] < best_pathLength)
			{
				best_pathLength = pathLength_array[index];
				indexBest = index;
			}
		}

		return indexBest;
	}


	// Selection a path in the population to evolve:
	private static int selection(double pathLength_array[], double sum_inv_pathLength, SelectionMode selMode)
	{
		if (selMode == SelectionMode.Uniform)
		{
			// Uniform selection of a path in the population:
			return Utilities.randInt(pathLength_array.length);
		}

		else // Probabilistic
		{
			// Choose a path whith higher probabiblity the better it is.
			// 'pathLength_array' is an array containing the lengths of each path of the population.
			// 'sum_inv_values' is the sum of all the inverse of lengths from the population, and is already computed,
			// for better performances. The inverse of a path length, divided by 'sum_inv_values', is the used distribution,
			// i.e the fitness function of a path is the inverse of the path length:

			double threshold = Utilities.randdouble(sum_inv_pathLength);

			int index = 0;

			double partialSum = 1f / pathLength_array[0];

			while (partialSum < threshold)
			{
				++index;

				if (index == pathLength_array.length)
				{
					--index;
					break;
				}

				partialSum += 1f / pathLength_array[index];
			}

			return index;
		}
	}


	// Mutates the new path by swaping cities randomly. The last city
	// must be left untouched. Requires citiesNumber >= 3.
	private static void mutation(int new_path[])
	{
		int city_1, city_2;

		// Findind city_1 != city_2 randomly:

		city_1 = Utilities.randInt(new_path.length);

		do
		{
			city_2 = Utilities.randInt(new_path.length);
		}
		while (city_1 == city_2);

		// To be sure that city_start < city_end:

		int city_start, city_end;

		if (city_1 < city_2)
		{
			city_start = city_1;
			city_end = city_2;
		}

		else
		{
			city_start = city_2;
			city_end = city_1;
		}

		// Mirror mutations work best for this setup:
		Path.mirror(new_path, city_start, city_end);
	}


	// Replacing the worst path by a new one, if the latter is better,
	// and return the (eventually) modified 'sum_inv_pathLength':
	private static double replace(Map map, int population[][], double pathLength_array[],
		int new_path[], double sum_inv_pathLength)
	{
		// Computing the total length of the new path:
		double new_pathLength = Path.totalLength(map, new_path);

		// Searching the worst path, i.e the one with higher total length:
		int indexWorst = indexWorstPath(pathLength_array);

		if (new_pathLength < pathLength_array[indexWorst])
		{
			// Updating the sum of the inverse of the lengths:
			sum_inv_pathLength += 1f / new_pathLength - 1f / pathLength_array[indexWorst];

			// Replacing the worst path:
			for (int j = 0; j < map.citiesNumber(); ++j)
				population[indexWorst][j] = new_path[j];

			// Updating the length of the new path:
			pathLength_array[indexWorst] = new_pathLength;
		}

		return sum_inv_pathLength;
	}


	// Genetic search (kinda, has no crossover). A small path length is being seeked by evolving from
	// a starting path. Last city of any path is keeped fixed, as it is set to be the starting/end point.
	// Best found path is returned.
	public static int[] geneticSearch(Map map, int populationSize, int epochsNumber, SelectionMode selMode)
	{
		long startTime = System.nanoTime();

		if (map == null)
		{
			System.out.println("\nnull map in 'geneticSearch'.\n");
			throw new RuntimeException();
		}

		///////////////////////////////////////////////////////
		// Setup:

		int citiesNumber = map.citiesNumber();

		if (citiesNumber < 1)
		{
			System.out.println("Number of cities must be at least 3. is : " + citiesNumber);
			throw new RuntimeException();
		}

		// Choosing a starting path:
		int path_init[] = new int[citiesNumber];

		for (int i = 0; i < citiesNumber; ++i)
			path_init[i] = i; // a trivial permutation.

		// Total length of the starting path:
		double pathLength_init = Path.totalLength(map, path_init);

		// Creating a population of paths:
		int population[][] = new int[populationSize][citiesNumber];

		// Creating an array containing all the total lengths for each path of the population:
		double pathLength_array[] = new double[populationSize];

		// Copying the starting path all over 'population', and updating 'pathLength_array':
		for (int index = 0; index < populationSize; ++index)
		{
			for (int i = 0; i < citiesNumber; ++i)
				population[index][i] = path_init[i];

			pathLength_array[index] = pathLength_init;
		}

		// Sum of all the inverse of lengths from the population:
		double sum_inv_pathLength = populationSize / pathLength_init;

		// Buffer that will contain the newborn paths:
		int path_buffer[] = new int[citiesNumber];

		///////////////////////////////////////////////////////
		// Starting the evolution process:

		for (int epoch = 0; epoch < epochsNumber; ++epoch)
		{
			// Selection a path in the population to evolve:
			int indexSelectedPath = selection(pathLength_array, sum_inv_pathLength, selMode);

			// Copying the chosen path into the buffer:
			for (int i = 0; i < citiesNumber; ++i)
				path_buffer[i] = population[indexSelectedPath][i];

			// Mutates the new path by swaping cities randomly. The last city must be left untouched.
			mutation(path_buffer);

			// Replacing the worst path by a new one, if the latter is better,
			// and return the (eventually) modified 'sum_inv_pathLength':
			sum_inv_pathLength = replace(map, population, pathLength_array, path_buffer, sum_inv_pathLength);
		}

		///////////////////////////////////////////////////////
		// Saving the results:

		// Finding the smallest length found:
		int indexBest = indexBestPath(pathLength_array);

		int best_path[] = new int[citiesNumber];

		// Saving the best found path:
		for (int i = 0; i < citiesNumber; ++i)
			best_path[i] = population[indexBest][i];

		long endTime = System.nanoTime();

		double timeElapsed = (double) (endTime - startTime) / 1000000000;

		double best_pathLength = pathLength_array[indexBest];

		System.out.printf("\ngeneticSearch:\n -> Time elapsed: %.3f s, found length: %.6f km\n", timeElapsed, best_pathLength);

		return best_path;
	}

	// Genetic search (kinda, has no crossover). A small path length is being seeked by evolving from
		// a starting path. Last city of any path is keeped fixed, as it is set to be the starting/end point.
		// Best found path is returned counting on criticity.
		public static int[] geneticSearchWithCriticity(Map map, int populationSize, int epochsNumber, SelectionMode selMode)
		{
			long startTime = System.nanoTime();

			if (map == null)
			{
				System.out.println("\null map in 'geneticSearch'.\n");
				throw new RuntimeException();
			}

			///////////////////////////////////////////////////////
			// Setup:

			int citiesNumber = map.citiesNumber();

			if (citiesNumber < 1)
			{
				System.out.println("\nNumber of appointments must be at least 1.\n");
				throw new RuntimeException();
			}

			// Choosing a starting path:
			int path_init[] = new int[citiesNumber];

			for (int i = 0; i < citiesNumber; ++i)
				path_init[i] = i; // a trivial permutation.

			// Total length of the starting path:
			double pathLength_init = Path.totalLengthCriticity(map, path_init);

			// Creating a population of paths:
			int population[][] = new int[populationSize][citiesNumber];

			// Creating an array containing all the total lengths for each path of the population:
			double pathLength_array[] = new double[populationSize];

			// Copying the starting path all over 'population', and updating 'pathLength_array':
			for (int index = 0; index < populationSize; ++index)
			{
				for (int i = 0; i < citiesNumber; ++i)
					population[index][i] = path_init[i];

				pathLength_array[index] = pathLength_init;
			}

			// Sum of all the inverse of lengths from the population:
			double sum_inv_pathLength = populationSize / pathLength_init;

			// Buffer that will contain the newborn paths:
			int path_buffer[] = new int[citiesNumber];

			///////////////////////////////////////////////////////
			// Starting the evolution process:

			for (int epoch = 0; epoch < epochsNumber; ++epoch)
			{
				// Selection a path in the population to evolve:
				int indexSelectedPath = selection(pathLength_array, sum_inv_pathLength, selMode);

				// Copying the chosen path into the buffer:
				for (int i = 0; i < citiesNumber; ++i)
					path_buffer[i] = population[indexSelectedPath][i];

				// Mutates the new path by swaping cities randomly. The last city must be left untouched.
				mutation(path_buffer);

				// Replacing the worst path by a new one, if the latter is better,
				// and return the (eventually) modified 'sum_inv_pathLength':
				sum_inv_pathLength = replace(map, population, pathLength_array, path_buffer, sum_inv_pathLength);
			}

			///////////////////////////////////////////////////////
			// Saving the results:

			// Finding the smallest length found:
			int indexBest = indexBestPath(pathLength_array);

			int best_path[] = new int[citiesNumber];

			// Saving the best found path:
			for (int i = 0; i < citiesNumber; ++i)
				best_path[i] = population[indexBest][i];

			long endTime = System.nanoTime();

			double timeElapsed = (double) (endTime - startTime) / 1000000000;

			double best_pathLength = pathLength_array[indexBest];

			System.out.printf("\ngeneticSearch:\n -> Time elapsed: %.3f s, found length: %.6f km\n", timeElapsed, best_pathLength);

			return best_path;
		}


		/**
		 *
		 * @param lstPositions The list of position in disorder
		 * @param Doctor the doctor that have to reach all positions
		 * @return List of Position. First element of the list : doc position. Then the path to follow after the Doctor Positon.
		 */
		public static Diagnosis[] givePathToFollowWithDoctor(Diagnosis[] listeDiag, Doctor doctor) {

			int citiesNumber = listeDiag.length;

			Map map = new Map(listeDiag);

			// Those settings should work for all 'citiesNumber':
			int populationSize = 64;
			int epochsNumber = 10000 * citiesNumber;
			SelectionMode selMode = SelectionMode.Uniform;

			// Finding a good path (hopefully):
			int[] best_path = geneticSearchWithCriticity(map, populationSize, epochsNumber, selMode);
			//Position[] listeGagnante = new Position[citiesNumber];
			Diagnosis[] listeGagnante = new Diagnosis[listeDiag.length];
			for (int i = 0; i<citiesNumber; i++) {
				listeGagnante[i] = listeDiag[best_path[i]];
			}
			Path.print(best_path);

			int c = 0;
			double distance = Path.distanceCriticite(doctor.getLieuDeDepart().getX(), doctor.getLieuDeDepart().getY(), listeGagnante[0].getPatientConserne().getLieuDeVie().getX(), listeGagnante[0].getPatientConserne().getLieuDeVie().getY(), listeGagnante[0].getCriticite());;
			for (int i = 1; i < listeGagnante.length; i++) {
				double distance1 = Path.distanceCriticite(doctor.getLieuDeDepart().getX(), doctor.getLieuDeDepart().getY(), listeGagnante[i].getPatientConserne().getLieuDeVie().getX(), listeGagnante[i].getPatientConserne().getLieuDeVie().getY(), listeGagnante[i].getCriticite());
				if(distance1 < distance) {
					c = i;
					distance = distance1;
				}
			}

			//Re-organization of the list to have the list of Patient to see after the doc.
			Diagnosis[] lstGagnante = new Diagnosis[listeGagnante.length];
			for (int i = 0; i < listeGagnante.length; i++) {
				if(c+i == listeGagnante.length) {
					c = c - listeGagnante.length;
				}
				lstGagnante[i] = listeGagnante[c+i];
			}
			return lstGagnante;
		}



	/*public static void main(String[] args)
	{
		int citiesNumber = 50;
		System.out.printf("Cities number: %d\n", citiesNumber);

		Graphe gr = new Graphe();
		Position[] ListeNoeuds = new Position[citiesNumber];
	    ListeNoeuds = gr.generationNoeud(citiesNumber);

		Map map = new Map(ListeNoeuds);

		// Those settings should work for all 'citiesNumber':
		int populationSize = 64;
		int epochsNumber = 10000 * citiesNumber;
		SelectionMode selMode = SelectionMode.Uniform;

		// Finding a good path (hopefully):
		int[] best_path = geneticSearch(map, populationSize, epochsNumber, selMode);
		Position[] listeGagnante = new Position[citiesNumber];
		for (int i = 0; i<citiesNumber; i++) {
			listeGagnante[i] = ListeNoeuds[best_path[i]];
		}
		System.out.print("\nBest found path:\n");
		Path.print(best_path);
	}*/
}
