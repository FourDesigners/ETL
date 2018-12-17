/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package four.designers.etl;

import com.sun.glass.ui.SystemClipboard;
import etlScript.MainETL;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pan
 */
public class Main {

    public static void main(String[] args) {
        System.out.print(Utility.LOGO);

        Scanner scanner = new Scanner(System.in);
        int s=0;
        String comando = null;
        if (args.length >= 1) {
            comando = args[0];
        }
        do {
            try {
                Thread.sleep(1000);
                if (comando != null && comando.equals("default")) {
                    s = 1;
                } else {
                    System.out.print("\nElenco comandi: \n");
                    System.out.print("Comando: (1) eseguire procedura ETL con impostazioni di default\n");
                    System.out.print("Comando: (2) eseguire la procedura ETL selezionando un file\n");
                    System.out.print("Comando: (3) esegue i test sulla procedura\n");
                    System.out.print("Comando: (0) Chiudi la procedura\n");
                    System.out.print("\nInserisci un comando: ");
                    s = scanner.nextInt();
                }
                switch (s) {
                    case 1:
                        MainETL.main(new String[1]);
                        s = 0;
                        break;
                    case 2:
                        System.out.print("\nInserisci il file da utilizzare: ");
                        Scanner scanner2 = new Scanner(System.in);
                        String s2 = scanner2.nextLine();
                        File tmpDir = new File(s2);
                        boolean exists = tmpDir.exists();
                        if (exists) {
                            String[] path = {s2};
                            MainETL.main(path);
                            s = 0;
                        } else {
                            System.out.print("\nIl file che hai indicato non esiste o non è nel path corrente.\n\n");
                        }
                        break;
                    case 3:
                    case 0:
                        System.out.print("\nChiusura procedura.\n\n");
                        break;
                    default:
                        System.out.print("\nERRORE comando non valido.\nInserire un comando valido.\n\n");

                }

            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

        } while (s != 0);
        System.out.println("\nPremere Invio per chiudere la procedura.....");
        String line = scanner.nextLine();
    }
}
