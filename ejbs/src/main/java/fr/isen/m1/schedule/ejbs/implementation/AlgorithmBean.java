package fr.isen.m1.schedule.ejbs.implementation;

import javax.ejb.Stateless;
import fr.isen.m1.schedule.ejbs.ejbinterface.AlgorithmInterface;

@Stateless(mappedName = "AlgorithmInterface")
public class AlgorithmBean implements AlgorithmInterface {



    @Override
    public String helloWord() {

        return "Hello World";
    }


}
