package com.arcs.cibus.server.domain;

import java.io.Serializable;

import javax.persistence.*;

import com.arcs.cibus.server.domain.enums.Profile;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.serializer.UserSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonSerialize(using = UserSerializer.class)
@Entity(name = "cibus_users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;    
	@Transient
	@Builder.Default
    private TipoSerializer tipoSerializer = TipoSerializer.FULL;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	private String name;
	private String login;
    @Builder.Default
	private boolean isFirstLogin = Boolean.TRUE;
	private String pass;

	@Enumerated(EnumType.STRING)
	private Profile profile;
	
	public boolean isNewUser() {
		return id == null || id < 1? true : false;
	}
	
	public boolean isProfileAdmin(){
		return this.profile.equals(Profile.ROLE_ADMIN);
	}	
}
