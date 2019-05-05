
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.lang.Integer;
import java.util.Scanner;

import org.json.JSONObject;
/**
 *
 * @author Matthew
 */
public class GetSalary {

    public static int getSalary() throws Exception{

        // Get the code for the profession
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter an occupation:");
        String job = scan.nextLine();
        URL txt = new URL("https://download.bls.gov/pub/time.series/oe/oe.occupation");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(txt.openStream()));
        String[] jcode = TextScan.jobCode(job, in);
        in.close();

        // Prompts the user to enter a location depending on display code
        if (Integer.parseInt(jcode[1]) == 1 || Integer.parseInt(jcode[1]) == 2) {
            System.out.println("Enter 'National' (this occupation holds national data only): ");
        } else if (Integer.parseInt(jcode[1]) == 0 || Integer.parseInt(jcode[1]) == 3) {
            System.out.println("Enter 'National' or a state/city (no misspellings): ");
        }

        Scanner scan1 = new Scanner(System.in);
        String loc = scan1.nextLine();

        // Get the code for the city/state
        txt = new URL("https://download.bls.gov/pub/time.series/oe/oe.area");
        in = new BufferedReader(
                new InputStreamReader(txt.openStream()));
        String scode = TextScan.stateCode(loc, in);
        in.close();

        JSONObject json = SalaryAPI.salaryCall(scode, jcode[0]);
        String sal = json.getJSONObject("Results").getJSONArray("series").getJSONObject(0).getJSONArray("data").getJSONObject(0).getString("value");
        return Integer.parseInt(sal);
    }
}