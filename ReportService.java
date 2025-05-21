package org.elis.service;

import org.elis.dto.ReportVenditeDTO;
import org.elis.dto.VenditePerFilmDTO;
import org.elis.dto.VenditePerPeriodoDTO;
import java.time.LocalDate;
import java.util.List;

public interface ReportService 
{
    ReportVenditeDTO generateReportVendite();
    List<VenditePerFilmDTO> getVenditePerFilm();
    List<VenditePerPeriodoDTO> getVenditePerPeriodo(LocalDate from, LocalDate to);
}