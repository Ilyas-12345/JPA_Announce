package com.example.jpaannounce.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @Entity
    @Table(name = "announce")
    public class Announce {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "departmentId")
        private int departmentId;
        private String employee;
        private LocalDate date;
        @OneToMany(cascade = CascadeType.ALL, mappedBy = "group", fetch = FetchType.EAGER)
        @JsonIgnore// Добавляем аннотацию
        private List<StudentGroup> studentGroup;

        public Announce() {
            // This method is intentionally left empty as it serves as a placeholder for future implementation.
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public int getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(int departmentId) {
            this.departmentId = departmentId;
        }

        public String getEmployee() {
            return employee;
        }

        public void setEmployee(String employee) {
            this.employee = employee;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public List<StudentGroup> getStudentGroup() {
            return studentGroup;
        }

        public void setStudentGroup(List<StudentGroup> studentGroup) {
            this.studentGroup = studentGroup;
        }
    }
