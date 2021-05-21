/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;


/**
 *
 * @author User
 */
public class HomeForm extends BaseForm{
     public Resources theme2;
     public Resources theme3;
    
   public HomeForm(Resources res) {
         theme2 =UIManager.initFirstTheme("/theme"); 
       
        
       setUIID("SignIn");
        
       this.setTitle("Home");
       
      
       this.setLayout(BoxLayout.y());
       Button addposteBtn = new Button("add poste");
       Button listposteBtn = new Button("show poste");
        Button statBtn = new Button("add progress");
         Button statsBtn = new Button("show progress");
         Button profilbtn = new Button("show profile");
          Button abobtn = new Button("abonnement");
           Button progaddbtn = new Button(" add program");
            Button progshowbtn = new Button(" show program");
            Button recbtn = new Button(" reclamation");
            Button eventbtn = new Button(" event");
             Button rdvbtn = new Button(" reservation");
             Button rdvbtn2 = new Button(" show reservation");
       this.addAll(addposteBtn,listposteBtn,statBtn,statsBtn,profilbtn,abobtn,progaddbtn,progshowbtn,recbtn,eventbtn,rdvbtn,rdvbtn2);
       addposteBtn.addActionListener((p)->new AddPosteFrom( UIManager.initFirstTheme("/theme2")).show());
       listposteBtn.addActionListener(l->new posteListForm(UIManager.initFirstTheme("/theme2")).show());
       statBtn.addActionListener(l->new satitForm(UIManager.initFirstTheme("/theme2")).show());
       statsBtn.addActionListener(l->new statshowForm(UIManager.initFirstTheme("/theme2")).show());
       profilbtn.addActionListener(l->new ProfileForm(UIManager.initFirstTheme("/theme_1")).show());
       abobtn.addActionListener(l->new ListeAbonnementForm(res).show());
       progaddbtn.addActionListener(l->new addProgramForm(UIManager.initFirstTheme("/theme2")).show());
       progshowbtn.addActionListener(l->new programListForm(UIManager.initFirstTheme("/theme2")).show());
       recbtn.addActionListener(l->new AjoutReclamationForm(theme2).show());
        eventbtn.addActionListener(l->new afficherEvenement(UIManager.initFirstTheme("/theme2")).show());
         rdvbtn.addActionListener(l->new AddRdvForm(UIManager.initFirstTheme("/theme2")).show());
          rdvbtn2.addActionListener(l->new RdvListForm(theme2).show());
      // statBtn.addActionListener(s->new statForm().show());
       
       
   }
}
