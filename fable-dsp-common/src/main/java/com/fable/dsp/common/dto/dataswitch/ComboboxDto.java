package com.fable.dsp.common.dto.dataswitch;

import java.io.Serializable;
import java.util.List;


public class ComboboxDto implements Serializable {


    private static final long serialVersionUID = -3474136719613691866L;

    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
