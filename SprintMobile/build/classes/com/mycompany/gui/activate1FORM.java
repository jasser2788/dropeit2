/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ServiceClient;
import com.sun.mail.smtp.SMTPTransport;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Account activation UI
 *
 * @author Shai Almog
 */
public class activate1FORM extends BaseForm {
       TextField email;
    public activate1FORM(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
   setUIID("SignIn");

        
        add(BorderLayout.NORTH, 
                BoxLayout.encloseY(
                         new Label(res.getImage("oublier.png"), "LogoLabel"),
                        new Label("Mot de passe oublié", "LogoLabel")
                )
        );
        
       email = new TextField("", "Enter votre Email", 20, TextField.ANY);
        email.setSingleLineTextArea(false);
        
        Button Valider = new Button("Valider");
        
        
        Label HaveAnAccount = new Label("Retour se connecte?");
        Button signIn = new Button("Renouveler votre mot de passe");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("CenterLink");
        
        Container content = BoxLayout.encloseY(
                new FloatingHint(email),
               createLineSeparator(),
               
               Valider,
           
                FlowLayout.encloseCenter(HaveAnAccount, signIn)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        Valider.addActionListener(e->{
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDialog =ip.showInfiniteBlocking();
     SendMail(res);
     ipDialog.dispose();
     Dialog.show("Mot de passe","Nous avons envoyé le mot de passe à votre email: Veuillez verifier votre boite de reception ", new Command ("OK"));
     new SignInForm(res).show();
     refreshTheme();
        } );
        //signUp.addActionListener(e -> new NewsfeedForm(res).show());
    }
   public void SendMail(Resources res){
try{
    Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp"); 
		props.put("mail.smtps.host", "smtp.gmail.com"); 
		props.put("mail.smtps.auth", "true"); 
		
                Session session = Session.getInstance(props, null);
                MimeMessage msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress("Reinitialisation du mot de passe<monEmail@domaine.com>"));
		msg.setRecipients(Message.RecipientType.TO, email.getText().toString());
                msg.setSubject("Application Dropit:Confirmation du mot de ");
                 msg.setSentDate(new Date(System.currentTimeMillis()));
                 String mp= ServiceClient.getInstance().getPasswordByMail(email.getText().toString(), res);
                 String txt="Bienvenue sur Dropit : Tapez ce mot de passe "+mp+" dans le champ" ;
                 msg.setText(txt);
                 SMTPTransport st = (SMTPTransport) session.getTransport("smtps");
                 st.connect("smtp.gmail.com",465,"ons.trabelsi@esprit.tn","Ons9/6/2009");
                 st.sendMessage(msg, msg.getAllRecipients());
                 System.out.println("server response:"+st.getLastServerResponse());
                 
}catch(Exception e){
    e.printStackTrace();
}
}  
}
