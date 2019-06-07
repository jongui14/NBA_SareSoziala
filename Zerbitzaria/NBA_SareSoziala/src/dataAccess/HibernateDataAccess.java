package dataAccess;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

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
import domain.Taldea;
import domain.Transakzioa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class HibernateDataAccess {
	
	public static Set<Jokalaria> jokalariak=new HashSet<>();
	public static Set<Taldea> taldeak=new HashSet<>();

	
	/*
	 * KOMUNITATEA
	 */
	public Set<Komunitatea> getAllKomunitateak(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Set<Komunitatea> emaitza =  new HashSet<Komunitatea>();
		List results = session.createQuery("from Komunitatea").list(); 
		Iterator itr = results.iterator();
		while (itr.hasNext()){
			emaitza.add((Komunitatea)itr.next());
		}
		session.getTransaction().commit();
		return emaitza;
	}
	public void komunitateaGorde(Komunitatea komunitatea) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(komunitatea);
		session.getTransaction().commit();
	}
	public Komunitatea komunitateaLortu(int id) {
		try {	
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Komunitatea komunitatea = (Komunitatea)session.get(Komunitatea.class, id);
			session.getTransaction().commit();
			return komunitatea;
		}catch (Exception e){
			return null;
		}
	}
	public boolean KomunitateaLibre(String nick) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List results = session.createCriteria(Komunitatea.class).add(Restrictions.eq("nick", nick)).list(); 
		session.getTransaction().commit();
		if(results.size()==0) {
			return true;
		}
		return false;
	}
	public Komunitatea komunitateaLortu(String nick,String pasahitza) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List results = session.createCriteria(Komunitatea.class).add(Restrictions.eq("nick", nick)).add(Restrictions.eq("pasahitza", pasahitza)).list(); 
		session.getTransaction().commit();
		if(results.size()==0) {
			return null;
		}else {
			return (Komunitatea)results.get(0);
		}
	}
	
	
	
	/*
	 * ERABILTZAILEA
	 */
	public Set<Erabiltzailea> getAllErabiltzaileak(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Set<Erabiltzailea> emaitza =  new HashSet<Erabiltzailea>();
		List results = session.createQuery("from Erabiltzailea").list(); 
		Iterator itr = results.iterator();
		while (itr.hasNext()){
			emaitza.add((Erabiltzailea)itr.next());
		}
		session.getTransaction().commit();
		return emaitza;
	}
	public Set<Erabiltzailea> erabiltzaileakLortu(Komunitatea komunitatea){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Set<Erabiltzailea> emaitza =  new HashSet<Erabiltzailea>();
		List results = session.createCriteria(Erabiltzailea.class).add(Restrictions.eq("komunitatea", komunitatea)).list(); 
		Iterator itr = results.iterator();
		while (itr.hasNext()){
			emaitza.add((Erabiltzailea)itr.next());
		}
		session.getTransaction().commit();
		return emaitza;
	}
	public void erabiltzaileaGorde(Erabiltzailea erabiltzailea) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(erabiltzailea);
		session.getTransaction().commit();
	}
	public void erabiltzaileaEzabatu(Erabiltzailea erabiltzailea) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(erabiltzailea);
		session.getTransaction().commit();
	}
	public Erabiltzailea erabiltzaileaLortu(int id) {
		try {	
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Erabiltzailea erabiltzailea = (Erabiltzailea)session.get(Erabiltzailea.class, id);
			session.getTransaction().commit();
			return erabiltzailea;
		}catch (Exception e){
			return null;
		}
	}
	public Erabiltzailea erabiltzaileaLortu(String nick,String pasahitza) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List results = session.createCriteria(Erabiltzailea.class)
								.add(Restrictions.eq("nick", nick))
								.add(Restrictions.eq("pasahitza", pasahitza))
								.list(); 
		session.getTransaction().commit();
		if(results.size()==0) {
			return null;
		}else {
			return (Erabiltzailea)results.get(0);
		}
	}
	public boolean ErabiltzaileNickLibre(String nick) {System.out.println(nick);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List results = session.createCriteria(Erabiltzailea.class).add(Restrictions.eq("nick", nick)).list(); 
		session.getTransaction().commit();
		if(results.size()==0) {
			return true;
		}
		return false;
	}
	public boolean ErabiltzaileEmailLibre(String email) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List results = session.createCriteria(Erabiltzailea.class).add(Restrictions.eq("email", email)).list(); 
		session.getTransaction().commit();
		if(results.size()==0) {
			return true;
		}
		return false;
	}
	
	
	
	/*
	 * JOKALARIA
	 */
	public Set<Jokalaria> getAllJokalariak(){
		if(jokalariak.size()==0) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Set<Jokalaria> emaitza =  new HashSet<Jokalaria>();
			List results = session.createQuery("from Jokalaria").list(); 
			Iterator itr = results.iterator();
			while (itr.hasNext()){
				emaitza.add((Jokalaria)itr.next());
			}
			session.getTransaction().commit();
			jokalariak=emaitza;
			return emaitza;
		}else {
			return jokalariak;
		}
	}
	public void jokalariaGorde(Jokalaria jokalaria) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(jokalaria);
		session.getTransaction().commit();
	}
	public Set<Jokalaria> getErabiltzailearenJokalariak(Erabiltzailea era){
		Set<Jokalaria> emaitza =  new HashSet<Jokalaria>();
		//String sql="SELECT idJokalaria FROM erabiltzailearen_jokalaria WHERE idErabiltzailea="+era.getIdErabiltzailea();
		//Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		//session.beginTransaction();
		Erabiltzailea erabiltzailea=erabiltzaileaLortu(era.getIdErabiltzailea());
		System.out.println(erabiltzailea.getJokalarias().size());
		emaitza=erabiltzailea.getJokalarias();
		/*List<Object[]> list=session.createQuery(sql).list();  
		for(int i=0;i<list.size();i++) {
			Object[] object = list.get(i);
			Jokalaria jokalaria=jokalariaLortu(Integer.parseInt(object[0].toString()));
			emaitza.add(jokalaria);
		}*/
		//session.getTransaction().commit();
		return emaitza;
	}
	public Jokalaria jokalariaLortu(int id) {
		Iterator it=jokalariak.iterator();
		while(it.hasNext()) {
			Jokalaria jokalaria=(Jokalaria)it.next();
			if(jokalaria.getIdJokalaria()==id)return jokalaria;
		}
		try {	
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Jokalaria jokalaria = (Jokalaria)session.get(Jokalaria.class, id);
			session.getTransaction().commit();
			return jokalaria;
		}catch (Exception e){
			return null;
		}
	}
	public Set<Jokalaria> komunitatekoJokalariak(Komunitatea komunitatea){
		Set<Jokalaria> emaitza =  new HashSet<Jokalaria>();
		Iterator itr = erabiltzaileakLortu(komunitatea).iterator();
		while (itr.hasNext()){
			Erabiltzailea erabiltzailea= (Erabiltzailea)itr.next();
			Iterator itr2 = getErabiltzailearenJokalariak(erabiltzailea).iterator();
			while (itr2.hasNext()){emaitza.add((Jokalaria)itr2.next());}
		}
		Iterator itr3 = komunitatekoMerkatukoJokalariak(komunitatea).iterator();
		while(itr3.hasNext()) {
			MerkatukoJokalaria merk=(MerkatukoJokalaria)itr3.next();
			emaitza.add(merk.getJokalaria());
		}
		return emaitza;
	}
	public Set<Jokalaria> komunitatekoJokalariaLibreakLortu(Komunitatea komunitatea){
		Set<Jokalaria> emaitza =  new HashSet<Jokalaria>();
		Iterator it_komunitateko_jokalariak = komunitatekoJokalariak(komunitatea).iterator();
		Set<Integer> jokalarien_id=new HashSet<Integer>();
		while(it_komunitateko_jokalariak.hasNext()) {
			jokalarien_id.add(((Jokalaria)it_komunitateko_jokalariak.next()).getIdJokalaria());
		}
		
		Iterator itr = getAllJokalariak().iterator();
		while (itr.hasNext()){
			Jokalaria jokalaria = (Jokalaria)itr.next();
			if(!jokalarien_id.contains(jokalaria.getIdJokalaria()))
				emaitza.add(jokalaria);
		}
		return emaitza;
	}
	public Set<Jokalaria> komunitatekoMerkaturakoAukeratutakoJokalariak(Komunitatea komunitatea){
		Set<Jokalaria> res=new HashSet<Jokalaria>();
		int kop1=0,kop2=0,kop3=0,kop4=0;
		Set<Jokalaria> jokalariak = komunitatekoJokalariaLibreakLortu(komunitatea);
		for(Jokalaria jokalaria: jokalariak) {
			if(kop1==3 && kop2==2 && kop3==3 && kop4==4) {
				break;
			}
			if(kop1<3 &&jokalaria.getSoldata()> 25000000 && !res.contains(jokalaria)) {
				res.add(jokalaria);kop1++;
			}
			if(kop2<2 && jokalaria.getSoldata()> 15000000 && jokalaria.getSoldata()< 25000000 && !res.contains(jokalaria) ) {
				res.add(jokalaria);kop2++;
			}
			if(kop3<3 && jokalaria.getSoldata()> 5000000 && jokalaria.getSoldata()< 15000000 && !res.contains(jokalaria) ) {
				res.add(jokalaria);kop3++;
			}
		}	
		return res;
	}
	
	
	
	/*
	 * TALDEA
	 */
	public Set<Taldea> getAllTadeak(){
		if(taldeak.size()==0) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Set<Taldea> emaitza =  new HashSet<Taldea>();
			List results = session.createQuery("from Taldea").list(); 
			Iterator itr = results.iterator();
			while (itr.hasNext()){
				emaitza.add((Taldea)itr.next());
			}
			session.getTransaction().commit();
			taldeak=emaitza;
			return emaitza;
		}else {
			return taldeak;
		}
	}
	public void taldeaGorde(Taldea taldea) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(taldea);
		session.getTransaction().commit();
	}
	public Taldea taldeaLortu(int id) {
		Iterator it=taldeak.iterator();
		while(it.hasNext()) {
			Taldea taldea=(Taldea)it.next();
			if(taldea.getIdTaldea()==id)return taldea;
		}
		try {	
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Taldea taldea = (Taldea)session.get(Taldea.class, id);
			session.getTransaction().commit();
			return taldea;
		}catch (Exception e){
			return null;
		}
	}
	
	
	
	/*
	 * JARDUNALDIA
	 */
	public Set<Jardunaldia> getAllJardunaldiak(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Set<Jardunaldia> emaitza =  new HashSet<Jardunaldia>();
		List results = session.createQuery("from Jardunaldia").list(); 
		Iterator itr = results.iterator();
		while (itr.hasNext()){
			emaitza.add((Jardunaldia)itr.next());
		}
		session.getTransaction().commit();
		return emaitza;
	}
	public void jardunaldiaGorde(Jardunaldia objetua) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(objetua);
		session.getTransaction().commit();
	}
	
	
	
	/*
	 * MEZUA
	 */
	public void mezuaGorde(Mezua mezua) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(mezua);
		session.getTransaction().commit();
	}
	public void mezuaEzabatu(Mezua mezua) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(mezua);
		session.getTransaction().commit();
	}
	public Set<Mezua> getAllMezuak(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Set<Mezua> res =  new HashSet<Mezua>();
		List results = session.createQuery("from Mezua").list(); 
		Iterator itr = results.iterator();
		while (itr.hasNext()){
			res.add((Mezua)itr.next());
		}
		session.getTransaction().commit();
		return res;
	}
	public Set<Mezua> komunitatekoMezuak(Komunitatea komunitatea){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Set<Mezua> emaitza =  new HashSet<Mezua>();
		List results = session.createCriteria(Mezua.class).add(Restrictions.eq("komunitatea", komunitatea)).list(); 
		Iterator itr = results.iterator();
		while (itr.hasNext()){
			emaitza.add((Mezua)itr.next());
		}
		session.getTransaction().commit();
		return emaitza;
	}
	public Set<Mezua> erabiltzailearenMezuak(Erabiltzailea erabiltzailea){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Set<Mezua> emaitza =  new HashSet<Mezua>();
		List results = session.createCriteria(Erabiltzailea.class).add(Restrictions.eq("erabiltzailea", erabiltzailea)).list(); 
		Iterator itr = results.iterator();
		while (itr.hasNext()){
			emaitza.add((Mezua)itr.next());
		}
		session.getTransaction().commit();
		return emaitza;
	}
	public Mezua mezuaLortu(int id) {
		try {	
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Mezua res = (Mezua)session.get(Mezua.class, id);
			session.getTransaction().commit();
			return res;
		}catch (Exception e){
			return null;
		}
	}
	
	
	
	/*
	 * ALINEAZIOA
	 */
	public void alineazioaGorde(Alineazioa alineazioa) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			session.delete(alineazioa);
		}catch(Exception e) {
			e.printStackTrace();
		}
		session.saveOrUpdate(alineazioa);
		session.getTransaction().commit();
	}
	public Alineazioa erabiltzailearenAlineazioa(Erabiltzailea erabiltzailea) {
		Alineazioa alineazioa=new Alineazioa();
		try {	
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			List results = session.createCriteria(Alineazioa.class).add(Restrictions.eq("erabiltzailea", erabiltzailea)).list(); 
			Iterator itr = results.iterator();
			while (itr.hasNext()){
				Alineazioa alineazioaLag=(Alineazioa)itr.next();
				if(alineazioaLag.getId().getMota()==0)
					return alineazioaLag;
			}
			session.getTransaction().commit();
		}catch (Exception e){
			e.printStackTrace();
		}
		return alineazioa;
	}
	public Set<Alineazioa> erabiltzailearenAlineazioGuztiak(Erabiltzailea erabiltzailea) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Set<Alineazioa> emaitza =  new HashSet<Alineazioa>();
		List results = session.createCriteria(Alineazioa.class).add(Restrictions.eq("erabiltzailea", erabiltzailea)).list(); 
		Iterator itr = results.iterator();
		while (itr.hasNext()){
			emaitza.add((Alineazioa)itr.next());
		}
		session.getTransaction().commit();
		return emaitza;
	}
	public Set<Alineazioa> getAllAlineazioak(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Set<Alineazioa> emaitza =  new HashSet<Alineazioa>();
		List results = session.createQuery("from Alineazioa").list(); 
		Iterator itr = results.iterator();
		while (itr.hasNext()){
			emaitza.add((Alineazioa)itr.next());
		}
		session.getTransaction().commit();
		return emaitza;
	}
	public Set<Alineazioa> getAllAlineazioPublikoak(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Set<Alineazioa> emaitza =  new HashSet<Alineazioa>();
		List results = session.createQuery("from Alineazioa").list(); 
		Iterator itr = results.iterator();
		while (itr.hasNext()){
			Alineazioa alineazioa=(Alineazioa)itr.next();
			if(alineazioa.getId().getMota()==2)
				emaitza.add(alineazioa);
		}
		session.getTransaction().commit();
		return emaitza;
	}
	
	
	/*
	 * MerkatukoJokalaria
	 */
	public void merkatukoJokalariaGorde(MerkatukoJokalaria merkatukoJokalaria) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(merkatukoJokalaria);
		session.getTransaction().commit();
	}
	public MerkatukoJokalaria merkatukoJokalariaLortu(int id) {
		try {	
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			MerkatukoJokalaria merkatukoJokalaria = (MerkatukoJokalaria)session.get(MerkatukoJokalaria.class, id);
			session.getTransaction().commit();
			return merkatukoJokalaria;
		}catch (Exception e){
			return null;
		}
	}
	public Set<MerkatukoJokalaria> getAllMerkatukoJokalariak(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Set<MerkatukoJokalaria> emaitza =  new HashSet<MerkatukoJokalaria>();
		List results = session.createQuery("from MerkatukoJokalaria").list(); 
		Iterator itr = results.iterator();
		while (itr.hasNext()){
			emaitza.add((MerkatukoJokalaria)itr.next());
		}
		session.getTransaction().commit();
		return emaitza;
	}
	public Set<MerkatukoJokalaria> komunitatekoMerkatukoJokalariak(Komunitatea komunitatea){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Set<MerkatukoJokalaria> emaitza =  new HashSet<MerkatukoJokalaria>();
		List results = session.createCriteria(MerkatukoJokalaria.class).add(Restrictions.eq("komunitatea", komunitatea)).add(Restrictions.eq("tramitatua", false)).list(); 
		Iterator itr = results.iterator();
		while (itr.hasNext()){
			emaitza.add((MerkatukoJokalaria)itr.next());
		}
		session.getTransaction().commit();
		return emaitza;
	}

	
	
	
	/*
	 * PuntuazioaJokalariaJardunaldia
	 */
	public void puntuazioaJokalariaJardunaldiaGorde(PuntuazioaJokalariaJardunaldia puntuazioaJokalariaJardunaldia) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(puntuazioaJokalariaJardunaldia);
		session.getTransaction().commit();
	}
	public Set<PuntuazioaJokalariaJardunaldia> jokalariarenPuntuak(Jokalaria jokalaria) {
		Set<PuntuazioaJokalariaJardunaldia> emaitza =  new HashSet<PuntuazioaJokalariaJardunaldia>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List results = session.createCriteria(PuntuazioaJokalariaJardunaldia.class).add(Restrictions.eq("jokalaria", jokalaria)).list(); 
		Iterator itr = results.iterator();
		while (itr.hasNext()){
			emaitza.add((PuntuazioaJokalariaJardunaldia)itr.next());
		}
		session.getTransaction().commit();
		return emaitza;
	}
	public List<PuntuazioaJokalariaJardunaldia> jokalarienPuntuazioMaximoak() {
		List<PuntuazioaJokalariaJardunaldia> emaitza= new ArrayList<PuntuazioaJokalariaJardunaldia>();
		String sql="SELECT jok.idJokalaria,jok.izena,jok.abizena,jok.taldeaByIdTaldea.idTaldea,sum(punt.puntuak) "
					+ "FROM Jokalaria jok,PuntuazioaJokalariaJardunaldia punt "
					+ "where jok.idJokalaria=punt.jokalaria.idJokalaria "
					+ "GROUP BY jok.idJokalaria";
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Object[]> list=session.createQuery(sql).list();  
		for(int i=0;i<list.size();i++) {
			Object[] object = list.get(i);
			Taldea taldea=new Taldea();
			taldea.setIdTaldea(Integer.parseInt(object[3].toString()));
			
			Jokalaria jokalaria=new Jokalaria();
			jokalaria.setIdJokalaria(Integer.parseInt(object[0].toString()));
			jokalaria.setIzena(object[1].toString());
			jokalaria.setAbizena(object[2].toString());
			jokalaria.setTaldeaByIdTaldea(taldea);
			
			PuntuazioaJokalariaJardunaldia lag=new PuntuazioaJokalariaJardunaldia();
			lag.setJokalaria(jokalaria);
			lag.setPuntuak(Integer.parseInt(object[4].toString()));
			
			emaitza.add(lag);
		}
		session.getTransaction().commit();
		return emaitza;
	}
	
	
	
	/*
	 * PuntuazioaErabiltzaileaJardunaldia
	 */
	public void puntuazioaErabiltzaileaJardunaldiaGorde(PuntuazioaErabiltzaileaJardunaldia puntuazioaErabiltzaileaJardunaldia) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(puntuazioaErabiltzaileaJardunaldia);
		session.getTransaction().commit();
	}
	public Set<PuntuazioaErabiltzaileaJardunaldia> komunitatekoPuntuazioaErabiltzaileaJardunaldiaLortu(Komunitatea komunitatea){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Set<PuntuazioaErabiltzaileaJardunaldia> emaitza =  new HashSet<PuntuazioaErabiltzaileaJardunaldia>();
		Iterator erabiltzaileakIT = erabiltzaileakLortu(komunitatea).iterator();
		while(erabiltzaileakIT.hasNext()) {
			Erabiltzailea erabiltzailea= (Erabiltzailea) erabiltzaileakIT.next();
			List results = session.createQuery("FROM PuntuazioaErabiltzaileaJardunaldia").list();
			Iterator itr = results.iterator();
			while (itr.hasNext()){
				PuntuazioaErabiltzaileaJardunaldia lag =(PuntuazioaErabiltzaileaJardunaldia)itr.next();
				if(lag.getErabiltzailea().getIdErabiltzailea()==erabiltzailea.getIdErabiltzailea())
					emaitza.add(lag);
			}
		}		
		session.getTransaction().commit();
		return emaitza;
	}
	public PuntuazioaErabiltzaileaJardunaldia erabiltzailearenPuntuakJardunaldia(Erabiltzailea erabiltzailea,Jardunaldia jardunaldia) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List results = session.createCriteria(PuntuazioaErabiltzaileaJardunaldia.class).add(Restrictions.eq("erabiltzailea", erabiltzailea)).add(Restrictions.eq("jardunaldia", jardunaldia)).list(); 
		session.getTransaction().commit();
		if(results.size()==0) {
			PuntuazioaErabiltzaileaJardunaldia res=new PuntuazioaErabiltzaileaJardunaldia();
			res.setErabiltzailea(erabiltzailea);
			res.setJardunaldia(jardunaldia);
			res.setPuntuak(0);
			return res;
		}else {
			return ((PuntuazioaErabiltzaileaJardunaldia)results.get(0)).getKopia();
		}
	}
	public Set<PuntuazioaErabiltzaileaJardunaldia> erabiltzailearenPuntuak(Erabiltzailea erabiltzailea) {
		Set<PuntuazioaErabiltzaileaJardunaldia> emaitza =  new HashSet<PuntuazioaErabiltzaileaJardunaldia>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List results = session.createCriteria(PuntuazioaErabiltzaileaJardunaldia.class).add(Restrictions.eq("erabiltzailea", erabiltzailea)).list(); 
		Iterator itr = results.iterator();
		while (itr.hasNext()){
			emaitza.add((PuntuazioaErabiltzaileaJardunaldia)itr.next());
		}
		session.getTransaction().commit();
		return emaitza;
	}
	
	/*
	 * Eskaintza
	 */
	public void eskaintzaGorde(Eskaintza eskaintza) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(eskaintza);
		session.getTransaction().commit();
	}
	public Eskaintza eskaintzaLortu(int id) {
		try {	
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Eskaintza res = (Eskaintza)session.get(Eskaintza.class, id);
			session.getTransaction().commit();
			return res;
		}catch (Exception e){
			return null;
		}
	}
	public Set<Eskaintza> getJasotakoEskaintzak(Erabiltzailea erabiltzailea){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Set<Eskaintza> emaitza =  new HashSet<Eskaintza>();
		List results = session.createCriteria(MerkatukoJokalaria.class).add(Restrictions.eq("erabiltzaileaByIdErabiltzaileaJabea", erabiltzailea)).list(); 
		Iterator itr = results.iterator();
		while (itr.hasNext()){
			MerkatukoJokalaria merkatukoJokalaria=(MerkatukoJokalaria)itr.next();
			Iterator it2=merkatukoJokalaria.getEskaintzas().iterator();
			while(it2.hasNext())emaitza.add((Eskaintza)it2.next());					
		}
		session.getTransaction().commit();
		return emaitza;
	}
	public Set<Eskaintza> getEgindakoEskaintzak(Erabiltzailea erabiltzailea){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Set<Eskaintza> emaitza =  new HashSet<Eskaintza>();
		List results = session.createCriteria(Eskaintza.class).add(Restrictions.eq("erabiltzailea", erabiltzailea)).list(); 
		Iterator itr = results.iterator();
		while (itr.hasNext()){
			emaitza.add((Eskaintza)itr.next());
		}
		session.getTransaction().commit();
		return emaitza;
	}
	
	
	
	/*
	 * Eskaintza
	 */
	public void transakzioaGorde(Transakzioa transakzioa) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(transakzioa);
		session.getTransaction().commit();
	}
	public Set<Transakzioa> getErabiltzailearenTransakzioak(Erabiltzailea erabiltzailea){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Set<Transakzioa> emaitza =  new HashSet<Transakzioa>();
		List results = session.createCriteria(Transakzioa.class).add(Restrictions.eq("erabiltzailea", erabiltzailea)).list(); 
		Iterator itr = results.iterator();
		while (itr.hasNext()){
			Transakzioa transakzioa=(Transakzioa)itr.next();
			emaitza.add(transakzioa);
		}
		session.getTransaction().commit();
		return emaitza;
	}
	
	
}
