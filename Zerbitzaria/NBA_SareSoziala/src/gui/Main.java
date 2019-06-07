package gui;

import java.net.URL;

import javax.xml.namespace.QName;

import businessLogic.UserNotLogged_Interface;
import configuration.ConfigXML;
import domain.Erabiltzailea;
import domain.Komunitatea;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		ConfigXML conf=ConfigXML.getInstance();
		
		try{

            String serviceName= "http://localhost:9999/usernotlogged";

            System.out.println(serviceName);
            URL url = new URL(serviceName);

            QName qname = new QName("http://businessLogic/", "UserNotLogged_ImplementationService");

            Service service = Service.create(url,qname);

            UserNotLogged_Interface appInterface = service.getPort(UserNotLogged_Interface.class);

            System.out.println(appInterface.ErabiltzaileNickLibre("jongui"));
            System.out.println(appInterface.ErabiltzaileNickLibre("jongui1"));
            Komunitatea komunitatea = appInterface.KomunitateaAukeratu("ugari", "123456");
            System.out.println(komunitatea.getIdKomunitatea());
            Erabiltzailea erabiltzailea = appInterface.ErabiltzaileaLogeatu("jongui", "123456");
            System.out.println(erabiltzailea.getIdErabiltzailea());
            System.out.println(erabiltzailea.getKomunitatea());

        }catch (WebServiceException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

	}

}
