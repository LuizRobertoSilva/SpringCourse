package com.lrsilva.projetospring.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lrsilva.projetospring.domain.enums.Profile;

public class UserSS implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String password;
	private String email;
	private Collection<? extends GrantedAuthority> authorities;

	public UserSS() {
	}

	public UserSS(Integer id, String password, String email, Set<Profile> profiles) {
		super();
		this.id = id;
		this.password = password;
		this.email = email;

		this.authorities = profiles.stream().map(x -> new SimpleGrantedAuthority(x.getDescription()))
				.collect(Collectors.toList());
	}

	public Integer getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
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

	public boolean hasRole(Profile profile) {
		return getAuthorities().contains(new SimpleGrantedAuthority(profile.getDescription()));

	}

}
