package fr.isen.m1.schedule.marchant.Moteur;

import fr.isen.m1.schedule.utilities.Position;

public class Map {
	// private final static float distBound = 20; // 20 km. Arbitrary.

	private int citiesNumber;
	private Diagnosis listDiag[];
	private float net[][]; // citiesNumber * citiesNumber -> distance map.
	private float netCriticity[][];

	public Map(Diagnosis[] listNoeud) {
		this.citiesNumber = listNoeud.length;
		this.locations = listNoeud;
		this.net = new float[citiesNumber][citiesNumber];
		this.netCriticity = new float[citiesNumber][citiesNumber];

		this.init(); // A map must be initialized.
	}

	public int citiesNumber() {
		return this.citiesNumber;
	}

	public Diagnosis[] locations() {
		return this.locations;
	}

	public float[][] net() {
		return this.net;
	}

	public float[][] netCriticity() {
		return this.netCriticity;
	}

	public void init() {
		for (int i = 0; i < this.citiesNumber; ++i) {
			for (int j = i + 1; j < this.citiesNumber; ++j) {
				float x1 = (float) this.listDiag[i].getPatientConserne().getLieuDeVie().getX();
				float y1 = (float) this.listDiag[i].getPatientConserne().getLieuDeVie().getY();

				float x2 = (float) this.listDiag[j].getPatientConserne().getLieuDeVie().getX();
				float y2 = (float) this.listDiag[j].getPatientConserne().getLieuDeVie().getY();

				this.net[i][j] = Path.distance(x1, y1, x2, y2);
				this.netCriticity[i][j] = Path.distanceCriticite(x1, y1, x2, y2, (float) listDiag[j].getCriticite());
				this.net[j][i] = this.net[i][j];
				this.netCriticity[j][i] = Path.distanceCriticite(x2, y2, x1, y1, (float) listDiag[i].getCriticite());
			}
		}
	}

	public void print() {
		System.out.printf("\nCities number: %d\n", this.citiesNumber);

		System.out.printf("\nLocations:\n\n");
		for (int i = 0; i < this.citiesNumber; ++i)
			System.out.printf("x = %6.3f, y = %6.3f\n", this.locations[i].getX(), this.locations[i].getY());

		System.out.printf("\nNet:\n\n");
		for (int i = 0; i < this.citiesNumber; ++i) {
			for (int j = 0; j < this.citiesNumber; ++j)
				System.out.printf("%6.3f  ", this.net[i][j]);

			System.out.println();
		}
	}

	/*public static void main(String[] args) {
		int citiesNumber = 5;

		Graphe gr = new Graphe();
		Position[] ListeNoeuds = new Position[citiesNumber];
		ListeNoeuds = gr.generationNoeud(citiesNumber);

		Map map = new Map(ListeNoeuds);

		map.print();

		int path[] = new int[citiesNumber];

		for (int i = 0; i < citiesNumber; ++i)
			path[i] = i;

		System.out.print("\nA path:\n");
		Path.print(path);

		System.out.printf("\nIts length: %.3f km\n", Path.totalLength(map, path));
	}*/
}
