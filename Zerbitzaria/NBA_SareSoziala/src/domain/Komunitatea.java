package domain;
// Generated 08-may-2019 9:56:30 by Hibernate Tools 5.4.1.Final

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.Session;

import dataAccess.HibernateDataAccess;
import dataAccess.HibernateUtil;


/**
 * Komunitatea generated by hbm2java
 */
@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Komunitatea implements java.io.Serializable {

	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	@GeneratedValue
	private Integer idKomunitatea=0;
	
	private String nick="";
	private String izenOsoa="";
	private String pasahitza="";
	private String ongietorriMezua="";
	private Integer saria1=0;
	private Integer saria2=0;
	private Integer saria3=0;
	
	private Set erabiltzaileas = new HashSet(0);
	private Set merkatukoJokalarias = new HashSet(0);
	private Set mezuas = new HashSet(0);

	public Komunitatea() {
	}
	
	
	public Komunitatea(int idKomunitatea) {
		this.idKomunitatea=idKomunitatea;
	}
	
	public Komunitatea getKopia() {
		Komunitatea res=new Komunitatea();
		res.setIdKomunitatea(idKomunitatea);
		res.setNick(nick);
		res.setIzenOsoa(izenOsoa);
		res.setPasahitza(pasahitza);
		res.setOngietorriMezua(ongietorriMezua);
		res.setSaria1(saria1);
		res.setSaria2(saria2);
		res.setSaria3(saria3);		
		return res;
	}
	


	public Komunitatea(String nick) {
		this.nick = nick;
	}

	public Komunitatea(String nick, String izenOsoa, String pasahitza, String ongietorriMezua, Integer saria1,
			Integer saria2, Integer saria3, Set erabiltzaileas, Set merkatukoJokalarias, Set mezuas) {
		this.nick = nick;
		this.izenOsoa = izenOsoa;
		this.pasahitza = pasahitza;
		this.ongietorriMezua = ongietorriMezua;
		this.saria1 = saria1;
		this.saria2 = saria2;
		this.saria3 = saria3;
		this.erabiltzaileas = erabiltzaileas;
		this.merkatukoJokalarias = merkatukoJokalarias;
		this.mezuas = mezuas;
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

	public Integer getSaria1() {
		return this.saria1;
	}

	public void setSaria1(Integer saria1) {
		this.saria1 = saria1;
	}

	public Integer getSaria2() {
		return this.saria2;
	}

	public void setSaria2(Integer saria2) {
		this.saria2 = saria2;
	}

	public Integer getSaria3() {
		return this.saria3;
	}

	public void setSaria3(Integer saria3) {
		this.saria3 = saria3;
	}

	public Set getErabiltzaileas() {
		return this.erabiltzaileas;
	}

	public void setErabiltzaileas(Set erabiltzaileas) {
		this.erabiltzaileas = erabiltzaileas;
	}

	public Set getMerkatukoJokalarias() {
		return this.merkatukoJokalarias;
	}

	public void setMerkatukoJokalarias(Set merkatukoJokalarias) {
		this.merkatukoJokalarias = merkatukoJokalarias;
	}

	public Set getMezuas() {
		return this.mezuas;
	}

	public void setMezuas(Set mezuas) {
		this.mezuas = mezuas;
	}

}
