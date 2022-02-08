package com.android.replant;

public class RecentPlantersModel {
    private String recentPlanterName;
    private String recentPlanterCountry;
    private String recentPlanterTotal;
    private int recentPlanterPicture;

    public RecentPlantersModel(String recentPlanterName, String recentPlanterCountry, String recentPlanterTotal, int recentPlanterPicture) {
        this.recentPlanterName = recentPlanterName;
        this.recentPlanterCountry = recentPlanterCountry;
        this.recentPlanterTotal = recentPlanterTotal;
        this.recentPlanterPicture = recentPlanterPicture;
    }

    public RecentPlantersModel() {}

    public String getRecentPlanterName() {
        return recentPlanterName;
    }

    public void setRecentPlanterName(String recentPlanterName) {
        this.recentPlanterName = recentPlanterName;
    }

    public String getRecentPlanterCountry() {
        return recentPlanterCountry;
    }

    public void setRecentPlanterCountry(String plantCountry) {
        this.recentPlanterCountry = recentPlanterCountry;
    }

    public String getRecentPlanterTotal() {
        return recentPlanterTotal;
    }

    public void setRecentPlanterTotal(String plantPrice) {
        this.recentPlanterTotal = recentPlanterTotal;
    }

    public int getRecentPlanterPicture() {
        return recentPlanterPicture;
    }

    public void setRecentPlanterPicture(int plantPicture) {
        this.recentPlanterPicture = recentPlanterPicture;
    }
}
