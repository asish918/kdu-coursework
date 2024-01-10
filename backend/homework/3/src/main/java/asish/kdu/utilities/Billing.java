package asish.kdu.utilities;

import asish.kdu.entities.Patient;
import asish.kdu.insurance.HealthInsurancePlan;

public class Billing {
    private Billing() {
    }

    public static double calculateInsuranceAmount(double amount, HealthInsurancePlan insurancePlan) {
        return amount * insurancePlan.getCoverage();
    }

    public static double calculatePatientAmount(double remainingAmount, HealthInsurancePlan insurancePlan) {
        return remainingAmount - insurancePlan.getHospitalDiscount();
    }

    public static double[] computePaymentAmount(Patient patient, double amount) {
        double[] payments = new double[2];

        double insuranceAmount = 0;
        double patientAmount = 0;

        HealthInsurancePlan patientInsurancePlan = patient.getInsurancePlan();

        if (patientInsurancePlan == null) {
            insuranceAmount = 0;
            patientAmount = amount - 20;
        } else {
            insuranceAmount = calculateInsuranceAmount(amount, patientInsurancePlan);
            patientAmount = calculatePatientAmount(amount - insuranceAmount, patientInsurancePlan);
        }

        payments[0] = insuranceAmount;
        payments[1] = patientAmount;

        return payments;
    }

}