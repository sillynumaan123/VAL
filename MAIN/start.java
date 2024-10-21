package MAIN;

import java.io.BufferedReader;
import java.io.FileReader; // Use FileReader to read files
import java.io.IOException;

class start { //{"platformGameId": "val:05c91cd4-ac8e-47c9-961d-503a4f2b7160",
    static StringBuilder str = new StringBuilder();
    static String gameId = "\"val:05c91cd4-ac8e-47c9-961d-503a4f2b7160\"";
    static String skp = "{\"platformGameId\": "+gameId+", \"";
    static String file = "GAME1.json";
    static boolean configCreated = false;
    void readFile(){
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String z;
            while((z = br.readLine())!=null)
                str.append(z);
        } catch(IOException ioe) { System.out.println("FILE NOT FOUND"); }
    }
    void evList() {
        int bCount = 0;
        for(int i = 0;i<str.length();i++) {
            if(str.charAt(i)=='{') {
                if(bCount==0) extractData(getEVName(i), i);
                bCount++;
            }
            if(str.charAt(i)=='}') 
                bCount--;
        }
    }
    public String getEVName(int index) {
        int start = index+skp.length();
        int end = str.indexOf("\"",start);
        String evName = str.substring(start, end);
        return evName;
    }

    public static int getLastIndexOf(int index) {
        int bCount = 0;
        for(int i = index;i<str.length();i++) {
            if(str.charAt(i)=='{') 
                bCount++;
            if(str.charAt(i)=='}') {
                bCount--;
                if(bCount==0) return i;
            }
        }
        return index;
    }

    void extractData(String name, int FI) {
        String[] req = {"playerDied", "abilityUsed", "damageEvent", "configuration"};
        int LI = getLastIndexOf(FI);
        DataReader datareader = new DataReader(str.substring(FI,LI));
        if(name.equals(req[0])) datareader.playerDied();
        if(name.equals(req[1])) datareader.abilityUsed();
        if(name.equals(req[2])) datareader.damageEvent();
        if(name.equals(req[3])&&configCreated==false) {
            datareader.configuration();
            configCreated = true;
        }           
    }

    public static void main(String args[]) {
        start ob = new start();
        ob.readFile();
        ob.evList();
    }
}
