package asish.kdu;

import asish.kdu.entities.InsuranceBrand;
import asish.kdu.entities.Patient;
import asish.kdu.entities.User;
import asish.kdu.insurance.HealthInsurancePlan;
import asish.kdu.insurance.plans.PlatinumPlan;
import asish.kdu.logging.CustomLogger;
import asish.kdu.utilities.Billing;

public class Main {

    /**
     * I have used INFO logger type only because our program today
     * doesn't really have a lot of spots for different levels of logging
     * @param args
     */
    public static void main(String[] args) {
        CustomLogger.LoggerType logType = CustomLogger.LoggerType.INFO;

        User staff = new User();
        InsuranceBrand insuranceBrand = new BlueCrossBlueShield();
        HealthInsurancePlan insurancePlan = new PlatinumPlan();
        Patient patient = new Patient();
        patient.setInsurancePlan(insurancePlan);

        double[] payments = Billing.computePaymentAmount(patient, 1000.0);

        insurancePlan.setOfferedBy(insuranceBrand);
        staff.setInsurancePlan(insurancePlan);

        double payment = insurancePlan.computeMonthlyPremium(5000, 56, true);


        CustomLogger.printLogger("The Bill Amount Paid By Patient and Insurance Company are: ", logType);
        CustomLogger.printLogger(payments[0] + " " +  payments[1], logType);

        CustomLogger.printLogger("Premium for user with salary - 500\nAge - 56\nwho smokes is: ", logType);
        CustomLogger.printLogger(Double.toString(payment), logType);
    }
}
