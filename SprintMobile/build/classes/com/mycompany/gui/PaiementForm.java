/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ServicePaiement;


/**
 *
 * @author OMEN
 */
public class PaiementForm extends BaseForm {
     public PaiementForm(Resources res) {
        super(new BorderLayout());

        if (!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout) getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
  super.addSideMenu( res); 
        String us = SessionManager.getNom();
        System.out.println(us);

        TextField nom = new TextField(us);
        nom.setUIID("TextFieldBlack");
      
           String us1 = SessionManager.getPrenom();
        System.out.println(us1);
        TextField prenom = new TextField(us1);
      
        prenom.setUIID("TextFieldBlack");

        

        
        TextField nameoncard = new TextField("", "Name on Card", 4, TextField.ANY);
        nameoncard.setUIID("TextFieldBlack");
        nameoncard.setAlignment(LEFT);
        TextField cardnumber = new TextField("", "Card number", 20, TextField.ANY);
        cardnumber.setUIID("TextFieldBlack");
        cardnumber.setAlignment(LEFT);
      cardnumber.setSingleLineTextArea(true);
          TextField securitycode = new TextField("", "Security Code", 20, TextField.ANY);
    securitycode.setUIID("TextFieldBlack");
        securitycode.setAlignment(LEFT);
      securitycode.setSingleLineTextArea(true);
        TextField expiration_date = new TextField("", "expiration date", 20, TextField.PASSWORD);
       expiration_date.setUIID("TextFieldBlack");
        expiration_date.setAlignment(LEFT);
      expiration_date.setSingleLineTextArea(true);
       
       
        Form previous = Display.getInstance().getCurrent();

       Button payer = new Button("payer");
        Button cancel = new Button("Cancel");
       
      cancel.addActionListener(e -> previous.showBack());
    cancel.setUIID("Link");
        
        
        Container content = BoxLayout.encloseY(
                new Label("Payer", "LogoLabel"),
                new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(prenom),
                createLineSeparator(),
              
               
              
                 new FloatingHint(nameoncard),
                createLineSeparator(),
                 new FloatingHint(expiration_date),
                createLineSeparator(),
                 new FloatingHint(cardnumber),
                createLineSeparator(),
                new FloatingHint(securitycode),
                createLineSeparator(),
                createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                payer,
                FlowLayout.encloseCenter( cancel)
        ));
        payer.requestFocus();
        payer.addActionListener((e)-> {
          
             ServicePaiement.getInstance().Paiement(nom, prenom, expiration_date, cardnumber, nameoncard, securitycode, res);
             
        
        });
    }
     private void addStringValue(String s, Component v) {
//        add(BorderLayout.west(new Label(s, "PaddedLabel")).
  //              add(BorderLayout.CENTER, v));
//        add(createLineSeparator(0xeeeeee));
    } 
  
}
