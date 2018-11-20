package com.videoShop.product;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "TITLE" }))
public class Product implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -3578999599908919632L;

    public @JsonProperty @Version Long version;

    public @JsonProperty @LastModifiedDate LocalDateTime date;

    @JsonProperty @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @JsonProperty
    @NotEmpty
    @Size(max = 140)
    private String title;
    
    @JsonProperty
    @NotNull
    private Double price;

    @JsonProperty
    private Boolean enabled = false;
    
	@JsonProperty
    @OneToMany(fetch = FetchType.EAGER, mappedBy="product")
    private final Set<ProductID> productIds = new HashSet<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
    public Set<ProductID> getProductIds() {
        return productIds;
    }

    public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        return Objects.equals(getId(), other.getId()) && Objects.equals(getTitle(), other.getTitle());
    }
}
