package MAIN;
import java.util.*;
import java.io.*;
public class PlayerData {
    static String str;
    static int teamId1, teamId2;
    public record Data(int num, String name, String agent) {}
    List<Data> abc = List.to(
        new Data(1, "TYL Scales", "JETT")
    );
    public PlayerData(String abc) {
        str = abc;
        readPlayerList();
    }
    public static HashMap<String, String> AgentGUIDmap = new HashMap<>();
    static {
        AgentGUIDmap.put("add6443a-41bd-e414-f6ad-e58d267f4e95", "Jett");
        AgentGUIDmap.put("a3bfb853-43b2-7238-a4f1-ad90e9e46bcc", "Reyna");
        AgentGUIDmap.put("f94c3b30-42be-e959-889c-5aa313dba261", "Raze");
        AgentGUIDmap.put("7f94d92c-4234-0a36-9646-3a87eb8b5c89", "Yoru");
        AgentGUIDmap.put("eb93336a-449b-9c1b-0a54-a891f7921d69", "Phoenix");
        AgentGUIDmap.put("bb2a4828-46eb-8cd1-e765-15848195d751", "Neon");
        AgentGUIDmap.put("5f8d3a7f-467b-97f3-062c-13acf203c006", "Breach");
        AgentGUIDmap.put("6f2a04ca-43e0-be17-7f36-b3908627744d", "Skye");
        AgentGUIDmap.put("320b2a48-4d9b-a075-30f1-1f93a9b638fa", "Sova");
        AgentGUIDmap.put("601dbbe7-43ce-be57-2a40-4abd24953621", "Kayo");
        AgentGUIDmap.put("1e58de9c-4950-5125-93e9-a0aee9f98746", "Killjoy");
        AgentGUIDmap.put("117ed9e3-49f3-6512-3ccf-0cada7e3823b", "Cypher");
        AgentGUIDmap.put("569fdd95-4d10-43ab-ca70-79becc718b46", "Sage");
        AgentGUIDmap.put("22697a3d-45bf-8dd7-4fec-84a9e28c69d7", "Chamber");
        AgentGUIDmap.put("8e253930-4c05-31dd-1b6c-968525494517", "Omen");
        AgentGUIDmap.put("9f0d8ba9-4140-b941-57d3-a7ad57c6b417", "Brimstone");
        AgentGUIDmap.put("41fb69c1-4189-7b37-f117-bcaf1e96f1bf", "Astra");
        AgentGUIDmap.put("707eab51-4836-f488-046a-cda6bf494859", "Viper");
        AgentGUIDmap.put("dade69b4-4f5a-8528-247b-219e5a1facd6", "Fade");
        AgentGUIDmap.put("95b78ed7-4637-86d9-7e41-71ba8c293152", "Harbor");
        AgentGUIDmap.put("e370fa57-4757-3604-3648-499e1f642d3f", "Gekko");
        AgentGUIDmap.put("cc8b64c8-4b25-4ff9-6e7f-37b4da43d235", "Deadlock");
        AgentGUIDmap.put("0e38b510-41a8-5780-5e8f-568b2a4f2d6c", "Iso");
        AgentGUIDmap.put("1dbf2edd-4729-0984-3115-daa5eed44993", "Clove");
        AgentGUIDmap.put("efba5359-4016-a1e5-7626-b1ae76895940", "Vyse");
    }
    void readPlayerList() {
        int start = str.indexOf("\"teams\": ")+"\"teams\": [{".length();
        DataReader dr = new DataReader(str.substring(start));
        teamId1 = dr.keytoValue("{\"teamId\": {\"value\": ");
        teamId2 = dr.keytoValue("{\"teamId\": {\"value\": ");
        start = str.indexOf("\"players\": [");
        int end = str.indexOf("]",start);
        
        /*for(int i = 0;i<10;i++) {
            String selectedAgent = dr.largeKeytoValue("\"selectedAgent\": ", "\"guid\": \"").toString();
            String displayName = dr.keyValue("\"displayName\": \"").toString();
            PLAYERS.add(new Data(i+1, displayName, selectedAgent));
        }*/
    }
}