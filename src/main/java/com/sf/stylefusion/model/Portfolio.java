package com.sf.stylefusion.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String ownerEmail;
    private String height;
    private String weight;
    private byte[] pdf;
    private byte[] image;
    private String message;

}
