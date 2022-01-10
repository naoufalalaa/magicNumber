package application;

import java.io.*;
import java.net.Socket;

public class Joueur {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost",8009);
            OutputStream os=s.getOutputStream();
            PrintWriter pr=new PrintWriter(os,true);
            pr.println("Joueur En jeu ...");
            InputStream is=s.getInputStream();
            InputStreamReader isr=new InputStreamReader(is);
            BufferedReader br=new BufferedReader(isr);
            String magicNumber = br.readLine();
            System.out.println(magicNumber);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
