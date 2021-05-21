/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Abonnement;
import com.mycompany.services.ServiceAbonnement;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author OMEN
 */
public class ListeAbonnementForm extends BaseForm {
 protected Form hi;
    Label nomprog;
    Label prix ;
    Label description;
    Label date_abonnement;
    Label datedebut;
    Label datefin;
Image img;
    public ListeAbonnementForm(Resources res) {
          super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Abonnement");
        
        getContentPane().setScrollVisible(true);
           Image img = res.getImage("font.png");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
           add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                        GridLayout.encloseIn(3,
                                FlowLayout.encloseCenter(
                                        new Label(res.getImage(""), "PictureWhiteBackgrond"))
                        )
                )
        ));
        setLayout(BoxLayout.y());
       // Container InfoContainer = new Container();
       Container c1 = new Container();
       Container c2 = new Container();
       super.addSideMenu( res);
        for (Abonnement f  : ServiceAbonnement.getInstance().getAllTheme()) 
        {
            Container InfoContainer = new Container(BoxLayout.y());
            Container InfoContainerr = new Container(BoxLayout.x());
            //BoxLayout InfoContainerRight = new BoxLayout(BoxLayout.Y_AXIS);
            Label nomf = new Label(f.getnomprog());
            Label description = new Label(String.valueOf(f.getdescription()));  
            
            
         /*    if  (f.getId_user_respensability()==0){
            Developpers dev=   ServiceDeveloppers.getInstance().finduser(f.getId_user_respensability());
            Label FirstNameDev = new Label(dev.getFirstname());
            Label LastNameDev = new Label(dev.getLastname());}
           
            */
            
          
           
            Label prix = new Label(String.valueOf(f.getprix()));  
            Label dateabonnement = new Label(String.valueOf(f.getdateAbonnement()));  
            
           
            
                Button btnaffect = new Button("Abonner" );
           btnaffect.addActionListener(e-> new PaiementForm(res).show());
            
            InfoContainer.add(nomf);
            InfoContainer.add(description);
//             if  (f.getId_user_respensability()!=0){
//            Developpers dev=   ServiceDeveloppers.getInstance().finduser(f.getId_user_respensability());
//            Label FirstNameDev = new Label(dev.getFirstname());
//            Label LastNameDev = new Label(dev.getLastname());
//                InfoContainer.add(FirstNameDev);
//            InfoContainer.add(LastNameDev);
//             }
           
          
            InfoContainer.add(prix);
            InfoContainer.add(dateabonnement);
          //  if (role == maser)
            //InfoContainerr.add(btnReadUserStory);
            //InfoContainerr.add(btnAddUserStory);
            // InfoContainerr.add(btnprogress);
              InfoContainerr.add(btnaffect);
           // c1.add(pb.getProductBacklog());
        InfoContainer.add(InfoContainerr);
        //    c2.add(pb.getIdBacklog());
        
        

        
       // SpanLabel sp = new SpanLabel();
        //sp.setText(productBacklogService.getInstance().getAllProductBacklog().toString());
     /*   
        Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Button name = new Button("", "Label");
    name.addActionListener(e -> Log.p("You clicked: " ));
    // ...
    Container b = BorderLayout.center(cnt).add(BorderLayout.EAST);
    b.add(name);
    
    
        */
        
        
       add(InfoContainer);
        }
        // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    

}


