package com.example.jpaannounce.controller;

import com.example.jpaannounce.entity.StudentGroup;
import com.example.jpaannounce.service.StudentGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;

@RestController
@RequestMapping("/studentGroup/")
public class StudentGroupController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentGroupController.class);

    @Autowired
    public StudentGroupService studentGroupService;

    public StudentGroupController(StudentGroupService studentGroupService) {
        this.studentGroupService = studentGroupService;
    }

    @PostMapping("/create")
    public ResponseEntity createStudentGroup(@RequestBody StudentGroup studentGroup,
                                             @RequestParam Long groupId) {
        LOGGER.debug("Создание студенческой группы: {}", studentGroup);
        return ResponseEntity.ok(studentGroupService.createGroup(studentGroup, groupId));
    }

    @PutMapping("/complete")
    public ResponseEntity createStudentGroup(@RequestParam Long id,
                                             @RequestBody StudentGroup studentGroup) {
        LOGGER.debug("Обновление студенческой группы с id: {}", id);
        return ResponseEntity.ok(studentGroupService.complete(id, studentGroup));
    }

    @GetMapping("/studentGroups")
    public List<StudentGroup> getStudentGroupsByGroupId(@RequestParam("groupId") int groupId) {
        LOGGER.debug("Получение студенческих групп по id группы: {}", groupId);
        return studentGroupService.getStudentGroupsByGroupId(groupId);
    }
}
