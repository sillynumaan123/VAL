package MAIN;
import java.util.*;
public class DataReader {
    static String str;
    public DataReader(String abc ) {
        str = abc;
    }
    public record PD_playerDied(String victim, String killer, String assistants, String weapon,String round) {}
    public static List<PD_playerDied> PD = new ArrayList<>();
    public record AU_abilityUsed(String user, String ability, String round) {}
    public static List<AU_abilityUsed> AU = new ArrayList<>();
    public record DE_damageEvent(String cause, String victim, String location, String AbWe, String amt, String round) {}
    public static List<DE_damageEvent> DE = new ArrayList<>();
    public void playerDied() {
        String PD1 = keytoValue("\"deceasedId\": {\"value\": ");
        String PD2 = keytoValue("\"killerId\": {\"value\": ");
        String PD3 = assistants();
        String PD4 = largeKeytoValue("\"weapon\": ", "\"guid\": \"");//for weaponGUID */
        String PD5 = largeKeytoValue("\"currentGamePhase\": ", "\"roundNumber\": ");
        PD.add(new PD_playerDied(PD1, PD2, PD3, PD4, PD5));
    }
    public void abilityUsed() {
        String AU1 = keytoValue("\"playerId\": {\"value\": ");
        String AU2 = largeKeytoValue("\"ability\": ","\"slot\": \"");//for abilitySLOT
        String AU3 = largeKeytoValue("\"currentGamePhase\": ", "\"roundNumber\": ");
        AU.add(new AU_abilityUsed(AU1, AU2, AU3));
    }
    public void damageEvent() {
        String DE1 = str.indexOf("\"hazard\"")!=-1?"Self":keytoValue("\"causerId\": {\"value\": ");
        String DE2 = keytoValue("\"victimId\": {\"value\": ");
        String DE3 = keyValue("\"location\": \"");
        String DE4 = str.indexOf("\"weapon\"")!=-1?largeKeytoValue("\"weapon\": ", "\"guid\": \""):largeKeytoValue("\"ability\": ","\"slot\": \"");
        String DE5 = keyValue("\"damageDealt\": ");
        String DE6 = largeKeytoValue("\"currentGamePhase\": ", "\"roundNumber\": ");
        DE.add(new DE_damageEvent(DE1, DE2, DE3, DE4, DE5, DE6));
    }
    public void configuration() {
        /* System.out.println(str);
        PlayerData pd = new PlayerData(str); 
        int start = str.indexOf("\"selectedMap\": ");
        start = str.indexOf("\"displayName\": \"",start)+"\"displayName\": \"".length();
        int end = str.indexOf("\"",start);
        String Map = str.substring(start,end);         */
    }
    String keyValue(String key) {
        int start = str.indexOf(key)+key.length();
        int end;
        if(str.indexOf("\"",start)==-1) end = str.indexOf("}",start);
        else if(str.indexOf("}",start)==-1) end = str.indexOf("\"",start);
        else end = str.indexOf("\"",start)>str.indexOf("}",start)?str.indexOf("}",start):str.indexOf("\"",start);
        String value = str.substring(start,end);
        str = str.replaceFirst(key+value,"");
        return value;
        
    }
    String keytoValue(String s) {
        int start = str.indexOf(s)+s.length();
        int end = str.indexOf("}",start);
        String value = str.substring(start,end);
        str = str.replace(s+value+"}","");
        return value;
    }
    static String largeKeytoValue(String external, String internal) {
        int start = str.indexOf(external)+external.length();
        int end = getLastIndexOf(str,start);
        String newstr = str.substring(start,end+1);
        start = newstr.indexOf(internal)+internal.length();
        if(newstr.indexOf("\"",start)==-1) end = newstr.indexOf("}",start);
        else if(newstr.indexOf("}",start)==-1) end = newstr.indexOf("\"",start);
        else end = newstr.indexOf("\"",start)>newstr.indexOf("}",start)?newstr.indexOf("}",start):newstr.indexOf("\"",start);
        String value = newstr.substring(start,end);
        str = str.replace(external+newstr+"}","");
        return value;
    }
    String assistants() {
        int start = str.indexOf("\"assistants\": [")+"\"assistants\": [".length();
        int end = str.indexOf("]",start);
        String newstr = str.substring(start,end);
        String assistants = "";
        while((newstr.indexOf("{\"assistantId\": {\"value\": ")!=-1)){
            start = newstr.indexOf("{\"assistantId\": {\"value\": ")+"{\"assistantId\": {\"value\": ".length();
            end = newstr.indexOf("}",start);
            if(assistants!="") assistants+=",";
            assistants+=newstr.substring(start,end);
            newstr = newstr.replace("{\"assistantId\": {\"value\": "+newstr.substring(start,end)+"}}","");
        }
        return "["+assistants+"]";
    }

    public static int getLastIndexOf(String st, int index) {
        int bCount = 0;
        for(int i = index;i<st.length();i++) {
            if(st.charAt(i)=='{') 
                bCount++;
            if(st.charAt(i)=='}') {
                bCount--;
                if(bCount==0) return i;
            }
        }
        return index+1;
    }
}
