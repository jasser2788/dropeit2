/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ServiceClient;

import java.io.IOException;
import java.util.Vector;

/**
 * @author Mechlaoui
 */
public class ProfileForm extends BaseForm {

    private static String i;

    public ProfileForm( Resources res) {
     
      super("Newsfeed", BoxLayout.y());
      
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(true);

    

        tb.addSearchCommand(e -> {
        });

        Image img = res.getImage("font.png");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        //Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        //Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        //facebook.setTextPosition(BOTTOM);
        //twitter.setTextPosition(BOTTOM);
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                        GridLayout.encloseIn(3,
                                FlowLayout.encloseCenter(
                                        new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
                        )
                )
        ));

        Button modiff = new Button("Modifier");
    
        

     
        
     // Image pap = res.getImage("profile-pic.jpg");
     // pp.setIcon(pap);

      

        String us = SessionManager.getNom();
        System.out.println(us);

        TextField nom = new TextField(us);
        nom.setUIID("TextFieldBlack");
        addStringValue("nom", nom);
           String us1 = SessionManager.getPrenom();
        System.out.println(us1);
        TextField prenom = new TextField(us1);
        addStringValue("prenom", prenom);
        prenom.setUIID("TextFieldBlack");
int us2 = SessionManager.gettelephone();
        System.out.println(us2);

        TextField telephone = new TextField(us2);
      
        addStringValue("telephone", telephone);
         telephone.setUIID("TextFieldBlack");
        
        
        TextField password = new TextField(SessionManager.getPassowrd(), "password", 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        addStringValue("Password", password);

        TextField email = new TextField(SessionManager.getEmail(), "email", 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("email", email);
        
        
        
       
        
        
       
        modiff.setUIID("Edit");
      
        addStringValue("", modiff);
        
        TextField path = new TextField("");
        
            
        
        modiff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent edit) {
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog ipDlg    = ip.showInifiniteBlocking();
                ServiceClient.EditUser(nom.getText(), prenom.getText(),Integer.parseInt(telephone.getText()) , email.getText(), password.getText());
                SessionManager.setNom(nom.getText());
                SessionManager.setPrenom(prenom.getText());
                   SessionManager.settelephone(Integer.parseInt(telephone.getText()));
                SessionManager.setEmail(email.getText());
                SessionManager.setPassowrd(password.getText());
                Dialog.show("Succès", "Modifications des coordonnées avec succès", "Ok", null);
                ipDlg.dispose();
                refreshTheme();
            }
        });
        
    } 

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}