package fr.isen.m1.schedule.marchant.moteur;

public class Utilities
{
	/**
	 * @param bound : the max range to have the random number.
	 * @return a random number integer between 0 and bound
	 */
	public static int randInt(int bound)
	{
		return (int) (Math.random() * bound);
	}

	/**
	 * @param bound : the max range to have the random number.
	 * @return a random number double between 0 and bound
	 */
	public static double randdouble(double bound)
	{
		return (double) (Math.random() * bound);
	}
}
