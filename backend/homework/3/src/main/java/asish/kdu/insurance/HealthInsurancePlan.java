package asish.kdu.insurance;

import asish.kdu.entities.InsuranceBrand;

public abstract class HealthInsurancePlan {
    private double coverage;
    private double hospitalDiscount;

    public double getHospitalDiscount() {
        return hospitalDiscount;
    }

    public void setHospitalDiscount(double hospitalDiscount) {
        this.hospitalDiscount = hospitalDiscount;
    }

    private InsuranceBrand offeredBy;
    public double getCoverage() {
        return coverage;
    }

    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }

    public InsuranceBrand getOfferedBy() {
        return offeredBy;
    }

    public void setOfferedBy(InsuranceBrand offeredBy) {
        this.offeredBy = offeredBy;
    }

    public abstract double computeMonthlyPremium(double salary, int age, boolean smoking);
}
