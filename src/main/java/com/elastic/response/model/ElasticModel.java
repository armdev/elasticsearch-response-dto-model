package com.elastic.response.model;

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
public class ElasticModel {
    
    @Setter
    @Getter
    private int took;
    @Setter
    @Getter
    private boolean timed_out;
    @Setter
    @Getter
    private Shards _shards;    
    @Setter
    @Getter
    private MainHits hits;
    
}
