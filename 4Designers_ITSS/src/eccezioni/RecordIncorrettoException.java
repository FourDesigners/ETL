/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eccezioni;

/**
 *
 * @author FourDesigners
 */
public class RecordIncorrettoException extends Exception {

    String errore;

    public RecordIncorrettoException() {
        errore = "Record incorretto";
    }

    public RecordIncorrettoException(String nomeCampo) {
        errore = "Record incorretto, errore nel campo " + nomeCampo;
    }

    @Override
    public String toString() {
        return errore;
    }
}
