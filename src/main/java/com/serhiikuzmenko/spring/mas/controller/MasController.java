package com.serhiikuzmenko.spring.mas.controller;

import com.serhiikuzmenko.spring.mas.agents.GoogleAgent;
import com.serhiikuzmenko.spring.mas.agents.SumyStateUniversityAgent;
import com.serhiikuzmenko.spring.mas.entity.Country;
import com.serhiikuzmenko.spring.mas.entity.University;
import com.serhiikuzmenko.spring.mas.service.CountryService;
import com.serhiikuzmenko.spring.mas.service.UniversityService;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xml.sax.SAXException;


import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;


@Controller
public class MasController {
    @Autowired
    private UniversityService universityService;
    @Autowired
    private CountryService countryService;


    @GetMapping("/")
    public String showAllUniversities(Model model){
        List<Country> allCountries = countryService.getAllCountries();
        model.addAttribute("allCountries", allCountries);
        return "select-countries";
    }

    @RequestMapping("/selectCountries")
    public String selectCountries(HttpServletRequest request, Model model){

        String county1Param = request.getParameter("country1");
        String county2Param = request.getParameter("country2");


        List<University> localUniversities1=null;
        List<University> localUniversities2=null;

        if(county1Param!=null){
            int c1  = Integer.parseInt(request.getParameter("country1"));
            localUniversities1 = countryService.getCountry(c1).getUniversities();
        }else{
            localUniversities1 = universityService.getAllUniversity();
        }

        if(county2Param!=null){
            int c2  = Integer.parseInt(request.getParameter("country2"));
            localUniversities2 = countryService.getCountry(c2).getUniversities();
        }else{
            localUniversities2 = universityService.getAllUniversity();
        }
        model.addAttribute("localUniversities1", localUniversities1);
        model.addAttribute("localUniversities2", localUniversities2);

        return "universities";
    }

    @RequestMapping("/handleSyllabus")
    public String handleSyllabus(HttpServletRequest request, Model model){
        String firstUni = request.getParameter("university1");
        String secondUni = request.getParameter("university2");
        String desiredSubject = request.getParameter("subjectField");

        String firstURL, secondURL;

        model.addAttribute("firstUniName", firstUni);
        model.addAttribute("secondUniName", secondUni);




        if(firstUni.equals("Sumy State University")){
            SumyStateUniversityAgent sumyStateUniversityAgent = new SumyStateUniversityAgent();
            firstURL = sumyStateUniversityAgent.linkFounder(desiredSubject, firstUni);
        }else{
            GoogleAgent googleAgent = new GoogleAgent();
            if(universityService.getCountry(firstUni).getName().equals("Ukraine")){
                googleAgent.setLang("");
            }
            firstURL = googleAgent.linkFounder(desiredSubject, firstUni);
        }

        if(secondUni.equals("Sumy State University")){
            SumyStateUniversityAgent sumyStateUniversityAgent = new SumyStateUniversityAgent();
            secondURL = sumyStateUniversityAgent.linkFounder(desiredSubject, secondUni);
        }else{
            GoogleAgent googleAgent = new GoogleAgent();
            if(universityService.getCountry(secondUni).getName().equals("Ukraine")){
                googleAgent.setLang("");
            }
            secondURL = googleAgent.linkFounder(desiredSubject, secondUni);
        }

        System.out.println(firstURL);
        System.out.println(secondURL);
        final String saveUrl = "D:\\USER\\JAVA\\sources\\ua\\edu\\sumdu\\j2se\\Multi-agent-system-data-collection\\src\\main\\webapp\\syllabuses";

        File syl1 = null;
        File syl2 = null;
        String content1 = "";
        String content2 = "";
        try {

            if(!firstURL.equals("Not found")){
                if(getContentType(firstURL).equals("text/html")){
                    downloadHtml(firstURL, saveUrl, 1);
                    syl1 = new File(saveUrl+"\\syllabus1.html");
                }else if(getContentType(firstURL).equals("application/pdf")){
                    downloadFile(firstURL, saveUrl, 1, ".pdf");
                    syl1 = new File(saveUrl+"\\syllabus1.pdf");
                }else{
                    downloadFile(secondURL, saveUrl, 2, ".docx");
                    syl1 = new File(saveUrl+"\\syllabus1.docx");
                }

                try{
                    content1 = getContent(syl1).trim().replaceAll("\\s+", " ").replaceAll("\\. ", ".<br>");
                }catch (TikaException | IOException | SAXException e){
                    e.printStackTrace();
                }

                model.addAttribute("firstUni", content1);
            }else{
                model.addAttribute("firstUni", "No information");
            }

            if(!secondURL.equals("Not found")){
                if (getContentType(secondURL).equals("text/html")){
                    downloadHtml(secondURL, saveUrl, 2);
                    syl2 = new File(saveUrl+"\\syllabus2.html");
                }else if(getContentType(secondURL).equals("application/pdf")){
                    downloadFile(secondURL, saveUrl, 2, ".pdf");
                    syl2 = new File(saveUrl+"\\syllabus2.pdf");
                }else{
                    downloadFile(secondURL, saveUrl, 2, ".docx");
                    syl2 = new File(saveUrl+"\\syllabus2.pdf");
                }

                try{
//                    content1 = getContent(syl1).trim().replaceAll("\\s+", " ").replaceAll("\\. ", ".\n");
                    content2 = getContent(syl2).trim().replaceAll("\\s+", " ").replaceAll("\\. ", ".<br>");
                }catch (TikaException | IOException | SAXException e){
                    e.printStackTrace();
                }

                model.addAttribute("secondUni", content2);
            }else{
                model.addAttribute("secondUni", "No information");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(content1);
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println(content2);

        return "result-view";
    }

    public static void downloadHtml(String url, String saveFilePath, int num) throws IOException {
        Document doc = Jsoup.connect(url).get();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveFilePath+"\\syllabus"+num+".html"))) {
            writer.write(doc.html());
        }
        System.out.println("HTML-page successfully downloaded and saved: syllabus"+num+".html");
    }
    public static String getContentType(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("HEAD");
        connection.connect();
        String contentType = connection.getContentType();
        connection.disconnect();
        return contentType;
    }
    public static void downloadFile(String fileURL, String saveDir, int num, String ext) throws IOException {
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {

            InputStream inputStream = httpConn.getInputStream();
            String fileName = "syllabus"+num+ext;


            String saveFilePath = saveDir + File.separator + fileName;
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);
            int bytesRead;
            byte[] buffer = new byte[4096];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            inputStream.close();
            System.out.println("File successfully downloaded and saved: " + fileName);
        } else {
            System.out.println("Error. Code response: " + responseCode);
        }
        httpConn.disconnect();
    }

    public static String getContent(File file) throws TikaException, IOException, SAXException {

        Parser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler(-1);
        Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();
        InputStream stream = new FileInputStream(file);

        metadata.set(Metadata.CONTENT_ENCODING, StandardCharsets.UTF_8.name());

        parser.parse(stream, handler, metadata, context);


        String content = handler.toString();

        return content;
    }

}


