package com.example.microservicespike.booking.models;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.UUID;

public class Appointment {
    @Id
    private int id;
    private LocalDateTime startDateTime;
    private int patientId;
    private int doctorId;

    public Appointment() {}

    public Appointment(int id, LocalDateTime startDateTime, int doctorId, int patientId) {
        this.id = id;
        this.startDateTime = startDateTime;
        this.doctorId = doctorId;
        this.patientId = patientId;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public int getId() {
        return id;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
}
