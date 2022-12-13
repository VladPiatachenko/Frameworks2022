/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sumdu.edu.ua.studentweb.Support;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 *
 * @author Erlkonig
 */
public class Utils {
    public static String calculatePopularDomain(LinkedList<Student> studs) throws EmailException{
          Map<String,Integer> mails=new HashMap<String,Integer>(); 
          String tempDomain="";
          String popularDomain="";
          String pat="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$";
          int count=0;
          for(Student st:studs){
              if(Utils.patternMatches(st.getEmail(), pat)){
              tempDomain=Utils.getEmailDomain(st.getEmail());
              if(mails.containsKey(tempDomain)){count=mails.get(tempDomain)+1;}
              else{count=1;}
              mails.put(tempDomain,count);
            }
          else{throw new EmailException();}
          }
        Set<String> keys= mails.keySet();
        int max=0;
        for(String current:keys){
        if(max<mails.get(current)){
        max=mails.get(current);
        popularDomain=current;
        }
        }
        return popularDomain;
      }   
    public static double calculateMeanAge(LinkedList<Student> studs){
        double tempAge=0;
        int count=0;
        for(Student st:studs){
            int age=Integer.parseInt(st.getAge());
            if(age>0){
            tempAge+=age;
            count++;
            }
        }
        if(count==0){return -1;}
        return tempAge/count;
    }   
     public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
          .matcher(emailAddress)
          .matches();
    } 
     public static String getEmailDomain(String someEmail)
    {
        return  someEmail.substring(someEmail.indexOf("@") + 1);
    }
}
