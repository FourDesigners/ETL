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
public class WrongHeaderException extends Exception {

    private final String errore;

    public WrongHeaderException() {
        errore = "Esecuzione interrotta, intestazione non conforme al protocollo";
    }

    public WrongHeaderException(int i) {
        errore = "Esecuzione interrotta, il campo " + i + " dell'intestazione non è conforme al protocollo";
    }

    @Override
    public String toString() {
        return errore;
    }

}
