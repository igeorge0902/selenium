package main.java.qa.framework.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class PasswordChecker {

    public static void main(String[] args) throws Exception {


        String s="This is a test";
        MessageDigest m=MessageDigest.getInstance("MD5");
        m.update(s.getBytes(),0,s.length());
        
        System.out.println("MD5: "+new BigInteger(1,m.digest()).toString(16));
        String password = new BigInteger(1,m.digest()).toString(16);

        //String password = "mypassword";
        String passwordEnc = PasswordTest.encrypt(password);
        String passwordDec = PasswordTest.decrypt(passwordEnc);

        System.out.println("Plain Text : " + password);
        System.out.println("Encrypted Text : " + passwordEnc);
        System.out.println("Decrypted Text : " + passwordDec);
        
        
    }
    
}
