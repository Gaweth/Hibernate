package pl.academy.database.entity;

import javax.persistence.*;

@Entity
@Table(name = "RUN")
public class Run {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 100)
    private String name;
    @Column(name = "members_limit")
    private Integer membersLimit;

    public Run() {

    }

    public Run(Long id, String name, int membersLimit) {
        this.id = id;
        this.name = name;
        this.membersLimit = membersLimit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMembersLimit() {
        return membersLimit;
    }

    public void setMembersLimit(int membersLimit) {
        this.membersLimit = membersLimit;
    }
}
