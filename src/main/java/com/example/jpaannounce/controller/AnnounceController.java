package com.example.jpaannounce.controller;

import com.example.jpaannounce.entity.Announce;
import com.example.jpaannounce.service.AnnounceService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/announcements")
public class AnnounceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnnounceController.class);

    @Autowired
    private AnnounceService announceService;

    @PostMapping
    public ResponseEntity createAnnounce(@RequestBody Announce announce) {
        LOGGER.debug("Создание объявления: {}", announce);
        announceService.createAnnounce(announce);
        return ResponseEntity.ok("OK!");
    }

    @PostMapping("/bulk")
    public ResponseEntity createBulkAnnounces(@RequestBody List<Announce> announces) {
        LOGGER.debug("Creating bulk announces. Count: {}", announces.size());
        announceService.createBulkAnnounces(announces);
        return ResponseEntity.ok("OK!");
    }

    @GetMapping()
    public ResponseEntity getAnnounce(@RequestParam Long id) {
        LOGGER.debug("Получение объявления с id: {}", id);
        return ResponseEntity.ok(announceService.getAnnounce(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDepartmentId(@PathVariable Long id) {
        LOGGER.debug("Удаление объявления с id: {}", id);
        return ResponseEntity.ok(announceService.delete(id));
    }
    //
    /*@GetMapping("/DepId/{DepId}")
    public ResponseEntity getAnnouncesByStudentDepartmentId(@PathVariable Long DepId) {
        try {
            List<AnnounceModel> announces = announceService.getAnnouncesByStudent(DepId);
            return ResponseEntity.ok(announces);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error!");
        }
    }*/
}
