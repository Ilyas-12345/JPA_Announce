package com.example.jpaannounce.model;

import com.example.jpaannounce.entity.StudentGroup;

public class Group {
    private Long id;
    private int groupId;
    private int groupName;

    public static Group toModel(StudentGroup entity) {
        Group model = new Group();
        model.setId(entity.getId());
        model.setGroupId(entity.getGroupId());
        model.setGroupName(entity.getGroupName());
        return model;
    }

    public Group() {
    }

    public int getGroupName() {
        return groupName;
    }

    public void setGroupName(int groupName) {
        this.groupName = groupName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
