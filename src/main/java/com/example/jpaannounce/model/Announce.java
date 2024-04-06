package com.example.jpaannounce.model;

import java.util.List;
import java.util.stream.Collectors;

public class Announce {
    private Long id;
    private int departmentId;
    private List<Group> groups;
    //конвертер

    public static Announce toModel(com.example.jpaannounce.entity.Announce entity) {
        Announce model = new Announce();
        model.setId(entity.getId());
        model.setDepartmentId(entity.getDepartmentId());
        //здесь мы преобразовываем список в stream, map итерируется  по массиву и вызывает to model для каждого
        //элемента и коллектом обратно к листу,(из массива entity в массив model
        model.setGroups(entity.getStudentGroup().stream().map(Group::toModel).collect(Collectors.toList()));
        return model;
    }

    public Announce() {
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
}
