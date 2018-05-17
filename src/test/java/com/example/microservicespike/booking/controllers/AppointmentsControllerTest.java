package com.example.microservicespike.booking.controllers;

import com.example.microservicespike.booking.models.Appointment;
import com.example.microservicespike.booking.repositories.AppointmentRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppointmentsControllerTest {

    @Mock
    AppointmentRepository appointmentRepository;

    @InjectMocks
    AppointmentsController appointmentsController;
    int appointmentId = 1;
    Appointment appointment = new Appointment(appointmentId, LocalDateTime.now(), 1, 1);


    @Test
    public void shouldCreateAppointment() {
        when(appointmentRepository.insert(appointment)).thenReturn(appointment);

        appointmentsController.createAppointment(appointment);

        verify(appointmentRepository).insert(appointment);
    }

    @Test
    public void shouldUpdateAppointment() {
        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(appointment));
        when(appointmentRepository.save(appointment)).thenReturn(appointment);

        appointmentsController.updateAppointment(appointmentId, appointment);

        verify(appointmentRepository).save(appointment);
    }

    @Test
    public void shouldNotUpdateAppointmentIfAppointmentNotFound() {
        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.empty());

        ResponseEntity<String> responseEntity = appointmentsController.updateAppointment(appointmentId, appointment);

        verify(appointmentRepository).findById(appointmentId);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void shouldDeleteAppointment() {
        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(appointment));

        appointmentsController.deleteAppointment(appointmentId);

        verify(appointmentRepository).delete(appointment);
    }

    @Test
    public void shouldNotDeleteAppointmentIfAppointmentNotFound() {
        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.empty());

        ResponseEntity<String> responseEntity = appointmentsController.deleteAppointment(appointmentId);

        verify(appointmentRepository).findById(appointmentId);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void shouldReturnAppointment() {
        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(appointment));

        appointmentsController.getAppointment(appointmentId);

        verify(appointmentRepository).findById(appointmentId);
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotReturnAppointmentIfAppointmentNotFound() {
        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.empty());

        appointmentsController.getAppointment(appointmentId);

        verify(appointmentRepository).findById(appointmentId);
    }


}