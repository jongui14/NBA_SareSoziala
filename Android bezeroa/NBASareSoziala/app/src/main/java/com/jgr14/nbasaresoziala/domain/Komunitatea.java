package com.jgr14.nbasaresoziala.domain;
// Generated 06-mar-2019 12:17:39 by Hibernate Tools 5.4.1.Final

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import java.io.Serializable;

/**
 * Komunitatea generated by hbm2java
 */

public class Komunitatea implements Serializable , KvmSerializable {

	private Integer idKomunitatea=1;
	
	private String nick="";
	private String izenOsoa="";
	private String pasahitza="";
	private String ongietorriMezua="";
	private int saria1=0;
	private int saria2=0;
	private int saria3=0;

	private ArrayList<Erabiltzailea> erabiltzaileas = new ArrayList<>();
	private ArrayList<MerkatukoJokalaria> merkatukoJokalarias = new ArrayList<>();
	private ArrayList<Mezua> mezuas = new ArrayList<>();

	public Komunitatea() {
		idKomunitatea=1;
	}


	public Komunitatea(Integer idKomunitatea) {
		this.idKomunitatea = idKomunitatea;
	}
	public Komunitatea(String nick) {
		this.nick = nick;
	}

	public Komunitatea(String nick, String izenOsoa, String pasahitza, String ongietorriMezua, int saria1,int saria2, int saria3) {
		this.nick = nick;
		this.izenOsoa = izenOsoa;
		this.pasahitza = pasahitza;
		this.ongietorriMezua = ongietorriMezua;
		this.saria1 = saria1;
		this.saria2 = saria2;
		this.saria3 = saria3;
	}

	@Override
	public Object getProperty(int i) {
		switch (i){
			case 0: return idKomunitatea;
			case 1: return nick;
			case 2: return izenOsoa;
			case 3: return pasahitza;
			case 4: return ongietorriMezua;
			case 5: return saria1;
			case 6: return saria2;
			case 7: return saria3;
		}
		return null;
	}

	@Override
	public int getPropertyCount() {
		return 8;
	}

	@Override
	public void setProperty(int i, Object o) {
		switch (i){
			case 0: idKomunitatea=Integer.parseInt(o.toString());break;
			case 1: nick=o.toString();break;
			case 2: izenOsoa=o.toString();break;
			case 3: pasahitza=o.toString();break;
			case 4: ongietorriMezua=o.toString();break;
			case 5: saria1=Integer.parseInt(o.toString());break;
			case 6: saria2=Integer.parseInt(o.toString());break;
			case 7: saria3=Integer.parseInt(o.toString());break;
		}
	}

	@Override
	public void getPropertyInfo(int i, Hashtable properties, PropertyInfo info) {
		switch (i){
			case 0:info.name="idKomunitatea";info.type = PropertyInfo.INTEGER_CLASS;break;
			case 1:info.name="nick";info.type = PropertyInfo.STRING_CLASS;break;
			case 2:info.name="izenOsoa";info.type = PropertyInfo.STRING_CLASS;break;
			case 3:info.name="pasahitza";info.type = PropertyInfo.STRING_CLASS;break;
			case 4:info.name="ongietorriMezua";info.type = PropertyInfo.STRING_CLASS;break;
			case 5:info.name="saria1";info.type = PropertyInfo.INTEGER_CLASS;break;
			case 6:info.name="saria2";info.type = PropertyInfo.INTEGER_CLASS;break;
			case 7:info.name="saria3";info.type = PropertyInfo.INTEGER_CLASS;break;
		}
	}


	
	public void erabiltzaileaGehitu(Erabiltzailea erab) {
		erabiltzaileas.add(erab);
	}

	public Integer getIdKomunitatea() {
		return this.idKomunitatea;
	}

	public void setIdKomunitatea(Integer idKomunitatea) {
		this.idKomunitatea = idKomunitatea;
	}

	public String getNick() {
		return this.nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getIzenOsoa() {
		return this.izenOsoa;
	}

	public void setIzenOsoa(String izenOsoa) {
		this.izenOsoa = izenOsoa;
	}

	public String getPasahitza() {
		return this.pasahitza;
	}

	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}

	public String getOngietorriMezua() {
		return this.ongietorriMezua;
	}

	public void setOngietorriMezua(String ongietorriMezua) {
		this.ongietorriMezua = ongietorriMezua;
	}

	public int getSaria1() {
		return this.saria1;
	}

	public void setSaria1(int saria1) {
		this.saria1 = saria1;
	}

	public int getSaria2() {
		return this.saria2;
	}

	public void setSaria2(int saria2) {
		this.saria2 = saria2;
	}

	public int getSaria3() {
		return this.saria3;
	}

	public void setSaria3(int saria3) {
		this.saria3 = saria3;
	}


	public ArrayList<Erabiltzailea> getErabiltzaileas() {
		return erabiltzaileas;
	}

	public void setErabiltzaileas(ArrayList<Erabiltzailea> erabiltzaileas) {
		this.erabiltzaileas = erabiltzaileas;
	}

	public ArrayList<MerkatukoJokalaria> getMerkatukoJokalarias() {
		return merkatukoJokalarias;
	}

	public void setMerkatukoJokalarias(ArrayList<MerkatukoJokalaria> merkatukoJokalarias) {
		this.merkatukoJokalarias = merkatukoJokalarias;
	}

	public ArrayList<Mezua> getMezuas() {
		return mezuas;
	}

	public void setMezuas(ArrayList<Mezua> mezuas) {
		this.mezuas = mezuas;
	}

}