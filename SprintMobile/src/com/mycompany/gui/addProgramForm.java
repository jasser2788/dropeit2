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
import com.codename1.ui.list.MultiList;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import models.Prog;
import services.programService;


/**
 *
 * @author User
 */
public class addProgramForm  extends Form{
    public addProgramForm(){
       this.setTitle("add program");
       this.setLayout(BoxLayout.y());
        TextField tname = new TextField("","insert name");
         TextField ttype = new TextField("","type");
         
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
       
 TextField tdays = new TextField("","days");
          
       Button addBtn = new Button("add ");
       this.addAll(tname,ttype,timage,tdays,addBtn,uploadbtn);
       addBtn.addActionListener((evt) -> {
            
            if (tname.getText().length() ==0 || ttype.getText().length()==0|| timage.getText().length()==0|| tdays.getText().length()==0) {
                Dialog.show("Alert", "Textfields cannot be empty.",null, "OK");
            }else {
                
                
                    Prog prog= new Prog(tname.getText(),ttype.getText(), timage.getText(),Integer.parseInt(tdays.getText()));
                if(programService.getInstance().addProgeAction(prog))
                {
                 Dialog.show("Success", "program added successfully.",null, "OK");
                
                }
                    
               
                
                
                
            }
            
            
            
        });
       
         

 
       
       
       
      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeForm().showBack());
       
    }
    
}
