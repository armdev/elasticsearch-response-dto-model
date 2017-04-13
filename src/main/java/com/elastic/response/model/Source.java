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
public class Source {

    @Setter
    @Getter
    private String userId;
    @Setter
    @Getter
    private String firstname;
    @Setter
    @Getter
    private String lastname;
    @Setter
    @Getter
    private String email;
    @Setter
    @Getter
    private int age;
}
