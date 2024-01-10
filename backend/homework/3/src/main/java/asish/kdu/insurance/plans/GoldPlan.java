package asish.kdu.insurance.plans;

import asish.kdu.insurance.HealthInsurancePlan;

public class GoldPlan extends HealthInsurancePlan {
    public GoldPlan() {
        this.setCoverage(0.8);
        this.setHospitalDiscount(40);
    }

    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return 0.07 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }
}
