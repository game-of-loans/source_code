import java.util.*;
import java.text.*;

public class monthlyTotalTwo {
    /**
     * @param interest_rate
     * @param loan_principal
     * @param months
     * @return monthly amount to be paid
     */
    public static double calc_unsub(double interest_rate, double loan_principal, int months) {
        double interest_fee = interest_rate * loan_principal;
        double interest_total = (interest_fee * months)/12.0;
        loan_principal += interest_total;

        return calc_month(interest_rate, loan_principal, 10);
    }

    /**
     * @param interest_rate
     * @param loan_principal
     * @return monthly amount to be paid
     */
    public static double calc_month(double interest_rate, double loan_principal, double payment_period) {
        double month_rate = interest_rate/12.0;
        return loan_principal/((java.lang.Math.pow((month_rate+1), payment_period*12)-1)/
                (month_rate*(java.lang.Math.pow((month_rate+1), payment_period*12))));
    }


    public static void main(String[] args) {

        double total_month = 0;

        double payment_period = 10;

        boolean loop = true;
        while (loop) {
            boolean toBeOrNotToBe = false;
            boolean toBeOrNotToBeToo = false;

            Scanner s = new Scanner(System.in);

            System.out.print("\nSubsidized or Unsubsidized (enter s or u or p): ");
            String userInput = s.nextLine();


            System.out.print("\nEnter an interest rate: ");
            double interest_rate = s.nextDouble();
            interest_rate = interest_rate/100.0;

            System.out.print("\nEnter a principal: ");
            double principal = s.nextDouble();

            int months = 0;

            if (userInput.equals("u") || userInput.equals("p")) { // Sees whether or not to call unsub or sub
                toBeOrNotToBe = true;

                System.out.print("\nEnter graduation month for the" + (userInput.equals("u") ? " federal unsubsidized loan: " : " private loan: "));
                int grad_month = s.nextInt();

                System.out.print("\nEnter graduation year for the" + (userInput.equals("u") ? " federal unsubsidized loan: " : " private loan: "));
                int grad_year = s.nextInt();


                System.out.print("\nEnter start month: ");
                int start_month = s.nextInt();

                System.out.print("\nEnter start year: ");
                int start_year = s.nextInt();

                if (userInput.equals("p")) {
                    System.out.print("\nEnter loan duration in years: ");
                    payment_period= s.nextInt();

                    System.out.print("\nEnter grace period in months: ");
                    int grace_period= s.nextInt();

                    months= ((grad_year - start_year)*12) + (grad_month - start_month)+ grace_period;
                } else {
                    months = ((grad_year - start_year)*12) + (grad_month - start_month)+ 6;
                }

            }

            if (toBeOrNotToBe) {

                    total_month += calc_unsub(interest_rate, principal, months);

            } else if (toBeOrNotToBeToo) {
                    total_month += calc_month(interest_rate, principal, months);
            } else {
                    total_month += calc_month(interest_rate, principal,payment_period);
            }

            System.out.print("\nDo you have another loan? Enter y for yes and n for no: ");

            Scanner x = new Scanner(System.in);

            String answer = x.nextLine();

            if (answer.compareTo("n") == 0) {
                loop = false;
            }
        }

        NumberFormat fmt = new DecimalFormat("#0.00");
        System.out.print("\n" + fmt.format(total_month));
    }
}