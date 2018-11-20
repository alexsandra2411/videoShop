package com.videoShop.productSite;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "HOST" }) )
public class ProductSite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -238166739235816553L;

	@JsonProperty
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonProperty
	@NotEmpty
	private String host;

	@JsonProperty
	private Boolean enabled = false;

	@JsonProperty
	private Boolean deleted = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getHost(), getEnabled());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		ProductSite other = (ProductSite) obj;
		return Objects.equals(getId(), other.getId()) 
				&& Objects.equals(getHost(), other.getHost())
				&& Objects.equals(getEnabled(), other.getEnabled());
	}

}
