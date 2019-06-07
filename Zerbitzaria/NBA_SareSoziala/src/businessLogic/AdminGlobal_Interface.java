package businessLogic;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import domain.Jardunaldia;
import domain.Komunitatea;

@WebService
public interface AdminGlobal_Interface {
	
	/*
	 * Administratzaile globala
	 */
	@WebMethod
	public void JokalarienInformazioaEguneratu();
	@WebMethod
	public void SoldatakEguneratu();
	@WebMethod
	public void TaldeenInformazioaEguneratu();
	
	@WebMethod
	public void MezuGeneralakBidali(String mezua);
	
	@WebMethod
	public List<Komunitatea> KomunitateakLortu();
	
	@WebMethod
	public void MerkatuenEgoeraEguneratu();
	@WebMethod
	public void MerkatuanJokalariakTxertatu(Komunitatea komunitatea);
	
	@WebMethod
	public boolean AlineazioakFinkatu();
	
	@WebMethod
	public void JardunaldiakHasieratu(int urtea,int hilabetea,int eguna);
	@WebMethod
	public List<Jardunaldia> JardunaldiakLortu();
	@WebMethod
	public boolean JardunaldikoPuntuakLortu(Jardunaldia jardunaldia);
	

}
