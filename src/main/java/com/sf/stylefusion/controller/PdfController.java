package com.sf.stylefusion.controller;
import com.itextpdf.text.DocumentException;
import com.sf.stylefusion.model.Portfolio;
import com.sf.stylefusion.repository.PortfolioRepository;
import com.sf.stylefusion.service.PdfService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class PdfController {
    private final PortfolioRepository portfolioRepository;
    private final PdfService pdfService;


    public PdfController(PortfolioRepository portfolioRepository, PdfService pdfService) {
        this.portfolioRepository = portfolioRepository;
        this.pdfService = pdfService;
    }


    @GetMapping("/download-pdf")
    public ResponseEntity<byte[]> downloadPdf() throws DocumentException {
        List<Portfolio> portfolios = portfolioRepository.findAll();

        StringBuilder dataBuilder = new StringBuilder();
        for (Portfolio portfolio : portfolios) {
            dataBuilder.append("ID: ").append(portfolio.getId()).append("\n")
                    .append("Owner Email: ").append(portfolio.getOwnerEmail()).append("\n")
                    .append("Height: ").append(portfolio.getHeight()).append("\n")
                    .append("Weight: ").append(portfolio.getWeight()).append("\n")
                    .append("Message: ").append(portfolio.getMessage()).append("\n")
                    .append("Name: ").append(portfolio.getName()).append("\n\n");
        }

        String data = dataBuilder.toString();

        byte[] pdfBytes = pdfService.generatePdf(data);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "portfolios.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(pdfBytes);
    }


    @GetMapping("/download-modelPdf/{email}")
    public ResponseEntity<byte[]> downloadModelPdf(@PathVariable("email") String email) throws DocumentException {
        Optional<Portfolio> portfolioOptional = portfolioRepository.findPortfolioByOwnerEmail(email);
        if (!portfolioOptional.isPresent()) {
            // Handle the case when the portfolio does not exist
            // Return an appropriate response or error message
            return ResponseEntity.notFound().build();
        }

        Portfolio portfolio = portfolioOptional.get();

        StringBuilder dataBuilder = new StringBuilder();
        dataBuilder.append("ID: ").append(portfolio.getId()).append("\n")
                .append("Owner Email: ").append(portfolio.getOwnerEmail()).append("\n")
                .append("Height: ").append(portfolio.getHeight()).append("\n")
                .append("Weight: ").append(portfolio.getWeight()).append("\n")
                .append("Message: ").append(portfolio.getMessage()).append("\n")
                .append("Name: ").append(portfolio.getName()).append("\n\n");

        String data = dataBuilder.toString();

        byte[] pdfBytes = pdfService.generatePdf(data);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "ModelPortfolio.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(pdfBytes);
    }}