package com.sf.stylefusion.service;

import com.sf.stylefusion.dto.PortfolioDto;
import com.sf.stylefusion.model.Portfolio;
import com.sf.stylefusion.repository.PortfolioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PortfolioServiceImpl implements PortfolioService {
    private final PortfolioRepository portfolioRepository;

    public PortfolioServiceImpl(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    @Override
    public List<PortfolioDto> getAllPortfolios() {
         List<Portfolio> portfolios = portfolioRepository.findAll();
         return portfolios.stream()
                 .map(this::mapToPortfolioDto)
                 .collect(Collectors.toList());
//        return portfolioRepository.findAll();

    }
    private PortfolioDto mapToPortfolioDto(Portfolio portfolio){
        PortfolioDto portfolioDto = new PortfolioDto();
        portfolioDto.setId(portfolio.getId());
        portfolioDto.setName(portfolio.getName());
        portfolioDto.setOwnerEmail(portfolio.getOwnerEmail());
        portfolioDto.setHeight(portfolio.getHeight());
        portfolioDto.setWeight(portfolio.getWeight());
        portfolioDto.setPdf(portfolio.getPdf());
        portfolioDto.setImage(portfolio.getImage());
        portfolioDto.setMessage(portfolio.getMessage());
        return portfolioDto;
    }
    @Override
    public Optional<Portfolio> getPortfolioById(int id) {
        return portfolioRepository.findById(id);
    }

    @Override
    public void savePortfolio(PortfolioDto portfolioDto) {
        Portfolio portfolio= mapToPortfolio(portfolioDto);
        portfolioRepository.save(portfolio);
    }
    private Portfolio mapToPortfolio(PortfolioDto portfolioDto){
        Portfolio portfolio = new Portfolio();
        portfolio.setId(portfolioDto.getId());
        portfolio.setName(portfolioDto.getName());
        portfolio.setOwnerEmail(portfolioDto.getOwnerEmail());
        portfolio.setHeight(portfolioDto.getHeight());
        portfolio.setWeight(portfolioDto.getWeight());
        portfolio.setPdf(portfolioDto.getPdf());
        portfolio.setImage(portfolioDto.getImage());
        portfolio.setMessage(portfolioDto.getMessage());
        return portfolio;
    }
    @Override
    public void deletePortfolioById(int id) {
        portfolioRepository.deleteById(id);

    }

    @Override
    public void updatePortfolio(PortfolioDto portfolioDto) {
        Portfolio existingPortfolio = portfolioRepository.findById(portfolioDto.getId())
                .orElseThrow(()-> new EntityNotFoundException("Portfolio not found"));
        Portfolio updatedPortfolio = mapToPortfolio(portfolioDto);
        updatedPortfolio.setId(existingPortfolio.getId());
        portfolioRepository.save(updatedPortfolio);

    }

    @Override
    public Optional<Portfolio> getPortfolioByEmail(String email) {
        return portfolioRepository.findPortfolioByOwnerEmail(email);
    }
}
