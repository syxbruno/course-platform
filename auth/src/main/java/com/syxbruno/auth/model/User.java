package com.syxbruno.auth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @Column(unique = true)
  private String email;
  private String password;
  @Enumerated(EnumType.STRING)
  private Role role;
  private LocalDateTime dateRegister;
  private LocalDateTime lastLogin;

  @PrePersist
  public void userDateRegister() {

    this.dateRegister = LocalDateTime.now();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    if (this.role == Role.INSTRUCTOR) {

      return List.of(new SimpleGrantedAuthority("ROLE_INSTRUCTOR"), new SimpleGrantedAuthority("ROLE_STUDENT"));
    }

    return List.of(new SimpleGrantedAuthority("ROLE_STUDENT"));
  }

  @Override
  public String getUsername() {
    return this.email;
  }
}
