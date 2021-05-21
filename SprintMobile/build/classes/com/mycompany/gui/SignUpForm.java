/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import static com.codename1.io.Log.e;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ServiceClient;


/**
 *
 * @author OMEN
 */
public class SignUpForm extends BaseForm{
        public SignUpForm(Resources res) {
         
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
         TextField nom = new TextField("", "Nom", 20, TextField.ANY);
        nom.setUIID("TextFieldBlack");
        nom.setAlignment(LEFT);
        TextField prenom = new TextField("", "Prenom", 20, TextField.ANY);
        prenom.setUIID("TextFieldBlack");
        prenom.setAlignment(LEFT);
        TextField telephone = new TextField("", "Telephone", 20, TextField.ANY);
        prenom.setUIID("TextFieldBlack");
        prenom.setAlignment(LEFT);
       

        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        email.setAlignment(LEFT);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        password.setAlignment(LEFT);
      
      
   
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);

        Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
        
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(prenom),
                createLineSeparator(),
                new FloatingHint(telephone),
                createLineSeparator(),
               
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
              
                createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        next.addActionListener((e)-> {
          
             ServiceClient.getInstance().signup(nom, prenom, telephone, email, password,res);
              Dialog.show("success", "account is saved","OK",null);
        
        });
    }
    


 }