/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Lenovo
 */
public class UserStory {
    int id_user_story;
    String user_story_description;
    int doing;
    int total_estimation_userstory_jours;
    int priority;
    int id_feature;

    public UserStory() {
    }

    public UserStory(String user_story_description, int total_estimation_userstory_jours, int priority, int id_feature) {
        this.user_story_description = user_story_description;
        this.total_estimation_userstory_jours = total_estimation_userstory_jours;
        this.priority = priority;
        this.id_feature = id_feature;
    }
    

    public UserStory(int id_user_story, String user_story_description, int doing, int total_estimation_userstory_jours, int priority) {
        this.id_user_story = id_user_story;
        this.user_story_description = user_story_description;
        this.doing = doing;
        this.total_estimation_userstory_jours = total_estimation_userstory_jours;
        this.priority = priority;
    }

    public int getId_user_story() {
        return id_user_story;
    }

    public void setId_user_story(int id_user_story) {
        this.id_user_story = id_user_story;
    }

    public String getUser_story_description() {
        return user_story_description;
    }

    public void setUser_story_description(String user_story_description) {
        this.user_story_description = user_story_description;
    }

    public int getDoing() {
        return doing;
    }

    public void setDoing(int doing) {
        this.doing = doing;
    }

    public int getTotal_estimation_userstory_jours() {
        return total_estimation_userstory_jours;
    }

    public void setTotal_estimation_userstory_jours(int total_estimation_userstory_jours) {
        this.total_estimation_userstory_jours = total_estimation_userstory_jours;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getId_feature() {
        return id_feature;
    }

    public void setId_feature(int id_feature) {
        this.id_feature = id_feature;
    }

    @Override
    public String toString() {
        return "UserStory{" + "id_user_story=" + id_user_story + ", user_story_description=" + user_story_description + ", doing=" + doing + ", total_estimation_userstory_jours=" + total_estimation_userstory_jours + ", priority=" + priority + '}';
    }
    
    
    
    
}
