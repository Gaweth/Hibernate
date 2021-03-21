package pl.academy.database.entity;

import javax.persistence.*;

@Entity
@Table(name = "run_member")

public class RunMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 100)
    private String name;
    @Column(name = "start_number")
    private Integer startNumber;

    public RunMember() {

    }

    public RunMember(int id, String name, Integer startNumber) {
        this.id = id;
        this.name = name;
        this.startNumber = startNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(Integer startNumber) {
        this.startNumber = startNumber;
    }
}
