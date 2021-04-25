/*package Gra;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Kalaha kalaha = new Kalaha();
        kalaha.printBoard();
        //kalaha.wykonajRuchGracz1(3);
        boolean czyZakonczono = false;
        while(!czyZakonczono) {
            Scanner scanner = new Scanner(System.in);
            boolean czyWykonanyRuch = false;
            while (!czyWykonanyRuch && !czyZakonczono) {
                System.out.println("\nGracz1: ");
                int scan = scanner.nextInt();
                czyWykonanyRuch = kalaha.wykonajRuchGracz(scan, 1);
                czyZakonczono = kalaha.sprawdzCzyKoniec();
                if(czyZakonczono) {
                    kalaha.zsumujKamyki();
                    break;
                }
                kalaha.printBoard();
            }

            czyWykonanyRuch = false;
            while (!czyWykonanyRuch && !czyZakonczono) {
                System.out.println("\nGracz2: ");
                int scan = scanner.nextInt();
                czyWykonanyRuch = kalaha.wykonajRuchGracz(scan, 2);
                czyZakonczono = kalaha.sprawdzCzyKoniec();
                if(czyZakonczono) {
                    kalaha.zsumujKamyki();
                    break;
                }
                kalaha.printBoard();
            }

        }
    }
}*/
