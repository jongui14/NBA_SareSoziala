package businessLogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.net.ssl.HttpsURLConnection;

import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import dataAccess.DataAccesNBA;
import dataAccess.HibernateDataAccess;
import dataAccess.HibernateUtil;
import domain.Alineazioa;
import domain.Erabiltzailea;
import domain.Jardunaldia;
import domain.Jokalaria;
import domain.Jokalariaren_partidua;
import domain.Komunitatea;
import domain.MerkatukoJokalaria;
import domain.Mezua;
import domain.Partidua;
import domain.PuntuazioaErabiltzaileaJardunaldia;
import domain.PuntuazioaErabiltzaileaJardunaldiaId;
import domain.PuntuazioaJokalariaJardunaldia;
import domain.PuntuazioaJokalariaJardunaldiaId;
import domain.Taldea;
import domain.Transakzioa;

@WebService(endpointInterface = "businessLogic.AdminGlobal_Interface")
public class AdminGlobal_Implementation implements AdminGlobal_Interface {
	
	/*
	 * Administratzaile globala
	 */
	@WebMethod
	public void JokalarienInformazioaEguneratu() {
		DataAccesNBA dataAccesNBA= new DataAccesNBA();
		HibernateDataAccess dbManager = new HibernateDataAccess();
    	for(Jokalaria jokalaria:dataAccesNBA.jokalariakLortu()) {
    		dbManager.jokalariaGorde(jokalaria);
    	}
	}
	@WebMethod
	public void SoldatakEguneratu() {
		DataAccesNBA dataAccesNBA= new DataAccesNBA();
		HibernateDataAccess dbManager = new HibernateDataAccess();
    	for(Jokalaria jokalaria:dataAccesNBA.jokalariakSoldatekinLortu()) {
    		dbManager.jokalariaGorde(jokalaria);
    	}
	}
	@WebMethod
	public void TaldeenInformazioaEguneratu() {
		DataAccesNBA dataAccesNBA= new DataAccesNBA();
		HibernateDataAccess dbManager = new HibernateDataAccess();
    	for(Taldea taldea:dataAccesNBA.taldeakLortu()) {
    		dbManager.taldeaGorde(taldea);
    	}
	}
	
	@WebMethod
	public void MezuGeneralakBidali(String mezuaTextua) {
		Calendar cal = Calendar.getInstance();
	    Date date=cal.getTime();
	    
		HibernateDataAccess dbManager = new HibernateDataAccess();
		Erabiltzailea root=dbManager.erabiltzaileaLortu(1);

		for(Komunitatea komunitatea : KomunitateakLortu() ) {
			Mezua mezua = new Mezua();
			mezua.setErabiltzailea(root);
			mezua.setEguna(date);
			mezua.setEzabagarria(false);
			mezua.setMezua(mezuaTextua);
			mezua.setKomunitatea(komunitatea);
			dbManager.mezuaGorde(mezua);
		}
	}
	
	@WebMethod
	public List<Komunitatea> KomunitateakLortu(){
		List<Komunitatea> emaitza = new ArrayList<Komunitatea>();
		HibernateDataAccess dbManager = new HibernateDataAccess();
		Set<Komunitatea> komunitateak = dbManager.getAllKomunitateak();
		for(Komunitatea komunitatea:komunitateak) {
			emaitza.add(komunitatea);
		}
		return emaitza;
	}
	
