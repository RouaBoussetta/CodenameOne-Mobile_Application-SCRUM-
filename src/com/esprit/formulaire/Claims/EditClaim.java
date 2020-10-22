/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.formulaire.Claims;

import Entity.Claim;
import Entity.User;
import WebService.MeetingClaimService;
import WebService.MeetingService;
import com.codename1.components.ImageViewer;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.esprit.formulaire.Meeting.Meetings;
import com.esprit.formulaire.SideMenuBaseForm;

/**
 *
 * @author Lenovo
 */
public class EditClaim extends SideMenuBaseForm{

    public EditClaim(Claim c, User u,Resources res) {
        
          super( BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("bleu.png");        
        Image tintedImage = Image.createImage(profilePic.getWidth(), profilePic.getHeight());
        Graphics g = tintedImage.getGraphics();
        g.drawImage(profilePic, 0, 0);
        g.drawImage(res.getImage("gradient-overlay.png"), 0, 0, profilePic.getWidth(), profilePic.getHeight());
        
        tb.getUnselectedStyle().setBgImage(tintedImage);
        
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Button settingsButton = new Button("");
        settingsButton.setUIID("Title");
        FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_SETTINGS);
        
        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent = 
                BorderLayout.north(
                    BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)
                ).
                add(BorderLayout.CENTER, space).
                add(BorderLayout.SOUTH, 
                        FlowLayout.encloseIn(
                                new Label("Edit "+u.getUserName()+"'s claiming ", "WelcomeBlue")
//                                new Label("list", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        
        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(separator);
    
        
        
        ImageViewer img = new ImageViewer(res.getImage("claim1.png").scaled(500, 500));
        Label title = new Label("Phone number :");
        
        Label availability = new Label("Availability");
        Label other = new Label("Other:");
        Label reason = new Label("Reason:");
        Label date = new Label("Date:");
       
      
       Label l1=new Label(c.getTel());
       l1.setUIID("TextFieldF");
       Label l2=new Label(c.getAvailable());
       l2.setUIID("TextFieldF");
       Label l3=new Label(c.getOther());
       l3.setUIID("TextFieldF");
       Label l4=new Label(c.getReason());
       l4.setUIID("TextFieldF");
       Label l5=new Label(String.valueOf(c.getDate()));
       l5.setUIID("TextFieldF");
      
     TextField telField = new TextField(TextField.PHONENUMBER);
telField.setUIID("TextFieldF");
        telField.setText(c.getTel());

        TextField availabilityField = new TextField(TextArea.ANY);
        availabilityField.setUIID("TextFieldF");
        availabilityField.setText(c.getAvailable());
        TextField otherField = new TextField(TextArea.ANY);
        otherField.setUIID("TextFieldF");
        otherField.setText(c.getOther());
        TextField reasonField = new TextField(TextArea.ANY);
        reasonField.setUIID("TextFieldF");
        reasonField.setText(c.getReason());
        TextField dateField = new TextField(TextArea.ANY);
        dateField.setUIID("TextFieldF");
        dateField.setText(String.valueOf(c.getDate()));
     
        Container Container = new Container(BoxLayout.y());
        Container.addAll(title, telField, availability, availabilityField, other, otherField, reason,reasonField, date, dateField);
        add(img);
        add(Container);  
    
     Container ButtonContainer = new Container(new FlowLayout(Component.CENTER, Component.CENTER));

        Button Edit = new Button("Edit Claim");
        ButtonContainer.add(Edit);

        add(ButtonContainer);
        revalidate();
        
            Edit.addActionListener(ev -> {
            
            c.setTel(telField.getText());
            c.setAvailable(availabilityField.getText());
            c.setOther(otherField.getText());
            c.setReason(reasonField.getText());


            if (MeetingClaimService.getInstance().EditClaim(c)) {
                Dialog.show("Success", "Claim Edited Successfully", new Command("OK"));
                new Claims(u,res).showBack();
                
                              Message msg = new Message(u.getUserName()+"'s claiming "+"was edited now ");

Display.getInstance().sendMessage(new String[] {u.getUserMail()}, "Claim edit ", msg);
            
            } else {
                Dialog.show("ERROR", "Server error", new Command("OK"));
            }

        });
        
        
        
        setupSideMenu(u,res);

    }

    
    
    @Override
    protected void showOtherForm(User u, Resources res) {
     }
    
}
