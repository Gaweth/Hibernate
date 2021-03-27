package pl.academy.database.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "RUN_MEMBER")
public class RunMember {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(name = "start_number")
    private Integer startNumber;

    @ManyToOne
    @JoinColumn(name = "id_run")
    private Run run;

    @ManyToMany(mappedBy = "members")
    private Set<NfcTag> tags = new HashSet<>();

    public RunMember() {
    }

    public Set<NfcTag> getTags() {
        return tags;
    }

    public void setTags(Set<NfcTag> tags) {
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Run getRun() {
        return run;
    }

    public void setRun(Run run) {
        this.run = run;
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
