import java.util.*;
import java.text.*;

public class monthlyTotalThree {
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

    public static void do_stuff(String userInput, double interest_rate, double principal, int grad_month, int grad_year, int start_month, int start_year, double payment_period, int grace_period, String answer){

        double total_month = 0;

        payment_period = 10;

        boolean loop = true;
        while (loop) {
            boolean toBeOrNotToBe = false;
            boolean toBeOrNotToBeToo = false;

            interest_rate = interest_rate/100.0;

            int months = 0;

            if (userInput.equals("u") || userInput.equals("p")) { // Sees whether or not to call unsub or sub
                toBeOrNotToBe = true;


                if (userInput.equals("p")) {
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


            if (answer.compareTo("n") == 0) {
                loop = false;
            }
        }

        NumberFormat fmt = new DecimalFormat("#0.00");
        System.out.print("\n" + fmt.format(total_month));
    }
    public static void main(String[] args){
        String userInput= "";
        double interest_rate = 0;
        double principal= 0;
        int grad_month= 0;
        int grad_year= 0;
        int start_month= 0;
        int start_year= 0;
        double payment_period=0;
        int grace_period=0;
        String answer= "";
        do_stuff(userInput, interest_rate, principal, grad_month, grad_year, start_month, start_year, payment_period, grace_period, answer);
    }
}
