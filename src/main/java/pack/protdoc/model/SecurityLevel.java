package pack.protdoc.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by alexa on 19.04.2016.
 */
@Entity
@Table
public class SecurityLevel extends BaseObject implements Comparable<SecurityLevel> {
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
}
