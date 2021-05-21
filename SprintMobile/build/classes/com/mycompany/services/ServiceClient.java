/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Properties;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.mycompany.utils.Statics;
import com.codename1.ui.util.Resources; 
import com.mycompany.entites.Abonnement;
import com.mycompany.entites.Client;
import com.mycompany.myapp.gui.ProfileForm;

import com.mycompany.myapp.gui.SessionManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author OMEN
 */
public class ServiceClient {
    public static ServiceClient instance=null ;
    public static boolean resultOK= true ;
    private ConnectionRequest req ;
     String json;
      public ArrayList<Client> clients;
    public static ServiceClient getInstance(){
    if (instance==null)
        instance =new ServiceClient();
    return instance;
    }
   public ServiceClient()
   {
       req=new ConnectionRequest();
   }
   public void signup (TextField nom,TextField prenom,TextField telephone,TextField email,TextField password,Resources res
   )
   {
       String url=Statics.BASE_URL+"client/add1?nom="+nom.getText().toString()+"&prenom="+prenom.getText().toString()+"&telephone="+telephone.getText()+"&email="+email.getText().toString()+"&password="+password.getText().toString();
       req.setUrl(url);
       //controle saisi
       if(nom.getText().equals("")&&nom.getText().equals("")&&prenom.getText().equals("")&&telephone.getText().equals("")&&email.getText().equals("")&&password.getText().equals("")){
           Dialog.show("Erreur","Veuillez remplir les champs vides ","Ok",null);
       
       } else if (nom.getText().equals("nom")) {
                            Dialog.show("Erreur", "Le nom ne doit pas être vide et doit être valide", "Ok", null);
                        } else if (prenom.getText().equals("prenom")) {
                            Dialog.show("Erreur", "Le prenom ne doit pas être vide et doit être valide", "Ok", null);
                        } else if (prenom.getText().equals("username")) {
                            Dialog.show("Erreur", "Le nom d'utilisateur doit être de cette forme : essai94", "Ok", null);
                        } else if (email.getText().equals("mail")) {
                            Dialog.show("Erreur", "Adresse e-mail incorrecte", "Ok", null);
                        } else if (password.getText().equals("password")) {
                            Dialog.show("Erreur", "Le mot de passe et sa confirmation ne doivent pas être différents", "Ok", null);
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
    public void signin (TextField email,TextField password,Resources res)
   
   {
       String url=Statics.BASE_URL+"/a/ne?email="+email.getText().toString()+"&password="+password.getText().toString();
       req.setUrl(url);
      
      
       req.addResponseListener((e)->{
           JSONParser j = new JSONParser();
           String json = new String(req.getResponseData())+"";
    try {
           if(json.equals("failed")){
       
       Dialog.show("Echec d'authentification","email ou mot de passe erroné ","Ok",null);
       } 
       else{
           System.out.println("data =="+json);
             
                   Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                
                SessionManager.setPassowrd(user.get("password").toString());
                SessionManager.setNom(user.get("nom").toString());
                SessionManager.setPrenom(user.get("prenom").toString());
                  // SessionManager.settelephone((int) user.get("telephone"));
                SessionManager.setEmail(user.get("email").toString());
                System.out.println("current user ==>"+ SessionManager.getEmail()+","+SessionManager.getPassowrd());
                if (user.size()>0)
                       new ProfileForm(res).show();
              
      }
        } catch (IOException ex) {
                  ex.printStackTrace();
               }
       });
       NetworkManager.getInstance().addToQueueAndWait(req);
}
   public String getPasswordByMail(String email , Resources res)
   {
    
      String url = Statics.BASE_URL+"/user/getPasswordByEmail?email="+email;
      req=new ConnectionRequest(url, false);
      req.setUrl(url);
      req.addResponseListener(( e)->{
      JSONParser j = new JSONParser();
     json = new String (req.getResponseData())+"";
      try{
       
       
        
             System.out.println("data=="+json);
             Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
             
        
      }catch(Exception ex){
       ex.printStackTrace();
      }
              
              
      
      
      });
      NetworkManager.getInstance().addToQueueAndWait(req);
      return json;
   } 
   
   public ArrayList<Client> parseClients(String jsonText){
        try {
           // tasks=new ArrayList<>();
         clients=new ArrayList<>();
            
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
              //  Task t = new Task();
             Client t = new Client();
                
                //float id = Float.parseFloat(obj.get("id_theme").toString());
                //t.setId_theme((int)id);
                //pb.(((int)Float.parseFloat(obj.get("status").toString())));
               // t.setNom_theme(jsonText); e(obj.get("nom_theme").toString());
                
               // t.setnomprog(obj.get("nomprog").toString());
                      t.setNom(obj.get("nom").toString());
                       // t.setprix(obj.get("prix").toString());
//                        t.setdescription(obj.get("description").toString());
                
                // float totalEstim = Float.parseFloat(obj.get("total_estimation_theme_jours").toString());
               // t.setTotal_estimation_theme_jours((int)totalEstim);
                
               
               clients.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return clients;
    }
    
    public ArrayList<Client> getAllClients(){
     //   String url = Statics.BASE_URL+"/tasks/";
    String url=Statics.BASE_URL+"/c?id="+SessionManager.getId();
        
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               clients = parseClients(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return clients;
    } 
    
    public static void EditUser(String nom, String prenom, int telephone, String email,String password){
        
    String url = Statics.BASE_URL+"/user/ediUser?nom="+nom+"&prenom="+prenom+"&telephone="+telephone+"&email="+email+"&password="+password;
                MultipartRequest req = new MultipartRequest();
                
                req.setUrl(url);
                req.setPost(true);
                req.addArgument("id", String.valueOf(SessionManager.getId()));
                req.addArgument("nom", nom);
                req.addArgument("prenom", prenom);
                 req.addArgument("telephone", Integer.toString(telephone));
                req.addArgument("password", password);
                req.addArgument("email", email);
                System.out.println(email);
                req.addResponseListener((response)-> {
                    
                    byte[] data = (byte[]) response.getMetaData();
                    String s = new String(data);
                    System.out.println(s);
                    if (s.equals("success")) {
                    }       
                });
                NetworkManager.getInstance().addToQueueAndWait(req);
    }
   
    
}