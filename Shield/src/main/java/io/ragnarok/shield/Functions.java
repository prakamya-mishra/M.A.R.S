package io.ragnarok.shield;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Functions {


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


}

