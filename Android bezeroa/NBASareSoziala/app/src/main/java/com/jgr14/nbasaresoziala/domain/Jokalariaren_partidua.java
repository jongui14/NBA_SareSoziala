package com.jgr14.nbasaresoziala.domain;


import java.util.*;
import java.io.Serializable;

/**
 * 
 */
public class Jokalariaren_partidua implements Serializable {

    /**
     * Default constructor
     */
    public Jokalariaren_partidua() {
    }

    /**
     * 
     */
    private int puntuak;
    private int reboteak;
    private int asistentziak;
    private int lapurretak;
    private int tapoiak;
    private int galdutakoak;
    private int falta_pertsonalak;
    private int plus_minus;
    private int sartutako_tiroak;
    private int sartutako_tiro_libreak;
    private int sartutako_tripleak;
    private int saiatutako_tiroak;
    private int saiatutako_tiro_libreak;
    private int saiatutako_tripleak;
    private int minutuak;
    private int segunduak;


    private Partidua partidua;
    private Jokalaria jokalaria;
    
    
    public int jokalariaren_balorazioa() {
    	return puntuak+reboteak+asistentziak+lapurretak+tapoiak+sartutako_tiro_libreak+sartutako_tripleak+sartutako_tiroak
    			-this.galdutakoak-this.falta_pertsonalak-this.saiatutako_tripleak-this.saiatutako_tiro_libreak-this.saiatutako_tiroak;
    }
    
    
	public int getPuntuak() {
		return puntuak;
	}
	public int getReboteak() {
		return reboteak;
	}
	public int getAsistentziak() {
		return asistentziak;
	}
	public int getLapurretak() {
		return lapurretak;
	}
	public int getTapoiak() {
		return tapoiak;
	}
	public int getGaldutakoak() {
		return galdutakoak;
	}
	public int getFalta_pertsonalak() {
		return falta_pertsonalak;
	}
	public int getPlus_minus() {
		return plus_minus;
	}
	public int getSartutako_tiroak() {
		return sartutako_tiroak;
	}
	public int getSartutako_tiro_libreak() {
		return sartutako_tiro_libreak;
	}
	public int getSartutako_tripleak() {
		return sartutako_tripleak;
	}
	public int getSaiatutako_tiroak() {
		return saiatutako_tiroak;
	}
	public int getSaiatutako_tiro_libreak() {
		return saiatutako_tiro_libreak;
	}
	public int getSaiatutako_tripleak() {
		return saiatutako_tripleak;
	}
	public int getMinutuak() {
		return minutuak;
	}
	public int getSegunduak() {
		return segunduak;
	}
	public Partidua getPartidua() {
		return partidua;
	}
	public Jokalaria getJokalaria() {
		return jokalaria;
	}
	public void setPuntuak(int puntuak) {
		this.puntuak = puntuak;
	}
	public void setReboteak(int reboteak) {
		this.reboteak = reboteak;
	}
	public void setAsistentziak(int asistentziak) {
		this.asistentziak = asistentziak;
	}
	public void setLapurretak(int lapurretak) {
		this.lapurretak = lapurretak;
	}
	public void setTapoiak(int tapoiak) {
		this.tapoiak = tapoiak;
	}
	public void setGaldutakoak(int galdutakoak) {
		this.galdutakoak = galdutakoak;
	}
	public void setFalta_pertsonalak(int falta_pertsonalak) {
		this.falta_pertsonalak = falta_pertsonalak;
	}
	public void setPlus_minus(int plus_minus) {
		this.plus_minus = plus_minus;
	}
	public void setSartutako_tiroak(int sartutako_tiroak) {
		this.sartutako_tiroak = sartutako_tiroak;
	}
	public void setSartutako_tiro_libreak(int sartutako_tiro_libreak) {
		this.sartutako_tiro_libreak = sartutako_tiro_libreak;
	}
	public void setSartutako_tripleak(int sartutako_tripleak) {
		this.sartutako_tripleak = sartutako_tripleak;
	}
	public void setSaiatutako_tiroak(int saiatutako_tiroak) {
		this.saiatutako_tiroak = saiatutako_tiroak;
	}
	public void setSaiatutako_tiro_libreak(int saiatutako_tiro_libreak) {
		this.saiatutako_tiro_libreak = saiatutako_tiro_libreak;
	}
	public void setSaiatutako_tripleak(int saiatutako_tripleak) {
		this.saiatutako_tripleak = saiatutako_tripleak;
	}
	public void setMinutuak(int minutuak) {
		this.minutuak = minutuak;
	}
	public void setSegunduak(int segunduak) {
		this.segunduak = segunduak;
	}
	public void setPartidua(Partidua partidua) {
		this.partidua = partidua;
	}
	public void setJokalaria(Jokalaria jokalaria) {
		this.jokalaria = jokalaria;
	}


}
