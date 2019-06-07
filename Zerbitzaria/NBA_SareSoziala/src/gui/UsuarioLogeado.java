package gui;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;

import businessLogic.UserLogged_Interface;
import businessLogic.UserNotLogged_Interface;
import configuration.ConfigXML;
import domain.Erabiltzailea;

public class UsuarioLogeado {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		ConfigXML conf=ConfigXML.getInstance();
		
		try{
			String usernotlogged= "http://localhost:9999/usernotlogged";
            URL url_usernotlogged = new URL(usernotlogged);
            QName qname = new QName("http://businessLogic/", "UserNotLogged_ImplementationService");
            Service service = Service.create(url_usernotlogged,qname);
            UserNotLogged_Interface userNotLogged_Interface = service.getPort(UserNotLogged_Interface.class);
            Erabiltzailea erabiltzailea=userNotLogged_Interface.ErabiltzaileaLogeatu("jongui2", "123456");
            

            String userlogged= "http://localhost:9999/userlogged";
            URL url_userlogged = new URL(userlogged);
            QName qname_userlogged = new QName("http://businessLogic/", "UserLogged_ImplementationService");
            Service service_userlogged = Service.create(url_userlogged,qname_userlogged);
            UserLogged_Interface appInterface = service_userlogged.getPort(UserLogged_Interface.class);

            appInterface.ErabiltzailearenJokalariakLortu(erabiltzailea);

        }catch (WebServiceException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

	}


}
