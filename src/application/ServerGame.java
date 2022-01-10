package application;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerGame {
    private static int nbJoueur;


    public static void main(String[] args) {
        try {
            ServerSocket ss=new ServerSocket(8009);
            int magicNumber = (int) (Math.random()*100);
            System.out.println("The secret number is : " + magicNumber);
            while(true && !ServerThread.end ){
                System.out.println("Attente d'une connexion d'un client");
                Socket s = ss.accept();
                nbJoueur++;
                ServerThread st=new ServerThread(s,nbJoueur,magicNumber);
                st.start();
                if(ServerThread.end == true){
                    ss.close();
                    break;
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
