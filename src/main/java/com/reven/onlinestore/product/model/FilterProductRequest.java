package com.reven.onlinestore.product.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.StringUtils;

@Data
@Accessors(chain = true)
public class FilterProductRequest {

    private String color;
    private String brand;
    private String name;

    public boolean isNotValid() {
        return StringUtils.isEmpty(this.brand)
                && StringUtils.isEmpty(this.color)
                && StringUtils.isEmpty(this.brand);
    }

}
