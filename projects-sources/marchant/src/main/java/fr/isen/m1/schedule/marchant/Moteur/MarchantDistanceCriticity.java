package fr.isen.m1.schedule.marchant.moteur;

<<<<<<< HEAD
=======
import fr.isen.m1.schedule.utilities.Diagnosis;
import testProjetBigData.Genetic;
import testProjetBigData.Map;
import testProjetBigData.Path;

>>>>>>> 5ee149ca4f9c7ff80ad7c6491d1331a44287e006
import java.util.ArrayList;
import fr.isen.m1.schedule.marchant.moteur.Genetic.SelectionMode;
import fr.isen.m1.schedule.utilities.Diagnosis;
import fr.isen.m1.schedule.utilities.Doctor;

public class MarchantDistanceCriticity{
	private double distance;
	private double distanceCriticity;
	private Diagnosis[] listDiag;


	public MarchantDistanceCriticity(Diagnosis[] list, Doctor doctor) {
		distance = 0;
		distanceCriticity = 0;
		listDiag = givePathToFollowPrioHighCriticity(list, doctor);
	}

	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}


	/**
	 * @return the distanceCriticity
	 */
	public double getDistanceCriticity() {
		return distanceCriticity;
	}


	/**
	 * @return the listDiag
	 */
	public Diagnosis[] getListDiag() {
		return listDiag;
	}

	private Diagnosis[] givePathToFollowPrioHighCriticity(Diagnosis[] listeDiag, Doctor doctor){
        if(listeDiag.length == 0) {
        	System.out.println("List of appointments empty.");
        	return listeDiag;
        }
		// Those settings should work for all 'citiesNumber':
        int citiesNumber = listeDiag.length;
        int populationSize = 64;
	    int epochsNumber = 10000 * citiesNumber;
		SelectionMode selMode = SelectionMode.Uniform;

        distance = calculDistanceWithoutCriticity(listeDiag, citiesNumber, populationSize, epochsNumber, selMode);
        Diagnosis[] listeGagnante = new Diagnosis[listeDiag.length];
        ArrayList<Integer> listIndexHigh = new ArrayList<Integer>();
        ArrayList<Integer> listIndexLow = new ArrayList<Integer>();

        //Creation of the two list high and low criticity to give priority to high criticality sicked
        for (int i = 0; i<listeDiag.length; i++){
            if(listeDiag[i].getCriticite() >= 0.7){
                listIndexHigh.add(i);
            }
            else{
                listIndexLow.add(i);
            }
        }

        Diagnosis[] highCriticityList = null;
        Diagnosis[] listPositionHigh = null;
        Diagnosis[] lowCriticityList = null;
        Diagnosis[] listPositionLow = null;
        Diagnosis[] lstPositionLow = null;


        //Searching the best path to follow for the low criticality list
        if(listIndexLow.size() != 0) {
	        lowCriticityList = new Diagnosis[listIndexLow.size()];

	        for(int i = 0 ; i < listIndexLow.size(); i++){
	            lowCriticityList[i] = listeDiag[listIndexLow.get(i)];
	        }

        }

        //Searching the best to follow for the high criticality list
        if(listIndexHigh.size() != 0) {
        	highCriticityList = new Diagnosis[listIndexHigh.size()];
	        for(int i = 0 ; i < listIndexHigh.size(); i++){
	            highCriticityList[i] = listeDiag[listIndexHigh.get(i)];
	        }

	        if(listIndexHigh.size() > 1) {
	        	listPositionHigh = Genetic.givePathToFollowWithDoctor(highCriticityList, doctor);
	        }
	        else {
	        	listPositionHigh = highCriticityList;
	        }

	        if(listIndexLow.size() > 1) {
		        int[] bestPathLowCriticity = Genetic.geneticSearchWithCriticity(new Map(lowCriticityList), populationSize, epochsNumber, selMode);
		        listPositionLow = new Diagnosis[bestPathLowCriticity.length];
		        for (int i = 0 ; i < bestPathLowCriticity.length ; i++){
		            listPositionLow[i] = lowCriticityList[bestPathLowCriticity[i]];
		        }

		        //Organisation of the low criticity list to fill the entire list in order to have the best general path
		        int c = 0;
		            //parameters for distance :
		        double x1 = listPositionHigh[listPositionHigh.length-1].getPatientConserne().getLieuDeVie().getX();
		        double y1 = listPositionHigh[listPositionHigh.length-1].getPatientConserne().getLieuDeVie().getY();
		        double x2 = listPositionLow[0].getPatientConserne().getLieuDeVie().getX();
		        double y2 = listPositionLow[0].getPatientConserne().getLieuDeVie().getY();
		        double criticite = listPositionLow[0].getCriticite();
		        double distance = Path.distanceCriticite(x1, y1, x2, y2, criticite);

		        for (int i = 0 ; i < listPositionLow.length; i++){
		            double distance1 = Path.distanceCriticite(x1, x2, listPositionLow[i].getPatientConserne().getLieuDeVie().getX(), listPositionLow[i].getPatientConserne().getLieuDeVie().getY(), listPositionLow[i].getCriticite());
					if(distance1 < distance) {
						c = i;
						distance = distance1;
					}
		        }

		        //Re-organization of the low criticity list to have the list of Patient to see after the high criticty.
				lstPositionLow = new Diagnosis[listPositionLow.length];
				for (int i = 0; i < listPositionLow.length; i++) {
					if(c+i == listPositionLow.length) {
						c = c - listPositionLow.length;
					}
					lstPositionLow[i] = listPositionLow[c+i];
		        }
        	}else {
        		lstPositionLow = lowCriticityList;
        	}
        }else {
        	lstPositionLow = Genetic.givePathToFollowWithDoctor(lowCriticityList, doctor);
        }





        //Melting the two list in one to have the final list :
        if(lowCriticityList == null) {
        	for(int i = 0; i < listPositionHigh.length; i++) {
        		listeGagnante[i] = listPositionHigh[i];
        	}
        }else if(highCriticityList == null) {
        	for(int i = 0; i < lstPositionLow.length; i++) {
        		listeGagnante = lstPositionLow;
        	}
        }else {
	        for (int i = 0 ; i < listPositionHigh.length ; i++){
	            listeGagnante[i] = listPositionHigh[i];
	        }
	        for (int i = 0 ; i < lstPositionLow.length ; i++){
	            listeGagnante[i+listPositionHigh.length] = lstPositionLow[i];
	        }
        }

        //Saving the distance into the variable distanceCriticity
        int[] path = new int[listeGagnante.length];
        for(int i = 0; i<path.length; i++) {
        	path[i] = i;
        }
        distanceCriticity = Path.totalLength(new Map(listeGagnante), path);
        return listeGagnante;
    }

	private double calculDistanceWithoutCriticity(Diagnosis[] listDiag, int citiesNumber, int populationSize, int epochsNumber, SelectionMode selMode) {
		double distanceWithoutCriticity;
		Map map = new Map(listDiag);
		int[] path = Genetic.geneticSearch(map, populationSize, epochsNumber, selMode);
		distanceWithoutCriticity = Path.totalLength(map, path);
		return distanceWithoutCriticity;
	}


}