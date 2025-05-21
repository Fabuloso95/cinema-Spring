package org.elis.service.impl;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import org.elis.dto.*;
import org.elis.model.*;
import org.elis.repository.BigliettoRepository;
import org.elis.service.ReportService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService
{
    private final BigliettoRepository bigliettoRepository;
    @SuppressWarnings("unused")
	private final ModelMapper modelMapper;

    public ReportServiceImpl(BigliettoRepository bigliettoRepository, ModelMapper modelMapper)
    {
        this.bigliettoRepository = bigliettoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReportVenditeDTO generateReportVendite()
    {
        List<Biglietto> allBiglietti = bigliettoRepository.findAll();

        double totaleRicavo = allBiglietti.stream()
                .mapToDouble(Biglietto::getPrezzoFinale)
                .sum();
        int totaleBigliettiVenduti = allBiglietti.size();

        ReportVenditeDTO report = new ReportVenditeDTO();
        report.setTotaleRicavo(totaleRicavo);
        report.setTotaleBigliettiVenduti(totaleBigliettiVenduti);

        report.setVenditePerFilm(getVenditePerFilm());
        report.setVenditePerPeriodo(getVenditePerPeriodo(null, null));
        return report;
    }

    @Override
    public List<VenditePerFilmDTO> getVenditePerFilm()
    {
        List<Biglietto> allBiglietti = bigliettoRepository.findAll();
        Map<Long, VenditePerFilmDTO> venditePerFilmMap = new HashMap<>();

        for (Biglietto biglietto : allBiglietti) 
        {
            Spettacolo spettacolo = biglietto.getSpettacolo();
            if (spettacolo != null) 
            {
                Film film = spettacolo.getFilm();
                if (film != null) 
                {
                    Long filmId = film.getId();

                    venditePerFilmMap.computeIfAbsent(filmId, id -> 
                    {
                        VenditePerFilmDTO dto = new VenditePerFilmDTO();
                        dto.setFilmId(filmId);
                        dto.setTitoloFilm(film.getTitolo());
                        dto.setTotaleBigliettiVenduti(0);
                        dto.setTotaleRicavo(0.0);
                        return dto;
                    });

                    VenditePerFilmDTO vendite = venditePerFilmMap.get(filmId);
                    vendite.setTotaleBigliettiVenduti(vendite.getTotaleBigliettiVenduti() + 1);
                    vendite.setTotaleRicavo(vendite.getTotaleRicavo() + biglietto.getPrezzoFinale());
                }
            }
        }
        return new java.util.ArrayList<>(venditePerFilmMap.values());
    }

    @Override
    public List<VenditePerPeriodoDTO> getVenditePerPeriodo(LocalDate from, LocalDate to)
    {
        List<Biglietto> bigliettiNelPeriodo;
        if (from != null && to != null) 
        {
            bigliettiNelPeriodo = bigliettoRepository.findBySpettacoloDataOraBetween(from.atStartOfDay(), to.atTime(23, 59, 59));
            bigliettiNelPeriodo = bigliettoRepository.findAll();
        } 
        else 
        {
            bigliettiNelPeriodo = bigliettoRepository.findAll();
        }

        Map<LocalDate, VenditePerPeriodoDTO> venditePerPeriodoMap = new HashMap<>();

        for (Biglietto biglietto : bigliettiNelPeriodo) 
        {
             Spettacolo spettacolo = biglietto.getSpettacolo();
             if (spettacolo != null) 
             {
                 LocalDate dataSpettacolo = spettacolo.getDataOra().toLocalDate();

                 venditePerPeriodoMap.computeIfAbsent(dataSpettacolo, data -> 
                 {
                     VenditePerPeriodoDTO dto = new VenditePerPeriodoDTO();
                     dto.setDataInizio(data);
                     dto.setDataFine(data);
                     dto.setTotaleBigliettiVenduti(0);
                     dto.setTotaleRicavo(0.0);
                     return dto;
                 });

                 VenditePerPeriodoDTO vendite = venditePerPeriodoMap.get(dataSpettacolo);
                 vendite.setTotaleBigliettiVenduti(vendite.getTotaleBigliettiVenduti() + 1);
                 vendite.setTotaleRicavo(vendite.getTotaleRicavo() + biglietto.getPrezzoFinale());
             }
        }

        return venditePerPeriodoMap.values().stream()
                .sorted(java.util.Comparator.comparing(VenditePerPeriodoDTO::getDataInizio))
                .collect(Collectors.toList());
    }
}