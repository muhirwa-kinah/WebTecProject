package com.sf.stylefusion.dto;

import jakarta.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioDto {
    private int id;
    private String name;
    private String ownerEmail;
    private String height;
    private String weight;
    private byte[] pdf;
    private byte[] image;
    private String message;

}
