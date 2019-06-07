package dataAccess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import domain.Jokalaria;
import domain.Jokalariaren_partidua;
import domain.Partidua;
import domain.Taldea;

public class DataAccesNBA {

	/*
	 * NBA-ko API-a 
	 */
	private static final String API_jokalari_helbidea = "https://data.nba.net/10s/prod/v1/2018/players.json";
	private static final String API_taldeen_helbidea = "https://data.nba.net/10s/prod/v2/2018/teams.json";
	private static final String API_partiduen_helbidea = "https://data.nba.net/10s/prod/v2/DIA/scoreboard.json";
	private static final String API_partiduaren_inf_helbidea = "https://data.nba.net/10s/prod/v1/DATA/GAMEID_boxscore.json";


	public List<Jokalaria> jokalariakLortu(){
		HibernateDataAccess dbManager = new HibernateDataAccess();
		List<Jokalaria> jokalariak = new ArrayList<>();
		try {
        	JSONParser parser = new JSONParser();
    		
    		URL url = new URL(API_jokalari_helbidea);
    		
    		HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.connect();
            
            Object obj = parser.parse(new InputStreamReader(urlConnection.getInputStream()));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject league = (JSONObject) jsonObject.get("league");
            JSONArray jokalari_lista = (JSONArray) league.get("standard");
            
            for(int i=0;i<jokalari_lista.size();i++){
                JSONObject jokalariaOBJ = (JSONObject) jokalari_lista.get(i);
                Jokalaria jokalaria = new Jokalaria();

                if(jokalariaOBJ.get("personId").toString().equals("")) {
                    jokalaria.setIdJokalaria(-1);
                }else {
                    jokalaria.setIdJokalaria(Integer.parseInt(jokalariaOBJ.get("personId").toString()));
                }
                if(jokalariaOBJ.get("teamId").toString().equals("")) {
                    jokalaria.setTaldeaByIdTaldea(null);
                }else {
                    jokalaria.setTaldeaByIdTaldea(dbManager.taldeaLortu(Integer.parseInt(jokalariaOBJ.get("teamId").toString())));
                }
                
                jokalaria.setIzena(jokalariaOBJ.get("firstName").toString());
                jokalaria.setAbizena(jokalariaOBJ.get("lastName").toString());
                
                if(jokalariaOBJ.get("jersey").toString().equals("")) {
                    jokalaria.setDortsala(-1);
                }else {
                    jokalaria.setDortsala(Integer.parseInt(jokalariaOBJ.get("jersey").toString()));
                }
                jokalaria.setPosizioa(jokalariaOBJ.get("pos").toString());

                if(jokalariaOBJ.get("heightMeters").toString().equals("")) {
                    jokalaria.setAltuera(-1.0f);
                }else {
                    jokalaria.setAltuera(Float.parseFloat(jokalariaOBJ.get("heightMeters").toString()));
                }
                if(jokalariaOBJ.get("weightKilograms").toString().equals("")) {
                    jokalaria.setPisua(-1.0f);
                }else {
                    jokalaria.setPisua(Float.parseFloat(jokalariaOBJ.get("weightKilograms").toString()));
                }

                try{jokalaria.setJaiotzeData( (new SimpleDateFormat("yyyy-MM-dd")).parse(jokalariaOBJ.get("dateOfBirthUTC").toString()) ); }catch(Exception e) {jokalaria.setJaiotzeData(new Date());}
                jokalaria.setNazionalitatea(jokalariaOBJ.get("country").toString());

                if(jokalariaOBJ.get("nbaDebutYear").toString().equals("")) {
                    jokalaria.setNbaDebutUrtea(-1);
                }else {
                	jokalaria.setNbaDebutUrtea(Integer.parseInt(jokalariaOBJ.get("nbaDebutYear").toString()));
                }                
                if(jokalariaOBJ.get("yearsPro").toString().equals("")) {
                    jokalaria.setNbaUrteak(0);
                }else {
                    jokalaria.setNbaUrteak(Integer.parseInt(jokalariaOBJ.get("yearsPro").toString()));
                }

                //draft
                JSONObject draft_obj = (JSONObject) jokalariaOBJ.get("draft");
                if(draft_obj.get("seasonYear").toString().equals("")) {
                	 jokalaria.setDraftUrtea(-1);
                     jokalaria.setDraftPostua(-1);
                     jokalaria.setTaldeaByDraftIdTaldea(null);
                }else {
                	 jokalaria.setDraftUrtea(Integer.parseInt(draft_obj.get("seasonYear").toString()));
                     jokalaria.setDraftPostua(Integer.parseInt(draft_obj.get("pickNum").toString()));
                     jokalaria.setTaldeaByDraftIdTaldea(dbManager.taldeaLortu(Integer.parseInt(draft_obj.get("teamId").toString())));
                }
                jokalaria.setUnibertsitatea(jokalariaOBJ.get("collegeName").toString());

                jokalariak.add(jokalaria);
            }
    		
        	System.out.println("JOKALARI GUZTIAK EGOKI IRAKURRI DIRA!!!");
            
    	}catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch (ParseException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
    	}
		
		return jokalariak;
	}
	public List<Jokalaria> jokalariakSoldatekinLortu(){
		List<Jokalaria> res = new ArrayList<>();
		try {
			HibernateDataAccess dbManager = new HibernateDataAccess();
			Set<Jokalaria> jokalariak=dbManager.getAllJokalariak();
			
            URL url = new URL("https://hoopshype.com/salaries/players/");
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = "";

            while( (line=br.readLine()) != null )
                if(line.contains("<td class=\"name\">"))break;

            String izena="",abizena="";int soldata=0;
            while( (line=br.readLine()) != null){
                while(line!=null && !line.contains("<td class=\"name\">")){
                    line=br.readLine();
                }
                if(line==null)break;

                br.readLine();line=br.readLine().replace("								", "").replace("							</a>", "");
                if(!line.contains(" ")) {
                	izena=line;
                	abizena="";
                }else {
                	izena=line.split(" ")[0];
            		abizena=line.substring(izena.length()+1);
                }
                
                
                br.readLine();br.readLine();line=br.readLine().replace("							", "").replace("						</td>", "");
                soldata=Integer.parseInt(line.replace("$", "").replace(",", ""));
                System.out.println("soldata: "+soldata);
                System.out.println("izena: "+izena+"    abizena: "+abizena);
                
                for(Jokalaria jokalaria:jokalariak) {
                	if(jokalaria.jokalaria_den_konprobatu(izena,abizena)) {
                		jokalaria.setSoldata(soldata);  
                		dbManager.jokalariaGorde(jokalaria);
                		break;
                		
                	}else if(jokalaria.antzekoa_den_konprobatu(izena,abizena)) {
                		jokalaria.setSoldata(soldata);
                		dbManager.jokalariaGorde(jokalaria);
                	}
                }
                
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
		
		return res;
	}
	
	public List<Taldea> taldeakLortu(){
		List<Taldea> taldeak = new ArrayList<Taldea>();
		try {
			HibernateDataAccess dbManager = new HibernateDataAccess();
        	JSONParser parser = new JSONParser();
    		
    		URL url = new URL(API_taldeen_helbidea);
    		
    		HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.connect();
            
            Object obj = parser.parse(new InputStreamReader(urlConnection.getInputStream()));
            JSONObject jsonObject = (JSONObject) obj;
           
            JSONObject league = (JSONObject) jsonObject.get("league");
            JSONArray taldeen_lista = (JSONArray) league.get("standard");
            
            for(int i=0;i<taldeen_lista.size();i++) {
            	
            	JSONObject taldeaOBJ = (JSONObject) taldeen_lista.get(i);
            	if(taldeaOBJ.get("isNBAFranchise").toString().equals("true")) {
            		
            		Taldea taldea = new Taldea();
            		
            		taldea.setIdTaldea(Integer.parseInt(taldeaOBJ.get("teamId").toString()));
            		taldea.setHiria(taldeaOBJ.get("city").toString());
            		taldea.setApodoa(taldeaOBJ.get("nickname").toString());
            		taldea.setIzenOsoa(taldeaOBJ.get("fullName").toString());
            		taldea.setKonferentzia(taldeaOBJ.get("confName").toString());
            		taldea.setUrlIzena(taldeaOBJ.get("urlName").toString());
            		
            		taldeak.add(taldea);
            	}
            }
        	System.out.println("TALDE GUZTIAK EGOKI IRAKURRI DIRA!!!");
            
    	}catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch (ParseException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
    	}
		
		return taldeak;
	}
	
	public List<Partidua> partiduakLortu(Date date){
		//HibernateDataAccess dbManager = new HibernateDataAccess();
		List<Partidua> partiduak = new ArrayList<>();
		try {
            JSONParser parser = new JSONParser();
            
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH)+1;String monthString=month+"";if(month<10)monthString='0'+monthString;
            int day = cal.get(Calendar.DAY_OF_MONTH);String dayString=day+"";if(day<10)dayString='0'+dayString;
            String fechaString=year+monthString+dayString;

            URL url = new URL(API_partiduen_helbidea.replace("DIA", fechaString));
            System.out.println(API_partiduen_helbidea.replace("DIA", fechaString));
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.connect();
            Object obj = parser.parse(new InputStreamReader(urlConnection.getInputStream()));
            JSONObject jsonObject = (JSONObject) obj;


            int numGames = Integer.parseInt(jsonObject.get("numGames")+"");
            JSONArray listaPartidos = (JSONArray) jsonObject.get("games");
            for(int i=0;i<numGames;i++){
            	Partidua partidua=new Partidua();
                JSONObject partidoObjecto = (JSONObject) listaPartidos.get(i);

                partidua.setIdPartidua(Integer.parseInt(partidoObjecto.get("gameId").toString()));
                partidua.setData(date);
                partidua.setPartiduaren_egoera(Integer.parseInt(partidoObjecto.get("statusNum").toString()));

                
                String horaCompleta=partidoObjecto.get("startTimeEastern").toString();
                String hora=horaCompleta.split(":")[0];
                if(horaCompleta.contains("PM") && !hora.contains("12"))hora=(Integer.parseInt(hora)+12)+"";
                int horaInt=Integer.parseInt(hora);
                int minutosInt=Integer.parseInt(horaCompleta.split(":")[1].split(" ")[0]);
                Time ordua=new Time(horaInt,minutosInt,0);
                //partidua.setOrduaString(horaCompleta);//horaInt+":"+minutosInt);
                partidua.setOrduaString(horaInt+":"+minutosInt+":0");
                partidua.setOrdua(ordua);
                
                Time erlojua=new Time(0,0,0);
                String reloj=partidoObjecto.get("clock").toString();
                if(reloj.contains(":")) {
                	erlojua.setMinutes(Integer.parseInt(reloj.split(":")[0]));
                	erlojua.setSeconds(Integer.parseInt(reloj.split(":")[1]));
                }
                partidua.setErlojua(erlojua);
                
                JSONObject periodObj = (JSONObject) partidoObjecto.get("period");
                partidua.setZatia(Integer.parseInt(periodObj.get("current").toString()));


                //VISITANTE
                JSONObject equipoVisitanteObj = (JSONObject) partidoObjecto.get("vTeam");
                Taldea taldea1 = new Taldea();taldea1.setIdTaldea(Integer.parseInt(equipoVisitanteObj.get("teamId").toString()));
                taldea1.setUrlIzena(equipoVisitanteObj.get("triCode").toString());
                partidua.setKanpoko_taldea(taldea1);
                if(partidua.getPartiduaren_egoera()==1) {
                	partidua.setKanpoko_puntuak(0);
                }else {
                	partidua.setKanpoko_puntuak(Integer.parseInt(equipoVisitanteObj.get("score").toString()));
                }


                //LOKAL
                JSONObject equipoLokalObj = (JSONObject) partidoObjecto.get("hTeam");
                Taldea taldea2 = new Taldea();taldea2.setIdTaldea(Integer.parseInt(equipoLokalObj.get("teamId").toString()));
                taldea2.setUrlIzena(equipoLokalObj.get("triCode").toString());
                partidua.setEtxeko_taldea(taldea2);
                if(partidua.getPartiduaren_egoera()==1) {
                	partidua.setEtxeko_puntuak(0);
                }else {
                	partidua.setEtxeko_puntuak(Integer.parseInt(equipoLokalObj.get("score").toString()));
                }
                
                partiduak.add(partidua);

            }

        }catch(Exception e){
            e.printStackTrace();
        }
		
		return partiduak;
	}
	public Partidua partiduarenInformazioa(Partidua partidua) {
		//HibernateDataAccess dbManager = new HibernateDataAccess();
		try {
			Partidua emaitza = new Partidua();
			emaitza.setIdPartidua(partidua.getIdPartidua());
			emaitza.setData(partidua.getData());
			emaitza.setKanpoko_taldea(partidua.getKanpoko_taldea());
			emaitza.setEtxeko_taldea(partidua.getEtxeko_taldea());
			emaitza.setOrdua(partidua.getOrdua());
			
			Calendar cal = Calendar.getInstance();
            cal.setTime(partidua.getData());
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH)+1;String monthString=month+"";if(month<10)monthString='0'+monthString;
            int day = cal.get(Calendar.DAY_OF_MONTH);String dayString=day+"";if(day<10)dayString='0'+dayString;
            String fechaString=year+monthString+dayString;
			
			JSONParser parser = new JSONParser();
			String partiduaID=partidua.getIdPartidua()+"";
			while(partiduaID.length()<10) {
				partiduaID="0"+partiduaID;
			}
			URL url = new URL(API_partiduaren_inf_helbidea.replace("DATA", fechaString).replace("GAMEID",partiduaID));
			System.out.println(API_partiduaren_inf_helbidea.replace("DATA", fechaString).replace("GAMEID",partiduaID));
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.connect();
            Object obj = parser.parse(new InputStreamReader(urlConnection.getInputStream()));
            JSONObject jsonObject = (JSONObject) obj;

            
            JSONObject basicGameDataOBJ = (JSONObject) jsonObject.get("basicGameData");
			
            emaitza.setPartiduaren_egoera(Integer.parseInt(basicGameDataOBJ.get("statusNum").toString()));
            
            Time erlojua=new Time(0,0,0);
            String reloj=basicGameDataOBJ.get("clock").toString();
            if(reloj.contains(":")) {
            	erlojua.setMinutes(Integer.parseInt(reloj.split(":")[0]));
            	erlojua.setSeconds(Integer.parseInt(reloj.split(":")[1]));
            }
            emaitza.setErlojua(erlojua);
            
            JSONObject period = (JSONObject) basicGameDataOBJ.get("period");
            emaitza.setZatia(Integer.parseInt(period.get("current").toString()));
            
            //KANPOKOA
            JSONObject vTeam = (JSONObject) basicGameDataOBJ.get("vTeam");
            JSONArray linescoreV = (JSONArray) vTeam.get("linescore");
            int suma=0;
            for(int i=0;i<linescoreV.size();i++){
                JSONObject marcador = (JSONObject) linescoreV.get(i);
                suma=suma+Integer.parseInt(marcador.get("score").toString());
                emaitza.kanpoko_puntuaketak_gehitu(Integer.parseInt(marcador.get("score").toString()));
            }emaitza.setKanpoko_puntuak(suma);
            
            //ETXEKOA
            JSONObject hTeam = (JSONObject) basicGameDataOBJ.get("hTeam");
            JSONArray linescoreH = (JSONArray) hTeam.get("linescore");
            suma=0;
            for(int i=0;i<linescoreH.size();i++){
                JSONObject marcador = (JSONObject) linescoreH.get(i);
                suma=suma+Integer.parseInt(marcador.get("score").toString());
                emaitza.etxeko_puntuaketak_gehitu(Integer.parseInt(marcador.get("score").toString()));
            }emaitza.setEtxeko_puntuak(suma);
            
            
            /*
             * ESTADISTIKAK & JOKALARIAK
             */
            JSONObject statsOBJ = (JSONObject) jsonObject.get("stats");
            
            JSONArray todosLosJugadores = (JSONArray) statsOBJ.get("activePlayers");
            for(int i=0;i<todosLosJugadores.size();i++){
                JSONObject jugadorOBJ = (JSONObject) todosLosJugadores.get(i);

                Jokalariaren_partidua jokalarien_partidua = new Jokalariaren_partidua();
                
                //dbManager
                Jokalaria jokalaria = new Jokalaria();
                jokalaria.setIdJokalaria(Integer.parseInt(jugadorOBJ.get("personId").toString()));
                jokalarien_partidua.setJokalaria(jokalaria);
                //jokalarien_partidua.setJokalaria(dbManager.jokalariaLortu(Integer.parseInt(jugadorOBJ.get("personId").toString())));
                if(!jugadorOBJ.get("points").toString().equals("")) {
                	jokalarien_partidua.setPuntuak(Integer.parseInt(jugadorOBJ.get("points").toString()));
                    jokalarien_partidua.setReboteak(Integer.parseInt(jugadorOBJ.get("totReb").toString()));
                    jokalarien_partidua.setAsistentziak(Integer.parseInt(jugadorOBJ.get("assists").toString()));
                    jokalarien_partidua.setLapurretak(Integer.parseInt(jugadorOBJ.get("steals").toString()));
                    jokalarien_partidua.setTapoiak(Integer.parseInt(jugadorOBJ.get("blocks").toString()));
                    jokalarien_partidua.setGaldutakoak(Integer.parseInt(jugadorOBJ.get("turnovers").toString()));
                    jokalarien_partidua.setFalta_pertsonalak(Integer.parseInt(jugadorOBJ.get("pFouls").toString()));
                    jokalarien_partidua.setPlus_minus(Integer.parseInt(jugadorOBJ.get("plusMinus").toString()));
                    
                    jokalarien_partidua.setSartutako_tiroak(Integer.parseInt(jugadorOBJ.get("fgm").toString()));
                    jokalarien_partidua.setSartutako_tiro_libreak(Integer.parseInt(jugadorOBJ.get("ftm").toString()));
                    jokalarien_partidua.setSartutako_tripleak(Integer.parseInt(jugadorOBJ.get("tpm").toString()));
                    jokalarien_partidua.setSaiatutako_tiroak(Integer.parseInt(jugadorOBJ.get("fga").toString()));
                    jokalarien_partidua.setSaiatutako_tiro_libreak(Integer.parseInt(jugadorOBJ.get("fta").toString()));
                    jokalarien_partidua.setSaiatutako_tripleak(Integer.parseInt(jugadorOBJ.get("tpa").toString()));
                    
                    int min=Integer.parseInt(jugadorOBJ.get("min").toString().split(":")[0]);
                    int seg=Integer.parseInt(jugadorOBJ.get("min").toString().split(":")[1]);
                    jokalarien_partidua.setMinutuak(min);
                    jokalarien_partidua.setSegunduak(seg);

                    int taldea_id=Integer.parseInt(jugadorOBJ.get("teamId").toString());
                    if(emaitza.getEtxeko_taldea().getIdTaldea()==taldea_id){
                    	emaitza.etxeko_jokalaria_gehitu(jokalarien_partidua);
                    }else{
                    	emaitza.kanpoko_jokalaria_gehitu(jokalarien_partidua);
                    }
                }
                
            }
            
			
			return emaitza;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
