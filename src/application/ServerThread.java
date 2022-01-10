package application;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread{
    private Socket s;
    private int magicNumber;
    private int nbJoueur;
    static int winner;
    public static boolean end=false;
    public ServerThread(Socket s,int nbJoueur,int magicNumber) {
        this.s = s;
        this.nbJoueur = nbJoueur;
        this.magicNumber=magicNumber;
    }

    @Override
    public void run() {
        try {
            InputStream is=s.getInputStream();
            OutputStream os=s.getOutputStream();
            PrintWriter pr=new PrintWriter(os,true);
            InputStreamReader isr=new InputStreamReader(is);
            BufferedReader br=new BufferedReader(isr);
            pr.println("Bienvenue Joueur - "+nbJoueur);
            String msg;

            do{
                pr.println("Joueur " + nbJoueur + " : ");
                msg= br.readLine();
                int nb = Integer.parseInt(msg);
                if (nb < magicNumber) {
                    pr.println("Entrez un plus grand nombre!");
                } else if (nb > magicNumber) {
                    pr.println("Entrez un plus petit nombre!");
                } else if(nb==magicNumber) {
                    pr.println("Joueur " + nbJoueur + " a gagné!");
                    winner=nbJoueur;
                    end = true;
                }
            }while(msg!=null && Integer.parseInt(msg) != magicNumber && !end);

            pr.println("\nFin de la partie le joueur "+winner+" a gagné!");
            os.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
