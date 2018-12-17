/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etlScript;

import java.util.Objects;


/**
 *
 * @author FourDesigners
 */
public class Chiave {
    private final String annoIncidente;
    private final String comune;
    
    public Chiave(String annoIncidente, String comune) {
        this.annoIncidente = annoIncidente;
        this.comune = comune;
    }

    @Override
    public String toString() {
        return "Chiave{" + "annoIncidente=" + annoIncidente + ", comune=" + comune + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Chiave other = (Chiave) obj;
        if (!Objects.equals(this.annoIncidente, other.annoIncidente)) {
            return false;
        }
        if (!Objects.equals(this.comune, other.comune)) {
            return false;
        }
        return true;
    }
    
    
}
