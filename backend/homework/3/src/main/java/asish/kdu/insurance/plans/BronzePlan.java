package asish.kdu.insurance.plans;

import asish.kdu.insurance.HealthInsurancePlan;

public class BronzePlan extends HealthInsurancePlan {
    public BronzePlan() {
        this.setCoverage(0.6);
        this.setHospitalDiscount(25);
    }

    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return 0.05 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }
}