	@WebMethod
	public void MerkatuenEgoeraEguneratu(){
		System.out.println("merkatuak eguneratu");
		HibernateDataAccess dbManager = new HibernateDataAccess();
		for(MerkatukoJokalaria merkatukoJokalaria:dbManager.getAllMerkatukoJokalariak()) {
			if(!merkatukoJokalaria.isTramitatua() && merkatukoJokalaria.getErabiltzaileaByIdErabiltzaileaIrabazlea()!=null && merkatukoJokalaria.getErabiltzaileaByIdErabiltzaileaIrabazlea().getIdErabiltzailea()!=1) {
				if(merkatukoJokalaria.getErabiltzaileaByIdErabiltzaileaJabea()!=null && merkatukoJokalaria.getErabiltzaileaByIdErabiltzaileaJabea().getIdErabiltzailea()!=1 && merkatukoJokalaria.isOnartua()) {
					Erabiltzailea jabea=merkatukoJokalaria.getErabiltzaileaByIdErabiltzaileaJabea();
					jabea.getJokalarias().remove(merkatukoJokalaria.getJokalaria());
					jabea.setDirua(jabea.getDirua()+merkatukoJokalaria.getEskaintzaIrabazlea());
					dbManager.erabiltzaileaGorde(jabea);
					
					Transakzioa tran=new Transakzioa();
					tran.setErabiltzailea(jabea);
					tran.setKantitatea(merkatukoJokalaria.getEskaintzaIrabazlea());
					tran.setMezua("5_"+merkatukoJokalaria.getJokalaria().izenOsoa());
					tran.setEguna(new Date());
					dbManager.transakzioaGorde(tran);
				}
				Erabiltzailea irabazlea=merkatukoJokalaria.getErabiltzaileaByIdErabiltzaileaIrabazlea();
				irabazlea.getJokalarias().add((Jokalaria)merkatukoJokalaria.getJokalaria());
				irabazlea.setDirua(irabazlea.getDirua()-merkatukoJokalaria.getEskaintzaIrabazlea());
				dbManager.erabiltzaileaGorde(irabazlea);
				
				Transakzioa tran=new Transakzioa();
				tran.setErabiltzailea(irabazlea);
				tran.setKantitatea(-merkatukoJokalaria.getEskaintzaIrabazlea());
				tran.setMezua("4_"+merkatukoJokalaria.getJokalaria().izenOsoa());
				tran.setEguna(new Date());
				dbManager.transakzioaGorde(tran);
			}
			merkatukoJokalaria.setTramitatua(true);
			dbManager.merkatukoJokalariaGorde(merkatukoJokalaria);
		}
		
		
		for(Komunitatea komunitatea : KomunitateakLortu() ) {
			if(komunitatea.getIdKomunitatea()!=1)
				MerkatuanJokalariakTxertatu(komunitatea);
		}
	}
	@WebMethod
	public void MerkatuanJokalariakTxertatu(Komunitatea komunitatea) {
		HibernateDataAccess dbManager = new HibernateDataAccess();
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
		
	}
	
	@WebMethod
	public void JardunaldiakHasieratu(int urtea,int hilabetea,int eguna) {		
    	//Calendar : 1 Igandea, 2 Astelehena, 3 Asteartea, 4 Asteazkena, 5 Osteguna, 6 Ostirala, 7 Larunbata
		HibernateDataAccess dbManager = new HibernateDataAccess();

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, urtea);
		calendar.set(Calendar.MONTH, hilabetea-1);
		calendar.set(Calendar.DAY_OF_MONTH, eguna);
		calendar.set(Calendar.DAY_OF_WEEK,2);
		
