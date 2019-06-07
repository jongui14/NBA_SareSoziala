package businessLogic;

import javax.jws.WebService;

import dataAccess.DataAccesNBA;
import dataAccess.HibernateDataAccess;
import domain.Alineazioa;
import domain.Erabiltzailea;
import domain.Jardunaldia;
import domain.Jokalaria;
import domain.Komunitatea;
import domain.MerkatukoJokalaria;
import domain.Partidua;
import domain.Taldea;
import domain.Transakzioa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.jws.WebMethod;


@WebService(endpointInterface = "businessLogic.UserNotLogged_Interface")
public class UserNotLogged_Implementation implements UserNotLogged_Interface {
	
	/*
	 * REGISTRO/LOGIN  FUNTZIOAK
	 */
	@WebMethod
	public boolean KomunitateaLibre(String nick) {
		System.out.println("KomunitateaLibre: "+nick);
		HibernateDataAccess dbManager = new HibernateDataAccess();
		return dbManager.KomunitateaLibre(nick);
	}
	
	@WebMethod
	public boolean ErabiltzaileNickLibre(String nick) {	
		System.out.println("ErabiltzaileNickLibre: "+nick);
		HibernateDataAccess dbManager = new HibernateDataAccess();
		return dbManager.ErabiltzaileNickLibre(nick);
	}

	@Override
	public boolean ErabiltzaileEmailLibre(String email) {
		System.out.println("ErabiltzaileEmailLibre: "+email);
		HibernateDataAccess dbManager = new HibernateDataAccess();
		return dbManager.ErabiltzaileEmailLibre(email);
	}

	@Override
	public Komunitatea KomunitateaErregistratu(String nick, String pasahitza, String izen_osoa , String ongietorri_mezua,
			int saria1, int saria2, int saria3) {
		System.out.println("KomunitateaErregistratu: "+nick+" "+pasahitza);
		Komunitatea komunitatea = new Komunitatea();
		komunitatea.setNick(nick);
		komunitatea.setIzenOsoa(izen_osoa);
		komunitatea.setPasahitza(pasahitza);
		komunitatea.setOngietorriMezua(ongietorri_mezua);
		komunitatea.setSaria1(saria1);
		komunitatea.setSaria2(saria2);
		komunitatea.setSaria3(saria3);
		
		HibernateDataAccess dbManager = new HibernateDataAccess();
		dbManager.komunitateaGorde(komunitatea);	
		
		Erabiltzailea root=dbManager.erabiltzaileaLortu(1);
		Iterator it=dbManager.komunitatekoMerkaturakoAukeratutakoJokalariak(komunitatea).iterator();
		while(it.hasNext()) {
			Jokalaria jokalaria=(Jokalaria)it.next();
			System.out.println("MerkatuaEgoeraEguneratu    "+jokalaria.getIzena()+" "+jokalaria.getAbizena());
			MerkatukoJokalaria merkatukoJokalaria = new MerkatukoJokalaria();
			merkatukoJokalaria.setErabiltzaileaByIdErabiltzaileaJabea(root);
			merkatukoJokalaria.setErabiltzaileaByIdErabiltzaileaIrabazlea(root);
			merkatukoJokalaria.setJokalaria(jokalaria);
			merkatukoJokalaria.setKomunitatea(komunitatea);
			merkatukoJokalaria.setHasierakoPrezioa(jokalaria.getSoldata());
			merkatukoJokalaria.setEskaintzaIrabazlea(0);
			merkatukoJokalaria.setOnartua(false);
			merkatukoJokalaria.setTramitatua(false);
			merkatukoJokalaria.setTramitazioEguna(new Date());
			
			dbManager.merkatukoJokalariaGorde(merkatukoJokalaria);
		}
		
		return komunitatea;
	}
	
	@WebMethod
	public Komunitatea KomunitateaAukeratu(String nick,String pasahitza) {
		System.out.println("KomunitateaAukeratu: "+nick+" "+pasahitza);
		HibernateDataAccess dbManager = new HibernateDataAccess();
		Komunitatea komunitatea =  dbManager.komunitateaLortu(nick,pasahitza);
		Komunitatea komunitateaLag = new Komunitatea();
		try {
			komunitateaLag.setIdKomunitatea(komunitatea.getIdKomunitatea());
			komunitateaLag.setNick(komunitatea.getNick());
		}catch(Exception e) {
			komunitateaLag.setIdKomunitatea(0);
			komunitateaLag.setNick("");
		}
		return komunitateaLag;
	}
	
