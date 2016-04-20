package pack.protdoc.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by alexa on 19.04.2016.
 */
@Entity
@Table
public class SecurityLevel extends BaseObject {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
