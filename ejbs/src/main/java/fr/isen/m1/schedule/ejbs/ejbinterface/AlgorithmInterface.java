package fr.isen.m1.schedule.ejbs.ejbinterface;

import javax.ejb.Remote;

@Remote
public interface AlgorithmInterface {
        public String helloWord();
}