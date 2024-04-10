package com.example.jpaannounce.model;

import com.example.jpaannounce.entity.StudentGroup;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AnnounceModel {
    private Long id;
    private int departmentId;
    private List<Group> groups;
    //конвертер

    public static AnnounceModel toModel(com.example.jpaannounce.entity.Announce entity) {
        AnnounceModel model = new AnnounceModel();
        model.setId(entity.getId());
        model.setDepartmentId(entity.getDepartmentId());

        List<StudentGroup> studentGroups = entity.getStudentGroup();
        if (studentGroups != null) {
            List<Group> groups = studentGroups.stream()
                    .map(studentGroup -> {
                        Group group = new Group();
                        // Set the properties of the Group object based on the StudentGroup object
                        group.setId(studentGroup.getId());
                        group.setGroupName(studentGroup.getGroupName());
                        // ... Set other properties as needed
                        return group;
                    })
                    .collect(Collectors.toList());
            model.setGroups(groups);
        }

        return model;
    }

    public AnnounceModel() {
    }

    public Long getId() {
        return id;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AnnounceModel other = (AnnounceModel) obj;
        return Objects.equals(id, other.id)
                && departmentId == other.departmentId
                && Objects.equals(groups, other.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departmentId, groups);
    }
}
