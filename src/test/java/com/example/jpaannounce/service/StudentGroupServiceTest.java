package com.example.jpaannounce.service;

import com.example.jpaannounce.component.Cach;
import com.example.jpaannounce.entity.Announce;
import com.example.jpaannounce.entity.StudentGroup;
import com.example.jpaannounce.model.Group;
import com.example.jpaannounce.repository.AnnounceRepository;
import com.example.jpaannounce.repository.StudentGroupRepository;
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
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateGroup() {
        Long groupId = 1L;
        Announce announce = new Announce();
        StudentGroup studentGroup = new StudentGroup();
        when(announceRepository.findById(groupId)).thenReturn(Optional.of(announce));
        when(studentGroupRepository.save(studentGroup)).thenReturn(studentGroup);

        Group result = studentGroupService.createGroup(studentGroup, groupId);

        assertEquals(Group.toModel(studentGroup), result);
        assertEquals(announce, studentGroup.getGroup());
        verify(announceRepository, times(1)).findById(groupId);
        verify(studentGroupRepository, times(1)).save(studentGroup);
    }

    @Test
    public void testComplete() {
        Long id = 1L;
        StudentGroup existingStudentGroup = new StudentGroup();
        StudentGroup updatedStudentGroup = new StudentGroup();
        when(studentGroupRepository.findById(id)).thenReturn(Optional.of(existingStudentGroup));
        when(studentGroupRepository.save(existingStudentGroup)).thenReturn(updatedStudentGroup);

        Group result = studentGroupService.complete(id, updatedStudentGroup);

        assertEquals(Group.toModel(updatedStudentGroup), result);
        assertEquals(updatedStudentGroup.getGroupId(), existingStudentGroup.getGroupId());
        assertEquals(updatedStudentGroup.getGroupName(), existingStudentGroup.getGroupName());
        verify(studentGroupRepository, times(1)).findById(id);
        verify(studentGroupRepository, times(1)).save(existingStudentGroup);
    }

    @Test
    public void testGetStudentGroupsByGroupId_withCacheHit() {
        int groupId = 1;
        List<StudentGroup> studentGroups = new ArrayList<>();
        studentGroups.add(new StudentGroup());
        when(cache.containsKey(anyString())).thenReturn(true);
        when(cache.get(anyString())).thenReturn(studentGroups);

        List<StudentGroup> result = studentGroupService.getStudentGroupsByGroupId(groupId);

        assertEquals(studentGroups, result);
        verify(cache, times(1)).containsKey("studentGroups_1");
        verify(cache, times(1)).get("studentGroups_1");
        verify(studentGroupRepository, times(0)).findByGroupId(groupId);
        verify(cache, times(0)).put(anyString(), anyList());
    }

    @Test
    public void testGetStudentGroupsByGroupId_withCacheMiss() {
        int groupId = 1;
        List<StudentGroup> studentGroups = new ArrayList<>();
        studentGroups.add(new StudentGroup());
        when(cache.containsKey(anyString())).thenReturn(false);
        when(studentGroupRepository.findByGroupId(groupId)).thenReturn(studentGroups);

        List<StudentGroup> result = studentGroupService.getStudentGroupsByGroupId(groupId);

        assertEquals(studentGroups, result);
        verify(cache, times(1)).containsKey("studentGroups_1");
        verify(cache, times(0)).get(anyString());
        verify(studentGroupRepository, times(1)).findByGroupId(groupId);
        verify(cache, times(1)).put("studentGroups_1", studentGroups);
    }
}