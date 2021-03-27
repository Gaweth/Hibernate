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
    private Integer distance;

    @OneToMany(mappedBy = "run", fetch = FetchType.EAGER)
    private Set<RunMember> members = new HashSet<>();

    public Run() {
    }

    public Run(Long id, String name, Integer membersLimit, Integer distance) {
        this.id = id;
        this.name = name;
        this.membersLimit = membersLimit;
        this.distance = distance;
    }

    public Set<RunMember> getMembers() {
        return members;
    }

    public void setMembers(Set<RunMember> members) {
        this.members = members;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
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

    public Integer getMembersLimit() {
        return membersLimit;
    }

    public void setMembersLimit(Integer membersLimit) {
        this.membersLimit = membersLimit;
    }
}
