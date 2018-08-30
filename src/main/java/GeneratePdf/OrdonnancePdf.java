package GeneratePdf;

import java.awt.List;
import java.util.*;

import sn.gestion.clinique.entites.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.toolbox.plugins.Split;

public class OrdonnancePdf extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map model, Document document,
			PdfWriter arg2, HttpServletRequest arg3, HttpServletResponse response)
			throws Exception {
			
			response.setHeader("Content-Disposition", "attachment: filename=\"ordonnance.pdf\"");
			@SuppressWarnings("unchecked" )
			Ordonnance ord= (Ordonnance) model.get("ordonnance");
			Paragraph header=new Paragraph(new Chunk("\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Ordonnance\n\n",FontFactory.getFont(FontFactory.TIMES_ITALIC,22)));
			Paragraph date=new Paragraph(new Chunk("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"
					+ "								\t\t\t\t\t\t\t\t\t\t\t Notre Clinique, le "+ord.getDate(),FontFactory.getFont(FontFactory.TIMES_ITALIC,12)));
			Paragraph patient=null;
			if (ord.getPatient().getSexe().equals("Masculin")) {
				 patient=new Paragraph(new Chunk("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t M. "+ord.getPatient().getPrenom()+" "+ord.getPatient().getNom()+"\n\n",FontFactory.getFont(FontFactory.TIMES_ITALIC,14)));

			}else if (ord.getPatient().getSexe().equals("Féminin")) {
				 patient=new Paragraph(new Chunk("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Mme. "+ord.getPatient().getPrenom()+" "+ord.getPatient().getNom()+"\n\n\n",FontFactory.getFont(FontFactory.TIMES_ITALIC,14)));
			}else {
				 patient=new Paragraph(new Chunk("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t "+ord.getPatient().getPrenom()+" "+ord.getPatient().getNom()+"\n\n\n",FontFactory.getFont(FontFactory.TIMES_ITALIC,14)));

			}
			String[] tableau=ord.getMedicament().split(",");
			document.add(header);
			document.add(date);	
			document.add(patient);
			for (int i = 0; i < tableau.length; i++) {
				String indice=String.valueOf(i+1);
				Paragraph head=new Paragraph(new Chunk("\t\t\t\t\t\t\t"+indice+". "+tableau[i],FontFactory.getFont(FontFactory.HELVETICA,12)));
				document.add(head);
			}	
	}

}
