package domain;

import java.util.*;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;

/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Puntuazioa implements Serializable {

    /**
     * Default constructor
     */
    public Puntuazioa() {
    }

    /**
     * 
     */
    public int puntuak;
    
    @XmlIDREF public Erabiltzailea erabiltzailea;
    @XmlIDREF public Jardunaldia jardunaldia;


}