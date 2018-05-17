package com.example.microservicespike.booking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class AppointmentsMainTest {
    @Test
    public void applicationStarts() {
        AppointmentsMain.main(new String[] {});
    }
}