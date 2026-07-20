package io.github.stock_control_api.specification;

import io.github.stock_control_api.dto.v1.batch.BatchFilter;
import io.github.stock_control_api.entity.Batch;
import io.github.stock_control_api.entity.Product;
import io.github.stock_control_api.entity.ProductType;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BatchSpecification {
    private BatchSpecification() {}

    public static Specification<Batch> withFilter(BatchFilter filter) {
        return (root, query, cb) ->
        {
            List<Predicate> predicates = new ArrayList<>();
            if(filter.validity() != null){
                predicates.add(cb.lessThanOrEqualTo(root.get("validity"), filter.validity()));
            }
            if(filter.minQuantity() != null){
                predicates.add(cb.greaterThanOrEqualTo(root.get("quantity"), filter.minQuantity()));
            }
            if(StringUtils.isNotBlank(filter.batchNumber())){
               addLike(predicates, cb, root.get("batchNumber"), filter.batchNumber());
            }
            if(filter.location() != null){
                predicates.add(cb.equal(root.get("location"), filter.location()));
            }
            if(filter.receivedAt() != null){
                predicates.add(cb.equal(root.get("receivedAt"), filter.receivedAt()));
            }

            Join<Batch, Product> product;

            if (StringUtils.isNotBlank(filter.productName())
                    || StringUtils.isNotBlank(filter.productCode())
                    || StringUtils.isNotBlank(filter.productType())) {

                product = root.join("product");

                if(StringUtils.isNotBlank(filter.productName())){
                    addLike(predicates, cb, product.get("name"), filter.productName());
                }
                if(StringUtils.isNotBlank(filter.productCode())){
                    addLike(predicates, cb, product.get("code"), filter.productCode());
                }
                if(StringUtils.isNotBlank(filter.productType())){
                    Join<Product, ProductType> productType = product.join("productType");
                    addLike(predicates, cb, productType.get("name"), filter.productType());
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static void addLike(
            List<Predicate> predicates,
            CriteriaBuilder cb,
            Expression<String> field,
            String value
    ){
        predicates.add(cb.like(cb.lower(field), "%" + value.toLowerCase() + "%"));
    }
}
