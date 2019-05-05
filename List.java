/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codefest2019;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 *
 * @author Matthew
 */
public class List {
    
    public static void ListProfessions() throws Exception {
        // Get the code for the profession
        URL txt = new URL("https://download.bls.gov/pub/time.series/oe/oe.area");
        BufferedReader in = new BufferedReader(
        new InputStreamReader(txt.openStream()));
        
        PrintWriter writer = new PrintWriter("locations.txt", "UTF-8");
        String inputLine;
        inputLine = in.readLine();
        
        while ((inputLine = in.readLine()) != null) {
                String[] split = inputLine.split("\\s+");
                String[] locArray = Arrays.copyOfRange(split, 3, split.length);
                String location = String.join(" ", locArray);
                writer.println(location);
        }
        
        writer.close();
        in.close();
    }
    
    public static void ListOccupations() throws Exception {
        // Get the code for the profession
        URL txt = new URL("https://download.bls.gov/pub/time.series/oe/oe.occupation");
        BufferedReader in = new BufferedReader(
        new InputStreamReader(txt.openStream()));
        
        PrintWriter writer = new PrintWriter("occupations.txt", "UTF-8");
        String inputLine;
        inputLine = in.readLine();
        
        while ((inputLine = in.readLine()) != null) {
                String[] split = inputLine.split("\\s+");
                String[] locArray = Arrays.copyOfRange(split, 0, (split.length - 3));
                locArray = Arrays.copyOfRange(locArray, 1, locArray.length);
                String occupation = String.join(" ", locArray);
                writer.println(occupation);
        }
        
        writer.close();
        in.close();
    }
    
}
