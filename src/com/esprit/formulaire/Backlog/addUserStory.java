/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.formulaire.Backlog;

import Entity.UserStory;
import WebService.ServiceUserStory;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Lenovo
 */
public class addUserStory extends Form {
    
     public addUserStory (int idf, Form previous) {
        setTitle("Add a new User Story");
        setLayout(BoxLayout.y());
        
        TextField USDescription = new TextField("","Feature Name");
        TextField totalEStimation= new TextField("", "Estimation Time");
         TextField Priority= new TextField("", "Priority: 1 - 3");
       
       
       
       
        Button btnValider = new Button("Add User Story");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((USDescription.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        UserStory us = new UserStory (USDescription.getText(),Integer.parseInt(totalEStimation.getText()),Integer.parseInt(Priority.getText()),idf);
                        if( ServiceUserStory.getInstance().addUserStory(us))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(USDescription,totalEStimation,Priority,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
