package com.example.microservicespike.booking.repositories;

import com.example.microservicespike.booking.models.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppointmentRepository extends MongoRepository<Appointment, Integer> {
}
