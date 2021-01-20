package com.raghv.demo.sezzle;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGeneratorUtil {

    public static void main(String[] args){
        BCryptPasswordEncoder BCrypto = new BCryptPasswordEncoder();
        System.out.println(BCrypto.encode("apple"));
        System.out.println(BCrypto.encode("orange"));

    }
}
