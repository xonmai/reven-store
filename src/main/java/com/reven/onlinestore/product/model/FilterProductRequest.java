package com.reven.onlinestore.product.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Builder
@Getter
public class FilterProductRequest {

    private String color;
    private String brand;
    private String name;

    public boolean isValid() {
        return StringUtils.isEmpty(this.brand)
                && StringUtils.isEmpty(this.color)
                && StringUtils.isEmpty(this.brand);
    }

}
