package com.sf.stylefusion.service;

import com.sf.stylefusion.dto.PortfolioDto;
import com.sf.stylefusion.model.Portfolio;

import java.util.List;
import java.util.Optional;

public interface PortfolioService {
    List<PortfolioDto> getAllPortfolios();
    Optional<Portfolio> getPortfolioById(int id);
    void savePortfolio(PortfolioDto portfolioDto);
    void deletePortfolioById(int id);
    void updatePortfolio( PortfolioDto portfolioDto);

    Optional<Portfolio> getPortfolioByEmail(String email);
}
