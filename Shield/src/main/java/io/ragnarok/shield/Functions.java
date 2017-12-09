package io.ragnarok.shield;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.lang.String.*;

public class Functions {

    static int c=0;
    public static ArrayList<String[]> addRandomSongs() {
        Scanner scanner = null;
        ArrayList<String[]> song_names = new ArrayList<String[]>();
        ArrayList<String[]> songs_random = new ArrayList<String[]>();
        try {
            scanner = new Scanner(new File("Songs_db.csv"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] columns = line.split(",");
                String[] temp=new String[2];
                temp[0]=columns[0];
                temp[1]=columns[1];
                song_names.add(temp);

            }

            for (int i = 0; i < song_names.size(); i++) {
                ArrayList<Integer> temp = new ArrayList<Integer>();
                int j;
                Random rand = new Random();
                while (true) {
                    j = rand.nextInt(song_names.size());
                    if (temp.indexOf(j) == -1) {
                        songs_random.add(song_names.get(j));
                        temp.add(j);
                        break;
                    } else {
                        continue;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<String[]> songs_random_r = new ArrayList<String[]>();
        for (int i=0; i < 50 ; i++)
            songs_random_r.add(songs_random.get(i));
        return songs_random_r;
    }

    public static boolean authenticate(String username,String password)
    {
            Scanner scanner=null;
            boolean flag=false;
            int i;
            try{

                scanner = new Scanner(new File("User_db.csv"));
                ArrayList<String> rewrite=new ArrayList<String>();
                rewrite.add(scanner.nextLine());
                while (scanner.hasNextLine())
                {
                    String line=scanner.nextLine();
                    String[] columns = line.split(",");
                    if (columns[0].equals(username)){
                        if (columns[1].equals(password)) {
                            columns[2] = "1";
                            flag=true;
                        }
                    }
                    else{
                            columns[2] = "0";
                        }
                    line = columns[0];
                for(i =1;i<columns.length;i++)
                    line+= ","+columns[i];
                rewrite.add(line);

            }

            FileWriter writer=new FileWriter("User_db.csv");
            writer.append(rewrite.get(0));
            for(i=1;i<rewrite.size();i++)
            {
                writer.append("\n"+rewrite.get(i));
            }
            writer.flush();
            writer.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }

    public static void signUp(String username,String password)
    {
        try{
            c = c+1;
            FileWriter writer = new FileWriter("User_db.csv",true);
            String line=username+","+password+","+"0"+", , , , , , , , , , , ";
            if(c == 1){
                writer.append(line);
            }else{
                writer.append("\n"+line);
            }
            writer.flush();
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }




}

