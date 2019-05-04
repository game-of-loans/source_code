import java.util.*;

public class monthlyTotal {
    // TODO: throw in the fee which is like 1%

    /**
     *
     * @param interest_rate
     * @param loan_principal
     * @param numLoans
     * @param months
     * @return monthly amount to be paid
     */
    public static double calc_unsub(double interest_rate, double loan_principal, int numLoans, int months) {
        double interest_fee = interest_rate * loan_principal;
        double interest_total = (interest_fee * months)/12.0;
        loan_principal += interest_total;

        System.out.println(loan_principal);

        return calc_month(interest_rate, loan_principal);
    }

    /**
     *
     * @param interest_rate
     * @param loan_principal
     * @return monthly amount to be paid
     */
    public static double calc_month(double interest_rate, double loan_principal) {
        double month_rate = interest_rate/12.0;
        return loan_principal/((java.lang.Math.pow((month_rate+1), 120)-1)/
                (month_rate*(java.lang.Math.pow((month_rate+1), 120))));
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean toBeOrNotToBe = false;
        int months = 0;

        System.out.print("\nSubsidized or Unsubsidized (enter s or u): ");
        String userInput = s.nextLine();

        int numLoans = 1;
        if (userInput.equals("u")) { // Sees whether or not to call unsub or sub
            toBeOrNotToBe = true;

            System.out.print("\nHow many unsubsidized loans do you have: ");
            numLoans = s.nextInt();

            System.out.print("\nEnter start month: ");
            int start_month = s.nextInt();

            System.out.print("\nEnter start year: ");
            int start_year = s.nextInt();

            System.out.print("\nEnter graduation month: ");
            int grad_month = s.nextInt();

            System.out.print("\nEnter graduation year: ");
            int grad_year = s.nextInt();

            months = ((grad_year - start_year)*12) + (grad_month - start_month)+ 6;
        }

        System.out.print("\nEnter an interest rate: ");
        double interest_rate = s.nextDouble();
        interest_rate = interest_rate/100.0;

        System.out.print("\nEnter loan principal: ");
        double loan_principal = s.nextDouble();

        if (toBeOrNotToBe) {
            System.out.print("\n" + calc_unsub(interest_rate, loan_principal, numLoans, months));
        } else {
            System.out.print("\n" + calc_month(interest_rate, loan_principal));
        }
    }
}
