package AppRev1.highLevelApp.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 Заявка
 */

@Entity
@Table(name = "admissions")
public class Admission implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", length = 6, nullable = false)
    private Long admissionId;

    @OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="OPERATOR_ID")
    private MOperator MOperator;

    @OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="PERSON_ID")
    private Person person;

    @OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "OPERATION_ID")
    private MOperation MOperation;

    @Column(name = "datetime")
    private Timestamp date;

    public Admission() {
    }

    public Admission(MOperation MOperation, MOperator MOperator, Person person, Timestamp date) {
        this.person = person;
        this.MOperation = MOperation;
        this.MOperator = MOperator;
        this.date = date;
    }

    public Long getAdmissionId() {
        return admissionId;
    }

    public MOperator getMOperator() {
        return MOperator;
    }

    public Person getPerson() {
        return person;
    }

    public MOperation getMOperation() {
        return MOperation;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setAdmissionId(Long admissionId) {
        this.admissionId = admissionId;
    }

    public void setMOperator(MOperator MOperator) {
        this.MOperator = MOperator;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setMOperation(MOperation MOperation) {
        this.MOperation = MOperation;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        Role other = (Role)obj;
        return this.getAdmissionId()==other.getId();
    }
    @Override
    public int hashCode()
    {
        int result = (int)(getAdmissionId()%Integer.MAX_VALUE);
        return result;
    }
}


