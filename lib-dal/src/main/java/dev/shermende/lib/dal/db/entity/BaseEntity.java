package dev.shermende.lib.dal.db.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@MappedSuperclass
@RequiredArgsConstructor
public abstract class BaseEntity<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 3584406775766359057L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity<?> that = (BaseEntity<?>) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
