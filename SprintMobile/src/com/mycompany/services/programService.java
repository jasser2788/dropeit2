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
import models.Prog;
import utils.Statics;





/**
 *
 * @author asus
 */
public class programService {
   
    
    
   boolean resultOK;
    ConnectionRequest req;
    static programService instance;
    ArrayList<Prog> Prog = new ArrayList<>();  
    
     private programService()
    {
        req = new ConnectionRequest();
        
    }
    
    
    public static programService getInstance(){
        
        if (instance == null) {
            instance = new programService();
        }
        
        return instance;
    }
    
     public boolean addProgeAction(Prog p){
        
        String url = Statics.BASE_URL + "/programme/addProgramJSON/new?name="+ p.getName()+ "&type=" + p.getType()+"&image="+p.getImage()+"&days="+p.getDays();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((evt) -> {
            resultOK = req.getResponseCode()==200;
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
      //PARSE TASKS JSON : convert JSON to java objects
    public ArrayList<Prog> parseJSONAction(String textJson){
        
        JSONParser j = new JSONParser();
        
        try {
            
            Map<String, Object> posteListJson = j.parseJSON(new CharArrayReader(textJson.toCharArray()));
            ArrayList<Map<String,Object>> postList = (ArrayList<Map<String,Object>>) posteListJson.get("root");
           
            for (Map<String, Object> obj : postList) {
                
                Prog t = new Prog();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setName(obj.get("name").toString());
                t.setType(obj.get("type").toString());
                t.setImage(obj.get("image").toString());

                  float days = Float.parseFloat(obj.get("days").toString());
                             t.setDays((int)(days));
                             
                
               Prog.add(t);

            }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return Prog;  
    }
      
          //Delete 
    public boolean deleteprog(int id ) {
        String url = Statics.BASE_URL +"/programme/deleteProgramJSON/?id="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
    
    
    
    
     //GET Programme
    public ArrayList<Prog> getProg(){
        
         String url = Statics.BASE_URL+"/programme/p/displayprog/";
         ConnectionRequest request = new ConnectionRequest(url);
         request.setPost(false);
         request.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 
             Prog = parseJSONAction(new String(request.getResponseData()));
             request.removeResponseListener((ActionListener<NetworkEvent>) this);
             }
         });
      
    
    
    
   
        
         
         
         
         
         
         
         
         
         
         
        
        
        NetworkManager.getInstance().addToQueueAndWait(request);
        return Prog;
    }
    
    
    
}
