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
	private double distanceWithoutCriticality;
	private double distanceCriticality;
	private Diagnosis[] listDiag;
	
	/**
	 * @param list : the list of diagnosis to sort.
	 * @param doctor : the Doctor that will go through the list of diagnosis.
	 */
	public MarchantDistanceCriticality(Diagnosis[] list, Doctor doctor) {
		distanceWithoutCriticality = 0;
		distanceCriticality = 0;
		listDiag = givePathToFollowPrioHighCriticality(list, doctor);
	}


	/**
	 * @return the distanceWithoutCriticality
	 */
	public double getDistanceWithoutCriticality() {
		return distanceWithoutCriticality;
	}


	/**
	 * @return the distanceCriticality
	 */
	public double getDistanceCriticality() {
		return distanceCriticality;
	}


	/**
	 * @return the listDiag
	 */
	public Diagnosis[] getListDiag() {
		return listDiag;
	}

	/**
	 * The aim of this method is to create two list of diagnosis, one above the threshold and another below 
	 * and then sort the two lists separately.
	 * The threshold is fixed to 0,7.
	 * @param list : the list of diagnosis to sort.
	 * @param doctor : the Doctor that will go through the list of diagnosis.
	 * @return the sorted list giving priority to high criticality sicked. 
	 */
	private Diagnosis[] givePathToFollowPrioHighCriticality(Diagnosis[] listeDiag, Doctor doctor){
        if(listeDiag.length == 0) {
        	System.out.println("List of appointments empty.");
        	return listeDiag;
        }
		// Those settings should work for all 'citiesNumber':
        int citiesNumber = listeDiag.length;     
        int populationSize = 64;
	    int epochsNumber = 10000 * citiesNumber;
		SelectionMode selMode = SelectionMode.Uniform;
       
        
        Diagnosis[] listeGagnante = new Diagnosis[listeDiag.length];
        ArrayList<Integer> listIndexHigh = new ArrayList<Integer>();
        ArrayList<Integer> listIndexLow = new ArrayList<Integer>();

        //Creation of the two list high and low criticality to give priority to high criticality sicked
        for (int i = 0; i<listeDiag.length; i++){
            if(listeDiag[i].getCriticite() >= 0.7){
                listIndexHigh.add(i);
            }
            else{
                listIndexLow.add(i);
            }
        }
        
        Diagnosis[] highCriticalityList = null;
        Diagnosis[] listPositionHigh = null;
        Diagnosis[] lowCriticalityList = null;
        Diagnosis[] listPositionLow = null;
        Diagnosis[] lstPositionLow = null;
        
        
        //Searching the best path to follow for the low criticality list
        if(listIndexLow.size() != 0) {
	        lowCriticalityList = new Diagnosis[listIndexLow.size()];
	        
	        for(int i = 0 ; i < listIndexLow.size(); i++){
	            lowCriticalityList[i] = listeDiag[listIndexLow.get(i)];
	        }
	        
        }
        
        //Searching the best to follow for the high criticality list
        if(listIndexHigh.size() != 0) {
        	highCriticalityList = new Diagnosis[listIndexHigh.size()];
	        for(int i = 0 ; i < listIndexHigh.size(); i++){
	            highCriticalityList[i] = listeDiag[listIndexHigh.get(i)];
	        }
	        
	        if(listIndexHigh.size() > 1) {
	        	listPositionHigh = Genetic.givePathToFollowWithDoctor(highCriticalityList, doctor);
	        }
	        else {
	        	listPositionHigh = highCriticalityList;
	        }
	        
	        if(listIndexLow.size() > 1) {
		        int[] bestPathLowCriticality = Genetic.geneticSearchWithCriticity(new Map(lowCriticalityList), populationSize, epochsNumber, selMode);
		        listPositionLow = new Diagnosis[bestPathLowCriticality.length];
		        for (int i = 0 ; i < bestPathLowCriticality.length ; i++){
		            listPositionLow[i] = lowCriticalityList[bestPathLowCriticality[i]];
		        }
	
		        //Organisation of the low criticality list to fill the entire list in order to have the best general path
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
		        
		        //Re-organization of the low criticality list to have the list of Patient to see after the high criticty.
				lstPositionLow = new Diagnosis[listPositionLow.length];
				for (int i = 0; i < listPositionLow.length; i++) {
					if(c+i == listPositionLow.length) {
						c = c - listPositionLow.length;
					}
					lstPositionLow[i] = listPositionLow[c+i];
		        }
        	}else {
        		lstPositionLow = lowCriticalityList;
        	}
        }else {
        	lstPositionLow = Genetic.givePathToFollowWithDoctor(lowCriticalityList, doctor);
        }
          
        
        //Melting the two list in one to have the final list :
        if(lowCriticalityList == null) {
        	for(int i = 0; i < listPositionHigh.length; i++) {
        		listeGagnante[i] = listPositionHigh[i];
        	}
        }else if(highCriticalityList == null) {
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
        
        //Saving the distance into the variable distanceCriticality
        int[] path = new int[listeGagnante.length];
        for(int i = 0; i<path.length; i++) {
        	path[i] = i;
        }
        distanceWithoutCriticality = calculDistanceWithoutCriticality(listeDiag, citiesNumber, populationSize, epochsNumber, selMode); 
        distanceCriticality = Path.totalLength(new Map(listeGagnante), path);
        return listeGagnante; 
    }

	
	/**
	 *@param listDiag : the sorted list 
	 *@return the distance total that the doctor will have to run through
	 */
	private double calculDistanceWithoutCriticality(Diagnosis[] listDiag, int citiesNumber, int populationSize, int epochsNumber, SelectionMode selMode) {
		double distanceWithoutCriticality;
		Map map = new Map(listDiag);
		int[] path = Genetic.geneticSearch(map, populationSize, epochsNumber, selMode);
		distanceWithoutCriticality = Path.totalLength(map, path);
		return distanceWithoutCriticality;
	}


}