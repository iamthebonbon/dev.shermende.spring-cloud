package dev.shermende.authorization.db.entity;

import dev.shermende.lib.dal.db.entity.TimedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends TimedEntity<Long> {
    private static final long serialVersionUID = -2683732998281446313L;

    @NotEmpty
    @Column(unique = true)
    @Size(min = 3, max = 64)
    private String email;

    @NotEmpty
    @Size(min = 16, max = 128)
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (!Objects.equals(email, user.email)) return false;
        return Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
