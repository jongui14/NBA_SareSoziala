package com.jgr14.nbasaresoziala.domain;
// Generated 06-mar-2019 12:17:39 by Hibernate Tools 5.4.1.Final

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Date;

import java.io.Serializable;
import java.util.Hashtable;

/**
 * Mezua generated by hbm2java
 */
public class Mezua implements Serializable,KvmSerializable {

	private Integer idMezua;
	private Erabiltzailea erabiltzailea;
	private Komunitatea komunitatea;
	
	private Date eguna;
	private Boolean ezabagarria;
	private String mezua;

	public Mezua() {
	}

	public Mezua(Erabiltzailea erabiltzailea, Komunitatea komunitatea, Date eguna, Boolean ezabagarria, String mezua) {
		this.erabiltzailea = erabiltzailea;
		this.komunitatea = komunitatea;
		this.eguna = eguna;
		this.ezabagarria = ezabagarria;
		this.mezua = mezua;
	}

	@Override
	public Object getProperty(int i) {
		switch (i){
			case 0: return idMezua;
			case 1: return eguna;
			case 2: return ezabagarria;
			case 3: return mezua;
		}
		return null;
	}
	@Override
	public int getPropertyCount() {
		return 4;
	}
	@Override
	public void setProperty(int i, Object o) {
		switch (i){
			case 0: idMezua=Integer.parseInt(o.toString());break;
			case 1: eguna=(Date) o;break;
			case 2: ezabagarria=Boolean.parseBoolean(o.toString());break;
			case 3: mezua=o.toString();break;
		}
	}
	@Override
	public void getPropertyInfo(int i, Hashtable properties, PropertyInfo info) {
		switch (i){
			case 0:info.name="idMezua";info.type = PropertyInfo.INTEGER_CLASS;break;
			case 1:info.name="eguna";info.setType(Date.class);break;
			case 2:info.name="ezabagarria";info.type = PropertyInfo.BOOLEAN_CLASS;break;
			case 3:info.name="mezua";info.type = PropertyInfo.STRING_CLASS;break;
		}
	}



	public Integer getIdMezua() {
		return this.idMezua;
	}

	public void setIdMezua(Integer idMezua) {
		this.idMezua = idMezua;
	}

	public Erabiltzailea getErabiltzailea() {
		return this.erabiltzailea;
	}

	public void setErabiltzailea(Erabiltzailea erabiltzailea) {
		this.erabiltzailea = erabiltzailea;
	}

	public Komunitatea getKomunitatea() {
		return this.komunitatea;
	}

	public void setKomunitatea(Komunitatea komunitatea) {
		this.komunitatea = komunitatea;
	}

	public Date getEguna() {
		return this.eguna;
	}

	public void setEguna(Date eguna) {
		this.eguna = eguna;
	}

	public Boolean getEzabagarria() {
		return this.ezabagarria;
	}

	public void setEzabagarria(Boolean ezabagarria) {
		this.ezabagarria = ezabagarria;
	}

	public String getMezua() {
		return this.mezua;
	}

	public void setMezua(String mezua) {
		this.mezua = mezua;
	}

}
