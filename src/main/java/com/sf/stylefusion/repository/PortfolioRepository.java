package com.sf.stylefusion.repository;

import com.sf.stylefusion.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository <Portfolio, Integer> {
    Optional<Portfolio> findPortfolioByOwnerEmail(String email);
}
