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
import models.wdata;
import utils.Statics;

/**
 *
 * @author User
 */
public class wdataService {
    boolean resultOK;
    ConnectionRequest req;
    static wdataService instance;
    ArrayList<wdata> data = new ArrayList<>();
    
    
    private wdataService()
    {
        req = new ConnectionRequest();
    }
    
    
    public static wdataService getInstance(){
        
        if (instance == null) {
            instance = new wdataService();
        }
        
        return instance;
    }
    
    //ADD poste 
    public boolean addPosteAction(wdata p){
        
        String url = Statics.BASE_URL + "/addWdataJson/new?id_client="+p.getId_client() +"&weight="+p.getWeight()+ "&height=" + p.getHeight()+"&waist="+p.getWaist()+"&neck="+p.getNeck()+"&hip="+p.getHip()+"&date="+p.getDate();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((evt) -> {
            resultOK = req.getResponseCode()==200;
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    //PARSE TASKS JSON : convert JSON to java objects
    public ArrayList<wdata> parseJSONAction(String textJson){
        
        JSONParser j = new JSONParser();
        
        try {
            
            Map<String, Object> posteListJson = j.parseJSON(new CharArrayReader(textJson.toCharArray()));
            ArrayList<Map<String,Object>> wdatalist = (ArrayList<Map<String,Object>>) posteListJson.get("root");
            
            for (Map<String, Object> obj : wdatalist) {
                
                wdata t = new wdata();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                float idc = Float.parseFloat(obj.get("id_client").toString());
                t.setId_client((int) idc);
                t.setWeight(Float.parseFloat(obj.get("weight").toString()));
                
               
               
                    t.setWaist(Float.parseFloat(obj.get("waist").toString()));
                t.setNeck(Float.parseFloat(obj.get("neck").toString()));
                t.setHip(Float.parseFloat(obj.get("hip").toString()));
                t.setHeight(Float.parseFloat(obj.get("height").toString()));
                   
               
               
                
                t.setDate(obj.get("date").toString());
                
                
                
                data.add(t);

            }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return data;  
    }



    //GET TASKS
    public ArrayList<wdata> getdata(){
        
         String url = Statics.BASE_URL+"/displayweight/";
         ConnectionRequest request = new ConnectionRequest(url);
         request.setPost(false);
         request.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 
             data = parseJSONAction(new String(request.getResponseData()));
             request.removeResponseListener((ActionListener<NetworkEvent>) this);
             }
         });
         
        
        
        NetworkManager.getInstance().addToQueueAndWait(request);
        return data;
    }
    
}
