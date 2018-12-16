/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package four.designers.etl;

import java.util.Calendar;

/**
 *
 * @author Pan
 */
public class Timestamp {

    int giorno;
    int mese;
    int anno;
    int ora;
    int minuto;

    public Timestamp() {
        Calendar cal = Calendar.getInstance();
        giorno = cal.get(Calendar.DATE);
        mese = cal.get(Calendar.MONTH) + 1;
        anno = cal.get(Calendar.YEAR);
        ora = cal.get(Calendar.HOUR_OF_DAY);
        minuto = cal.get(Calendar.MINUTE);
    }

    public String getData() {
        return anno + "_" + mese + "_" + giorno;
    }

    public String getTimestamp() {
        return anno + "/" + mese + "/" + giorno + " " + ora + ":" + minuto;
    }
    
    public String getTimestampWithSeconds(){
        Calendar cal = Calendar.getInstance();
    return getTimestamp()+":"+cal.get(Calendar.SECOND);
    }
}
