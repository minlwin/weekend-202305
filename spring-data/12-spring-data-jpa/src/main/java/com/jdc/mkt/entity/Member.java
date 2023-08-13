package com.jdc.mkt.entity;

import static javax.persistence.EnumType.STRING;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.MapsId;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "member_tbl")
@SecondaryTables({
	@SecondaryTable(name = "login_info",
			indexes = { @Index(columnList = "email") },
			uniqueConstraints = {
					@UniqueConstraint(columnNames = "loginId") }
		),
	@SecondaryTable(name = "address_tbl")
})
public class Member implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "member_name",nullable = false,length = 30,unique = true)
	private String name;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dob;
	@Enumerated(STRING)
	private Role role;
	
	@Column(table = "login_info")
	private String loginId;
	@Column(table = "login_info")
	private String password;
	@Column(table = "login_info")
	private String email;
	@Column(table = "address_tbl")
	private String street;
	@Column(table = "address_tbl")
	private String township;
	@Column(table = "address_tbl")
	private String city;
	
	@ElementCollection
	@CollectionTable(name = "member_info_tbl", 
	joinColumns = {
			@JoinColumn(name = "member_id",nullable = false),
			})
	@Enumerated(STRING)
	private List<Role> infos;
	
	@ElementCollection
	@CollectionTable(name = "member_hobbies_tbl", 
	joinColumns = @JoinColumn(name = "member_id", 
	referencedColumnName = "id"))
	@OrderBy("desc")
	private Set<String> hobbies;
	
	@MapKeyColumn(name = "member_map_key")
	@MapKeyEnumerated(STRING)
	@CollectionTable(name = "member_maps_tbl",
			joinColumns = @JoinColumn(name = "member_id"))
	@ElementCollection
	private Map<Role, String> maps;
	
	
	@ElementCollection
	@CollectionTable(name = "member_ids_tbl",
	joinColumns = @JoinColumn(name = "member_id"))
	private List<MemberPk> memberIds;
	
	@OrderColumn
	@ElementCollection
	private List<String> messages;
	
	@Transient
	private boolean isDeleted;
	
	public enum Role{
		ADMIN,CUSTOMER;
	}
	

	public Member(String name, Date dob, String loginId, String password, String email) {
		super();
		this.name = name;
		this.dob = dob;
		this.loginId = loginId;
		this.password = password;
		this.email = email;
	}

}
