package businessLogic;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import domain.Alineazioa;
import domain.Erabiltzailea;
import domain.Eskaintza;
import domain.Jardunaldia;
import domain.Jokalaria;
import domain.Komunitatea;
import domain.MerkatukoJokalaria;
import domain.Mezua;
import domain.PuntuazioaErabiltzaileaJardunaldia;
import domain.PuntuazioaJokalariaJardunaldia;
import domain.Transakzioa;



@WebService
public interface UserLogged_Interface {

	
	/*
	 * JOKALARIAK
	 */
	@WebMethod
	public List<Jokalaria> ErabiltzailearenJokalariakLortu(Erabiltzailea erabiltzailea);
	@WebMethod
	public Alineazioa AlineazioaLortu(Erabiltzailea erabiltzailea);
	@WebMethod
	public boolean AlineazioaGorde(Erabiltzailea erabiltzailea,Jokalaria jokalaria1,Jokalaria jokalaria2,Jokalaria jokalaria3,Jokalaria jokalaria4,Jokalaria jokalaria5,boolean publikoa);	
	
	/*
	 * MERKATUA
	 */
	@WebMethod
	public boolean MerkatuanJarri(Komunitatea komunitatea,Erabiltzailea erabiltzailea,Jokalaria jokalaria,int hasierakoPrezioa);
	@WebMethod
	public List<MerkatukoJokalaria> MerkatuaKontsultatu(Komunitatea komunitatea);
	@WebMethod
	public boolean OfertaEgin(MerkatukoJokalaria merkatukoJokalaria,Erabiltzailea erabiltzailea,int oferta);
	@WebMethod
	public List<MerkatukoJokalaria> SaldutakoJokalariGuztiak(Komunitatea komunitatea);
	@WebMethod
	public void TraspasoaEzeztatu(MerkatukoJokalaria merkatukoJokalaria);
	@WebMethod
	public List<Eskaintza> EgindakoEskaintzak(Erabiltzailea erabiltzailea);
	@WebMethod
	public List<Eskaintza> JasotakoEskaintzak(Erabiltzailea erabiltzailea);
	@WebMethod
	public boolean EskaintzaErantzun(MerkatukoJokalaria merkatukoJokalaria,Eskaintza eskaintza,boolean onartu);
	
	/*
	 * FINANTZAK
	 */
	@WebMethod
	public List<Transakzioa> ErabiltzailearenTransakzioak(Erabiltzailea erabiltzailea);
	@WebMethod
	public void SariakAldatu(Komunitatea komunitatea,int saria1,int saria2,int saria3);
	@WebMethod
	public boolean TransakzioaGorde(Erabiltzailea erabiltzailea,int kantitatea,String mezua);
	
	
	/*
	 * ESTADISTIKAK
	 */
	@WebMethod
	public List<PuntuazioaJokalariaJardunaldia> JokalarienPuntuaketak();
	@WebMethod
	public List<PuntuazioaJokalariaJardunaldia> JardunaldikoJokalarienPuntuak(Jardunaldia jardunaldia);
	@WebMethod
	public List<PuntuazioaJokalariaJardunaldia> JokalariarenPuntuaketak(Jokalaria jokalaria);
	
	
	/*
	 * KOMUNITATEA
	 */
	@WebMethod
	public Komunitatea KomunitateaLortu(Komunitatea komunitatea);
	@WebMethod
	public List<Erabiltzailea> ErabiltzaileakLortu(Komunitatea komunitatea);
	@WebMethod
	public List<Mezua> MezuakLortu(Komunitatea komunitatea);
	@WebMethod
	public boolean MezuaIdatzi(String mezua,Komunitatea komunitatea,Erabiltzailea erabiltzailea);
	@WebMethod
	public boolean MezuaEzabatu(Mezua mezua);
	@WebMethod
	public boolean ErabiltzaileaEzabatu(Erabiltzailea erabiltzailea);
	@WebMethod
	public boolean KomunitateaGorde(Komunitatea komunitatea);
	@WebMethod
	public boolean ErabiltzaileaGorde(Erabiltzailea erabiltzailea);
	
	/*
	 * KLASIFIKAZIOA
	 */
	@WebMethod
	public List<Jardunaldia> OrainArtekoJardunaldiak();
	@WebMethod
	public List<PuntuazioaErabiltzaileaJardunaldia> JornadakoKlasifikazioa(Komunitatea komunitatea,Jardunaldia jardunaldia);
	@WebMethod
	public List<PuntuazioaErabiltzaileaJardunaldia> KlasifikazioOrokorra(Komunitatea komunitatea);
	

	
	
}
