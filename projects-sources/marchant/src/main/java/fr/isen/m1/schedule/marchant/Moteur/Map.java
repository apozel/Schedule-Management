package fr.isen.m1.schedule.marchant.moteur;

import fr.isen.m1.schedule.utilities.Diagnosis;
import testProjetBigData.Path;

public class Map {
	// private final static double distBound = 20; // 20 km. Arbitrary.

	private int citiesNumber;
	private Diagnosis listDiag[];
	private double net[][]; // citiesNumber * citiesNumber -> distance map.
	private double netCriticity[][];

	public Map(Diagnosis[] listNoeud) {
		this.citiesNumber = listNoeud.length;
		this.listDiag = listNoeud;
		this.net = new double[citiesNumber][citiesNumber];
		this.netCriticity = new double[citiesNumber][citiesNumber];

		this.init(); // A map must be initialized.
	}
	
	/**
	 * @return the number of Diagnosis in the list
	 */
	public int citiesNumber() {
		return this.citiesNumber;
	}

	/**
	 * @return the list of Diagnosis
	 */
	public Diagnosis[] locations() {
		return this.listDiag;
	}

	/**
	 * @return the map of distance calculated
	 */
	public double[][] net() {
		return this.net;
	}

	/**
	 * @return the map of distance calculated with criticality
	 */
	public double[][] netCriticity() {
		return this.netCriticity;
	}

	/**
	 * initialize the map by calculating all distances with and without criticality.
	 */
	public void init() {
		for (int i = 0; i < this.citiesNumber; ++i) {
			for (int j = i + 1; j < this.citiesNumber; ++j) {
				double x1 = (double) this.listDiag[i].getPatientConserne().getLieuDeVie().getX();
				double y1 = (double) this.listDiag[i].getPatientConserne().getLieuDeVie().getY();

				double x2 = (double) this.listDiag[j].getPatientConserne().getLieuDeVie().getX();
				double y2 = (double) this.listDiag[j].getPatientConserne().getLieuDeVie().getY();

				this.net[i][j] = Path.distance(x1, y1, x2, y2);
				this.netCriticity[i][j] =
						Path.distanceCriticite(x1, y1, x2, y2, (double) listDiag[j].getCriticite());
				this.net[j][i] = this.net[i][j];
				this.netCriticity[j][i] =
						Path.distanceCriticite(x2, y2, x1, y1, (double) listDiag[i].getCriticite());
			}
		}
	}

	/**
	 * print the map.
	 */
	public void print() {
		System.out.printf("\nCities number: %d\n", this.citiesNumber);

		System.out.printf("\nLocations:\n\n");
		for (int i = 0; i < this.citiesNumber; ++i)
			System.out.printf("x = %6.3f, y = %6.3f\n", this.listDiag[i]
					.getPatientConserne().getLieuDeVie().getX(),
					this.listDiag[i].getPatientConserne().getLieuDeVie().getY());

		System.out.printf("\nNet:\n\n");
		for (int i = 0; i < this.citiesNumber; ++i) {
			for (int j = 0; j < this.citiesNumber; ++j)
				System.out.printf("%6.3f  ", this.net[i][j]);

			System.out.println();
		}
	}
}

