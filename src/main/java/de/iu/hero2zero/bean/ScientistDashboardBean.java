package de.iu.hero2zero.bean;

import de.iu.hero2zero.entity.Co2DataRecord;
import de.iu.hero2zero.entity.User;
import de.iu.hero2zero.service.Co2DataService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("scientistDashboardBean")
@ViewScoped
public class ScientistDashboardBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String countryName;
    private String countryCode;
    private int year;
    private Double co2Emissions;
    private String successMessage;

    @Inject
    private Co2DataService dataService;

    @Inject
    private LoginBean loginBean;

    public String saveNewData() {
        try {
          
            User loggedInUser = null;
            if (loginBean != null && loginBean.isLoggedIn()) {
                loggedInUser = loginBean.getCurrentUser();
            } else {
                loggedInUser = new User("Dr. Schmidt", "hash123", "SCIENTIST");
            }

            Co2DataRecord newRecord = new Co2DataRecord(
                countryName, 
                countryCode, 
                year, 
                co2Emissions, 
                loggedInUser
            );

            dataService.saveRecord(newRecord);

            this.successMessage = "Erfolg: CO2-Datensatz erfolgreich hinterlegt.";

            // Formularfelder zurücksetzen
            this.countryName = "";
            this.countryCode = "";
            this.year = 0;
            this.co2Emissions = null;

            return null;
        } catch (Exception e) {
            this.successMessage = "Fehler beim Speichern: " + e.getMessage();
            return null;
        }
    }

    //  Getter & Setter 

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Double getCo2Emissions() {
        return co2Emissions;
    }

    public void setCo2Emissions(Double co2Emissions) {
        this.co2Emissions = co2Emissions;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public Co2DataService getDataService() {
        return dataService;
    }

    public void setDataService(Co2DataService dataService) {
        this.dataService = dataService;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
}
