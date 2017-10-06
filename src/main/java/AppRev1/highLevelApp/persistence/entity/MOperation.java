package AppRev1.highLevelApp.persistence.entity;

import AppRev1.highLevelApp.persistence.entity.Role;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;

/**
 Опция
 */

@Entity
@Table(name = "operations")
public class MOperation implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "operationId", length = 6, nullable = false)
    private Long operationId;

    @Column(name = "description")
    private String text;

    @Column(name = "link")
    private String resourceLink;

    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setResource(File file){
        // add photo
    }

    private void setResourceLink(String resourceLink) {
        this.resourceLink = resourceLink;
    }

    public Long getOperationId() {
        return operationId;
    }

    public String getText() {
        return text;
    }

    public String getResourceLink() {
        return resourceLink;
    }
    @Override
    public boolean equals(Object obj) {
        Role other = (Role)obj;
        return this.getOperationId()==other.getId();
    }
    @Override
    public int hashCode()
    {
        int result = (int)(getOperationId()%Integer.MAX_VALUE);
        return result;
    }
}
