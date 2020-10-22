/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.formulaire.Claims;

import Entity.Claim;
import Entity.Meeting;
import Entity.User;
import WebService.MeetingClaimService;
import WebService.MeetingService;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.esprit.formulaire.Meeting.MeetingDetail;
import com.esprit.formulaire.SideMenuBaseForm;

/**
 *
 * @author Lenovo
 */
public class Claims extends SideMenuBaseForm{
    
    Meeting m;

    public Claims(User u,Resources res) {
        
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
                                new Label("  Meeting  ", "WelcomeBlue"),
                                new Label("list", "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        
        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(separator);
        Container B=new Container();
        
  Button add=new Button("Create New Claim");
          add.setUIID("LoginButton");
add.addActionListener(new ActionListener() {

              @Override
              public void actionPerformed(ActionEvent evt) {
                  new AddClaim(u, res).show();
              }
          });
  B.add(add);
  add(B);
  
       
           for (Claim c  : MeetingClaimService.getInstance().getAllClaims()) {
            Container InfoContainer = new Container(BoxLayout.y());
Label l=new Label("Meeting Claim "+c.getId());
            Label date=new Label(c.getDate());
            
               System.out.println("testttt"+date);
            InfoContainer.addAll(l,date);
 
            
                       Container Container = new Container(BoxLayout.x());

       Container.add(res.getImage("claim2.png").scaled(150, 150));
       Container.add(InfoContainer);
       
      

            add(Container);
            
             date.addPointerPressedListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    
                    new ClaimDetails(c,u, res).show();
                    
                    
                }
            });
        }

        setupSideMenu(u,res);
        
     
    }

    
    
    
    @Override
    protected void showOtherForm(User u, Resources res) {
    }
    
}
