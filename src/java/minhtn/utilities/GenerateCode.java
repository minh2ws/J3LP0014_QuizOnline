/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtn.utilities;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 *
 * @author minhv
 */
public class GenerateCode {
    //create OrderId with format YYYYMMdd-hhmm-xxxxxx (x is subject Id)
    public static String generateQuestionID(String subID) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMdd-hhmmss");
        java.util.Date date = new java.util.Date();
        
        String dateGetted = dateFormat.format(date);
        
        return dateGetted + "-" + subID;
    }
    
    //create OrderId with format YYYYMMdd-hhmm-xxxxxx-yyy (x is subject Id, y is random number)
    public static String generateQuizID(String subID) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMdd-hhmmss");
        java.util.Date date = new java.util.Date();
        
        String dateGetted = dateFormat.format(date);
        
        String randomNumberString = "";
        
        for (int i = 0; i < 3; i++) {
            int randomNumber = new Random().nextInt(10);
            randomNumberString += randomNumber;
        }
        
        return dateGetted + "-" + subID + "-" + randomNumberString;
    }
}
