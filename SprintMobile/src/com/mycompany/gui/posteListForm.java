/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;

import models.Poste;
import services.posteService;

/**
 *
 * @author User
 */
public class posteListForm extends BaseForm{
   
    public posteListForm(Resources res){
        

        
       //setUIID("SignIn");
        // mine 
        ArrayList<Poste>p=posteService.getInstance().getPoste();
       
        this.setTitle("Poste List");
        this.setLayout(BoxLayout.y());
       
        
        for(int i=14;i<p.size();i++)
            
        {    
          
            try {
                
                 
                Image img;
                ImageViewer iv=new ImageViewer();
                EncodedImage ec;
                String url=p.get(i).getImage();
                ec=EncodedImage.create("/head.jpg");//image temporaire
                img =URLImage.createToStorage(ec, url, url,URLImage.RESIZE_SCALE_TO_FILL);
                iv.setImage(img);
               // Form cn=new Form("caption"+i+":"+p.get(i).getTitre());
                //cn.setLayout(BoxLayout.y());
               
                
                
                
                //Label ltitre=new Label();
                Label lgroupe=new Label();
                SpanLabel posteListSP = new SpanLabel();
                 SpanLabel posteListSP2 = new SpanLabel();
                //ltitre.setText(p.get(i).getTitre());
                  posteListSP2.setText("caption: "+p.get(i).getTitre());
                posteListSP.setText("subject: "+p.get(i).getSujet());
                lgroupe.setText(p.get(i).getGroupe());
                //cn.addAll(posteListSP,iv);
                this.addAll(posteListSP2,posteListSP,iv);
            }
            // SpanLabel posteListSP = new SpanLabel();
            // posteListSP.setText(p.toString());
            catch (IOException ex) {
                System.out.println("gui.posteListForm.<init>()");
            }
        }
        
       
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeForm(res).showBack());
    }
}
