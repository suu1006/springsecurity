package com.example.javamvc.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private Date regDt;

    private Date modDt;

}
