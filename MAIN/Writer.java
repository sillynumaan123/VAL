package MAIN;
import java.util.*;
import java.io.*;
public class Writer {
    String str;
    try(PrintWriter pw = new PrintWriter("Output.txt")) {}
    catch(IOException ioe) { System.out.println("CANNOT WRITE"); }
    public Writer(String ab) {
        str = ab;
    }
    void Write() {
        
    }
}