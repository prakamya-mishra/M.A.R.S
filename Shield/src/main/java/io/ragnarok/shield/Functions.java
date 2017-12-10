package io.ragnarok.shield;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.lang.String.*;

public class Functions {

    static int c = 0;

    public static ArrayList<String[]> addRandomSongs() {
        Scanner scanner = null;
        ArrayList<String[]> song_names = new ArrayList<String[]>();
        ArrayList<String[]> songs_random = new ArrayList<String[]>();
        try {
            scanner = new Scanner(new File("Songs_db.csv"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] columns = line.split(",");
                String[] temp = new String[2];
                temp[0] = columns[0];
                temp[1] = columns[1];
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
        for (int i = 0; i < 50; i++)
            songs_random_r.add(songs_random.get(i));
        return songs_random_r;
    }

    public static boolean authenticate(String username, String password) {
        Scanner scanner = null;
        boolean flag = false;
        int i;
        try {

            scanner = new Scanner(new File("User_db.csv"));
            ArrayList<String> rewrite = new ArrayList<String>();
            rewrite.add(scanner.nextLine());
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] columns = line.split(",");
                if (columns[0].equals(username)) {
                    if (columns[1].equals(password)) {
                        columns[2] = "1";
                        flag = true;
                    }
                } else {
                    columns[2] = "0";
                }
                line = columns[0];
                for (i = 1; i < columns.length; i++)
                    line += "," + columns[i];
                rewrite.add(line);

            }

            FileWriter writer = new FileWriter("User_db.csv");
            writer.append(rewrite.get(0));
            for (i = 1; i < rewrite.size(); i++) {
                writer.append("\n" + rewrite.get(i));
            }
            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    public static void signUp(String username, String password) {
        try {
            c = c + 1;
            FileWriter writer = new FileWriter("User_db.csv", true);
            String line = username + "," + password + "," + "0" + "0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0";
            if (c == 1) {
                writer.append(line);
            } else {
                writer.append("\n" + line);
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Like(String[] songInfo) {
        try {

            Scanner scannerUser = new Scanner(new File("User_db.csv"));
            Scanner scannerSongs = new Scanner(new File("Songs_db.csv"));
            ArrayList<String> userDbLines = new ArrayList<String>();
            userDbLines.add(scannerUser.nextLine());
            String loggedUserName = null,loggedUserPwd = null;
            String newSong;
            int logged = 0;
            while (scannerUser.hasNextLine()) {
                String line = scannerUser.nextLine();
                String[] columns = line.split(",");
                int loggedIn = Integer.parseInt(columns[2]);
                if(loggedIn == 1) {
                    loggedUserName = columns[0];
                    loggedUserPwd = columns[1];
                    logged = loggedIn;
                    break;
                }
            }
            while (scannerSongs.hasNextLine()) {
                String line = scannerSongs.nextLine();
                String[] columns = line.split(",");
                if(songInfo[0].equals(columns[0]) && songInfo[1].equals(columns[1])){
                    newSong = loggedUserName + "," + loggedUserPwd + "," + logged + "," + columns[0] + "," + columns[1] + "," + columns[2] + "," + columns[3] + "," + columns[4] + "," + columns[5] + "," + columns[6] + "," + columns[7] + "," + columns[8] + "," + columns[9] + "," + 1;
                    FileWriter writer = new FileWriter("User_db.csv", true);
                    writer.append("\n" + newSong);
                    writer.flush();
                    writer.close();
                    break;
                }
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void disLike(String[] songInfo) {
        try {

            Scanner scannerUser = new Scanner(new File("User_db.csv"));
            Scanner scannerSongs = new Scanner(new File("Songs_db.csv"));
            ArrayList<String> userDbLines = new ArrayList<String>();
            userDbLines.add(scannerUser.nextLine());
            String loggedUserName = null,loggedUserPwd = null;
            String newSong;
            int logged = 0;
            while (scannerUser.hasNextLine()) {
                String line = scannerUser.nextLine();
                String[] columns = line.split(",");
                int loggedIn = Integer.parseInt(columns[2]);
                if(loggedIn == 1) {
                    loggedUserName = columns[0];
                    loggedUserPwd = columns[1];
                    logged = loggedIn;
                    break;
                }
            }
            while (scannerSongs.hasNextLine()) {
                String line = scannerSongs.nextLine();
                String[] columns = line.split(",");
                if(songInfo[0].equals(columns[0]) && songInfo[1].equals(columns[1])){
                    newSong = loggedUserName + "," + loggedUserPwd + "," + logged + "," + columns[0] + "," + columns[1] + "," + columns[2] + "," + columns[3] + "," + columns[4] + "," + columns[5] + "," + columns[6] + "," + columns[7] + "," + columns[8] + "," + columns[9] + "," + 0;
                    FileWriter writer = new FileWriter("User_db.csv", true);
                    writer.append("\n" + newSong);
                    writer.flush();
                    writer.close();
                    break;
                }
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void recommendRefresh() {
        try {

            Scanner scannerUser = new Scanner(new File("User_db.csv"));
            Scanner scannerSongs = new Scanner(new File("Songs_db.csv"));
            ArrayList<String> userDbLines = new ArrayList<String>();
            userDbLines.add(scannerUser.nextLine());
            String loggedUserName = null,loggedUserPwd = null;
            int logged = 0;
            while (scannerUser.hasNextLine()) {
                String line = scannerUser.nextLine();
                String[] columns = line.split(",");
                int loggedIn = Integer.parseInt(columns[2]);
                if(loggedIn == 1) {
                    loggedUserName = columns[0];
                    loggedUserPwd = columns[1];
                    logged = loggedIn;
                    break;
                }
                System.out.println(loggedUserName);
            }

            List<Logistic.Instance> instances = Logistic.readDataSet("User_db.csv",loggedUserName);
            Logistic logistic = new Logistic(8);
            logistic.train(instances);
            int classify;
            FileWriter Recommended_db_writer = new FileWriter("Recommended_db.csv");
            Recommended_db_writer.append("user_name,user_pwd,logged_in,title,artist_name,artist.hottness,duration,familiarity,key,loudness,mode,tempo,time_signature,label");
            int c = 0;
            while (scannerSongs.hasNextLine()) {
                String line = scannerSongs.nextLine();
                c = c + 1;
                if(c>1) {
                    String[] columns = line.split(",");
                    for(int i = 2 ; i < columns.length-1;i++){

                    }
                    float[] x = {Float.parseFloat(columns[2]), Float.parseFloat(columns[3]), Float.parseFloat(columns[4]), Float.parseFloat(columns[5]), Float.parseFloat(columns[6]), Float.parseFloat(columns[7]), Float.parseFloat(columns[8]), Float.parseFloat(columns[9])};
                    for(float f:x){
                        System.out.print(f);
                    }
                    System.out.println();

                    classify = logistic.classify(x);
                    if (classify == 1) {
                        //System.out.println("\n" + loggedUserName + "," + loggedUserPwd + "," + logged + "," + (float) Float.parseFloat(columns[2]) + "," + (float) Float.parseFloat(columns[3]) + "," + (float) Float.parseFloat(columns[4]) + "," + (float) Float.parseFloat(columns[5]) + "," + (float) Float.parseFloat(columns[6]) + "," + (float) Float.parseFloat(columns[7]) + "," + (float) Float.parseFloat(columns[8]) + "," + (float) Float.parseFloat(columns[9]) + "," + classify);
                        Recommended_db_writer.append("\n" + loggedUserName + "," + loggedUserPwd + "," + logged + "," + (float) Float.parseFloat(columns[2]) + "," + (float) Float.parseFloat(columns[3]) + "," + (float) Float.parseFloat(columns[4]) + "," + (float) Float.parseFloat(columns[5]) + "," + (float) Float.parseFloat(columns[6]) + "," + (float) Float.parseFloat(columns[7]) + "," + (float) Float.parseFloat(columns[8]) + "," + (float) Float.parseFloat(columns[9]) + "," + classify);

                    }
                }
            }
            Recommended_db_writer.flush();
            Recommended_db_writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void songAdd(String[] songInfo) {
        try {

            Scanner scannerUser = new Scanner(new File("User_db.csv"));
            Scanner scannerSongs = new Scanner(new File("Songs_db.csv"));
            ArrayList<String> userDbLines = new ArrayList<String>();
            userDbLines.add(scannerUser.nextLine());
            String loggedUserName = null,loggedUserPwd = null;
            int logged = 0;
            while (scannerUser.hasNextLine()) {
                String line = scannerUser.nextLine();
                String[] columns = line.split(",");
                int loggedIn = Integer.parseInt(columns[2]);
                if(loggedIn == 1) {
                    loggedUserName = columns[0];
                    loggedUserPwd = columns[1];
                    logged = loggedIn;
                    break;
                }
            }
            FileWriter MySongs_db_writer = new FileWriter("MySongs_db.csv");
            MySongs_db_writer.append("user_name,user_pwd,logged_in,title,artist_name,artist.hottness,duration,familiarity,key,loudness,mode,tempo,time_signature,label");
            String newSong;
            while (scannerSongs.hasNextLine()) {
                String line = scannerUser.nextLine();
                String[] columns = line.split(",");
                if(songInfo[0].equals(columns[0]) && songInfo[1].equals(columns[1])){
                    newSong = loggedUserName + "," + loggedUserPwd + "," + logged + "," + columns[0] + "," + columns[1] + "," + columns[2] + "," + columns[3] + "," + columns[4] + "," + columns[5] + "," + columns[6] + "," + columns[7] + "," + columns[8] + "," + columns[9] + "," + columns[10];
                    MySongs_db_writer.append("\n"+newSong);
                    MySongs_db_writer.close();
                    MySongs_db_writer.flush();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static ArrayList<String[]> firstList(){

        try{
            Scanner scannerSongs = new Scanner(new File("Songs_db.csv"));
            ArrayList<String[]> allSongs = new ArrayList<>();
            int c = 50;
            scannerSongs.nextLine();
            while (scannerSongs.hasNextLine() && c>0) {
                c = c-1;
                String line = scannerSongs.nextLine();
                String[] columns = line.split(",");
                String[] songInfo = {columns[0],columns[1],null};
                allSongs.add(songInfo);
            }
            return allSongs;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<String[]> secondList(){

        try{
            Scanner scannerSongs = new Scanner(new File("Recommended_db.csv"));
            ArrayList<String[]> allSongs = new ArrayList<>();
            scannerSongs.nextLine();
            while (scannerSongs.hasNextLine()) {
                String line = scannerSongs.nextLine();
                String[] columns = line.split(",");
                String[] songInfo = {columns[0],columns[1],null};
                allSongs.add(songInfo);
            }
            return allSongs;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}

