package MAIN;

public class DataReader {
    static String str;
    public DataReader(String abc ) {
        str = abc;
    }
    public void playerDied() {
        /* System.out.println(str);
        System.out.println(keytoValue("\"deceasedId\": {\"value\": "));
        System.out.println(keytoValue("\"killerId\": {\"value\": "));
        System.out.println(assistants());
        System.out.println(largeKeytoValue("\"weapon\": ", "\"guid\": \""));//for weaponGUID */
    }
    public void abilityUsed() { 
        /*
        System.out.println(str);
        System.out.println(keytoValue("\"playerId\": {\"value\": "));
        System.out.println(largeKeytoValue("\"ability\": ","\"slot\": \""));//for abilitySLOT
        System.out.println(largeKeytoValue("\"currentGamePhase\": ", "\"roundNumber\": ")); */
    }
    public void damageEvent() {
        /*System.out.println(str);
        System.out.println(keyValue("\"location\": \""));
        System.out.println(keyValue("\"damageDealt\": "));
        if(str.indexOf("\"hazard\"")!=-1) {
            System.out.println(keytoValue("\"victimId\": {\"value\": "));
            System.out.println("Self Damage");
        }
        else {
            if(str.indexOf("\"weapon\"")!=-1)
                System.out.println("W+"+largeKeytoValue("\"weapon\": ", "\"guid\": \""));
            else if(str.indexOf("\"ability\"")!=-1)
                System.out.println("A+"+largeKeytoValue("\"ability\": ","\"slot\": \""));
            System.out.println(keytoValue("\"causerId\": {\"value\": "));
        }
        System.out.println(largeKeytoValue("\"currentGamePhase\": ", "\"roundNumber\": "));*/
    }
    public void configuration() {
        System.out.println(str);
        PlayerData pd = new PlayerData(str); 

         
    }
    String keyValue(String key) {
        int start = str.indexOf(key)+key.length();
        int end;
        if(str.indexOf("\"",start)==-1) end = str.indexOf("}",start);
        else if(str.indexOf("}",start)==-1) end = str.indexOf("\"",start);
        else end = str.indexOf("\"",start)>str.indexOf("}",start)?str.indexOf("}",start):str.indexOf("\"",start);
        String value = str.substring(start,end);
        str = str.replace(key+value,"");
        return value;
        
    }
    int keytoValue(String s) {
        int start = str.indexOf(s)+s.length();
        int end = str.indexOf("}",start);
        int value = Integer.parseInt(str.substring(start,end));
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
