package za.ac.cput.util;

import za.ac.cput.domain.ComicBook;
import za.ac.cput.domain.Customer;

import za.ac.cput.domain.Publisher;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class Helper {
    public static boolean isStringNullorEmpty(String string) {
        if (string.isEmpty() || string == null) {
            return true;
        }
        return false;
    }

    public static boolean isListNullorEmpty(List list) {
        if (list.isEmpty() || list == null) {
            return true;
        }
        return false;
    }
    public static boolean isSetNullorEmpty(Set s) {
        if (s.isEmpty() || s == null) {
            return true;
        }
        return false;
    }

    public static boolean isNumber10Digits(String number) {
        if (number.length() != 10) {
            return true;
        }

        for (int i = 0; i < number.length(); i++) {
            if (!Character.isDigit(number.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmailValid(String emailAddress) {
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        boolean isMatching = Pattern.compile(regex)
                .matcher(emailAddress)
                .matches();

        if (!isMatching) {
            return true;
        }

        return false;
    }

    public static boolean isValidDate(LocalDate date) {

        if (date == null) {
            return true;
        }

        LocalDate today = LocalDate.now();
        if (date.isAfter(today)) {
            return true;
        }

        return false;

    }

    public static boolean isNotInPast(LocalDate date) {
        return !date.isBefore(LocalDate.now());
    }

    public static boolean isAfter(LocalDate date, LocalDate referenceDate) {
        return date.isAfter(referenceDate);
    }


    public static boolean isValidPrice(double price) {
        if (price < 0) {
            return true;
        }
        return false;
    }

    public static boolean isInvalidInt(int number) {
        if (number <= 0) {
            return true;
        }
        return false;
    }

    public static boolean isInvalidPostalCode(String postalCode) {
        int postalCodeAsInt = Integer.parseInt(postalCode);
        if (postalCodeAsInt < 0001 || postalCodeAsInt > 9999) {
            throw new IllegalArgumentException("postalCode is invalid");
            //return true;
        }
        return false;

    }

    public static boolean isInvalidTime(LocalTime time) {
        boolean isInvalidTime = time.isAfter(time);
        return isInvalidTime;
    }

    public static boolean isArrayNullOrEmpty(byte[] photo) {
        if (photo == null) {
            return true;
        }
        return false;

    }


    public static boolean isObjectNull(Object object) {
        if (object == null) {
            return true;
        }
        return false;

    }


}
