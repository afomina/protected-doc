package pack.protdoc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by alexa on 19.04.2016.
 */
@Entity
@Table
public class SecurityLevel extends BaseObject implements Comparable<SecurityLevel> {
    private Integer id;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    private Integer level;
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public int compareTo(SecurityLevel o) {
        return getLevel().compareTo(o.getLevel());
    }

    @Override
    public String toString() {
        return value;
    }
}
