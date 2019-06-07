package com.jgr14.nbasaresoziala.domain;
// Generated 06-mar-2019 12:17:39 by Hibernate Tools 5.4.1.Final

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.ArrayList;
import java.util.Date;

import java.io.Serializable;
import java.util.Hashtable;


public class MerkatukoJokalaria implements Serializable,KvmSerializable {
	

	private Integer idMerkatukoJokalaria=0;
	
	private Erabiltzailea erabiltzaileaByIdErabiltzaileaIrabazlea=new Erabiltzailea();
	private Erabiltzailea erabiltzaileaByIdErabiltzaileaJabea=new Erabiltzailea();
	private Jokalaria jokalaria=new Jokalaria();
	private Komunitatea komunitatea=new Komunitatea();
	
	private int hasierakoPrezioa=0;
	private int eskaintzaIrabazlea=0;
	private Boolean onartua=false;
	private Boolean tramitatua=false;
	private Date tramitazioEguna;

	private ArrayList<Eskaintza> eskaintzak=new ArrayList<>();


	public MerkatukoJokalaria() {
	}

	public MerkatukoJokalaria(int idMerkatukoJokalaria) {
		this.idMerkatukoJokalaria=idMerkatukoJokalaria;
	}

	public MerkatukoJokalaria(Komunitatea komunitatea) {
		this.komunitatea = komunitatea;
	}

	public MerkatukoJokalaria(Erabiltzailea erabiltzaileaByIdErabiltzaileaIrabazlea,
							  Erabiltzailea erabiltzaileaByIdErabiltzaileaJabea, Jokalaria jokalaria, Komunitatea komunitatea,
							  int hasierakoPrezioa, int eskaintzaIrabazlea, Boolean onartua, Boolean tramitatua,
							  Date tramitazioEguna) {
		this.erabiltzaileaByIdErabiltzaileaIrabazlea = erabiltzaileaByIdErabiltzaileaIrabazlea;
		this.erabiltzaileaByIdErabiltzaileaJabea = erabiltzaileaByIdErabiltzaileaJabea;
		this.jokalaria = jokalaria;
		this.komunitatea = komunitatea;
		this.hasierakoPrezioa = hasierakoPrezioa;
		this.eskaintzaIrabazlea = eskaintzaIrabazlea;
		this.onartua = onartua;
		this.tramitatua = tramitatua;
		this.tramitazioEguna = tramitazioEguna;
	}

	public String eskaintzaIrabazleaFormatuarekin(){
		try{
			String salarioLag=""+this.eskaintzaIrabazlea;
			String salario="";
			while(salarioLag.length()>3) {
				salario=salario+","+salarioLag.substring(salarioLag.length()-3,salarioLag.length());
				salarioLag=salarioLag.substring(0,salarioLag.length()-3);
			}
			salario="$"+salarioLag+salario;
			return salario;

		}catch (Exception e){
			e.printStackTrace();
			return "";
		}
	}
	public String hasierakoPrezioaFormatuarekin(){
		try{
			String salarioLag=""+this.hasierakoPrezioa;
			String salario="";
			while(salarioLag.length()>3) {
				salario=salario+","+salarioLag.substring(salarioLag.length()-3,salarioLag.length());
				salarioLag=salarioLag.substring(0,salarioLag.length()-3);
			}
			salario="$"+salarioLag+salario;
			return salario;

		}catch (Exception e){
			e.printStackTrace();
			return "";
		}
	}

	@Override
	public Object getProperty(int i) {
		switch (i) {
			case 0: return idMerkatukoJokalaria;
			case 1: return erabiltzaileaByIdErabiltzaileaIrabazlea;
			case 2: return erabiltzaileaByIdErabiltzaileaJabea;
			case 3: return jokalaria;
			case 4: return komunitatea;
			case 5: return hasierakoPrezioa;
			case 6: return eskaintzaIrabazlea;
			case 7: return onartua;
			case 8: return tramitatua;
			case 9: return tramitazioEguna;
		}
		return null;
	}

	@Override
	public int getPropertyCount() {
		return 10;
	}

