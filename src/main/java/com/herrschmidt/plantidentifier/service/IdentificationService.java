package com.herrschmidt.plantidentifier.service;

import java.io.IOException;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

@Service
public class IdentificationService {

    public String identify(byte[] plantImage) throws IOException {
        URL url = new URL("http://localhost:5000/identify");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("image", "application/image");
        con.setDoOutput(true);
        con.getOutputStream().write(plantImage);
        con.getResponseCode();

        StringWriter writer = new StringWriter();
        IOUtils.copy(con.getInputStream(), writer, StandardCharsets.UTF_8);
        String classification = writer.toString();
        return classification;
    }

}
