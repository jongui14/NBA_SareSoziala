package domain;
// Generated 06-mar-2019 12:17:39 by Hibernate Tools 5.4.1.Final

import java.util.HashSet;
import java.util.Set;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javassist.bytecode.Descriptor.Iterator;

/**
 * Taldea generated by hbm2java
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Taldea implements Serializable {

	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	@GeneratedValue
	private Integer idTaldea=0;
	
	private String hiria="";
	private String apodoa="";
	private String izenOsoa="";
	private String konferentzia="";
	private String urlIzena="";
	
	private Set jokalariasForIdTaldea = new HashSet(0);
	@XmlIDREF private Set jokalariasForDraftIdTaldea = new HashSet(0);

	public Taldea() {
	}
	
	public Taldea getKopia() {
		Taldea res=new Taldea();
		res.setIdTaldea(idTaldea);
		res.setHiria(hiria);
		res.setApodoa(apodoa);
		res.setIzenOsoa(izenOsoa);
		res.setKonferentzia(konferentzia);
		res.setUrlIzena(urlIzena);
		/*Iterator it=jokalariasForIdTaldea.iterator();
		while(it.hasNext()) {
			res.jokalariasForIdTaldea.add(((Jokalaria)it.next()).getKopia());
		}*/
		return res;
	}

	public Taldea(int idTaldea) {
		this.idTaldea = idTaldea;
	}

	public Taldea(int idTaldea, String hiria, String apodoa, String izenOsoa, String konferentzia, String urlIzena) {
		this.idTaldea = idTaldea;
		this.hiria = hiria;
		this.apodoa = apodoa;
		this.izenOsoa = izenOsoa;
		this.konferentzia = konferentzia;
		this.urlIzena = urlIzena;
	}

	public int getIdTaldea() {
		return this.idTaldea;
	}

	public void setIdTaldea(int idTaldea) {
		this.idTaldea = idTaldea;
	}

	public String getHiria() {
		return this.hiria;
	}

	public void setHiria(String hiria) {
		this.hiria = hiria;
	}

	public String getApodoa() {
		return this.apodoa;
	}

	public void setApodoa(String apodoa) {
		this.apodoa = apodoa;
	}

	public String getIzenOsoa() {
		return this.izenOsoa;
	}

	public void setIzenOsoa(String izenOsoa) {
		this.izenOsoa = izenOsoa;
	}

	public String getKonferentzia() {
		return this.konferentzia;
	}

	public void setKonferentzia(String konferentzia) {
		this.konferentzia = konferentzia;
	}

	public String getUrlIzena() {
		return this.urlIzena;
	}

	public void setUrlIzena(String urlIzena) {
		this.urlIzena = urlIzena;
	}

	public Set getJokalariasForIdTaldea() {
		return this.jokalariasForIdTaldea;
	}

	public void setJokalariasForIdTaldea(Set jokalariasForIdTaldea) {
		this.jokalariasForIdTaldea = jokalariasForIdTaldea;
	}

	public Set getJokalariasForDraftIdTaldea() {
		return this.jokalariasForDraftIdTaldea;
	}

	public void setJokalariasForDraftIdTaldea(Set jokalariasForDraftIdTaldea) {
		this.jokalariasForDraftIdTaldea = jokalariasForDraftIdTaldea;
	}

}