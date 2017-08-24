package com.tendero.kennyyim.tendero.ui.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.tendero.kennyyim.tendero.R;
import com.tendero.kennyyim.tendero.model.FirebaseReferences;
import com.tendero.kennyyim.tendero.model.Solicitud;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 */
public class PdfFragment extends Fragment {

    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);


    private Button btnReporte;

    FirebaseDatabase database;
    DatabaseReference solicitudesRef;

    private List<Solicitud> list;

    private   File myFile;

    public PdfFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pdf, container, false);


        database = FirebaseDatabase.getInstance();
        solicitudesRef = database.getReference(FirebaseReferences.SOLICITUDES_REFERENCE);

        solicitudesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("PRODUCT",dataSnapshot.toString());

                list = new ArrayList<Solicitud>();
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    list.add(child.getValue(Solicitud.class));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnReporte = (Button)view.findViewById(R.id.btn_reporte);

        btnReporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    createPdf();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }


    private void createPdf() throws FileNotFoundException, DocumentException {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/reporte_");

        myDir.mkdirs();

        File pdfFolder = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), "pdfdemo");
        if (!pdfFolder.exists()) {
            pdfFolder.mkdir();
            Log.i("TAG", "Pdf Directory created");
        }

        //Create time stamp
        Date date = new Date() ;
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date);

        myFile = new File(myDir + timeStamp + ".pdf");

        OutputStream output = new FileOutputStream(myFile);

        Document document = new Document();

        //Step 2
        PdfWriter.getInstance(document, output);

        //Step 3
        document.open();

        Anchor anchor = new Anchor("Solicitudes", catFont);
        anchor.setName("Solicitudes");

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph("Solicitudes sin resolver ", subFont);
        Section subCatPart = catPart.addSection(subPara);

        int i = 0;
        for (Solicitud solicitud : list){
            if (solicitud.getFechaFinal().equals("")){
                i++;
                String fechaFin = "Sin finalizar";
                String responsable = " sin Asignar";
                if (!solicitud.getResponsable().equals("")){
                    responsable = " Asignada a :"+solicitud.getResponsable();
                }
                subCatPart.add(new Paragraph("Solicitud "+String.valueOf(i)+" : "+ solicitud.getTextSolicitud()+ " /Fecha Creacion :"+
                        solicitud.getFechaInicio() +fechaFin + responsable));
            }
        }
        subPara = new Paragraph("Solicitudes Finalizadas", subFont);
        subCatPart = catPart.addSection(subPara);

        i = 0;
        for (Solicitud solicitud : list){
            if (!solicitud.getFechaFinal().equals("")){
                i++;
                String fechaFin = " Finalizada el : "+ solicitud.getFechaFinal() ;
                String responsable = " sin Asignar";
                if (!solicitud.getResponsable().equals("")){
                    responsable = " Asignada a :"+solicitud.getResponsable();
                }
                subCatPart.add(new Paragraph("Solicitud "+String.valueOf(i)+" : "+ solicitud.getTextSolicitud()+ " /Fecha Creacion :"+
                        solicitud.getFechaInicio() +fechaFin + responsable));
            }
        }


        // add a list
        Paragraph paragraph = new Paragraph();
        subCatPart.add(paragraph);

        // add a table
        createTable(subCatPart);

        //Step 4 Add content
        document.add(catPart);
        //document.add(new Paragraph("LOOOOOOOOOOOOOOOOOOOOO"));

        //Step 5: Close the document
        document.close();

        viewPdf();

    }

    private void createTable(Section subCatPart)
            throws BadElementException {
        PdfPTable table = new PdfPTable(5);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

        PdfPCell c1 = new PdfPCell(new Phrase("Id solicitud"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Solicitud  "));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Fecha creacion"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Responsable"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Fecha Finalizacion"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(c1);
        table.setHeaderRows(1);

        for (Solicitud solicitud : list){
            table.addCell(solicitud.getId());
            table.addCell(solicitud.getTextSolicitud());
            table.addCell(solicitud.getFechaInicio());
            table.addCell(solicitud.getResponsable());
            table.addCell(solicitud.getFechaFinal());
        }

        subCatPart.add(table);

    }


    private void viewPdf(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri photoURI = FileProvider.getUriForFile(getActivity(), getActivity().getApplicationContext().getPackageName() + ".provider", myFile);
        intent.setDataAndType(photoURI, "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    private void emailNote() {

    }

}
