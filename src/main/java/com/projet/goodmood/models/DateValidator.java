package com.projet.goodmood.models;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateValidator
{


        public static boolean isValidDate(String date) {
            try {
                LocalDate.parse(date);
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        }

        public static boolean isDateAfterToday(String date) {
            LocalDate inputDate = LocalDate.parse(date);
            LocalDate today = LocalDate.now();

            if (inputDate.isAfter(today) || inputDate.isEqual(today)) {
                return true;
            } else {
                return false;
            }
        }



}


//utiliser danns une autre cllse

/*Ce code demande à l'utilisateur d'entrer la date de début et la date de fin. Pour chaque date, le code vérifie si elle est valide et postérieure à la date d'aujourd'hui en utilisant les méthodes isValidDate et isDateAfterToday de la classe DateValidator. Si la date n'est pas valide ou antérieure à la date d'aujourd'hui, un message d'avertissement est affiché et l'utilisateur est invité à entrer une nouvelle date. Une fois que les dates sont valides, elles peuvent être utilisées pour d'autres fins dans votre application.
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter start date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();
        while (!DateValidator.isValidDate(startDate) || !DateValidator.isDateAfterToday(startDate)) {
            System.out.println("Invalid date. Please enter a valid date after today.");
            System.out.print("Enter start date (YYYY-MM-DD): ");
            startDate = scanner.nextLine();
        }

        System.out.print("Enter end date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();
        while (!DateValidator.isValidDate(endDate) || !DateValidator.isDateAfterToday(endDate)) {
            System.out.println("Invalid date. Please enter a valid date after today.");
            System.out.print("Enter end date (YYYY-MM-DD): ");
            endDate = scanner.nextLine();
        }

        // Do something with the validated dates here...

        scanner.close();
    }

}
*/