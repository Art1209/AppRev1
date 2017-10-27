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
    @Column(name = "admissionId", length = 6, nullable = false)
    private Long admissionId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="OPERATOR_ID")
    private MOperator mOperator;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="PERSON_ID")
    private Person person;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="OPERATION_ID")
    private MOperation mOperation;

    @Column(name = "datetime")
    private Timestamp date;

    public Admission() {
    }

    public Admission(MOperation MOperation, MOperator mOperator, Person person, Timestamp date) {
        this.person = person;
        this.mOperation = MOperation;
        this.mOperator = mOperator;
        this.date = date;
    }

    public Long getAdmissionId() {
        return admissionId;
    }

    public MOperator getmOperator() {
        return mOperator;
    }

    public Person getPerson() {
        return person;
    }

    public MOperation getMOperation() {
        return mOperation;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setAdmissionId(Long admissionId) {
        this.admissionId = admissionId;
    }

    public void setmOperator(MOperator mOperator) {
        this.mOperator = mOperator;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setMOperation(MOperation MOperation) {
        this.mOperation = MOperation;
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


