package br.com.yesv.ecommercems.adapters.out.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CURRENCY")
public class CurrencyEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -8999684259198101589L;

    @Id
    @Column(name = "CURRENCY_ID")
    private String currencyId;

    @Column(name = "NAME")
    private String name;

}