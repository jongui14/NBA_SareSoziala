package businessLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlIDREF;

import dataAccess.HibernateDataAccess;
import domain.Alineazioa;
import domain.AlineazioaId;
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



@WebService(endpointInterface = "businessLogic.UserLogged_Interface")
public class UserLogged_Implementation implements UserLogged_Interface {

	
	/*
	 * JOKALARIAK
	 */
	@WebMethod
	public List<Jokalaria> ErabiltzailearenJokalariakLortu(Erabiltzailea erabiltzailea){
		System.out.println("ErabiltzailearenJokalariakLortu: "+erabiltzailea.getIdErabiltzailea());
		List<Jokalaria> emaitza = new ArrayList<Jokalaria>();
		HibernateDataAccess dbManager = new HibernateDataAccess();
		Iterator<Jokalaria> it = dbManager.getErabiltzailearenJokalariak(erabiltzailea).iterator();
		while(it.hasNext()) {
			emaitza.add((it.next()).getKopia());
		}
		return emaitza;
	}
	@WebMethod
	public Alineazioa AlineazioaLortu(Erabiltzailea erabiltzailea) {
		System.out.println("AlineazioaLortu: "+erabiltzailea.getNick());
		HibernateDataAccess dbManager = new HibernateDataAccess();
		return  dbManager.erabiltzailearenAlineazioa(erabiltzailea);
	}
	@WebMethod
	public boolean AlineazioaGorde(Erabiltzailea erabiltzailea,Jokalaria jokalaria1,Jokalaria jokalaria2,Jokalaria jokalaria3,Jokalaria jokalaria4,Jokalaria jokalaria5,boolean publikoa) {
		System.out.println("AlineazioaGorde");
		AlineazioaId alineazioaId= new AlineazioaId();
		if(publikoa)alineazioaId.setMota(2);else alineazioaId.setMota(0);
		alineazioaId.setIdErabiltzailea(erabiltzailea.getIdErabiltzailea());
		
		Alineazioa alineazioa = new Alineazioa();
		alineazioa.setId(alineazioaId);
		alineazioa.setErabiltzailea(erabiltzailea);
		alineazioa.setJokalariaByIdJokalaria1(jokalaria1);
		alineazioa.setJokalariaByIdJokalaria2(jokalaria2);
		alineazioa.setJokalariaByIdJokalaria3(jokalaria3);
		alineazioa.setJokalariaByIdJokalaria4(jokalaria4);
		alineazioa.setJokalariaByIdJokalaria5(jokalaria5);
		alineazioa.setEgunda(new Date());
		
		HibernateDataAccess dbManager = new HibernateDataAccess();
		dbManager.alineazioaGorde(alineazioa);
		return true;
	}
	
	
	/*
	 * MERKATUA
	 */
	@WebMethod
	public boolean MerkatuanJarri(Komunitatea komunitatea,Erabiltzailea erabiltzailea,Jokalaria jokalaria,int hasierakoPrezioa) {
		try {
			System.out.println("MerkatuanJarri");
			HibernateDataAccess dbManager = new HibernateDataAccess();
			//Erabiltzailea erabLag = dbManager.erabiltzaileaLortu(erabiltzailea.getIdErabiltzailea());
			MerkatukoJokalaria merkatukoJokalaria = new MerkatukoJokalaria();
			merkatukoJokalaria.setErabiltzaileaByIdErabiltzaileaJabea(erabiltzailea);
			merkatukoJokalaria.setErabiltzaileaByIdErabiltzaileaIrabazlea(erabiltzailea);
			merkatukoJokalaria.setJokalaria(jokalaria);
			merkatukoJokalaria.setKomunitatea(komunitatea);
			merkatukoJokalaria.setHasierakoPrezioa(hasierakoPrezioa);
			merkatukoJokalaria.setEskaintzaIrabazlea(0);
			merkatukoJokalaria.setOnartua(false);
			merkatukoJokalaria.setTramitatua(false);
			merkatukoJokalaria.setTramitazioEguna(new Date());
			dbManager.merkatukoJokalariaGorde(merkatukoJokalaria);
			
			int oferta=(int)jokalaria.getSoldata()*95/100;if(oferta<0)oferta=oferta*-1;
			OfertaEgin(merkatukoJokalaria,new Erabiltzailea(1),oferta);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@WebMethod
	public List<MerkatukoJokalaria> MerkatuaKontsultatu(Komunitatea komunitatea) {
		System.out.println("MerkatuaKontsultatu  "+komunitatea.getIdKomunitatea());
		List<MerkatukoJokalaria> merkatukoJokalariak = new ArrayList<MerkatukoJokalaria>();
		HibernateDataAccess dbManager = new HibernateDataAccess();
		/*Komunitatea komunitateaLag = dbManager.komunitateaLortu(komunitatea.getIdKomunitatea());
		Iterator it = komunitateaLag.getMerkatukoJokalarias().iterator();
		while(it.hasNext()) {
			try {
				MerkatukoJokalaria jok=(MerkatukoJokalaria)it.next();
				if(!jok.isTramitatua())
					merkatukoJokalariak.add(jok.getKopia());
			}catch(Exception e) {
				e.printStackTrace();
			}
		}*/
		Iterator it = dbManager.komunitatekoMerkatukoJokalariak(komunitatea).iterator();
		while(it.hasNext()) {
			try {
				MerkatukoJokalaria jok=(MerkatukoJokalaria)it.next();
				merkatukoJokalariak.add(jok.getKopia());					
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		merkatukoJokalariak.sort(Comparator.comparingInt(MerkatukoJokalaria::getIdMerkatukoJokalaria));
		return merkatukoJokalariak;
	}
	@WebMethod
	public boolean OfertaEgin(MerkatukoJokalaria merkatukoJokalaria,Erabiltzailea erabiltzailea,int oferta) {
		try {
			System.out.println("OfertaEgin");
			HibernateDataAccess dbManager = new HibernateDataAccess();
			MerkatukoJokalaria merkatukoJokalariaLag = dbManager.merkatukoJokalariaLortu(merkatukoJokalaria.getIdMerkatukoJokalaria());
			if(merkatukoJokalariaLag.getEskaintzaIrabazlea()<oferta) {
				merkatukoJokalariaLag.setEskaintzaIrabazlea(oferta);
				merkatukoJokalariaLag.setErabiltzaileaByIdErabiltzaileaIrabazlea(erabiltzailea);
				dbManager.merkatukoJokalariaGorde(merkatukoJokalariaLag);
			}
			Eskaintza eskaintza = new Eskaintza();
			eskaintza.setMerkatukoJokalaria(merkatukoJokalariaLag);
			eskaintza.setErabiltzailea(erabiltzailea);
			eskaintza.setEskaintza(oferta);
			dbManager.eskaintzaGorde(eskaintza);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@WebMethod
	public List<MerkatukoJokalaria> SaldutakoJokalariGuztiak(Komunitatea komunitatea) {
		return null;
	}
	@WebMethod
	public void TraspasoaEzeztatu(MerkatukoJokalaria merkatukoJokalaria) {
		
	}
	@WebMethod
	public List<Eskaintza> EgindakoEskaintzak(Erabiltzailea erabiltzailea){
		System.out.println("EgindakoEskaintzak   id "+erabiltzailea.getIdErabiltzailea());
		List<Eskaintza> eskaintzak = new ArrayList<Eskaintza>();
		HibernateDataAccess dbManager = new HibernateDataAccess();
		Iterator it = dbManager.getEgindakoEskaintzak(erabiltzailea).iterator();
		while(it.hasNext()) {
			eskaintzak.add(((Eskaintza)it.next()).getKopia());
		}
		return eskaintzak;
	}
	@WebMethod
	public List<Eskaintza> JasotakoEskaintzak(Erabiltzailea erabiltzailea){
		System.out.println("JasotakoEskaintzak  "+erabiltzailea.getIdErabiltzailea());
		List<Eskaintza> eskaintzak = new ArrayList<Eskaintza>();
		HibernateDataAccess dbManager = new HibernateDataAccess();
		Iterator it = dbManager.getJasotakoEskaintzak(erabiltzailea).iterator();
		while(it.hasNext()) {
			eskaintzak.add(((Eskaintza)it.next()).getKopia());
		}
		return eskaintzak;
	}
	@WebMethod
	public boolean EskaintzaErantzun(MerkatukoJokalaria merkatukoJokalaria,Eskaintza eskaintza,boolean onartu) {
		try {
			System.out.println("EskaintzaErantzun  "+merkatukoJokalaria.getIdMerkatukoJokalaria()+" "+eskaintza.getIdEskaintza()+" "+onartu);
			HibernateDataAccess dbManager = new HibernateDataAccess();
			MerkatukoJokalaria jok=dbManager.merkatukoJokalariaLortu(merkatukoJokalaria.getIdMerkatukoJokalaria());
			Eskaintza eskaintzaBerria=dbManager.eskaintzaLortu(eskaintza.getIdEskaintza());
			jok.setOnartua(onartu);
			jok.setErabiltzaileaByIdErabiltzaileaIrabazlea(eskaintzaBerria.getErabiltzailea());
			jok.setEskaintzaIrabazlea(eskaintzaBerria.getEskaintza());
			dbManager.merkatukoJokalariaGorde(jok);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	/*
	 * FINANTZAK
	 */
	@WebMethod
	public List<Transakzioa> ErabiltzailearenTransakzioak(Erabiltzailea erabiltzailea) {
		System.out.println("ErabiltzailearenTransakzioak  "+erabiltzailea.getIdErabiltzailea());
		List<Transakzioa> emaitza = new ArrayList<Transakzioa>();
		HibernateDataAccess dbManager = new HibernateDataAccess();
		Iterator it=dbManager.getErabiltzailearenTransakzioak(erabiltzailea).iterator();
		while(it.hasNext())emaitza.add((Transakzioa)it.next());
		return emaitza;
	}
	@WebMethod
	public void SariakAldatu(Komunitatea komunitatea_lag,int saria1,int saria2,int saria3) {
		HibernateDataAccess dbManager = new HibernateDataAccess();
		Komunitatea komunitatea = dbManager.komunitateaLortu(komunitatea_lag.getIdKomunitatea());
		komunitatea.setSaria1(saria1);
		komunitatea.setSaria1(saria1);
		komunitatea.setSaria1(saria1);
		dbManager.komunitateaGorde(komunitatea);
	}
	@WebMethod
	public boolean TransakzioaGorde(Erabiltzailea erabiltzailea,int kantitatea,String mezua) {
		try {
			System.out.println("TransakzioaGorde");
			HibernateDataAccess dbManager = new HibernateDataAccess();
			Transakzioa transakzioa=new Transakzioa();
			transakzioa.setErabiltzailea(erabiltzailea);
			transakzioa.setKantitatea(kantitatea);
			transakzioa.setMezua(mezua);
			transakzioa.setEguna(new Date());
			dbManager.transakzioaGorde(transakzioa);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/*
	 * ESTADISTIKAK
	 */
	@WebMethod
	public List<PuntuazioaJokalariaJardunaldia> JokalarienPuntuaketak(){
		HibernateDataAccess dbManager = new HibernateDataAccess();
		List<PuntuazioaJokalariaJardunaldia> emaitza=dbManager.jokalarienPuntuazioMaximoak();
		emaitza.sort(Comparator.comparingInt(PuntuazioaJokalariaJardunaldia::getPuntuak));
		Collections.reverse(emaitza);
		return emaitza;
	}
	@WebMethod
	public List<PuntuazioaJokalariaJardunaldia> JardunaldikoJokalarienPuntuak(Jardunaldia jardunaldia) {
		return null;
	}
	@WebMethod
	public List<PuntuazioaJokalariaJardunaldia> JokalariarenPuntuaketak(Jokalaria jokalaria) {
		List<PuntuazioaJokalariaJardunaldia> emaitza = new ArrayList<PuntuazioaJokalariaJardunaldia>();
		HibernateDataAccess dbManager = new HibernateDataAccess();
		Set<PuntuazioaJokalariaJardunaldia> puntuakJokalaria=dbManager.jokalariarenPuntuak(jokalaria);
		for(Jardunaldia jardunaldia:OrainArtekoJardunaldiak()) {
			PuntuazioaJokalariaJardunaldia puntuazioaJokalariaJardunaldia = new PuntuazioaJokalariaJardunaldia();
			puntuazioaJokalariaJardunaldia.setJardunaldia(jardunaldia);
			puntuazioaJokalariaJardunaldia.setPuntuak(0);
			
			Iterator it = puntuakJokalaria.iterator();
			while(it.hasNext()) {
				PuntuazioaJokalariaJardunaldia lag = (PuntuazioaJokalariaJardunaldia) it.next();
				if(lag.getIdJardunaldia()==jardunaldia.getIdJardunaldia()) {
					puntuazioaJokalariaJardunaldia.setPuntuak(lag.getPuntuak());
				}
			}
			emaitza.add(puntuazioaJokalariaJardunaldia);
		}
		emaitza.sort(Comparator.comparingInt(PuntuazioaJokalariaJardunaldia::getIdJardunaldia));
		Collections.reverse(emaitza);
		return emaitza;
	}
	
	
	/*
	 * KOMUNITATEA
	 */
	@WebMethod
	public Komunitatea KomunitateaLortu(Komunitatea komunitatea) {
		System.out.println("KomunitateaLortu  "+komunitatea.getIdKomunitatea());
		HibernateDataAccess dbManager = new HibernateDataAccess();
		return (dbManager.komunitateaLortu(komunitatea.getIdKomunitatea())).getKopia();
	}
	@WebMethod
	public List<Erabiltzailea> ErabiltzaileakLortu(Komunitatea komunitatea) {
		System.out.println("ErabiltzaileakLortu  "+komunitatea.getIdKomunitatea());
		HibernateDataAccess dbManager = new HibernateDataAccess();
		Iterator it=dbManager.erabiltzaileakLortu(komunitatea).iterator();
		List<Erabiltzailea>  res = new ArrayList<Erabiltzailea>();
		while(it.hasNext())res.add(((Erabiltzailea)it.next()).getKopia());
		return res;
	}
	@WebMethod
	public List<Mezua> MezuakLortu(Komunitatea komunitatea) {
		System.out.println("MezuakLortu  "+komunitatea.getIdKomunitatea());
		HibernateDataAccess dbManager = new HibernateDataAccess();
		List<Mezua> emaitza = new ArrayList<Mezua>();
		Iterator it=dbManager.komunitatekoMezuak(komunitatea).iterator();
		while(it.hasNext()) {
			Mezua mezua=(Mezua)it.next();
			emaitza.add(mezua);
		}
		emaitza.sort(Comparator.comparingInt(Mezua::getIdMezua));
		Collections.reverse(emaitza);
		return emaitza;
	}
	@WebMethod
	public boolean MezuaIdatzi(String textua,Komunitatea komunitatea,Erabiltzailea erabiltzailea) {
		try {
			System.out.println("MezuaIdatzi   textua: "+textua+"   komunitatea:"+komunitatea.getIdKomunitatea()+"   erabiltzailea:"+erabiltzailea.getIdErabiltzailea());
			HibernateDataAccess dbManager = new HibernateDataAccess();
			Mezua mezua=new Mezua(erabiltzailea,komunitatea,new Date(),true,textua);
			dbManager.mezuaGorde(mezua);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@WebMethod
	public boolean MezuaEzabatu(Mezua mezuaLag) {
		try {
			System.out.println("MezuaEzabatu   mezua: "+mezuaLag.getIdMezua());
			HibernateDataAccess dbManager = new HibernateDataAccess();
			Mezua mezua=dbManager.mezuaLortu(mezuaLag.getIdMezua());
			dbManager.mezuaEzabatu(mezua);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@WebMethod
	public boolean ErabiltzaileaEzabatu(Erabiltzailea erabiltzaileaLag) {
		try {
			System.out.println("ErabiltzaileaEzabatu   erabiltzailea: "+erabiltzaileaLag.getIdErabiltzailea());
			HibernateDataAccess dbManager = new HibernateDataAccess();
			Erabiltzailea erabiltzailea=dbManager.erabiltzaileaLortu(erabiltzaileaLag.getIdErabiltzailea());
			dbManager.erabiltzaileaEzabatu(erabiltzailea);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@WebMethod
	public boolean KomunitateaGorde(Komunitatea komunitateaLag) {
		System.out.println("KomunitateaGorde   komunitatea: "+komunitateaLag.getIdKomunitatea());
		HibernateDataAccess dbManager = new HibernateDataAccess();
		Komunitatea komunitatea=dbManager.komunitateaLortu(komunitateaLag.getIdKomunitatea());
		komunitatea.setSaria1(komunitateaLag.getSaria1());
		komunitatea.setSaria2(komunitateaLag.getSaria2());
		komunitatea.setSaria3(komunitateaLag.getSaria3());
		dbManager.komunitateaGorde(komunitatea);
		return true;
	}
	@WebMethod
	public boolean ErabiltzaileaGorde(Erabiltzailea erabiltzaileaLag) {
		System.out.println("Erabiltzailea   erabiltzailea: "+erabiltzaileaLag.getIdErabiltzailea());
		System.out.println(erabiltzaileaLag.getHizkuntza()+" "+erabiltzaileaLag.getOrduDiferentzia()+" "+erabiltzaileaLag.getKoloreak());
		HibernateDataAccess dbManager = new HibernateDataAccess();
		Erabiltzailea erabiltzailea=dbManager.erabiltzaileaLortu(erabiltzaileaLag.getIdErabiltzailea());
		erabiltzailea.setHizkuntza(erabiltzaileaLag.getHizkuntza());
		erabiltzailea.setOrduDiferentzia(erabiltzaileaLag.getOrduDiferentzia());
		erabiltzailea.setKoloreak(erabiltzaileaLag.getKoloreak());
		dbManager.erabiltzaileaGorde(erabiltzailea);
		return true;
	}
	
	
	/*
	 * KLASIFIKAZIOA
	 */
	@WebMethod
	public List<Jardunaldia> OrainArtekoJardunaldiak() {
		System.out.println("OrainArtekoJardunaldiak");
		Date gaurkoEguna = new Date();
		HibernateDataAccess dbManager = new HibernateDataAccess();
		List<Jardunaldia> emaitza = new ArrayList<Jardunaldia>();
		Iterator it=dbManager.getAllJardunaldiak().iterator();
		while(it.hasNext()) {
			Jardunaldia jardunaldia = new Jardunaldia();
			Jardunaldia jardunaldia_lag = (Jardunaldia) it.next();
			jardunaldia.setIdJardunaldia(jardunaldia_lag.getIdJardunaldia());
			jardunaldia.setHasierakoEguna(jardunaldia_lag.getHasierakoEguna());
			if(gaurkoEguna.compareTo(jardunaldia.getHasierakoEguna())>0)
				emaitza.add(jardunaldia);
		}
		emaitza.sort(Comparator.comparingInt(Jardunaldia::getIdJardunaldia));
		Collections.reverse(emaitza);
		return emaitza;
	}
	@WebMethod
	public List<PuntuazioaErabiltzaileaJardunaldia> JornadakoKlasifikazioa(Komunitatea komunitatea,Jardunaldia jardunaldia) {
		List<PuntuazioaErabiltzaileaJardunaldia> emaitza = new ArrayList<PuntuazioaErabiltzaileaJardunaldia>();
		HibernateDataAccess dbManager = new HibernateDataAccess();
		for(Erabiltzailea erabiltzailea:ErabiltzaileakLortu(komunitatea)) {
			emaitza.add((PuntuazioaErabiltzaileaJardunaldia)dbManager.erabiltzailearenPuntuakJardunaldia(erabiltzailea, jardunaldia));
		}
		emaitza.sort(Comparator.comparingInt(PuntuazioaErabiltzaileaJardunaldia::getPuntuak));
		Collections.reverse(emaitza);
		return emaitza;
	}
	@WebMethod
	public List<PuntuazioaErabiltzaileaJardunaldia> KlasifikazioOrokorra(Komunitatea komunitatea) {
		List<PuntuazioaErabiltzaileaJardunaldia> emaitza = new ArrayList<PuntuazioaErabiltzaileaJardunaldia>();
		HibernateDataAccess dbManager = new HibernateDataAccess();
		for(Erabiltzailea erabiltzailea:ErabiltzaileakLortu(komunitatea)) {
			int puntuak=0;
			Iterator it=dbManager.erabiltzailearenPuntuak(erabiltzailea).iterator();
			while(it.hasNext()) {
				puntuak=puntuak+((PuntuazioaErabiltzaileaJardunaldia)it.next()).getPuntuak();
			}
			PuntuazioaErabiltzaileaJardunaldia puntuazioaErabiltzaileaJardunaldia= new PuntuazioaErabiltzaileaJardunaldia();
			puntuazioaErabiltzaileaJardunaldia.setErabiltzailea(erabiltzailea);
			puntuazioaErabiltzaileaJardunaldia.setPuntuak(puntuak);
			emaitza.add(puntuazioaErabiltzaileaJardunaldia);
		}
		emaitza.sort(Comparator.comparingInt(PuntuazioaErabiltzaileaJardunaldia::getPuntuak));
		Collections.reverse(emaitza);
		return emaitza;
	}

	

	
	
}
