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
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import models.Poste;
import services.posteService;

/**
 *
 * @author User
 */
public class AddPosteFrom  extends BaseForm{
      //Resources theme_1;
    public AddPosteFrom( Resources res){
        
         setUIID("SignIn");
       this.setTitle("add poste");
       this.setLayout(BoxLayout.y());
        TextField ttitre = new TextField("","insert title");
         TextField tsujet = new TextField("","sujet");
         TextField timage = new TextField("");
         Button uploadbtn=new Button("upload");
         uploadbtn.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
               Display.getInstance().openGallery(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (ev != null && ev.getSource() != null) {
                    String filePath = (String) ev.getSource();
                    int fileNameIndex = filePath.lastIndexOf("/") + 1;
                    String fileName = filePath.substring(fileNameIndex);
                    

                    Image img = null;
                    try {
                        img = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath));
                         System.out.println("houyeeeeme");
                    System.out.println(filePath);
                    InputStream stream = FileSystemStorage.getInstance().openInputStream(filePath);
                  OutputStream out = Storage.getInstance().createOutputStream("MyImage");
                   //ImageIO.getImageIO().save();
                Util.copy(stream, out);
                Util.cleanup(stream);
                Util.cleanup(out);
        // System.out.println(stream);
                    timage.setText(filePath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //MultiList photoList = findPhotoList();
                    Hashtable tableItem = new Hashtable();
                    tableItem.put("icon", img.scaled(Display.getInstance().getDisplayHeight() / 10, -1));
                    tableItem.put("emblem", fileName);
                    //photoList.getModel().addItem(tableItem);
                    // Do something, add to List
                }
            }
        }, Display.GALLERY_IMAGE);
               
           }
       });
                 
          TextField tgroupe = new TextField("","groupe");
          
       Button addBtn = new Button("add ");
       this.addAll(ttitre,tsujet,timage,tgroupe,addBtn,uploadbtn);
       addBtn.addActionListener((evt) -> {
            
            if (ttitre.getText().length() ==0 || tsujet.getText().length()==0|| timage.getText().length()==0|| tgroupe.getText().length()==0) {
                Dialog.show("Alert", "Textfields cannot be empty.",null, "OK");
            }else {
                
                
                    
                Poste poste = new Poste(ttitre.getText(), tsujet.getText(),timage.getText(),tgroupe.getText());
                    if (posteService.getInstance().addPosteAction(poste)) {
                        Dialog.show("Success", "Task added successfully.",null, "OK");
                    }
                    
               
                
                
                
            }
            
            
            
        });
      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeForm(res).showBack());
       
    }
    
}
