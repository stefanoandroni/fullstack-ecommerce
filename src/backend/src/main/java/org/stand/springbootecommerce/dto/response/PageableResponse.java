package org.stand.springbootecommerce.dto.response;

import lombok.*;
import org.stand.springbootecommerce.entity.user.Product;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageableResponse<T> {

    private long total;
    private List<T> list;

}
