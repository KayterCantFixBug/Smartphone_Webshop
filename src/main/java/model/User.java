package model;

import jakarta.persistence.*;

import java.util.Date;

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
	@Column(name = "user_id")
	private int id;
	@Column(name = "phoneNumber")
	private String phoneNumber;
	@Column(name = "name")
	private String name;
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
	@Column(name = "image", columnDefinition = "BLOB")
	private byte[] image;
	@Column(name = "status")
	private Status status;
	@Column(name = "gender")
	private Gender gender;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public User() {

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
}