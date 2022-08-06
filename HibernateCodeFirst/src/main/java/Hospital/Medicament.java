package Hospital;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "medicament")
public class Medicament {

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 100, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "medicament")
    private Set<Diagnose> diagnoses;

    public Medicament(String name) {
        this.name = name;
    }

    public Medicament() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
