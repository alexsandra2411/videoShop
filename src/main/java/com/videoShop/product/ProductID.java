package com.videoShop.product;

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
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "ASIN", "GTIN", "ISBN" }))
public class ProductID implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 7195118781574436512L;

    public @JsonProperty @Version Long version;

    public @JsonProperty @LastModifiedDate LocalDateTime date;
    @JsonProperty
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    @Size(max = 20)
    @JsonProperty
    private String asin;
    @Size(max = 20)
    @JsonProperty
    private String gtin;
    @Size(max = 20)
    @JsonProperty
    private String isbn;

    @JsonProperty
    private Boolean enabled = false;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonProperty
    private Product product;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAsin() {
        return asin;
    }
    public void setAsin(String asin) {
        this.asin = asin;
    }
    public String getGtin() {
        return gtin;
    }
    public void setGtin(String gtin) {
        this.gtin = gtin;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAsin(), getGtin(), getIsbn(), getProduct(), getEnabled());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        ProductID other = (ProductID) obj;
        return Objects.equals(getId(), other.getId())
                && Objects.equals(getIsbn(), other.getIsbn())
                && Objects.equals(getAsin(), other.getAsin())
                && Objects.equals(getGtin(), other.getGtin())
                && Objects.equals(getProduct(), other.getProduct())
                && Objects.equals(getEnabled(), other.getEnabled());
    }
}
