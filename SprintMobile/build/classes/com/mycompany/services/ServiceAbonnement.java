/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entites.Abonnement;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author OMEN
 */
public class ServiceAbonnement {
      public static ServiceAbonnement instance=null ;
        public ArrayList<Abonnement> abonnements;
  private ConnectionRequest req ;
     public static ServiceAbonnement getInstance(){
    if (instance==null)
        instance =new ServiceAbonnement();
    return instance; 
}
      public ServiceAbonnement()
   {
       req=new ConnectionRequest();
   }
      
    public ArrayList<Abonnement> parseThemes(String jsonText){
        try {
           // tasks=new ArrayList<>();
          abonnements=new ArrayList<>();
            
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
              //  Task t = new Task();
             Abonnement t = new Abonnement();
                
              float id = Float.parseFloat(obj.get("id").toString());
     t.setid((int)id);
                //pb.(((int)Float.parseFloat(obj.get("status").toString())));
               // t.setNom_theme(jsonText); 
               // e(obj.get("nom_theme").toString());
           
           t.setnomprog(obj.get("nomprog").toString());
//                t.setdateAbonnement(( obj.get("date_abonnement").toString()));       
                   t.setprix(obj.get("prix").toString());
//                t.setdescription(obj.get("description").toString());
                
                // float totalEstim = Float.parseFloat(obj.get("total_estimation_theme_jours").toString());
               // t.setTotal_estimation_theme_jours((int)totalEstim);
                
               
                abonnements.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return abonnements;
    }
    
    public ArrayList<Abonnement> getAllTheme(){
     //   String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL +"liste" ;
        
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                abonnements = parseThemes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return abonnements;
    }
              
}