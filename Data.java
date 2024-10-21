package MAIN;
import java.util.*;

import MAIN.DataReader;

import java.io.*;
public class Data {
    public record Player1(int kills, int deaths, int assists) {}
    public record Player2(int kills, int deaths, int assists) {}
    public record Player3(int kills, int deaths, int assists) {}
    public record Player4(int kills, int deaths, int assists) {}
    public record Player5(int kills, int deaths, int assists) {}
    public record Player6(int kills, int deaths, int assists) {}
    public record Player7(int kills, int deaths, int assists) {}
    public record Player8(int kills, int deaths, int assists) {}
    public record Player9(int kills, int deaths, int assists) {}
    public record Player10(int kills, int deaths, int assists) {}
    public record Players(
        Player1 p1,
        Player2 p2,
        Player3 p3,
        Player4 p4,
        Player5 p5,
        Player6 p6,
        Player7 p7,
        Player8 p8,
        Player9 p9,
        Player10 p10
    ) {}
    public List<Players> p = new ArrayList<>();
    Data() {
        getAllData();
    }
    void getAllData() {
        int[] kills = new int[10];
        int[] deaths = new int[10];
        int[] assists = new int[10];
        for(int i = 0;i<DataReader.PD.size();i++) {
            kills[Integer.parseInt(DataReader.PD.get(i).killer())]+=1;
            deaths[Integer.parseInt(DataReader.PD.get(i).victim())]+=1;
            String[] assistants = DataReader.PD.get(i).assistants().split(",[]");
            for(String j : assistants) assists[Integer.parseInt(j)]+=1;
        }
        for(int i = 0 ;i<10;i++) {
            System.out.println(kills[i]);
        }
    }
}
