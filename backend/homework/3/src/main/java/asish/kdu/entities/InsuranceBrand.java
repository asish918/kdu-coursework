package asish.kdu.entities;

import asish.kdu.insurance.HealthInsurancePlan;

public interface InsuranceBrand {
    double computeMonthlyPremium(HealthInsurancePlan insurancePlan, int age, boolean smoking);
}
