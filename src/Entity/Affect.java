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
public class Affect {
    private int idu;
    private int idm;

    public Affect() {
    }

    public Affect(int idu, int idm) {
        this.idu = idu;
        this.idm = idm;
    }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public int getIdm() {
        return idm;
    }

    public void setIdm(int idm) {
        this.idm = idm;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.idu;
        hash = 79 * hash + this.idm;
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
        final Affect other = (Affect) obj;
        if (this.idu != other.idu) {
            return false;
        }
        if (this.idm != other.idm) {
            return false;
        }
        return true;
    }
    
    
}
