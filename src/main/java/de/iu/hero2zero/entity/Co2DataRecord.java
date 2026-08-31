package de.iu.hero2zero.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "co2_data_records")
public class Co2DataRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String countryName;

    @Column(nullable = false, length = 3)
    private String countryCode;

    
    @Column(name = "\"YEAR\"", nullable = false)
    private int year;

    @Column(nullable = false)
    private Double co2EmissionKt;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User createdBy;

    public Co2DataRecord() {}

    public Co2DataRecord(String countryName, String countryCode, int year, Double co2EmissionKt, User createdBy) {
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.year = year;
        this.co2EmissionKt = co2EmissionKt;
        this.createdBy = createdBy;
    }

    // Getter und Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCountryName() { return countryName; }
    public void setCountryName(String countryName) { this.countryName = countryName; }

    public String getCountryCode() { return countryCode; }
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public Double getCo2EmissionKt() { return co2EmissionKt; }
    public void setCo2EmissionKt(Double co2EmissionKt) { this.co2EmissionKt = co2EmissionKt; }

    public User getCreatedBy() { return createdBy; }
    public void setCreatedBy(User createdBy) { this.createdBy = createdBy; }
}
