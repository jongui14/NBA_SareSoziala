package businessLogic;

import javax.jws.WebService;

import domain.Alineazioa;
import domain.Erabiltzailea;
import domain.Jardunaldia;
import domain.Jokalaria;
import domain.Komunitatea;
import domain.Partidua;
import domain.Taldea;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;


@WebService
public interface UserNotLogged_Interface {

	/*
	 * REGISTRO/LOGIN  FUNTZIOAK
	 */
	@WebMethod
	public boolean KomunitateaLibre(String nick);
	
	@WebMethod
	public boolean ErabiltzaileNickLibre(String nick);

	@WebMethod
	public boolean ErabiltzaileEmailLibre(String email);
	
	@WebMethod
	public boolean ErabiltzaileaErregistratu(Komunitatea komunitatea,String nick,String email,String izen_osoa,String pasahitza,boolean administratzailea/*,String hizkuntza,int koloreak,int ordu_diferentzia*/);
	
	@WebMethod
	public Erabiltzailea ErabiltzaileaLogeatu(String nick,String pasahitza);
	
	@WebMethod
	public Komunitatea KomunitateaErregistratu(String nick,String izen_osoa,String pasahitza,String ongietorri_mezua,int saria1,int saria2,int saria3);
	
	@WebMethod
	public Komunitatea KomunitateaAukeratu(String nick,String pasahitza);
	
	@WebMethod
	public boolean PasahitzaOnargarria(String pass);
	
	

	/*
	 * GAINONTZEKOAK
	 */
	@WebMethod
	public List<Partidua> JornadakoPartiduak(Date data);
	@WebMethod
	public List<Alineazioa> AlineazioPublikoak();
	//@WebMethod
	//public List<Partidua> JokalariarenPartiduak(Jardunaldia jardunaldia);
	@WebMethod
	public List<Jokalaria> JokalariakLortu();
	@WebMethod
	public List<Taldea> TaldeakLortu();
	@WebMethod
	public Taldea TaldearenDatuakLortu(Taldea taldea);
	
	
	
}
