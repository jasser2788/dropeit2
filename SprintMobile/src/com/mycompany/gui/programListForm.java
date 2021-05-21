/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import java.io.IOException;
import java.util.ArrayList;

import models.Prog;
import services.programService;
/**
 *
 * @author asus
 */
public class programListForm  extends Form{
    public programListForm(){
        ArrayList<Prog>p=programService.getInstance().getProg();
       
        this.setTitle("Program List");
        this.setLayout(BoxLayout.y());
       
        
        for(int i=0;i<p.size();i++)
            
        {    
          
            try {
                
                 
                Image img;
                ImageViewer iv=new ImageViewer();
                EncodedImage ec;
                String url=p.get(i).getImage();
                ec=EncodedImage.create("/head.jpg");//image temporaire
                img =URLImage.createToStorage(ec, url, url,URLImage.RESIZE_SCALE_TO_FILL);
                iv.setImage(img);
                Form cn=new Form("name"+i+":"+p.get(i).getName());
                cn.setLayout(BoxLayout.y());
               
                
                
                
                //Label ltitre=new Label();
                Label lname=new Label();
                SpanLabel posteListSP = new SpanLabel();
                //ltitre.setText(p.get(i).getTitre());
                posteListSP.setText(p.get(i).getType());
                lname.setText(p.get(i).getName());
                cn.addAll(posteListSP,iv);
                this.add(cn);
                
                
                
                
                
                
                
                
                
                
            }
            // SpanLabel posteListSP = new SpanLabel();
            // posteListSP.setText(p.toString());
            catch (IOException ex) {
                System.out.println("gui.programListForm.<init>()");
            }
  
        
        }
        
       
        
        
        
        
        
        
        
        
      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeForm().showBack());
}

}