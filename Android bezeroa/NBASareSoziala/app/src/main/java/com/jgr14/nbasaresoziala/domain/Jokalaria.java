package com.jgr14.nbasaresoziala.domain;
// Generated 06-mar-2019 12:17:39 by Hibernate Tools 5.4.1.Final

import com.jgr14.nbasaresoziala.dataAccess.DataAccess;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import java.io.Serializable;





public class Jokalaria implements Serializable,KvmSerializable {
	

	private Integer idJokalaria=0;
	private Taldea taldeaByIdTaldea;
	private Taldea taldeaByDraftIdTaldea;
	
	private String izena;
	private String abizena;
	private Integer dortsala;
	private String posizioa;
	private Float altuera;
	private Float pisua;
	private Date jaiotzeData;
	private String nazionalitatea;
	private Integer draftUrtea;
	private Integer draftPostua;
	private String unibertsitatea;
	private Integer nbaDebutUrtea;
	private Integer nbaUrteak;
	private Integer soldata=0;


	public Jokalaria() {
	}

	public Jokalaria(int idJokalaria) {
		this.idJokalaria = idJokalaria;
	}

	public Jokalaria(int idJokalaria, Taldea taldeaByIdTaldea, Taldea taldeaByDraftIdTaldea, String izena,
					 String abizena, Integer dortsala, String posizioa, Float altuera, Float pisua, Date jaiotzeData,
					 String nazionalitatea, Integer draftUrtea, Integer draftPostua, String unibertsitatea,
					 Integer nbaDebutUrtea, Integer nbaUrteak, Set puntuazioaJokalariaJardunaldias, Set erabiltzaileas,
					 Set alineazioasForIdJokalaria5, Set alineazioasForIdJokalaria4, Set alineazioasForIdJokalaria3,
					 Set alineazioasForIdJokalaria2, Set merkatukoJokalarias, Set alineazioasForIdJokalaria1) {
		this.idJokalaria = idJokalaria;
		this.taldeaByIdTaldea = taldeaByIdTaldea;
		this.taldeaByDraftIdTaldea = taldeaByDraftIdTaldea;
		this.izena = izena;
		this.abizena = abizena;
		this.dortsala = dortsala;
		this.posizioa = posizioa;
		this.altuera = altuera;
		this.pisua = pisua;
		this.jaiotzeData = jaiotzeData;
		this.nazionalitatea = nazionalitatea;
		this.draftUrtea = draftUrtea;
		this.draftPostua = draftPostua;
		this.unibertsitatea = unibertsitatea;
		this.nbaDebutUrtea = nbaDebutUrtea;
		this.nbaUrteak = nbaUrteak;
	}

	public String getArgazkia(){
		return "https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/"+idJokalaria+".png";
		//return DataAccess.URL+":8080/nba_sare-soziala/img/jokalaria/"+idJokalaria+".png";
	}

