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
public class Shards {
    @Setter
    @Getter
    private int total;
    @Setter
    @Getter
    private int successful;
    @Setter
    @Getter
    private int failed;
}
