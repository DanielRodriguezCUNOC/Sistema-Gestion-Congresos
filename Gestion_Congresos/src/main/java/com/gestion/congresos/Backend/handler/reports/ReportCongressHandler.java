package com.gestion.congresos.Backend.handler.reports;

import java.util.List;

import com.gestion.congresos.Backend.db.controls.reporte.ReporteCongresosInstitucion;
import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.reports.GenerateReportCongressInstitution;

public class ReportCongressHandler {

    public boolean generateReportCongressInstitution() throws DataBaseException {

        GenerateReportCongressInstitution generateReport = new GenerateReportCongressInstitution();
        ReporteCongresosInstitucion reporteCongresosInstitucion = new ReporteCongresosInstitucion();

        List<List<Object>> data = reporteCongresosInstitucion.obtenerCongresosPorInstitucion();

        return generateReport.exportarTablaHTML(data);

    }

}
