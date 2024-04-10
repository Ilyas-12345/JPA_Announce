package com.example.jpaannounce.controller;
import com.example.jpaannounce.entity.StudentGroup;
import com.example.jpaannounce.controller.StudentGroupController;
import com.example.jpaannounce.model.Group;
import com.example.jpaannounce.service.StudentGroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StudentGroupControllerTest {

    private StudentGroupService studentGroupService;
    private StudentGroupController studentGroupController;

    @BeforeEach
    void setup() {
        studentGroupService = Mockito.mock(StudentGroupService.class);
        studentGroupController = new StudentGroupController(studentGroupService);
    }

    @Test
    void getStudentGroupsByGroupId_shouldReturnList() {
        // Create mock objects
        StudentGroupService studentGroupService = Mockito.mock(StudentGroupService.class);
        StudentGroupController studentGroupController = new StudentGroupController(studentGroupService);

        // Create test data
        int groupId = 1;
        List<StudentGroup> studentGroups = new ArrayList<>();
        when(studentGroupService.getStudentGroupsByGroupId(anyInt())).thenReturn(studentGroups);

        // Perform the controller method
        List<StudentGroup> response = studentGroupController.getStudentGroupsByGroupId(groupId);

        // Verify the response
        assertEquals(studentGroups, response);
        verify(studentGroupService).getStudentGroupsByGroupId(groupId);
    }
}
