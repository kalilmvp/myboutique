package com.kmvpsolutions.ao.boutiquespringboot.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kmvpsolutions.ao.boutiquespringboot.commons.domain.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "customer")
public class Customer extends AbstractEntity {

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String telephone;
    @OneToMany
    @JsonIgnore
    private Set<Cart> carts = new HashSet<>();
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;
}
