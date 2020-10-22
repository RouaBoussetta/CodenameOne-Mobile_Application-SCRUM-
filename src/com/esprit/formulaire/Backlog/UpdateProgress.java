/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.formulaire.Backlog;

import WebService.ServiceUserStory;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Lenovo
 */
public class UpdateProgress extends Form {
    
     public UpdateProgress (int idus , Form previous) {
        setTitle("Update Progress");
        setLayout(BoxLayout.y());
        //us.getClass().get
        TextField value = new TextField("","new Value");
      //  int val = Integer.parseInt(value.getText());
       
     
        
       // TextField tfStatus= new TextField("", "Status: 0 - 1");
        Button btnValider = new Button("Update ");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((value.getText().length()==0))  
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                
                else
                {
                    try {
                       // Theme t = new Theme (tfName.getText(),idpb);
                        if( ServiceUserStory.getInstance().UpdateProgressUserStory(idus,Integer.parseInt(value.getText())))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(value,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
}
