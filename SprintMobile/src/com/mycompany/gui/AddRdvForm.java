/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import models.Rdv;
import services.RdvServices;
import com.codename1.components.ToastBar;
import com.codename1.components.ToastBar.Status;
import com.codename1.io.Properties;
import com.codename1.ui.Image;
import com.codename1.ui.util.Resources;
import com.codename1.components.ToastBar;
import com.codename1.components.ToastBar.Status;

/**
 *
 * @author jasser
 */
public class AddRdvForm extends Form{
    public Resources res ;
    public AddRdvForm()
    {
      this.setTitle("Add rendez vous");
       this.setLayout(BoxLayout.y());
        TextField tnom = new TextField("","nom");
         TextField tprenom = new TextField("","prenom");
         TextField tspecialite = new TextField("","specialite");
         Picker bdate = new Picker();
         TextField tmeet = new TextField("","meet");
         
         
         

       
          
       Button addBtn = new Button("add ");
       this.addAll(tnom,tprenom,tspecialite,bdate,tmeet,addBtn);
       addBtn.addActionListener((evt) -> {
            
            if (tnom.getText().length() ==0 || tprenom.getText().length()==0|| tspecialite.getText().length()==0|| tmeet.getText().length()==0) {
                Dialog.show("Alert", "Textfields cannot be empty.",null, "OK");
            }else {
                
                
                    
                Rdv rdv = new Rdv(tnom.getText(), tprenom.getText(),tspecialite.getText(),bdate.getDate().toString(),tmeet.getText());
                   if (RdvServices.getInstance().addRdvAction(rdv)) {
                        Dialog.show("Success", "Task added successfully.",null, "OK");
                        
                        
                        ToastBar.getInstance().setPosition(BOTTOM);
                      ToastBar.Status status = ToastBar.getInstance().createStatus();
 status.setShowProgressIndicator(true);
                    try {
                        status.setIcon(Image.createImage("/a.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                    } catch (IOException ex) {
                    }
                            status.setMessage("makch nrml");
                                                  status.setExpires(10000);  // only show the status for 3 seconds, then have it automatically clear

                      status.show();
               //  iDialog.dispose(); //NAHIW LOADING BAED AJOUT
                                 

                 refreshTheme();
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                    }
                    
               
                
                
                
            }
            
            
            
        });
    

       
       
      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeForm().showBack());
       
    }
    }
    
