package pack.protdoc.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by alexa on 19.04.2016.
 */
@Entity
public class BaseObject {
    private Integer id;

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
