package models;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
	public enum Gender {
		MALE, FEMALE
	}

	public enum Role {
		USER, ADMIN
	}

	public enum Status {
		INACTIVE, ACTIVE
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "gender")
	private Gender gender;

	@Column(name = "role")
	private Role role;

	@Column(name = "password")
	private String password;

	@Column(name = "birthdate")
	private Date birthdate;

	@Column(name = "email")
	private String email;

	@Column(name = "code")
	private String code;

	@Column(name = "status")
	private Status status;

	@Column(name = "image")
	private String image;

	public User() {
		super();
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public User(String name, String email, String code) {
		super();
		this.name = name;
		this.email = email;
		this.code = code;
	}

	public User(String name, Role role, String email, String password, String code, Status status) {
		super();
		this.name = name;
		this.role = role;
		this.status = status;
		this.email = email;
		this.password = password;
		this.code = code;
	}

	public User(int id, String name, Gender gender, Role role, String password, Date birthdate, String email,
			String code, Status status, String image) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.role = role;
		this.password = password;
		this.birthdate = birthdate;
		this.email = email;
		this.code = code;
		this.status = status;
		this.image = image;
	}

}