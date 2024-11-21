package com.example.homos_cristian_cosmin_1101;

import java.io.Serializable;

public class Patient implements Serializable {
    private String patientName;
    private float examinationCost;
    private boolean insurance;

    public Patient(String patientName, float examinationCost, boolean inssurance) {
        this.patientName = patientName;
        this.examinationCost = examinationCost;
        this.insurance = inssurance;
    }

    public Patient() {
        this.patientName= "Necunoscut";
        this.examinationCost = 0;
        this.insurance = false;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        if (patientName == null || patientName.trim().isEmpty()) {
            throw new IllegalArgumentException("Numele pacientului nu poate fi null sau gol.");
        }
        this.patientName = patientName;
    }

    public float getExaminationCost() {
        return examinationCost;
    }

    public void setExaminationCost(float examinationCost) {
        if (examinationCost < 0) {
            throw new IllegalArgumentException("Costul examinării trebuie să fie mai mare sau egal cu 0.");
        }
        this.examinationCost = examinationCost;
    }

    public boolean isInssurance() {
        return insurance;
    }

    public void setInssurance(boolean inssurance) {
        this.insurance = inssurance;
    }

    @Override
    public String toString() {
        return
                "Patient Name: " + patientName +
                ", Examination Cost: " + examinationCost +
                ", Inssurance: " + insurance
                ;
    }


}
