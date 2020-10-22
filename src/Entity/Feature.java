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
public class Feature {
    int id_feature;
    String nom_feature;
    int id_user_respensability;
    int total_estimation_feature_jours;
    int statue;
    int id_theme;

    public Feature() {
    }

    public Feature(int id_feature, String nom_feature, int id_user_respensability, int total_estimation_feature_jours, int statue) {
        this.id_feature = id_feature;
        this.nom_feature = nom_feature;
        this.id_user_respensability = id_user_respensability;
        this.total_estimation_feature_jours = total_estimation_feature_jours;
        this.statue = statue;
    }

    public Feature(String nom_feature, int id_theme) {
        this.nom_feature = nom_feature;
        this.id_theme = id_theme;
    }

    public int getId_feature() {
        return id_feature;
    }

    public void setId_feature(int id_feature) {
        this.id_feature = id_feature;
    }

    public String getNom_feature() {
        return nom_feature;
    }

    public void setNom_feature(String nom_feature) {
        this.nom_feature = nom_feature;
    }

    public int getId_user_respensability() {
        return id_user_respensability;
    }

    public void setId_user_respensability(int id_user_respensability) {
        this.id_user_respensability = id_user_respensability;
    }

    public int getTotal_estimation_feature_jours() {
        return total_estimation_feature_jours;
    }

    public void setTotal_estimation_feature_jours(int total_estimation_feature_jours) {
        this.total_estimation_feature_jours = total_estimation_feature_jours;
    }

    public int getStatue() {
        return statue;
    }

    public void setStatue(int statue) {
        this.statue = statue;
    }

    public int getId_theme() {
        return id_theme;
    }

    public void setId_theme(int id_theme) {
        this.id_theme = id_theme;
    }

    @Override
    public String toString() {
        return "Feature{" + "id_feature=" + id_feature + ", nom_feature=" + nom_feature + ", id_user_respensability=" + id_user_respensability + ", total_estimation_feature_jours=" + total_estimation_feature_jours + ", statue=" + statue + '}';
    }
    
    
    
    
    
    
}
