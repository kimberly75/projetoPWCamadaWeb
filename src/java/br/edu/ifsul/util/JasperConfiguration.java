/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.util;

import java.io.File;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author kimberly.geremia
 */
public class JasperConfiguration {

    public static void imprimeRelatorio(String nomeRelatorio, Map<String, Object> parametros, List<? extends Object> lista) {

        try {

            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();

            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

            String path = servletContext.getRealPath("/WEB-INF/relatorios/");
            parametros.put("SUBREPORT_DIR", path + File.separator);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
            JasperPrint jasperPrint = JasperFillManager.fillReport(servletContext.getRealPath("/WEB-INF/relatorios/") + File.separator + nomeRelatorio + ".jasper", parametros, dataSource);

            byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);

            int codigo = (int) (Math.random() * 1000);

            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", ("inline;filename=realtorio_" + codigo + ".pdf"));
            response.getOutputStream().write(pdf);
            response.getCharacterEncoding();
            facesContext.responseComplete();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
