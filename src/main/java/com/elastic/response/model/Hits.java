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
public class Hits {

    @Setter
    @Getter
    private String _index;
    @Setter
    @Getter
    private String _type;
    @Setter
    @Getter
    private String _id;
    @Setter
    @Getter
    private double _score;
    @Setter
    @Getter
    private Source _source;
}
