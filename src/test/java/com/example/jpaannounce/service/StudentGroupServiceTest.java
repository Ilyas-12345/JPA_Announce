package com.example.jpaannounce.service;

import com.example.jpaannounce.component.Cach;
import com.example.jpaannounce.entity.Announce;
import com.example.jpaannounce.entity.StudentGroup;
import com.example.jpaannounce.model.Group;
import com.example.jpaannounce.repository.AnnounceRepository;
import com.example.jpaannounce.repository.StudentGroupRepository;
import com.example.jpaannounce.service.StudentGroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StudentGroupServiceTest {

    @Mock
    private StudentGroupRepository studentGroupRepository;

    @Mock
    private AnnounceRepository announceRepository;

    @Mock
    private Cach cache;

    @InjectMocks
    private StudentGroupService studentGroupService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateGroup() {
        Long groupId = 1L;
        Announce announce = new Announce();
        StudentGroup studentGroup = new StudentGroup();
        studentGroup.setGroupId(groupId.intValue());
        when(announceRepository.findById(groupId)).thenReturn(Optional.of(announce));
        when(studentGroupRepository.save(any(StudentGroup.class))).thenReturn(studentGroup);

        Group createdGroup = studentGroupService.createGroup(studentGroup, groupId);

        assertEquals(Group.toModel(studentGroup), createdGroup);
        assertEquals(announce, studentGroup.getGroup());
        verify(announceRepository, times(1)).findById(groupId);
        verify(studentGroupRepository, times(1)).save(studentGroup);
    }

    @Test
    public void testComplete() {
        Long id = 1L;
        StudentGroup existingStudentGroup = new StudentGroup();
        StudentGroup updatedStudentGroup = new StudentGroup();
        updatedStudentGroup.setGroupId(2);
        updatedStudentGroup.setGroupName(1);
        when(studentGroupRepository.findById(id)).thenReturn(Optional.of(existingStudentGroup));
        when(studentGroupRepository.save(any(StudentGroup.class))).thenReturn(updatedStudentGroup);

        Group completedGroup = studentGroupService.complete(id, updatedStudentGroup);

        assertEquals(Group.toModel(updatedStudentGroup), completedGroup);
        assertEquals(updatedStudentGroup.getGroupId(), existingStudentGroup.getGroupId());
        assertEquals(updatedStudentGroup.getGroupName(), existingStudentGroup.getGroupName());
        verify(studentGroupRepository, times(1)).findById(id);
        verify(studentGroupRepository, times(1)).save(existingStudentGroup);
    }
}