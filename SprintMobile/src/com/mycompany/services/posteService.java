/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Map;
import models.Poste;
import utils.Statics;

/**
 *
 * @author User
 */
public class posteService {
    boolean resultOK;
    ConnectionRequest req;
    static posteService instance;
    ArrayList<Poste> post = new ArrayList<>();
    
    
    private posteService()
    {
        req = new ConnectionRequest();
    }
    
    
    public static posteService getInstance(){
        
        if (instance == null) {
            instance = new posteService();
        }
        
        return instance;
    }
    
    //ADD poste 
    public boolean addPosteAction(Poste p){
        
        String url = Statics.BASE_URL + "/addposteJson/new?titre="+ p.getTitre()+ "&sujet=" + p.getSujet()+"&image="+p.getImage()+"&groupe="+p.getGroupe();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((evt) -> {
            resultOK = req.getResponseCode()==200;
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    //PARSE TASKS JSON : convert JSON to java objects
    public ArrayList<Poste> parseJSONAction(String textJson){
        
        JSONParser j = new JSONParser();
        
        try {
            
            Map<String, Object> posteListJson = j.parseJSON(new CharArrayReader(textJson.toCharArray()));
            ArrayList<Map<String,Object>> postList = (ArrayList<Map<String,Object>>) posteListJson.get("root");
           
            for (Map<String, Object> obj : postList) {
                
                Poste t = new Poste();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setTitre(obj.get("titre").toString());
                t.setSujet(obj.get("sujet").toString());
                t.setImage(obj.get("image").toString());
                t.setGroupe(obj.get("groupe").toString());
                
                
                
                
                post.add(t);

            }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return post;  
    }



    //GET TASKS
    public ArrayList<Poste> getPoste(){
        
         String url = Statics.BASE_URL+"/displayposte/";
         ConnectionRequest request = new ConnectionRequest(url);
         request.setPost(false);
         request.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 
             post = parseJSONAction(new String(request.getResponseData()));
             request.removeResponseListener((ActionListener<NetworkEvent>) this);
             }
         });
         
        
        
        NetworkManager.getInstance().addToQueueAndWait(request);
        return post;
    }
    
}
