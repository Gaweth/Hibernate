package pl.academy.database.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "run", fetch = FetchType.EAGER)
    private Set<RunMember> members = new HashSet<>();



    public void setMembersLimit(Integer membersLimit) {
        this.membersLimit = membersLimit;
    }


    public Run() {

    }

    public Run(Long id, String name, int membersLimit) {
        this.id = id;
        this.name = name;
        this.membersLimit = membersLimit;

    }

    public Set<RunMember> getMembers() {
        return members;
    }

    public void setMembers(Set<RunMember> members) {
        this.members = members;
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
