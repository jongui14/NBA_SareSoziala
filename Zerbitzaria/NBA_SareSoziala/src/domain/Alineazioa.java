package domain;
// Generated 08-may-2019 9:56:30 by Hibernate Tools 5.4.1.Final

import java.util.Date;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Alineazioa generated by hbm2java
 */
@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Alineazioa implements java.io.Serializable {

	@XmlID
	@XmlJavaTypeAdapter(AlineazioaIdAdapter.class)
	@Id
	@GeneratedValue
	private AlineazioaId id;
	
	@XmlIDREF 
	private Erabiltzailea erabiltzailea=new Erabiltzailea();
	@XmlIDREF 
	private Jokalaria jokalariaByIdJokalaria5=new Jokalaria();
	@XmlIDREF 
	private Jokalaria jokalariaByIdJokalaria4=new Jokalaria();
	@XmlIDREF 
	private Jokalaria jokalariaByIdJokalaria3=new Jokalaria();
	@XmlIDREF 
	private Jokalaria jokalariaByIdJokalaria2=new Jokalaria();
	@XmlIDREF 
	private Jokalaria jokalariaByIdJokalaria1=new Jokalaria();
	
	private Date egunda=new Date();

	public Alineazioa() {
	}
	
	public Alineazioa getKopia() {
		Alineazioa res=new Alineazioa();
		res.setId(id);
		res.setErabiltzailea(erabiltzailea.getKopia());
		res.setJokalariaByIdJokalaria1(jokalariaByIdJokalaria1.getKopia());
		res.setJokalariaByIdJokalaria2(jokalariaByIdJokalaria2.getKopia());
		res.setJokalariaByIdJokalaria3(jokalariaByIdJokalaria3.getKopia());
		res.setJokalariaByIdJokalaria4(jokalariaByIdJokalaria4.getKopia());
		res.setJokalariaByIdJokalaria5(jokalariaByIdJokalaria5.getKopia());
		return res;
	}

	public Alineazioa(AlineazioaId id, Erabiltzailea erabiltzailea) {
		this.id = id;
		this.erabiltzailea = erabiltzailea;
	}

	public Alineazioa(AlineazioaId id, Erabiltzailea erabiltzailea, Jokalaria jokalariaByIdJokalaria5,
			Jokalaria jokalariaByIdJokalaria4, Jokalaria jokalariaByIdJokalaria3, Jokalaria jokalariaByIdJokalaria2,
			Jokalaria jokalariaByIdJokalaria1, Date egunda) {
		this.id = id;
		this.erabiltzailea = erabiltzailea;
		this.jokalariaByIdJokalaria5 = jokalariaByIdJokalaria5;
		this.jokalariaByIdJokalaria4 = jokalariaByIdJokalaria4;
		this.jokalariaByIdJokalaria3 = jokalariaByIdJokalaria3;
		this.jokalariaByIdJokalaria2 = jokalariaByIdJokalaria2;
		this.jokalariaByIdJokalaria1 = jokalariaByIdJokalaria1;
		this.egunda = egunda;
	}

	public AlineazioaId getId() {
		return this.id;
	}

	public void setId(AlineazioaId id) {
		this.id = id;
	}

	public Erabiltzailea getErabiltzailea() {
		return this.erabiltzailea;
	}

	public void setErabiltzailea(Erabiltzailea erabiltzailea) {
		this.erabiltzailea = erabiltzailea;
	}

	public Jokalaria getJokalariaByIdJokalaria5() {
		return this.jokalariaByIdJokalaria5;
	}

	public void setJokalariaByIdJokalaria5(Jokalaria jokalariaByIdJokalaria5) {
		this.jokalariaByIdJokalaria5 = jokalariaByIdJokalaria5;
	}

	public Jokalaria getJokalariaByIdJokalaria4() {
		return this.jokalariaByIdJokalaria4;
	}

	public void setJokalariaByIdJokalaria4(Jokalaria jokalariaByIdJokalaria4) {
		this.jokalariaByIdJokalaria4 = jokalariaByIdJokalaria4;
	}

	public Jokalaria getJokalariaByIdJokalaria3() {
		return this.jokalariaByIdJokalaria3;
	}

	public void setJokalariaByIdJokalaria3(Jokalaria jokalariaByIdJokalaria3) {
		this.jokalariaByIdJokalaria3 = jokalariaByIdJokalaria3;
	}

	public Jokalaria getJokalariaByIdJokalaria2() {
		return this.jokalariaByIdJokalaria2;
	}

	public void setJokalariaByIdJokalaria2(Jokalaria jokalariaByIdJokalaria2) {
		this.jokalariaByIdJokalaria2 = jokalariaByIdJokalaria2;
	}

	public Jokalaria getJokalariaByIdJokalaria1() {
		return this.jokalariaByIdJokalaria1;
	}

	public void setJokalariaByIdJokalaria1(Jokalaria jokalariaByIdJokalaria1) {
		this.jokalariaByIdJokalaria1 = jokalariaByIdJokalaria1;
	}

	public Date getEgunda() {
		return this.egunda;
	}

	public void setEgunda(Date egunda) {
		this.egunda = egunda;
	}

}
