package asish.kdu;

import asish.kdu.entities.InsuranceBrand;
import asish.kdu.insurance.plans.BronzePlan;
import asish.kdu.insurance.plans.GoldPlan;
import asish.kdu.insurance.HealthInsurancePlan;
import asish.kdu.insurance.plans.PlatinumPlan;
import asish.kdu.insurance.plans.SilverPlan;

public class BlueCrossBlueShield implements InsuranceBrand {
    enum Insurance {
        PLATINUM,
        GOLD,
        SILVER,
        BRONZE,
        NONE
    }
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Insurance findInsuranceType(HealthInsurancePlan insurancePlan) {
        if(insurancePlan instanceof PlatinumPlan)
            return Insurance.PLATINUM;
        else if (insurancePlan instanceof GoldPlan)
                return Insurance.GOLD;
        else if (insurancePlan instanceof SilverPlan) {
            return Insurance.SILVER;
        } else if (insurancePlan instanceof BronzePlan) {
            return Insurance.BRONZE;
        }
        else return Insurance.NONE;
    }

    @Override
    public double computeMonthlyPremium(HealthInsurancePlan insurancePlan, int age, boolean smoking) {
        double premium = 0;

        switch (findInsuranceType(insurancePlan)) {
            case PLATINUM -> {
                if (age > 55) premium += 200;
                if (smoking) premium += 100;
            }
            case GOLD -> {
                if (age > 55) premium += 150;
                if (smoking) premium += 90;
            }
            case SILVER -> {
                if (age > 55) premium += 100;
                if (smoking) premium += 80;
            }
            case BRONZE -> {
                if (age > 55) premium += 50;
                if (smoking) premium += 70;
            }
            default -> premium += 0;
        }

        return premium;
    }
}
