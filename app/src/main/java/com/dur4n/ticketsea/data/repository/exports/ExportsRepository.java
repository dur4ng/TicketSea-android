package com.dur4n.ticketsea.data.repository.exports;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import com.dur4n.ticketsea.data.dao.TicketDAO;
import com.dur4n.ticketsea.data.model.Ticket;
import com.dur4n.ticketsea.data.repository.ticket.TicketLocalRepository;
import com.dur4n.ticketsea.data.room.LocalDB;
import com.dur4n.ticketsea.ui.preferences.settings.SettingsContract;
import com.google.gson.Gson;
import com.google.zxing.WriterException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class ExportsRepository implements SettingsContract.Repository {

    static ExportsRepository exportsRepository;
    static TicketDAO ticketDAO;

    public ExportsRepository() {
        ticketDAO = LocalDB.getDatabase().ticketDAO();
    }

    public static ExportsRepository getInstance() {
        if (exportsRepository == null) {
            exportsRepository = new ExportsRepository();
        }
        return exportsRepository;
    }

    public List<Ticket> getTickets() {
        List<Ticket> ticketList = null;
        try {
            ticketList = LocalDB.databaseWriteExecutor.submit(() -> ticketDAO.select()).get();
        } catch (ExecutionException e) {
            ticketList = null;
        } catch (InterruptedException e) {
            ticketList = null;
        }
        return ticketList;
    }

    @Override
    public void exportTicketsJson(SettingsContract.InteractorListener listener, Context context) {
        Log.d("al", "algosdfa");

        String file;

        List<Ticket> ticketList = null;
        ticketList = getTickets();
        if (ticketList != null) {
            Gson gson = new Gson();
            String json = gson.toJson(ticketList);
            try {
                String localFilename = "tickets.json";
                File absolutePathFile = new File("/data/data/com.dur4n.ticketsea", localFilename);

                file = absolutePathFile.getAbsolutePath();

                FileWriter fw = new FileWriter(file);
                fw.write(json);
                fw.close();

                absolutePathFile.createNewFile();
            } catch (FileNotFoundException e) {
                listener.onFailure("Error in the exportation process");
                return;
            } catch (IOException e) {
                listener.onFailure("Error in the exportation process");
                return;
            }
        } else {
            listener.onFailure("No tickets");
            return;
        }
        listener.OnSuccess("Exported: "+ file);
    }

    @Override
    public void exportTicketsCSV(SettingsContract.InteractorListener listener, Context context) {
        String file;

        List<Ticket> ticketList = null;
        ticketList = getTickets();
        if (ticketList != null) {
            String CSV_SEPARATOR = ",";
            StringBuffer oneLine = null;
            try {
                oneLine = new StringBuffer();
                for (Ticket ticket : ticketList) {
                    oneLine.append(ticket.getId());
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(ticket.getReferenceCode());
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(ticket.getEventId());
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(ticket.getEventName());
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(ticket.getPrice());
                    oneLine.append("\n");
                }


            } catch (Exception e) {
                listener.onFailure("Error in the exportation process");
                return;
            }
            try {
                //context.openFileOutput("tickets.csv", Context.MODE_WORLD_WRITEABLE).write(oneLine.toString().getBytes(StandardCharsets.UTF_8));

                String documentsPath = Environment.getDataDirectory().getPath();//GetFolderPath(Environment. SpecialFolder. MyPictures);
                File path = new File(documentsPath);
                String localFilename = "tickets.csv";
                File absolutePathFile = new File("/data/data/com.dur4n.ticketsea", localFilename);

                file = absolutePathFile.getAbsolutePath();

                FileWriter fw = new FileWriter(absolutePathFile.getAbsoluteFile());
                fw.write(oneLine.toString());
                fw.close();

                absolutePathFile.createNewFile();

            } catch (FileNotFoundException e) {
                listener.onFailure("Error in the exportation process");
                return;
            } catch (IOException e) {
                listener.onFailure("Error in the exportation process");
                return;
            }
        } else {
            listener.onFailure("No tickets");
            return;
        }
        listener.OnSuccess("Exported: "+ file);
    }

    @Override
    public void exportTicketsXML(SettingsContract.InteractorListener listener, Context context) {
        List<Ticket> ticketList = null;
        ticketList = getTickets();

        String file;

        if (ticketList != null) {
            String abre = "<";
            String cierre = ">";
            String abreFinal = "</";
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(abre);
            oneLine.append("tickets");
            oneLine.append(cierre);
            for (Ticket ticket : ticketList) {
                oneLine.append(abre);
                oneLine.append("ticket");
                oneLine.append(cierre);

                oneLine.append(abre);
                oneLine.append("id");
                oneLine.append(cierre);
                oneLine.append(ticket.getId());
                oneLine.append(abreFinal);
                oneLine.append("id");
                oneLine.append(cierre);

                oneLine.append(abre);
                oneLine.append("referenceCode");
                oneLine.append(cierre);
                oneLine.append(ticket.getReferenceCode());
                oneLine.append(abreFinal);
                oneLine.append("referenceCode");
                oneLine.append(cierre);

                oneLine.append(abre);
                oneLine.append("eventId");
                oneLine.append(cierre);
                oneLine.append(ticket.getEventId());
                oneLine.append(abreFinal);
                oneLine.append("eventId");
                oneLine.append(cierre);

                oneLine.append(abre);
                oneLine.append("eventName");
                oneLine.append(cierre);
                oneLine.append(ticket.getEventName());
                oneLine.append(abreFinal);
                oneLine.append("eventName");
                oneLine.append(cierre);

                oneLine.append(abre);
                oneLine.append("price");
                oneLine.append(cierre);
                oneLine.append(ticket.getPrice());
                oneLine.append(abreFinal);
                oneLine.append("price");
                oneLine.append(cierre);

                oneLine.append(abreFinal);
                oneLine.append("ticket");
                oneLine.append(cierre);
            }
            oneLine.append(abreFinal);
            oneLine.append("tickets");
            oneLine.append(cierre);

            try {
                //context.openFileOutput("tickets.xml", Context.MODE_WORLD_WRITEABLE).write(oneLine.toString().getBytes(StandardCharsets.UTF_8));
                String localFilename = "tickets.xml";
                File absolutePathFile = new File("/data/data/com.dur4n.ticketsea", localFilename);

                file = absolutePathFile.getAbsolutePath();

                FileWriter fw = new FileWriter(file);
                fw.write(oneLine.toString());
                fw.close();

                absolutePathFile.createNewFile();
            } catch (FileNotFoundException e) {
                listener.onFailure("Error in the exportation process");
                return;
            } catch (IOException e) {
                listener.onFailure("Error in the exportation process");
                return;
            }
        } else {
            listener.onFailure("No tickets");
            return;
        }
        listener.OnSuccess("Exported: " + file);
    }

    @Override
    public void exportTicketsQR(SettingsContract.InteractorListener listener, Context context) {
        List<Ticket> ticketList = null;
        String file;
        ticketList = getTickets();
        if (ticketList != null) {
            for (Ticket ticket : ticketList) {
                // setting this dimensions inside our qr code
                // encoder to generate our qr code.
                Gson gson = new Gson();
                String json = gson.toJson(ticketList);

                QRGEncoder qrgEncoder = new QRGEncoder(json, null, QRGContents.Type.TEXT, 200);

                Bitmap bitmap = null;
                try {
                    // getting our qrcode in the form of bitmap.
                    bitmap = qrgEncoder.encodeAsBitmap();
                    String localFilename = "tickets.bmp";
                    File absolutePathFile = new File("/data/data/com.dur4n.ticketsea", localFilename);

                    file = absolutePathFile.getAbsolutePath();

                    FileWriter fw = new FileWriter(file);
                    fw.write(bitmap.toString());
                    fw.close();

                    absolutePathFile.createNewFile();
                } catch (WriterException e) {
                    listener.onFailure("Error in the exportation process");
                    return;
                } catch (FileNotFoundException e) {
                    listener.onFailure("Error in the exportation process");
                    return;
                } catch (IOException e) {
                    listener.onFailure("Error in the exportation process");
                    return;
                }
            }
        } else {
            listener.onFailure("No tickets");
            return;
        }
        listener.OnSuccess("Exported");
    }
    /*
    public void exportTicketsJson(List<Ticket> ticketList){
        Gson gson = new Gson();
        String json = gson.toJson(ticketList);
        try {
            getContext().openFileOutput("tickets.json", Context.MODE_WORLD_WRITEABLE).write(json.getBytes(StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void exportTicketsCSV(List<Ticket> ticketList){
        String CSV_SEPARATOR = ",";
        StringBuffer oneLine = null;
        try
        {
            //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("products.csv"), "UTF-8"));
            oneLine = new StringBuffer();
            for (Ticket ticket : ticketList)
            {
                oneLine.append(ticket.getId());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(ticket.getReferenceCode());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(ticket.getEventId());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(ticket.getEventName());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(ticket.getPrice());
                oneLine.append("\n");
            }


        } catch (Exception e){

        }
        try {
            getContext().openFileOutput("tickets.csv", Context.MODE_WORLD_WRITEABLE).write(oneLine.toString().getBytes(StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void exportTicketsXML(List<Ticket> ticketList){

        String abre = "<";
        String cierre = ">";
        String abreFinal = "</";
        StringBuffer oneLine = new StringBuffer();
        oneLine.append(abre);
        oneLine.append("tickets");
        oneLine.append(cierre);
        for (Ticket ticket : ticketList)
        {
            oneLine.append(abre);
            oneLine.append("ticket");
            oneLine.append(cierre);

            oneLine.append(abre);
            oneLine.append("id");
            oneLine.append(cierre);
            oneLine.append(ticket.getId());
            oneLine.append(abreFinal);
            oneLine.append("id");
            oneLine.append(cierre);

            oneLine.append(abre);
            oneLine.append("referenceCode");
            oneLine.append(cierre);
            oneLine.append(ticket.getReferenceCode());
            oneLine.append(abreFinal);
            oneLine.append("referenceCode");
            oneLine.append(cierre);

            oneLine.append(abre);
            oneLine.append("EventId");
            oneLine.append(cierre);
            oneLine.append(ticket.getEventId());
            oneLine.append(abreFinal);
            oneLine.append("EventId");
            oneLine.append(cierre);

            oneLine.append(abre);
            oneLine.append("eventName");
            oneLine.append(cierre);
            oneLine.append(ticket.getEventName());
            oneLine.append(abreFinal);
            oneLine.append("eventName");
            oneLine.append(cierre);

            oneLine.append(abre);
            oneLine.append("price");
            oneLine.append(cierre);
            oneLine.append(ticket.getPrice());
            oneLine.append(abreFinal);
            oneLine.append("price");
            oneLine.append(cierre);

            oneLine.append(abreFinal);
            oneLine.append("ticket");
            oneLine.append(cierre);
        }
        oneLine.append(abreFinal);
        oneLine.append("tickets");
        oneLine.append(cierre);

        try {
            getContext().openFileOutput("tickets.xml", Context.MODE_WORLD_WRITEABLE).write(oneLine.toString().getBytes(StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void exportTicketsQR(List<Ticket> ticketList){
        for (Ticket ticket: ticketList) {
            // setting this dimensions inside our qr code
            // encoder to generate our qr code.
            Gson gson = new Gson();
            String json = gson.toJson(ticketList);

            QRGEncoder qrgEncoder = new QRGEncoder(json, null, QRGContents.Type.TEXT, 200);

            Bitmap bitmap = null;
            try {
                // getting our qrcode in the form of bitmap.
                bitmap = qrgEncoder.encodeAsBitmap();
                getContext().openFileOutput("tickets.bmp", Context.MODE_WORLD_WRITEABLE).write(bitmap.getNinePatchChunk());
            } catch (WriterException e) {
                // this method is called for
                // exception handling.
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //File dir = new File(path + “/Captures/”); if(! dir. exists()) { dir. mkdirs(); } File file = new File(path + “/Captures/”, “screen.
        }
    }
     */
}