package com.raghv.demo.sezzle;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGeneratorUtil {

    public static void main(String[] args){
        BCryptPasswordEncoder BCrypto = new BCryptPasswordEncoder();
        //This will print the password to be used for LDAP
        //System.out.println(BCrypto.encode("<password>"));

    }
}
