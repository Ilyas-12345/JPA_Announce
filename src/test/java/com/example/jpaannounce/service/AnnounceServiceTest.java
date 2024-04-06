package com.example.jpaannounce.service;

import com.example.jpaannounce.entity.Announce;
import com.example.jpaannounce.repository.AnnounceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AnnounceServiceTest {
    @Mock
    private AnnounceRepository announceRepository;

    @InjectMocks
    private AnnounceService announceService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAnnounce() {
        Announce announce = new Announce();
        when(announceRepository.save(announce)).thenReturn(announce);

        Announce result = announceService.createAnnounce(announce);

        assertEquals(announce, result);
        verify(announceRepository, times(1)).save(announce);
    }

    @Test
    public void testGetAnnounce() {
        Long id = 1L;
        Announce announce = new Announce();
        when(announceRepository.findById(id)).thenReturn(Optional.of(announce));

        com.example.jpaannounce.model.Announce result = announceService.getAnnounce(id);

        assertEquals(announce, result);
        verify(announceRepository, times(1)).findById(id);
    }

    @Test
    public void testDelete() {
        Long id = 1L;

        announceService.delete(id);

        verify(announceRepository, times(1)).deleteById(id);
    }

    @Test
    public void testCreateBulkAnnounces() {
        List<Announce> announces = new ArrayList<>();
        announces.add(new Announce());
        announces.add(new Announce());

        announceService.createBulkAnnounces(announces);

        verify(announceRepository, times(2)).save(any(Announce.class));
    }
}