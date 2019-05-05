/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codefest2019;

import java.io.BufferedReader;
/**
 *
 * @author Matthew
 */
public class TextScan {
    
    public static String stateCode(String s, BufferedReader in) throws Exception{
        
        try {      
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                if (inputLine.toLowerCase().contains(s.toLowerCase())) {
                    String[] split = inputLine.split("\\s+");
                    return split[2] + split[1];
                }
            
        } catch (Exception e) {
            throw new Exception("Location given is not valid");
            }
        throw new Exception("Location given is not valid");
        }
    
    public static String[] jobCode(String s, BufferedReader in) throws Exception{
        
        String res[] = new String[2];
        
        try {      
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.toLowerCase().contains(s.toLowerCase())) {
                    String[] split = inputLine.split("\\s+");
                    res[0] = split[0];
                    
                    int n = 1;
                    boolean display_code = true;
                    while (display_code) {
                        try {
                            int dc = Integer.parseInt(split[n]);
                            display_code = false;
                            res[1] = Integer.toString(dc);
                        } catch (Exception e) {
                            
                        }
                        n++;
                    }
                    return res;
                }
            }
        } catch (Exception e) {
            throw new Exception("Job given is not valid");
            }
        throw new Exception("Job given is not valid");
        }
    
}
