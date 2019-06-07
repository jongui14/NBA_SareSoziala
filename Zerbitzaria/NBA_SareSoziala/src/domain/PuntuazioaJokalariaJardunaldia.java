package domain;
// Generated 06-mar-2019 12:17:39 by Hibernate Tools 5.4.1.Final

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * PuntuazioaJokalariaJardunaldia generated by hbm2java
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class PuntuazioaJokalariaJardunaldia implements java.io.Serializable {
	
	@XmlID
	@XmlJavaTypeAdapter(PuntuazioaJokalariaJardunaldiaIdAdapter.class)
	@Id
	@GeneratedValue
	private PuntuazioaJokalariaJardunaldiaId id;
	
	@XmlIDREF private Jardunaldia jardunaldia;
	private Jokalaria jokalaria;
	
	private Integer puntuak=0;

	private int puntu_totalak;
	private int partidu_kopurua;
	 
	 
	 public PuntuazioaJokalariaJardunaldia getKopia() {
		 PuntuazioaJokalariaJardunaldia res=new PuntuazioaJokalariaJardunaldia();
		 res.setId(id);
		 res.setJardunaldia(jardunaldia.getKopia());
		 res.setJokalaria(jokalaria.getKopia());
		 res.setPuntuak(puntuak);
		 res.setPuntu_totalak(puntu_totalak);
		 res.setPartidu_kopurua(partidu_kopurua);
		return res;
	}
	 

	 public void puntuak_ezarri() {
	    	if(partidu_kopurua==0) {
	    		setPuntuak(0);
	    	}else {
	    		setPuntuak(puntu_totalak/partidu_kopurua);
	    	}
	    }

	    
	    @Override
	    public boolean equals(Object object){
	        boolean sameSame = false;

	        if (object != null && object instanceof PuntuazioaJokalariaJardunaldia){
	            sameSame = this.jokalaria.getIdJokalaria() == ((PuntuazioaJokalariaJardunaldia) object).jokalaria.getIdJokalaria();
	        }

	        return sameSame;
	    }
	    
	public int getIdJardunaldia() {
		return this.jardunaldia.getIdJardunaldia();
	}
	    
	public PuntuazioaJokalariaJardunaldia() {
	}

	public PuntuazioaJokalariaJardunaldia(PuntuazioaJokalariaJardunaldiaId id, Jardunaldia jardunaldia,
			Jokalaria jokalaria) {
		this.id = id;
		this.jardunaldia = jardunaldia;
		this.jokalaria = jokalaria;
	}

	public PuntuazioaJokalariaJardunaldia(PuntuazioaJokalariaJardunaldiaId id, Jardunaldia jardunaldia,
			Jokalaria jokalaria, Integer puntuak) {
		this.id = id;
		this.jardunaldia = jardunaldia;
		this.jokalaria = jokalaria;
		this.puntuak = puntuak;
	}

	public PuntuazioaJokalariaJardunaldiaId getId() {
		return this.id;
	}

	public void setId(PuntuazioaJokalariaJardunaldiaId id) {
		this.id = id;
	}

	public Jardunaldia getJardunaldia() {
		return this.jardunaldia;
	}

	public void setJardunaldia(Jardunaldia jardunaldia) {
		this.jardunaldia = jardunaldia;
	}

	public Jokalaria getJokalaria() {
		return this.jokalaria;
	}

	public void setJokalaria(Jokalaria jokalaria) {
		this.jokalaria = jokalaria;
	}

	public Integer getPuntuak() {
		return this.puntuak;
	}

	public void setPuntuak(Integer puntuak) {
		this.puntuak = puntuak;
	}


	public int getPuntu_totalak() {
		return puntu_totalak;
	}


	public int getPartidu_kopurua() {
		return partidu_kopurua;
	}


	public void setPuntu_totalak(int puntu_totalak) {
		this.puntu_totalak = puntu_totalak;
	}


	public void setPartidu_kopurua(int partidu_kopurua) {
		this.partidu_kopurua = partidu_kopurua;
	}

}
