/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elastic.response.model;

import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author armdev
 */
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainHits {

    @Setter
    @Getter
    private int total;
    @Setter
    @Getter
    private double max_score;
    @Setter
    @Getter
    private List<Hits> hits;
}
