/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Objects;

/**
 *
 * @author Lenovo
 */
public class Release {
    
    
      private int id;
    private String name;
    private String description;
    private String startDate;
    private String releaseDate;
    private int project;

    public Release() {
        
    }

    public Release(int id, String name, String description, String startDate, String releaseDate, int project) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.releaseDate = releaseDate;
        this.project = project;
    }
    public Release( String name, String description, String startDate, String releaseDate, int project) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.releaseDate = releaseDate;
        this.project = project;
    }
    public Release(String name, String description, String startDate, String releaseDate) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.description);
        hash = 67 * hash + Objects.hashCode(this.startDate);
        hash = 67 * hash + Objects.hashCode(this.releaseDate);
        hash = 67 * hash + this.project;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Release other = (Release) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        if (!Objects.equals(this.releaseDate, other.releaseDate)) {
            return false;
        }
        if (this.project != other.project) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Release{" + "id=" + id + ", name=" + name + ", description=" + description + ", startDate=" + startDate + ", releaseDate=" + releaseDate + ", project=" + project + '}';
    }

   

    
    
    
    
    
}
