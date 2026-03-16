package tableditor.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String firstname;
    private String lastname;
    private String prom;
    private Double grade;
    
    public Student() {
    }
    
    public Student(String firstname, String lastname, String prom, Double grade) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.prom = prom;
        this.grade = grade;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getFirstname() {
        return firstname;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    
    public String getLastname() {
        return lastname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
    public String getProm() {
        return prom;
    }
    
    public void setProm(String prom) {
        this.prom = prom;
    }
  
    
    public Double getGrade() {
        return grade;
    }
    
    public void setGrade(Double grade) {
        this.grade = grade;
    }
}
