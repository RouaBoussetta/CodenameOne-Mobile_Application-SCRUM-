/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.formulaire.DailyScrum;

import Entity.DailyScrum;
import Entity.User;
import WebService.DailyScrumService;
import WebService.UserService;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.esprit.formulaire.SideMenuBaseForm;

/**
 *
 * @author Deku
 */
public class AddDailyScrum extends SideMenuBaseForm{
        public AddDailyScrum(User u,Resources res) {
           super( BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("bleu.png").scaled(120, 90);        
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
                                new Label("  New Daily Scum File  ", "WelcomeBlue")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        
        Label separator = new Label("", "BlueSeparatorLine");
        separator.setShowEvenIfBlank(true);
        add(separator);
        
        TextField title = new TextField(null,"Enter the Daily file title here !",20,TextField.ANY);
        title.setUIID("TextFieldF");
        
        TextField work = new TextField(null,"Enter the yesterday-work of the DailyScrum here !",20,TextField.ANY);
        work.setUIID("TextFieldF");

        TextField plan = new TextField(null,"Enter the Today-plan of the Daily Scrum here !",20,TextField.ANY);
        plan.setUIID("TextFieldF");
        
        TextField blockers = new TextField(null,"Enter the blockers of the Daily Scrum here !",20,TextField.ANY);
        blockers.setUIID("TextFieldF");
        
        TextField brunt = new TextField(null,"Enter the Brunt-Hours of the Daily Scrum here !",20,TextField.ANY);
        brunt.setUIID("TextFieldF");
        
        TextField completed = new TextField(null,"Enter the Completed-Hours of the Daily Scrum here !",20,TextField.ANY);
        completed.setUIID("TextFieldF");
        
        
        Picker date = new Picker();
        Picker time = new Picker();
        date.setType(Display.PICKER_TYPE_DATE);
        time.setType(Display.PICKER_TYPE_TIME);
  
 

      
        Button btnValider = new Button("Add");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

              
                if ((title.getText().length() == 0) || (work.getText().length() == 0) || (plan.getText().length() == 0) || (blockers.getText().length() == 0) || (brunt.getText().length() == 0) || (completed.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {

                        int hrbrunt=Integer.parseInt(brunt.getText());
                        int hrcompleted=Integer.parseInt(completed.getText());
                      
                        
                        String datestring = (new SimpleDateFormat("yyyy-MM-dd")).format(date.getDate());

                        DailyScrum d = new DailyScrum(title.getText(), work.getText(), plan.getText(), blockers.getText(), hrbrunt, hrcompleted ,datestring,time.getText(),datestring,time.getText());                         
                                
                        if (DailyScrumService.getInstance().AddDaily(d)) {
                            Dialog.show("Success", "Daily File Added", new Command("OK"));
                             
                              Message msg = new Message("New Daily File Added now by "+u.getUserName());

Display.getInstance().sendMessage(new String[] {"amirabbes43@gmail.com"}, "New Daily File Added ", msg);
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }

        });

        addAll(title, work, plan, blockers, brunt, completed,date,time, btnValider);
        setupSideMenu(u,res);
        
     
        
    }

    @Override
    protected void showOtherForm(User u, Resources res) {
    }
}
