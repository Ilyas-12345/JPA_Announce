package com.example.jpaannounce.service;

import com.example.jpaannounce.component.Cach;
import com.example.jpaannounce.entity.Announce;
import com.example.jpaannounce.model.AnnounceModel;
import com.example.jpaannounce.repository.AnnounceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnounceService {
    @Autowired
    private AnnounceRepository announceRepository;
    private Cach cache;

    public Announce createAnnounce(Announce announce) {
        return announceRepository.save(announce);
    }

    public AnnounceModel getAnnounce(Long id) {
        Announce announce = announceRepository.findById(id).get();
        return AnnounceModel.toModel(announce);
    }

    public Long delete(Long id) {
        announceRepository.deleteById(id);
        return id;
    }

    @Transactional
    public void createBulkAnnounces(List<Announce> announces) {
        for (Announce announce : announces) {
            createAnnounce(announce);
        }
    }
}
