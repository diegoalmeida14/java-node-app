package me.aluga.inventory.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produto")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Produto implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "tipo é obrigatório")
    private String tipo;
    @NotEmpty(message = "description é obrigatório")
    private String description;
    @NotEmpty(message = "nome é obrigatório")
    private String nome;
    @Column(nullable = true, name = "quantity")
    private int quantity;
   
}
