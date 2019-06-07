package com.jgr14.nbasaresoziala.domain;


import java.sql.Time;
import java.util.*;

import java.io.Serializable;

/**
 *
 */
public class Partidua implements Serializable {

    /**
     * Default constructor
     */
    public Partidua() {
    }

    /**
     *
     */

    private Integer idPartidua=0;

    private int partiduaren_egoera=3;//1 (hasi gabe), 2 (hasita), 3 (bukatua)
    private Date data=new Date();
    private Time ordua=new Time(0,0,0);
	private String orduaString="";
    private Time erlojua=new Time(0,0,0);
    private int zatia=0;

    private int etxeko_puntuak=0;
    private int kanpoko_puntuak=0;

    private Taldea kanpoko_taldea=new Taldea();
    private Taldea etxeko_taldea=new Taldea();

    private ArrayList<Jokalariaren_partidua> etxeko_jokalariak = new ArrayList<Jokalariaren_partidua>();
    private ArrayList<Jokalariaren_partidua> kanpoko_jokalariak = new ArrayList<Jokalariaren_partidua>();

    private ArrayList<Integer> etxeko_puntuaketak = new ArrayList<Integer>();
    private ArrayList<Integer> kanpoko_puntuaketak = new ArrayList<Integer>();


	public String PartidukoZatia(){
		String cuarto="";
		if(zatia<5)cuarto="Q"+zatia;
		else cuarto="OT"+(zatia-4);
		return cuarto;
	}
	public String PartiduaBukatuaZatia(){
		int restante=zatia-4;
		if(restante>0){
			return "Final "+restante+"OT";
		}else{
			return "Final";
		}
	}
	public String PartiduarenEgoera(){
		String cuarto="";
		if(zatia<5)cuarto="Q"+zatia;
		else cuarto="OT"+(zatia-4);
		cuarto=cuarto+" "+erlojua.getMinutes()+":"+erlojua.getSeconds();
		return cuarto;
	}


    public void etxeko_jokalaria_gehitu(Jokalariaren_partidua Jokalariaren_partidua) {
    	etxeko_jokalariak.add(Jokalariaren_partidua);
    }
    public void kanpoko_jokalaria_gehitu(Jokalariaren_partidua Jokalariaren_partidua) {
    	kanpoko_jokalariak.add(Jokalariaren_partidua);
    }

    public void etxeko_puntuaketak_gehitu(int puntuak) {
    	etxeko_puntuaketak.add(puntuak);
    }
    public void kanpoko_puntuaketak_gehitu(int puntuak) {
    	kanpoko_puntuaketak.add(puntuak);
    }


    public int getIdPartidua() {
		return idPartidua;
	}
	public int getPartiduaren_egoera() {
		return partiduaren_egoera;
	}
	public Date getData() {
		return data;
	}
	public Time getOrdua() {
		return ordua;
	}
	public Time getErlojua() {
		return erlojua;
	}
	public int getZatia() {
		return zatia;
	}
	public int getEtxeko_puntuak() {
		return etxeko_puntuak;
	}
	public int getKanpoko_puntuak() {
		return kanpoko_puntuak;
	}
	public Taldea getKanpoko_taldea() {
		return kanpoko_taldea;
	}
	public Taldea getEtxeko_taldea() {
		return etxeko_taldea;
	}
	public ArrayList<Jokalariaren_partidua> getEtxeko_jokalariak() {
		return etxeko_jokalariak;
	}
	public ArrayList<Jokalariaren_partidua> getKanpoko_jokalariak() {
		return kanpoko_jokalariak;
	}
	public void setIdPartidua(int idPartidua) {
		this.idPartidua = idPartidua;
	}
	public void setPartiduaren_egoera(int partiduaren_egoera) {
		this.partiduaren_egoera = partiduaren_egoera;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public void setOrdua(Time ordua) {
		this.ordua = ordua;
	}
	public void setErlojua(Time erlojua) {
		this.erlojua = erlojua;
	}
	public void setZatia(int zatia) {
		this.zatia = zatia;
	}
	public void setEtxeko_puntuak(int etxeko_puntuak) {
		this.etxeko_puntuak = etxeko_puntuak;
	}
	public void setKanpoko_puntuak(int kanpoko_puntuak) {
		this.kanpoko_puntuak = kanpoko_puntuak;
	}
	public void setKanpoko_taldea(Taldea kanpoko_taldea) {
		this.kanpoko_taldea = kanpoko_taldea;
	}
	public void setEtxeko_taldea(Taldea etxeko_taldea) {
		this.etxeko_taldea = etxeko_taldea;
	}
	public void setEtxeko_jokalariak(ArrayList<Jokalariaren_partidua> etxeko_jokalariak) {
		this.etxeko_jokalariak = etxeko_jokalariak;
	}
	public void setKanpoko_jokalariak(ArrayList<Jokalariaren_partidua> kanpoko_jokalariak) {
		this.kanpoko_jokalariak = kanpoko_jokalariak;
	}
	public ArrayList<Integer> getEtxeko_puntuaketak() {
		return etxeko_puntuaketak;
	}
	public ArrayList<Integer> getKanpoko_puntuaketak() {
		return kanpoko_puntuaketak;
	}
	public void setEtxeko_puntuaketak(ArrayList<Integer> etxeko_puntuaketak) {
		this.etxeko_puntuaketak = etxeko_puntuaketak;
	}
	public void setKanpoko_puntuaketak(ArrayList<Integer> kanpoko_puntuaketak) {
		this.kanpoko_puntuaketak = kanpoko_puntuaketak;
	}
	public String getOrduaString() {
		return orduaString;
	}
	public void setOrduaString(String orduaString) {
		this.orduaString = orduaString;
	}




}