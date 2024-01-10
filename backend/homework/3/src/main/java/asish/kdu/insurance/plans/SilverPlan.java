package asish.kdu.insurance.plans;

import asish.kdu.insurance.HealthInsurancePlan;

public class SilverPlan extends HealthInsurancePlan {
    public SilverPlan() {
        this.setCoverage(0.7);
        this.setHospitalDiscount(30);
    }

    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return 0.06 * salary + getOfferedBy().computeMonthlyPremium(this, age, smoking);
    }
}
