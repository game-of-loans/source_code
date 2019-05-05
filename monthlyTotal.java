import java.util.*;

public class monthlyTotal {
    // TODO: throw in the fee which is like 1%

    /**
     *
     * @param interest_rate
     * @param loan_principal
     * @param months
     * @return monthly amount to be paid
     */
    public static double calc_unsub(double interest_rate, double loan_principal, int months) {
        double interest_fee = interest_rate * loan_principal;
        double interest_total = (interest_fee * months)/12.0;
        loan_principal += interest_total;

//        System.out.println(loan_principal);

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

        double total_month = 0;

        boolean loop = true;
        while (loop) {
            boolean toBeOrNotToBe = false;

            Scanner s = new Scanner(System.in);

            System.out.print("\nSubsidized or Unsubsidized (enter s or u): ");
            String userInput = s.nextLine();

            int numLoans = 1;
            System.out.print("\nHow many loans do you have: ");
            numLoans = s.nextInt();

            double[] rates_ = new double[numLoans];
            int[] months_ = new int[numLoans];
            double[] principal_ = new double[numLoans];

            for (int i = 0; i < numLoans; i++) {
                System.out.print("\nEnter an interest rate for loan " + (i+1) + ": ");
                double interest_rate = s.nextDouble();
                rates_[i] = interest_rate/100.0;

                System.out.print("\nEnter a principal for loan " + (i+1) + ": ");
                principal_[i] = s.nextDouble();
            }

            if (userInput.equals("u")) { // Sees whether or not to call unsub or sub
                toBeOrNotToBe = true;

                for (int i = 0; i < numLoans; i++) {
                    System.out.print("\nEnter start month for loan " + (i+1) + ": ");
                    int start_month = s.nextInt();

                    System.out.print("\nEnter start year for loan " + (i+1) + ": ");
                    int start_year = s.nextInt();

                    System.out.print("\nEnter graduation month for loan " + (i+1) + ": ");
                    int grad_month = s.nextInt();

                    System.out.print("\nEnter graduation year for loan " + (i+1) + ": ");
                    int grad_year = s.nextInt();

                    months_[i] = ((grad_year - start_year)*12) + (grad_month - start_month)+ 6;
                }
            }

            double sum = 0;
            if (toBeOrNotToBe) {
                for (int i = 0; i < numLoans; i++) {
                    total_month += calc_unsub(rates_[i], principal_[i], months_[i]);
                }
//                System.out.print("\n" + sum);
            } else {
                for (int i = 0; i < numLoans; i++) {
                    total_month += calc_month(rates_[i], principal_[i]);
                }
//                System.out.print("\n" + sum);
            }

            System.out.print("\nDo you have another loan? Enter y for yes and n for no: ");

            Scanner x = new Scanner(System.in);

            String answer = x.nextLine();

            if (answer.compareTo("n") == 0) {
                loop = false;
            }
        }
        System.out.print("\n" + total_month);
    }
}
