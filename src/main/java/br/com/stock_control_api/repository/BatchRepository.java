package br.com.stock_control_api.repository;

import br.com.stock_control_api.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepository extends JpaRepository<Batch, Long> {

}
