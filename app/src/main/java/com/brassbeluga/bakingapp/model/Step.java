package com.brassbeluga.bakingapp.model;

import lombok.Data;

@Data
public class Step {
    private int id;
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;
}
