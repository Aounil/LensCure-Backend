package org.soramed.LensCure.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.soramed.LensCure.order.Order;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data   // provide getter and setter methods and equals()
@Builder // This annotation allows for the creation of objects using the builder design pattern.
// Like this
// User user = User.builder()
//        .name("John")
//        .email("john@example.com")
//        .password("securePassword")
//        .role(Role.USER)
//        .build();
@NoArgsConstructor // Generates a no-argument constructor for the class.
// Use Case: Useful for frameworks that require a default constructor (e.g., JPA, Hibernate).
@AllArgsConstructor // Generates a constructor that takes parameters for all fields in the class.
@Entity // Specifies that the class is a JPA entity, meaning it represents a table in the database.
@Table(name = "_User_") // Create a database table for the class
@Getter
@Setter
public class User implements UserDetails { // makes you use a Spring Security User

    @Id // use the id as the key id for the class
    @GeneratedValue // auto generate the int id
    private int id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING) // use is when you are working with enum class
    private Role role;

    // Link User with their Orders
    // mappedBy = "user" means the 'user' field in Order owns the relationship
    // cascade = CascadeType.REMOVE means deleting User deletes all linked Orders
    // orphanRemoval = true means removing Order from this list will delete it from DB
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Order> orders;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return which role does the user have, wrapped as GrantedAuthority
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
