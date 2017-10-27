package AppRev1.highLevelApp.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "operators")
public class MOperator {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "operatorId", length = 6, nullable = false)
    private Long operatorId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="PERSON_ID")
    private Person person;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(targetEntity=MOperation.class, cascade = CascadeType.MERGE)
    @JoinTable(
            name="OPERATOR_OPERATION",
            joinColumns=@JoinColumn(name="OPERATOR_ID", referencedColumnName="operatorId"),
            inverseJoinColumns=@JoinColumn(name="OPERATION_ID", referencedColumnName="operationId"))
    private List<MOperation> operationsList = new ArrayList<>();

    @Getter
    @Setter
    @JsonIgnore
    @OneToMany(targetEntity = Admission.class,
            cascade = CascadeType.MERGE, mappedBy="mOperator")
    private List<Admission> admissions;

    public MOperator(Person person) {
        this.person = person;
    }

    public MOperator() {
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public Person getPerson() {
        return person;
    }

    public List<MOperation> getOperationsList() {
        return operationsList;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setOperationsList(List<MOperation> operationsList) {
        this.operationsList = operationsList;
    }

    @Override
    public boolean equals(Object obj) {
        Role other = (Role)obj;
        return this.getOperatorId()==other.getId();
    }
    @Override
    public int hashCode()
    {
        int result = (int)(getOperatorId()%Integer.MAX_VALUE);
        return result;
    }
}