	@Override
	public boolean ErabiltzaileaErregistratu(Komunitatea komunitatea, String nick, String email, String izen_osoa,
			String pasahitza, boolean administratzailea) {
		try {
			System.out.println("ErabiltzaileaErregistratu: "+komunitatea.getIdKomunitatea()+" "+komunitatea.getNick()+"  "+nick+" "+email);
			HibernateDataAccess dbManager = new HibernateDataAccess();
			
			Erabiltzailea erabiltzailea = new Erabiltzailea();
			erabiltzailea.setKomunitatea(komunitatea);
			erabiltzailea.setNick(nick);
			erabiltzailea.setEmail(email);
			erabiltzailea.setIzenOsoa(izen_osoa);
			erabiltzailea.setPasahitza(pasahitza);
			erabiltzailea.setAdministratzailea(administratzailea);
			erabiltzailea.setDirua(40000000);
			erabiltzailea.jokalariakEsleitu();
			dbManager.erabiltzaileaGorde(erabiltzailea);
			
			Transakzioa transakzioa=new Transakzioa(erabiltzailea,40000000,"0_",new Date());
			dbManager.transakzioaGorde(transakzioa);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@WebMethod
	public Erabiltzailea ErabiltzaileaLogeatu(String nick,String pasahitza) {
		System.out.println("ErabiltzaileaLogeatu: "+nick+" "+pasahitza);
		HibernateDataAccess dbManager = new HibernateDataAccess();
		Erabiltzailea erabiltzailea = dbManager.erabiltzaileaLortu(nick, pasahitza);
		Erabiltzailea erabiltzaileaLag = new Erabiltzailea();
		if(erabiltzailea==null) {System.out.println("erab okerra");return erabiltzaileaLag;}
		try{
			erabiltzaileaLag.setKomunitatea(erabiltzailea.getKomunitatea());
			erabiltzaileaLag.setIdErabiltzailea(erabiltzailea.getIdErabiltzailea());
			erabiltzaileaLag.setNick(erabiltzailea.getNick());
			erabiltzaileaLag.setEmail(erabiltzailea.getEmail());
			erabiltzaileaLag.setIzenOsoa(erabiltzailea.getIzenOsoa());
			erabiltzaileaLag.setDirua(erabiltzailea.getDirua());
			erabiltzaileaLag.setAdministratzailea(erabiltzailea.isAdministratzailea());
			erabiltzaileaLag.setHizkuntza(erabiltzailea.getHizkuntza());
			erabiltzaileaLag.setKoloreak(erabiltzailea.getKoloreak());
			erabiltzaileaLag.setOrduDiferentzia(erabiltzailea.getOrduDiferentzia());
			System.out.println(erabiltzaileaLag.getKomunitatea().getIdKomunitatea());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return erabiltzaileaLag;
	}
	
	@WebMethod
	public boolean PasahitzaOnargarria(String pass) {
		System.out.println("pass "+pass);
		if(pass==null)return false;
		if(pass.equals("1234"))return false;
		return true;
	}
	
	

	/*
	 * GAINONTZEKOAK
	 */
	@WebMethod
	public List<Partidua> JornadakoPartiduak(Date data){
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		System.out.println("JornadakoPartiduak: "+year+"/"+month+"/"+day);
		
		DataAccesNBA dataAccesNBA = new DataAccesNBA();
		return dataAccesNBA.partiduakLortu(data);
	}
	@WebMethod
	public List<Alineazioa> AlineazioPublikoak(){		
		System.out.println("AlineazioPublikoak Lortu");
		List<Alineazioa> res = new ArrayList<Alineazioa>();
		HibernateDataAccess dbManager = new HibernateDataAccess();
		Iterator it = dbManager.getAllAlineazioPublikoak().iterator();
		while(it.hasNext()) {
			Alineazioa lag = (Alineazioa)it.next();
			
			Erabiltzailea erabiltzailea = new Erabiltzailea();
			erabiltzailea.setIdErabiltzailea(lag.getErabiltzailea().getIdErabiltzailea());
			erabiltzailea.setNick(lag.getErabiltzailea().getNick());
			
			Alineazioa alineazioa = new Alineazioa();
			alineazioa.setErabiltzailea(erabiltzailea);
			alineazioa.setJokalariaByIdJokalaria1(new Jokalaria(lag.getJokalariaByIdJokalaria1().getIdJokalaria()));
			alineazioa.setJokalariaByIdJokalaria2(new Jokalaria(lag.getJokalariaByIdJokalaria2().getIdJokalaria()));
			alineazioa.setJokalariaByIdJokalaria3(new Jokalaria(lag.getJokalariaByIdJokalaria3().getIdJokalaria()));
			alineazioa.setJokalariaByIdJokalaria4(new Jokalaria(lag.getJokalariaByIdJokalaria4().getIdJokalaria()));
			alineazioa.setJokalariaByIdJokalaria5(new Jokalaria(lag.getJokalariaByIdJokalaria5().getIdJokalaria()));
			
			res.add(alineazioa);
		}
		return res;
	}
	@WebMethod
	public List<Jokalaria> JokalariakLortu(){
		System.out.println("JokalariakLortu");
		List<Jokalaria> res = new ArrayList<Jokalaria>();
		HibernateDataAccess dbManager = new HibernateDataAccess();
		Iterator it = dbManager.getAllJokalariak().iterator();
		while(it.hasNext()) {
			Jokalaria lag = (Jokalaria)it.next();
			Jokalaria jokalaria = new Jokalaria();
			jokalaria.setIdJokalaria(lag.getIdJokalaria());
			jokalaria.setSoldata(lag.getSoldata());
			jokalaria.setTaldeaByIdTaldea(lag.getTaldeaByIdTaldea());
			jokalaria.setIzena(lag.getIzena());
			jokalaria.setAbizena(lag.getAbizena());
			jokalaria.setPosizioa(lag.getPosizioa());
			jokalaria.setDortsala(lag.getDortsala());
			jokalaria.setAltuera(lag.getAltuera());
			jokalaria.setPisua(lag.getPisua());
			jokalaria.setJaiotzeData(lag.getJaiotzeData());
			jokalaria.setNazionalitatea(lag.getNazionalitatea());
			res.add(jokalaria);
		}
		return res;
	}
	@WebMethod
	public List<Taldea> TaldeakLortu(){
		System.out.println("TaldeakLortu");
		List<Taldea> res = new ArrayList<Taldea>();
		HibernateDataAccess dbManager = new HibernateDataAccess();
		Iterator it = dbManager.getAllTadeak().iterator();
		while(it.hasNext()) {
			Taldea lag = (Taldea)it.next();
			Taldea taldea = new Taldea();
			taldea.setIdTaldea(lag.getIdTaldea());
			taldea.setIzenOsoa(lag.getIzenOsoa());
			res.add(taldea);
		}
		res.sort(Comparator.comparingInt(Taldea::getIdTaldea));
		return res;
	}
	@WebMethod
	public Taldea TaldearenDatuakLortu(Taldea lag){
		System.out.println("TaldearenDatuakLortu");
		HibernateDataAccess dbManager = new HibernateDataAccess();
		Taldea taldea = dbManager.taldeaLortu(lag.getIdTaldea());
		
		Taldea res = new Taldea();
		res.setIdTaldea(taldea.getIdTaldea());
		res.setApodoa(taldea.getApodoa());
		res.setHiria(taldea.getHiria());
		res.setIzenOsoa(taldea.getIzenOsoa());
		
		Iterator it=taldea.getJokalariasForIdTaldea().iterator();
		while(it.hasNext()) {
			Jokalaria jok = (Jokalaria) it.next();
			Jokalaria jokalaria = new Jokalaria();
			jokalaria.setIdJokalaria(jok.getIdJokalaria());
			jokalaria.setIzena(jok.getIzena());
			jokalaria.setAbizena(jok.getAbizena());
			jokalaria.setSoldata(jok.getSoldata());
			jokalaria.setPosizioa(jok.getPosizioa());
			res.getJokalariasForIdTaldea().add(jokalaria);
		}
		return res;
	}
	
	


}
