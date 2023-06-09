package com.trunh.entity;

import javax.persistence.*;

import lombok.Data;

import java.util.List;

@Entity
@Table(name = "account")
@Data
public class Account{
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "username", length = 50, nullable = false)
	private String username;
	
	@Column(name = "password", length = 150, nullable = false)
	private String password;
	
	@Column(name = "first_name", length = 50, nullable = false)
	private String firstName;
	
	@Column(name = "last_name", length = 50, nullable = false)
	private String lastName;
	
	@Column(name = "email", length = 50, nullable = false)
	private String email;

	@Column(name = "phone", length = 10, nullable = false)
	private String phone;

	@Column(name = "role", columnDefinition = "ENUM('ADMIN', 'EMPLOYEE')")
	@Enumerated(EnumType.STRING)
	private AccountRole role;
	
	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private AccountStatus status;
	
	@Column(name = "avatar_url", length = 250)
	private String avatarUrl;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
	List<CartItem> cartItems;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
	List<Order> orders;

	public enum AccountStatus {
		NOT_ACTIVE, ACTIVE
	}
	
	public enum AccountRole {
		ADMIN, EMPLOYEE;
		public static AccountRole toEnum(String name) {
			for (AccountRole item : AccountRole.values()) {
				if (item.toString().equals(name))
					return item;
			}
			return null;
		}
	}
}
