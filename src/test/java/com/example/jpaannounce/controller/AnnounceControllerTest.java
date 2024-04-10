package com.example.jpaannounce.controller;

import com.example.jpaannounce.entity.Announce;
import com.example.jpaannounce.service.AnnounceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AnnounceControllerTest {

    @Mock
    private AnnounceService announceService;

    @InjectMocks
    private AnnounceController announceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAnnounce_shouldReturnOk() {
        Announce announce = new Announce();
        ResponseEntity expectedResponse = ResponseEntity.ok("OK!");

        ResponseEntity actualResponse = announceController.createAnnounce(announce);

        assertEquals(expectedResponse, actualResponse);
        verify(announceService, times(1)).createAnnounce(announce);
    }

    @Test
    void createBulkAnnounces_shouldReturnOk() {
        List<Announce> announces = new ArrayList<>();
        ResponseEntity expectedResponse = ResponseEntity.ok("OK!");

        ResponseEntity actualResponse = announceController.createBulkAnnounces(announces);

        assertEquals(expectedResponse, actualResponse);
        verify(announceService, times(1)).createBulkAnnounces(announces);
    }

    @Test
    void deleteAnnounce_shouldReturnOk() {
        Long id = 1L;
        ResponseEntity<?> expectedResponse = ResponseEntity.ok().build();

        ResponseEntity<?> actualResponse = announceController.deleteDepartmentId(id);

        assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
        verify(announceService, times(1)).delete(id);
    }
}