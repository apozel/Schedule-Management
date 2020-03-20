public class Map
{
	private final static float distBound = 20; // 20 km. Arbitrary.

	private int citiesNumber;
	private float locations[][];	// citiesNumber * 2
	private float net[][];			// citiesNumber * citiesNumber -> distance map.

	public Map(int citiesNumber)
	{
		this.citiesNumber = citiesNumber;
		this.locations = new float[citiesNumber][2];
		this.net = new float[citiesNumber][citiesNumber];

		this.init(); // A map must be initialized.
	}

	public int citiesNumber()
	{
		return this.citiesNumber;
	}

	public float[][] locations()
	{
		return this.locations;
	}

	public float[][] net()
	{
		return this.net;
	}

	public void init()
	{
		for (int i = 0; i < this.citiesNumber; ++i)
		{
			this.locations[i][0] = Utilities.randFloat(distBound); // between 0 and 'distBound' km from the origin.
			this.locations[i][1] = Utilities.randFloat(distBound);

			// this.locations[i][0] = 2.5f; // between 0 and 'distBound' km from the origin.
			// this.locations[i][1] = 0f;
		}

		for (int i = 0; i < this.citiesNumber; ++i)
		{
			for (int j = i + 1; j < this.citiesNumber; ++j)
			{
				float x1 = this.locations[i][0];
				float y1 = this.locations[i][1];

				float x2 = this.locations[j][0];
				float y2 = this.locations[j][1];

				this.net[i][j] = Path.distance(x1, y1, x2, y2);

				this.net[j][i] = this.net[i][j];
			}
		}
	}

	public void print()
	{
		System.out.printf("\nCities number: %d\n", this.citiesNumber);

		System.out.printf("\nLocations:\n\n");
		for (int i = 0; i < this.citiesNumber; ++i)
			System.out.printf("x = %6.3f, y = %6.3f\n", this.locations[i][0], this.locations[i][1]);

		System.out.printf("\nNet:\n\n");
		for (int i = 0; i < this.citiesNumber; ++i)
		{
			for (int j = 0; j < this.citiesNumber; ++j)
				System.out.printf("%6.3f  ", this.net[i][j]);

			System.out.println();
		}
	}

	public static void main(String[] args)
	{
		int citiesNumber = 5;

		Map map = new Map(citiesNumber);

		map.print();

		int path[] = new int[citiesNumber];

		for (int i = 0; i < citiesNumber; ++i)
			path[i] = i;

		System.out.print("\nA path:\n");
		Path.print(path);

		System.out.printf("\nIts length: %.3f km\n", Path.totalLength(map, path));
	}
}
