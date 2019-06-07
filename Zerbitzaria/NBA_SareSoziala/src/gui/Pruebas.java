package gui;

import java.util.List;

import businessLogic.UserLogged_Implementation;
import dataAccess.HibernateDataAccess;
import domain.Jokalaria;
import domain.PuntuazioaJokalariaJardunaldia;

public class Pruebas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jokalaria jok= new Jokalaria();
		jok.setIdJokalaria(2544);

		UserLogged_Implementation appI = new UserLogged_Implementation();
		List<PuntuazioaJokalariaJardunaldia> ema = appI.JokalariarenPuntuaketak(jok);
		for(int i=0;i<ema.size();i++) {
			PuntuazioaJokalariaJardunaldia p = ema.get(i);
			System.out.println(p.getJardunaldia().getHasierakoEguna()+" "+p.getPuntuak());
		}
		
	}

}
