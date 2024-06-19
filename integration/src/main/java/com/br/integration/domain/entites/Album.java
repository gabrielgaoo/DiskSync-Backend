package com.br.integration.domain.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ALBUM")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME", nullable = false, length = 150)
    private String name;
    @Column(name = "ID_SPOTIFY", nullable = false, length = 150)
    private String idSpotify;
    @Column(name = "ARTIST_NAME", nullable = false, length = 150)
    private String imageUrl;
    @Column(name = "VALUE", nullable = false)
    private BigDecimal value;
    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private User user;
}








