package fr.isen.m1.schedule.ejbs.implementation;

import java.util.Random;
import javax.ejb.Stateless;
import fr.isen.m1.schedule.ejbs.ejbinterface.InterfaceTest;

@Stateless(mappedName = "InterfaceTest")
public class TestBean implements InterfaceTest {

    @Override
    public int randomInt() {
        return new Random().nextInt(100);
    }


}