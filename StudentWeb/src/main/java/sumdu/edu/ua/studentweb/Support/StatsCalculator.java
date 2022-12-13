/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sumdu.edu.ua.studentweb.Support;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Pattern;

/**
 *
 * @author Erlkonig
 */
public class StatsCalculator {
    private static StatsCalculator instance;//to perform Singleton
    
    private static double meanAge;
    private static int maxAge;
    private static int minAge;
    private static String popularMail;
    private static int sumduMails;
    
    public static StatsCalculator getInstance() {//to perform Singleton
        if(instance==null){instance=new StatsCalculator();}
        return instance;
    } 
   
    public double getMeanAge() {
        return meanAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public int getMinAge() {
        return minAge;
    }

    public String getPopularMailDomain() {
        return popularMail;
    }

    public int getSumduMails() {
        return sumduMails;
    }

    public static void setMeanAge(double meanAge) {
        StatsCalculator.meanAge = meanAge;
    }

    public static void setMaxAge(int maxAge) {
        StatsCalculator.maxAge = maxAge;
    }

    public static void setMinAge(int minAge) {
        StatsCalculator.minAge = minAge;
    }

    public static void setPopularMail(String popularMail) {
        StatsCalculator.popularMail = popularMail;
    }

    public static void setSumduMails(int sumduMails) {
        StatsCalculator.sumduMails = sumduMails;
    }
    
    
}