	@Override
	public void setProperty(int i, Object o) {
		switch (i){
			case 0: idMerkatukoJokalaria=Integer.parseInt(o.toString());
			case 1: erabiltzaileaByIdErabiltzaileaIrabazlea=(Erabiltzailea) o;
			case 2: erabiltzaileaByIdErabiltzaileaJabea=(Erabiltzailea) o;
			case 3: jokalaria=(Jokalaria) o;
			case 4: komunitatea=(Komunitatea) o;
			case 5: hasierakoPrezioa=Integer.parseInt(o.toString());
			case 6: eskaintzaIrabazlea=Integer.parseInt(o.toString());
			case 7: onartua=(Boolean) o;
			case 8: tramitatua=(Boolean) o;
			case 9: tramitazioEguna=(Date) o;
		}
	}

	@Override
	public void getPropertyInfo(int i, Hashtable properties, PropertyInfo info) {
		switch (i){
			case 0:info.name="idMerkatukoJokalaria";info.type = PropertyInfo.INTEGER_CLASS;break;
			case 1:info.name="erabiltzaileaByIdErabiltzaileaEskaintzaIrabazlea";info.setType(Erabiltzailea.class);break;
			case 2:info.name="erabiltzaileaByIdErabiltzaileaJabea";info.setType(Erabiltzailea.class);break;
			case 3:info.name="jokalaria";info.setType(Jokalaria.class);break;
			case 4:info.name="komunitatea";info.setType(Komunitatea.class);break;
			case 5:info.name="hasierakoPrezioa";info.setType(Integer.class);break;
			case 6:info.name="eskaintzaIrabazlea";info.setType(Integer.class);break;
			case 7:info.name="onartua";info.setType(Boolean.class);break;
			case 8:info.name="tramitatua";info.setType(Boolean.class);break;
			case 9:info.name="tramitazioEguna";info.setType(Date.class);break;
		}
	}

	public Integer getIdMerkatukoJokalaria() {
		return this.idMerkatukoJokalaria;
	}

	public void setIdMerkatukoJokalaria(Integer idMerkatukoJokalaria) {
		this.idMerkatukoJokalaria = idMerkatukoJokalaria;
	}

	public Erabiltzailea getErabiltzaileaByIdErabiltzaileaIrabazlea() {
		return this.erabiltzaileaByIdErabiltzaileaIrabazlea;
	}

	public void setErabiltzaileaByIdErabiltzaileaIrabazlea(
			Erabiltzailea erabiltzaileaByIdErabiltzaileaEskaintzaIrabazlea) {
		this.erabiltzaileaByIdErabiltzaileaIrabazlea = erabiltzaileaByIdErabiltzaileaEskaintzaIrabazlea;
	}

	public Erabiltzailea getErabiltzaileaByIdErabiltzaileaJabea() {
		return this.erabiltzaileaByIdErabiltzaileaJabea;
	}

	public void setErabiltzaileaByIdErabiltzaileaJabea(Erabiltzailea erabiltzaileaByIdErabiltzaileaJabea) {
		this.erabiltzaileaByIdErabiltzaileaJabea = erabiltzaileaByIdErabiltzaileaJabea;
	}

	public Jokalaria getJokalaria() {
		return this.jokalaria;
	}

	public void setJokalaria(Jokalaria jokalaria) {
		this.jokalaria = jokalaria;
	}

	public Komunitatea getKomunitatea() {
		return this.komunitatea;
	}

	public void setKomunitatea(Komunitatea komunitatea) {
		this.komunitatea = komunitatea;
	}

	public int getHasierakoPrezioa() {
		return this.hasierakoPrezioa;
	}

	public void setHasierakoPrezioa(int hasierakoPrezioa) {
		this.hasierakoPrezioa = hasierakoPrezioa;
	}

	public int getEskaintzaIrabazlea() {
		return this.eskaintzaIrabazlea;
	}

	public void setEskaintzaIrabazlea(int eskaintzaIrabazlea) {
		this.eskaintzaIrabazlea = eskaintzaIrabazlea;
	}

	public Boolean getOnartua() {
		return this.onartua;
	}

	public void setOnartua(Boolean onartua) {
		this.onartua = onartua;
	}

	public Boolean getTramitatua() {
		return this.tramitatua;
	}

	public void setTramitatua(Boolean tramitatua) {
		this.tramitatua = tramitatua;
	}

	public Date getTramitazioEguna() {
		return this.tramitazioEguna;
	}

	public void setTramitazioEguna(Date tramitazioEguna) {
		this.tramitazioEguna = tramitazioEguna;
	}

	public ArrayList<Eskaintza> getEskaintzak() {
		return eskaintzak;
	}

	public void setEskaintzak(ArrayList<Eskaintza> eskaintzak) {
		this.eskaintzak = eskaintzak;
	}
}
