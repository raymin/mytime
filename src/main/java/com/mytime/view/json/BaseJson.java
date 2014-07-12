package com.mytime.view.json;


import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.io.Serializable;

public class BaseJson  implements Serializable {

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
