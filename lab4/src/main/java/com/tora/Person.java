package com.tora;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Person {
    private String fullName;
    private String cnp;
    private String email;

    public Person(String fullName, String cnp, String email) throws Throwable{
        email = email.toLowerCase();
        validateFullName(fullName);
        validateCNP(cnp);
        validateEmail(email);

        this.fullName = fullName;
        this.cnp = cnp;
        this.email = email;
    }

    private void validateFullName(String fullName) throws Throwable{
        if (!fullName.matches("[a-zA-z]{3,20} [a-zA-Z]{3,20} [a-zA-Z]{3,20}"))
            throw new Exception("Full name not valid!");
    }

    private void validateCNP(String cnp) throws Throwable{
        if (!cnp.matches("[0-9]{13,13}"))
            throw new Exception("CNP must cointain only digits!");

        if (cnp.charAt(0) != '1' && cnp.charAt(0) != '2')
            throw new Exception("CNP sex is not valid");

        String formatString = "yyMMdd";
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatString);
            format.setLenient(false);
            format.parse(cnp.substring(1, 7));
        } catch (ParseException e) {
            throw new Exception("CNP date not valid!");
        } catch (IllegalArgumentException e) {
            throw new Exception("CNP date not valid!");
        }
    }

    private void validateEmail(String email) throws Throwable{
        if (!email.matches("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$"))
            throw new Exception("Email not valid!");
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return fullName.replace(' ', '~')
                + "~"
                + cnp
                + "~"
                + email
                + "%";
    }
}
