package fr.isen.m1.schedule.marchant.moteur;

import testProjetBigData.Map;

public abstract class Path
{
	/**
	 * @return Euclidean distance between (x1, y1) and (x2, y2).
	 */
	public static double distance(double x1, double y1, double x2, double y2)
	{
		double delta_x = x1 - x2, delta_y = y1 - y2;

		return (double) Math.sqrt(delta_x * delta_x + delta_y * delta_y);
	}

	/**
	 * @return Euclidean distance between (x1, y1) and (x2, y2) with criticality.
	 */
	public static double distanceCriticite(double x1, double y1, double x2, double y2, double criticite)
		{
			double delta_x = x1 - x2, delta_y = y1 - y2;
			return (double) ((Math.sqrt(delta_x * delta_x + delta_y * delta_y))*criticite);
		}

	/**
	 * @return Total length of the path, coming back to the start
	 */
	public static double totalLength(Map map, int path[])
	{
		int n = path.length;

		if (n != map.citiesNumber())
		{
			System.out.println("\nIncompatible path sizes!\n");
			throw new RuntimeException();
		}

		double totalLength = map.net()[path[n - 1]][path[0]];

		for (int i = 0; i < n - 1; ++i)
			totalLength += map.net()[path[i]][path[i + 1]];;

		return totalLength;
	}

	/**
	 * @return Total lenght of the path with criticality.
	 */
	public static double totalLengthCriticity(Map map, int path[])
	{
		int n = path.length;

		if (n != map.citiesNumber())
		{
			System.out.println("\nIncompatible path sizes!\n");
			throw new RuntimeException();
		}

		double totalLength = map.netCriticity()[path[n - 1]][path[0]];

		for (int i = 0; i < n - 1; ++i)
			totalLength += map.netCriticity()[path[i]][path[i + 1]];;

		return totalLength;
	}
	/**
	 * print a Path.
	 */
	public static void print(int path[])
	{
		for (int i = 0; i < path.length; ++i)
			System.out.printf("%d ", path[i]);

		System.out.println();
	}

	/**
	 * swap places between to int in the path.
	 */
	public static void swap(int path[], int i, int j)
	{
		int temp = path[i];

		path[i] = path[j];

		path[j] = temp;
	}

	/**
	 * Mirror the values between start and end. Needed: start <= end.
	 */
	public static void mirror(int path[], int start, int end)
	{
		int midpoint = (end - start + 1) / 2; // central value isn't swapped if the number of value is odd.

		for (int i = 0; i < midpoint; ++i)
			swap(path, start + i, end - i);
	}

}
