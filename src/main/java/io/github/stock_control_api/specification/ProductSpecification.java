package io.github.stock_control_api.specification;

import io.github.stock_control_api.dto.v1.product.ProductFilter;
import io.github.stock_control_api.entity.Product;
import io.github.stock_control_api.entity.ProductType;
import jakarta.persistence.criteria.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class ProductSpecification {

    public static Specification<Product> withFilter(ProductFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(StringUtils.isNotBlank(filter.name())){
                Expression<String> lowerName = cb.lower(root.get("name"));
                predicates.add(cb.like(lowerName, "%" + filter.name().toLowerCase() + "%"));
            }
            if(StringUtils.isNotBlank(filter.code())){
                Expression<String> lowerCode = cb.lower(root.get("code"));
                predicates.add(cb.like(lowerCode, "%" + filter.code().toLowerCase() + "%"));
            }
            if(StringUtils.isNotBlank(filter.productTypeName())){
                Join<Product, ProductType> productType = root.join("productType");
                Expression<String> lowerName = cb.lower(productType.get("name"));
                predicates.add(cb.like(lowerName, "%" + filter.productTypeName().toLowerCase() + "%"));
            }
            if(Objects.nonNull(filter.minPrice())){
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), filter.minPrice()));
            }
            if(Objects.nonNull(filter.maxPrice())){
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), filter.maxPrice()));
            }

            return cb.and(predicates.toArray(Predicate[]::new));
        };
    }

}
