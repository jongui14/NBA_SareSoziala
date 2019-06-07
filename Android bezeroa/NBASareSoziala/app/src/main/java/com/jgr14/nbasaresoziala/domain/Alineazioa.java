package com.jgr14.nbasaresoziala.domain;

// Generated 06-mar-2019 12:17:39 by Hibernate Tools 5.4.1.Final

import java.util.Date;
import java.io.Serializable;



public class Alineazioa implements Serializable {
	

	private Erabiltzailea erabiltzailea=new Erabiltzailea();
	private int mota=0;
	private Jokalaria jokalariaByIdJokalaria5=new Jokalaria();
	private Jokalaria jokalariaByIdJokalaria4=new Jokalaria();
	private Jokalaria jokalariaByIdJokalaria3=new Jokalaria();
	private Jokalaria jokalariaByIdJokalaria2=new Jokalaria();
	private Jokalaria jokalariaByIdJokalaria1=new Jokalaria();
	
	private Date egunda=new Date();

	public Alineazioa() {
	}



	public Alineazioa( Erabiltzailea erabiltzailea, int mota, Jokalaria jokalariaByIdJokalaria5,
			Jokalaria jokalariaByIdJokalaria4, Jokalaria jokalariaByIdJokalaria3, Jokalaria jokalariaByIdJokalaria2,
			Jokalaria jokalariaByIdJokalaria1, Date egunda) {
		this.mota = mota;
		this.erabiltzailea = erabiltzailea;
		this.jokalariaByIdJokalaria5 = jokalariaByIdJokalaria5;
		this.jokalariaByIdJokalaria4 = jokalariaByIdJokalaria4;
		this.jokalariaByIdJokalaria3 = jokalariaByIdJokalaria3;
		this.jokalariaByIdJokalaria2 = jokalariaByIdJokalaria2;
		this.jokalariaByIdJokalaria1 = jokalariaByIdJokalaria1;
		this.egunda = egunda;
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
