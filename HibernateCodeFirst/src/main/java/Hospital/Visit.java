package Hospital;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "visits")
public class Visit {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "visitation_date", nullable = false)
    private Date visitationDate;

    @Column(columnDefinition = "TEXT")
    private String comments;

    @ManyToOne
    private Patient patient;

    public Visit(Date visitationDate, String comments) {
        this.visitationDate = visitationDate;
        this.comments = comments;
    }

    public Visit() {
    }

    public Date getVisitationDate() {
        return visitationDate;
    }

    public void setVisitationDate(Date visitationDate) {
        this.visitationDate = visitationDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
