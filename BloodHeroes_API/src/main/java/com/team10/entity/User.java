package com.team10.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

//    @ElementCollection
//    @CollectionTable(name = "user_contacts", joinColumns = @JoinColumn(name = "user_id"))
//    private List<String> contacts;
	@Column(name = "contact", nullable = false)
	private String contacts;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private ROLE role;

}
