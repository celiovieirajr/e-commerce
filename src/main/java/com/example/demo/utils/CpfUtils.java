package com.example.demo.utils;

import org.springframework.stereotype.Component;

@Component
public class CpfUtils {

    private CpfUtils() {}

    public boolean isValidCPF(String cpf) {
        if (cpf == null) return false;

        String digits = cpf.replaceAll("\\D", "");
        if (digits.length() != 11) return false;

        // rejeita sequências como 00000000000, 11111111111, ...
        if (digits.chars().distinct().count() == 1) return false;

        try {
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum += (digits.charAt(i) - '0') * (10 - i);
            }
            int r = sum % 11;
            int firstCheck = (r < 2) ? 0 : 11 - r;
            if (firstCheck != (digits.charAt(9) - '0')) return false;

            sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += (digits.charAt(i) - '0') * (11 - i);
            }
            r = sum % 11;
            int secondCheck = (r < 2) ? 0 : 11 - r;
            return secondCheck == (digits.charAt(10) - '0');
        } catch (Exception e) {
            return false;
        }
    }

    public String onlyDigits(String cpf) {
        return cpf == null ? null : cpf.replaceAll("\\D", "");
    }
}