		while(calendar.get(Calendar.MONTH)+1!=7) {			
			Jardunaldia jardunaldia = new Jardunaldia();
			jardunaldia.setHasierakoEguna(calendar.getTime());
			
			dbManager.jardunaldiaGorde(jardunaldia);
			
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+7);
		}
	}
	@WebMethod
	public List<Jardunaldia> JardunaldiakLortu(){
		HibernateDataAccess dbManager = new HibernateDataAccess();
		List<Jardunaldia> emaitza = new ArrayList<Jardunaldia>();
		Iterator it=dbManager.getAllJardunaldiak().iterator();
		while(it.hasNext()) {
			Jardunaldia jardunaldia = new Jardunaldia();
			Jardunaldia jardunaldia_lag = (Jardunaldia) it.next();
			jardunaldia.setIdJardunaldia(jardunaldia_lag.getIdJardunaldia());
			jardunaldia.setHasierakoEguna(jardunaldia_lag.getHasierakoEguna());
			emaitza.add(jardunaldia);
		}
		emaitza.sort(Comparator.comparingInt(Jardunaldia::getIdJardunaldia));
		return emaitza;
	}
	@WebMethod
	public boolean JardunaldikoPuntuakLortu(Jardunaldia jardunaldia){
		UserNotLogged_Implementation appI= new UserNotLogged_Implementation();
		DataAccesNBA dataAccesNBA = new DataAccesNBA();
		HibernateDataAccess dbManager = new HibernateDataAccess();
		
		System.out.println("JardunaldikoPuntuakLortu: ");
		Calendar cal = Calendar.getInstance();
		cal.setTime(jardunaldia.getHasierakoEguna());cal.add(Calendar.DATE, -1);
		
		Set<PuntuazioaJokalariaJardunaldia> jokalarien_Balorazioak = new HashSet<PuntuazioaJokalariaJardunaldia>();
		
		for(int i=0;i<7;i++) {
			cal.add(Calendar.DATE, 1);
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH)+1;
			int day = cal.get(Calendar.DAY_OF_MONTH);
			
			System.out.println(year+"/"+month+"/"+day);
			
			List<Partidua> egunekoPartiduak = appI.JornadakoPartiduak(cal.getTime());
			for(Partidua partiduaLag:egunekoPartiduak) {
				Partidua partidua=dataAccesNBA.partiduarenInformazioa(partiduaLag);
				
				for(Jokalariaren_partidua jokalariaren_partidua:partidua.getEtxeko_jokalariak()) {
					PuntuazioaJokalariaJardunaldia jokalarien_Balorazioa = new PuntuazioaJokalariaJardunaldia();
					jokalarien_Balorazioa.setJokalaria(jokalariaren_partidua.getJokalaria());
					jokalarien_Balorazioa.setPuntu_totalak(jokalariaren_partidua.jokalariaren_balorazioa());
					jokalarien_Balorazioa.setPartidu_kopurua(1);
					jokalarien_Balorazioa.setJardunaldia(jardunaldia);
					jokalarien_Balorazioa.puntuak_ezarri();
					
					if(jokalarien_Balorazioak.contains(jokalarien_Balorazioa)) {
						Iterator it = jokalarien_Balorazioak.iterator();
						while(it.hasNext()) {
							PuntuazioaJokalariaJardunaldia lag=(PuntuazioaJokalariaJardunaldia) it.next();
							if(lag.getJokalaria().getIdJokalaria()==jokalariaren_partidua.getJokalaria().getIdJokalaria()) {
								jokalarien_Balorazioak.remove(lag);
								lag.setPuntu_totalak(lag.getPuntu_totalak()+jokalariaren_partidua.jokalariaren_balorazioa());
								lag.setPartidu_kopurua(lag.getPartidu_kopurua()+1);
								lag.puntuak_ezarri();
								jokalarien_Balorazioak.add(lag);
								break;
							}
						}
						
					}else {
						PuntuazioaJokalariaJardunaldiaId puntuazioaJokalariaJardunaldiaId= new PuntuazioaJokalariaJardunaldiaId();
						puntuazioaJokalariaJardunaldiaId.setIdJokalaria(jokalarien_Balorazioa.getJokalaria().getIdJokalaria());
						puntuazioaJokalariaJardunaldiaId.setIdJardunaldia(jardunaldia.getIdJardunaldia());
						jokalarien_Balorazioa.setId(puntuazioaJokalariaJardunaldiaId);
						jokalarien_Balorazioak.add(jokalarien_Balorazioa);
					}
					
				}
				for(Jokalariaren_partidua jokalariaren_partidua:partidua.getKanpoko_jokalariak()) {
					PuntuazioaJokalariaJardunaldia jokalarien_Balorazioa = new PuntuazioaJokalariaJardunaldia();
					jokalarien_Balorazioa.setJokalaria(jokalariaren_partidua.getJokalaria());
					jokalarien_Balorazioa.setPuntu_totalak(jokalariaren_partidua.jokalariaren_balorazioa());
					jokalarien_Balorazioa.setPartidu_kopurua(1);
					jokalarien_Balorazioa.setJardunaldia(jardunaldia);
					jokalarien_Balorazioa.puntuak_ezarri();
					
					if(jokalarien_Balorazioak.contains(jokalarien_Balorazioa)) {
						Iterator it = jokalarien_Balorazioak.iterator();
						while(it.hasNext()) {
							PuntuazioaJokalariaJardunaldia lag=(PuntuazioaJokalariaJardunaldia) it.next();
							if(lag.getJokalaria().getIdJokalaria()==jokalariaren_partidua.getJokalaria().getIdJokalaria()) {
								jokalarien_Balorazioak.remove(lag);
								lag.setPuntu_totalak(lag.getPuntu_totalak()+jokalariaren_partidua.jokalariaren_balorazioa());
								lag.setPartidu_kopurua(lag.getPartidu_kopurua()+1);
								lag.puntuak_ezarri();
								jokalarien_Balorazioak.add(lag);
								break;
							}
						}
						
					}else {
						PuntuazioaJokalariaJardunaldiaId puntuazioaJokalariaJardunaldiaId= new PuntuazioaJokalariaJardunaldiaId();
						puntuazioaJokalariaJardunaldiaId.setIdJokalaria(jokalarien_Balorazioa.getJokalaria().getIdJokalaria());
						puntuazioaJokalariaJardunaldiaId.setIdJardunaldia(jardunaldia.getIdJardunaldia());
						jokalarien_Balorazioa.setId(puntuazioaJokalariaJardunaldiaId);
						jokalarien_Balorazioak.add(jokalarien_Balorazioa);
					}
				}
				
			}

		}
		
		Iterator it = jokalarien_Balorazioak.iterator();
		while(it.hasNext()) {
			PuntuazioaJokalariaJardunaldia lag=(PuntuazioaJokalariaJardunaldia) it.next();
			try {
				dbManager.puntuazioaJokalariaJardunaldiaGorde(lag);
			}catch(Exception e) {
				System.out.println(lag.getJokalaria().getIdJokalaria());
			}
			
		}
		
		ArrayList<PuntuazioaErabiltzaileaJardunaldia> puntuazioak=new ArrayList<>();
		Set<Alineazioa> alineazioak = dbManager.getAllAlineazioak();
		Iterator<Alineazioa> iterator = alineazioak.iterator();
		while(iterator.hasNext()) {
			Alineazioa alineazioa = iterator.next();
			if(alineazioa.getId().getMota()==1) {
				PuntuazioaErabiltzaileaJardunaldiaId puntuazioaErabiltzaileaJardunaldiaId=new PuntuazioaErabiltzaileaJardunaldiaId();
				puntuazioaErabiltzaileaJardunaldiaId.setIdErabiltzailea(alineazioa.getErabiltzailea().getIdErabiltzailea());
				puntuazioaErabiltzaileaJardunaldiaId.setIdJardunaldia(jardunaldia.getIdJardunaldia());
				
				PuntuazioaErabiltzaileaJardunaldia puntuazioaErabiltzaileaJardunaldia=new PuntuazioaErabiltzaileaJardunaldia();
				puntuazioaErabiltzaileaJardunaldia.setId(puntuazioaErabiltzaileaJardunaldiaId);
				puntuazioaErabiltzaileaJardunaldia.setErabiltzailea(alineazioa.getErabiltzailea());
				puntuazioaErabiltzaileaJardunaldia.setJardunaldia(jardunaldia);
				
				Iterator ite = jokalarien_Balorazioak.iterator();int puntuak=0;int kop=0;
				while(ite.hasNext()) {
					PuntuazioaJokalariaJardunaldia lag=(PuntuazioaJokalariaJardunaldia) ite.next();
					
					if(lag.getJokalaria().getIdJokalaria()==alineazioa.getJokalariaByIdJokalaria1().getIdJokalaria() || lag.getJokalaria().getIdJokalaria()==alineazioa.getJokalariaByIdJokalaria2().getIdJokalaria() || lag.getJokalaria().getIdJokalaria()==alineazioa.getJokalariaByIdJokalaria3().getIdJokalaria() || lag.getJokalaria().getIdJokalaria()==alineazioa.getJokalariaByIdJokalaria4().getIdJokalaria() || lag.getJokalaria().getIdJokalaria()==alineazioa.getJokalariaByIdJokalaria5().getIdJokalaria()) {
						//System.out.print(lag.getJokalaria().getIdJokalaria());
						puntuak=puntuak+lag.getPuntuak();
						kop++;if(kop==5)break;
					}
				}
				puntuazioaErabiltzaileaJardunaldia.setPuntuak(puntuak);
				puntuazioak.add(puntuazioaErabiltzaileaJardunaldia);
				dbManager.puntuazioaErabiltzaileaJardunaldiaGorde(puntuazioaErabiltzaileaJardunaldia);
			}
		}
		
		
		for(Komunitatea komunitatea : KomunitateakLortu() ) {
			if(komunitatea.getIdKomunitatea()!=1) {
				Iterator it1=dbManager.erabiltzaileakLortu(komunitatea).iterator();
				ArrayList<PuntuazioaErabiltzaileaJardunaldia> komunitateko_puntuazioak=new ArrayList<>();
				while(it1.hasNext()) {
					Erabiltzailea erab=(Erabiltzailea)it1.next();
					for(PuntuazioaErabiltzaileaJardunaldia puntuazioa:puntuazioak) {
						if(puntuazioa.getErabiltzailea().getIdErabiltzailea()==erab.getIdErabiltzailea()) {
							komunitateko_puntuazioak.add(puntuazioa);
						}
					}					
				}
				komunitateko_puntuazioak.sort(Comparator.comparingInt(PuntuazioaErabiltzaileaJardunaldia::getPuntuak));
				Collections.reverse(komunitateko_puntuazioak);
				
				int i=0;
				while(i<3 && i<komunitateko_puntuazioak.size()) {
					String textua="";int kantitatea=0;
					if(i==0) {
						kantitatea=komunitatea.getSaria1();textua="1_";
					}else if(i==1) {
						kantitatea=komunitatea.getSaria2();textua="2_";
					}else if(i==2) {
						kantitatea=komunitatea.getSaria3();textua="3_";
					}
					
					Erabiltzailea erabiltzailea=komunitateko_puntuazioak.get(i).getErabiltzailea();
					if(kantitatea<0)kantitatea=kantitatea*-1;
					int dirua=erabiltzailea.getDirua();
					erabiltzailea.setDirua(dirua+kantitatea);
					dbManager.erabiltzaileaGorde(erabiltzailea);
					Transakzioa transakzioa=new Transakzioa(erabiltzailea,kantitatea,textua,new Date());
					dbManager.transakzioaGorde(transakzioa);
					i++;
				}
			}
		}
		
		
		
		
		return true;
	}
	
	
	@WebMethod
	public boolean AlineazioakFinkatu() {
		try {
			HibernateDataAccess dbManager = new HibernateDataAccess();
			Set<Alineazioa> alineazioak = dbManager.getAllAlineazioak();
			Iterator<Alineazioa> iterator = alineazioak.iterator();
			while(iterator.hasNext()) {
				Alineazioa alineazioa = iterator.next();
				if(alineazioa.getId().getMota()==0) {
					alineazioa.getId().setMota(1);
					dbManager.alineazioaGorde(alineazioa);
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}




    
    
}
