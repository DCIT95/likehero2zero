package de.iu.hero2zero.bean;

import de.iu.hero2zero.entity.Co2DataRecord;
import de.iu.hero2zero.service.Co2DataService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("publicCo2Bean")
@ViewScoped
public class PublicCo2Bean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String selectedCountry;
    private List<Co2DataRecord> searchResults;
    private Co2DataRecord selectedRecord;

    @Inject
    private Co2DataService co2Service;

    public String searchCountry() {
        if (selectedCountry != null && !selectedCountry.trim().isEmpty()) {
            this.searchResults = co2Service.searchByCountry(selectedCountry);
            this.selectedRecord = co2Service.getLatestRecord(selectedCountry);
        } else {
            this.searchResults = List.of();
            this.selectedRecord = null;
        }
        return null;
    }

    
    public String getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(String selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public List<Co2DataRecord> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<Co2DataRecord> searchResults) {
        this.searchResults = searchResults;
    }

    public Co2DataRecord getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(Co2DataRecord selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    // Von index.xhtml in Zeile 17 verlangt (currentRecord):
    public Co2DataRecord getCurrentRecord() {
        return selectedRecord;
    }

    public void setCurrentRecord(Co2DataRecord currentRecord) {
        this.selectedRecord = currentRecord;
    }
}
