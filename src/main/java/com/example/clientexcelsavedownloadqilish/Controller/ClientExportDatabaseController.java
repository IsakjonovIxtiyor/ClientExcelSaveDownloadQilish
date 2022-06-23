package com.example.clientexcelsavedownloadqilish.Controller;

import com.example.clientexcelsavedownloadqilish.Entity.Client;
import com.example.clientexcelsavedownloadqilish.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Controller
public class ClientExportDatabaseController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/import")
    public HttpEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        List<Client> clients = clientService.ReadDataFromExcel(file);
        return ResponseEntity.ok().body(clients);
    }
}
