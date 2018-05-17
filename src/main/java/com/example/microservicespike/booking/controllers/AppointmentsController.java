package com.example.microservicespike.booking.controllers;

import com.example.microservicespike.booking.models.Appointment;
import com.example.microservicespike.booking.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/appointments")
public class AppointmentsController {
    @Autowired private AppointmentRepository appointmentRepository;

    @GetMapping("{appointmentId}")
    Appointment getAppointment(@PathVariable Integer appointmentId) {
        return appointmentRepository.findById(appointmentId).orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    @PostMapping
    public ResponseEntity<String> createAppointment(@Valid @RequestBody Appointment appointment) {
        appointmentRepository.insert(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{appointmentId}")
    public ResponseEntity<String> updateAppointment(@PathVariable Integer appointmentId, @Valid @RequestBody Appointment appointmentWithUpdates) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);

        Optional<ResponseEntity<String>> optionalResponseEntity = optionalAppointment.map(appointment -> {
            appointment.setDoctorId(appointmentWithUpdates.getDoctorId());
            appointment.setPatientId(appointmentWithUpdates.getPatientId());
            appointment.setStartDateTime(appointmentWithUpdates.getStartDateTime());

            appointmentRepository.save(appointment);
            return ResponseEntity.status(HttpStatus.OK).build();
        });

        return optionalResponseEntity.orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @DeleteMapping("{appointmentId}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Integer appointmentId) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);

        Optional<ResponseEntity<String>> optionalResponseEntity = optionalAppointment.map(appointment -> {
            appointmentRepository.delete(appointment);
            return ResponseEntity.status(HttpStatus.OK).build();
        });

        return optionalResponseEntity.orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