	public String soldatFormatuarekin(){
		try{
			String salarioLag=""+this.soldata;
			String salario="";
			System.out.println(salarioLag.substring(salarioLag.length()-3,salarioLag.length()));
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

	public String izenOsoa(){
		try{
			return this.izena+" "+this.abizena;
		}catch (Exception e){
			return "";
		}
	}
	public String izenMotza(){
		try{
			return this.izena.charAt(0)+" "+this.abizena;
		}catch (Exception e){
			return "";
		}
	}

	@Override
	public Object getProperty(int i) {
		switch (i){
			case 0: return idJokalaria;
			case 1: return taldeaByIdTaldea;
			case 2: return taldeaByDraftIdTaldea;
			case 3: return izena;
			case 4: return abizena;
			case 5: return dortsala;
			case 6: return posizioa;
			//case 7: return altuera;
			//case 8: return pisua;
			//case 7: return jaiotzeData;
			case 8: return nazionalitatea;
			case 9: return draftUrtea;
			case 10: return draftPostua;
			case 11: return unibertsitatea;
			case 12: return nbaDebutUrtea;
			case 13: return nbaUrteak;
			case 14: return soldata;
		}
		return null;
	}

	@Override
	public int getPropertyCount() {
		return 15;
	}

	@Override
	public void setProperty(int i, Object o) {
		switch (i){
			case 0: idJokalaria=Integer.parseInt(o.toString());break;
			case 1: taldeaByIdTaldea=(Taldea)o;break;
			case 2: taldeaByDraftIdTaldea=(Taldea)o;break;
			case 3: izena=o.toString();break;
			case 4: abizena=o.toString();break;
			case 5: dortsala=Integer.parseInt(o.toString());break;
			case 6: posizioa=o.toString();break;
			//case 7: altuera=Float.parseFloat(o.toString());break;
			//case 8: pisua=Float.parseFloat(o.toString());break;
			case 7: jaiotzeData=(Date)o;break;
			case 8: nazionalitatea=o.toString();break;
			case 9: draftUrtea=Integer.parseInt(o.toString());break;
			case 10: draftPostua=Integer.parseInt(o.toString());break;
			case 11: unibertsitatea=o.toString();break;
			case 12: nbaDebutUrtea=Integer.parseInt(o.toString());break;
			case 13: nbaUrteak=Integer.parseInt(o.toString());break;
			case 14: soldata=Integer.parseInt(o.toString());break;
		}
	}

	@Override
	public void getPropertyInfo(int i, Hashtable properties, PropertyInfo info) {
		switch (i){
			case 0:info.name="idJokalaria";info.type = PropertyInfo.INTEGER_CLASS;break;
			case 1:info.name="taldeaByIdTaldea";info.setType(Taldea.class);break;
			case 2:info.name="taldeaByDraftIdTaldea";info.setType(Taldea.class);break;
			case 3:info.name="izena";info.type = PropertyInfo.STRING_CLASS;break;
			case 4:info.name="abizena";info.type = PropertyInfo.STRING_CLASS;break;
			case 5:info.name="dortsala";info.type = PropertyInfo.INTEGER_CLASS;break;
			case 6:info.name="posizioa";info.type = PropertyInfo.STRING_CLASS;break;
			//case 7:info.name="altuera";info.setType(Float.class);break;
			//case 8:info.name="pisua";info.setType(Float.class);break;
			case 7:info.name="jaiotzeData";info.setType(Date.class);break;
			case 8:info.name="nazionalitatea";info.type = PropertyInfo.STRING_CLASS;break;
			case 9:info.name="draftUrtea";info.type = PropertyInfo.INTEGER_CLASS;break;
			case 10:info.name="draftPostua";info.type = PropertyInfo.INTEGER_CLASS;break;
			case 11:info.name="unibertsitatea";info.type = PropertyInfo.STRING_CLASS;break;
			case 12:info.name="nbaDebutUrtea";info.type = PropertyInfo.INTEGER_CLASS;break;
			case 13:info.name="nbaUrteak";info.type = PropertyInfo.INTEGER_CLASS;break;
			case 14:info.name="soldata";info.type = PropertyInfo.INTEGER_CLASS;break;
		}
	}


	public Integer getIdJokalaria() {
		return idJokalaria;
	}

	public void setIdJokalaria(Integer idJokalaria) {
		this.idJokalaria = idJokalaria;
	}

	public Taldea getTaldeaByIdTaldea() {
		return taldeaByIdTaldea;
	}

	public void setTaldeaByIdTaldea(Taldea taldeaByIdTaldea) {
		this.taldeaByIdTaldea = taldeaByIdTaldea;
	}

	public Taldea getTaldeaByDraftIdTaldea() {
		return taldeaByDraftIdTaldea;
	}

	public void setTaldeaByDraftIdTaldea(Taldea taldeaByDraftIdTaldea) {
		this.taldeaByDraftIdTaldea = taldeaByDraftIdTaldea;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getAbizena() {
		return abizena;
	}

	public void setAbizena(String abizena) {
		this.abizena = abizena;
	}

	public Integer getDortsala() {
		return dortsala;
	}

	public void setDortsala(Integer dortsala) {
		this.dortsala = dortsala;
	}

	public String getPosizioa() {
		return posizioa;
	}

	public void setPosizioa(String posizioa) {
		this.posizioa = posizioa;
	}

	public Float getAltuera() {
		return altuera;
	}

	public void setAltuera(Float altuera) {
		this.altuera = altuera;
	}

	public Float getPisua() {
		return pisua;
	}

	public void setPisua(Float pisua) {
		this.pisua = pisua;
	}

	public Date getJaiotzeData() {
		return jaiotzeData;
	}

	public void setJaiotzeData(Date jaiotzeData) {
		this.jaiotzeData = jaiotzeData;
	}

	public String getNazionalitatea() {
		return nazionalitatea;
	}

	public void setNazionalitatea(String nazionalitatea) {
		this.nazionalitatea = nazionalitatea;
	}

	public Integer getDraftUrtea() {
		return draftUrtea;
	}

	public void setDraftUrtea(Integer draftUrtea) {
		this.draftUrtea = draftUrtea;
	}

	public Integer getDraftPostua() {
		return draftPostua;
	}

	public void setDraftPostua(Integer draftPostua) {
		this.draftPostua = draftPostua;
	}

	public String getUnibertsitatea() {
		return unibertsitatea;
	}

	public void setUnibertsitatea(String unibertsitatea) {
		this.unibertsitatea = unibertsitatea;
	}

	public Integer getNbaDebutUrtea() {
		return nbaDebutUrtea;
	}

	public void setNbaDebutUrtea(Integer nbaDebutUrtea) {
		this.nbaDebutUrtea = nbaDebutUrtea;
	}

	public Integer getNbaUrteak() {
		return nbaUrteak;
	}

	public void setNbaUrteak(Integer nbaUrteak) {
		this.nbaUrteak = nbaUrteak;
	}

	public Integer getSoldata() {
		return soldata;
	}

	public void setSoldata(Integer soldata) {
		this.soldata = soldata;
	}



	




}
