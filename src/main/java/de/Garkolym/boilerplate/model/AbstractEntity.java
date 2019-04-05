package de.Garkolym.boilerplate.model;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class AbstractEntity implements Serializable, Comparable<AbstractEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Version
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public int compareTo(AbstractEntity o) {
        if (id == null && o.id != null) {
            return -1;
        } else if (id != null && o.id == null) {
            return 1;
        } else if (id == null && o.id == null) {
            return 0;
        }
        return (int) (id - o.id);
    }
}
