/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Client;
import com.mycompany.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author OMEN
 */
public class ServicePaiement {
    
    
     public static ServicePaiement instance=null ;
    public static boolean resultOK= true ;
    private ConnectionRequest req ;
     String json;
      public ArrayList<Client> clients;
    public static ServicePaiement getInstance(){
    if (instance==null)
        instance =new ServicePaiement();
    return instance;
    }
   public ServicePaiement()
   {
       req=new ConnectionRequest();
   }
    
     public void Paiement (TextField nom,TextField prenom,TextField expiration_date,TextField card_number,TextField nameoncard,TextField security_code,Resources res
   )
   {
       String url=Statics.BASE_URL+"client/add2?nom="+nom.getText().toString()+"&prenom="+prenom.getText().toString()+"&expiration_date="+expiration_date.getText().toString()+"&card_number="+ card_number.getText().toString()
               +"&nameoncard="+nameoncard.getText().toString()+"&security_code="+security_code.getText().toString();
       req.setUrl(url);
       //controle saisi
       if(nom.getText().equals("")&&nom.getText().equals("")&&prenom.getText().equals("")&&expiration_date.getText().equals("")&&card_number.getText().equals("")&&nameoncard.getText().equals("")&&security_code.getText().equals("")){
           Dialog.show("Erreur","Veuillez remplir les champs vides ","Ok",null);
       }
       //hethi waçt tsir execution imte3 url
       req.addResponseListener((e)->{
           //njid el data illi 7atithom fil form
       byte[]data=(byte[])e.getMetaData();//na5o id imte3 kol textfield
       String responseData=new String (data); //ba3d ne5o content
       System.out.println("data===>"+responseData);
       
       
       
       });
       //ba3d execution imte3 requete
       NetworkManager.getInstance().addToQueueAndWait(req);
 
   }
    
}
