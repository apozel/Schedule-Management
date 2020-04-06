package fr.isen.m1.schedule.marchant.moteur;


public abstract class Path
{
	// Euclidean distance between (x1, y1) and (x2, y2).
	public static float distance(float x1, float y1, float x2, float y2)
	{
		float delta_x = x1 - x2, delta_y = y1 - y2;

		return (float) Math.sqrt(delta_x * delta_x + delta_y * delta_y);
	}

	// Euclidean distance between (x1, y1) and (x2, y2) using criticity.
		public static float distanceCriticite(float x1, float y1, float x2, float y2, float criticite)
		{
			float delta_x = x1 - x2, delta_y = y1 - y2;
			if (criticite == 0) {
				criticite = 1;
			}
			criticite = (float) (criticite/10);
			return (float) ((Math.sqrt(delta_x * delta_x + delta_y * delta_y))/criticite);
		}

	// Total length of the path, coming back to the start:
	public static float totalLength(Map map, int path[])
	{
		int n = path.length;

		if (n != map.citiesNumber())
		{
			System.out.println("\nIncompatible path sizes!\n");
			throw new RuntimeException();
		}

		float totalLength = map.net()[path[n - 1]][path[0]];

		for (int i = 0; i < n - 1; ++i)
			totalLength += map.net()[path[i]][path[i + 1]];;

		return totalLength;
	}

	//Total lenght of the path with criticity.
	public static float totalLengthCriticity(Map map, int path[])
	{
		int n = path.length;

		if (n != map.citiesNumber())
		{
			System.out.println("\nIncompatible path sizes!\n");
			throw new RuntimeException();
		}

		float totalLength = map.netCriticity()[path[n - 1]][path[0]];

		for (int i = 0; i < n - 1; ++i)
			totalLength += map.netCriticity()[path[i]][path[i + 1]];;

		return totalLength;
	}

	public static void print(int path[])
	{
		for (int i = 0; i < path.length; ++i)
			System.out.printf("%d ", path[i]);

		System.out.println();
	}

	public static void swap(int path[], int i, int j)
	{
		int temp = path[i];

		path[i] = path[j];

		path[j] = temp;
	}

	// Mirror the values between start and end. Needed: start <= end.
	public static void mirror(int path[], int start, int end)
	{
		int midpoint = (end - start + 1) / 2; // central value isn't swapped if the number of value is odd.

		for (int i = 0; i < midpoint; ++i)
			swap(path, start + i, end - i);
	}

	/*public static void main(String[] args)
	{
		int citiesNumber = 10;

		int path[] = new int[citiesNumber];

		for (int i = 0; i < citiesNumber; ++i)
			path[i] = i;

		mirror(path, 1, 8); // first and last values untouched.

		print(path);
	}*/
}
