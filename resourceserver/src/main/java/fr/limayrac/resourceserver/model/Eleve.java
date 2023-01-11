package fr.limayrac.resourceserver.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "eleve")
public class Eleve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eleve_id")
    private Integer id;

    @Column(name = "eleve_nom")
    private String nom;

    @Column(name = "eleve_prenom")
    private String prenom;
}
