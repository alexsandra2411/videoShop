package com.videoShop.video;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.videoShop.common.domains.Language;
import com.videoShop.product.Product;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "videoLink", "deleted" }) )
public class Video implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6093069180235834104L;

	@JsonProperty
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonProperty
	private LocalDateTime date;

	@JsonProperty
	@NotEmpty
	@Size(max = 140)
	private String title;

	@JsonProperty
	@Max(5)
	@Min(0)
	private Integer rating = 0;

	@JsonProperty
	@Min(0)
	private Integer helpful = 0;

	@JsonProperty
	@Min(0)
	private Integer helpless = 0;

	@JsonProperty
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	private Language language;

	@JsonProperty
	private Boolean enabled = false;

	@JsonProperty
	private Boolean deleted = false;

	@JsonProperty
	@NotEmpty
	private String videoLink;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	private Product product;

	public Product getProduct() {
		return product;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Integer getRating() {
		return rating;
	}

	public Integer getHelpful() {
		return helpful;
	}

	public Integer getHelpless() {
		return helpless;
	}

	public Language getLanguage() {
		return language;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public String getVideoLink() {
		return videoLink;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public void setHelpful(Integer helpful) {
		this.helpful = helpful;
	}

	public void setHelpless(Integer helpless) {
		this.helpless = helpless;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getVideoLink());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Video other = (Video) obj;
		return Objects.equals(getId(), other.getId()) && Objects.equals(getVideoLink(), other.getVideoLink());
	}
}
