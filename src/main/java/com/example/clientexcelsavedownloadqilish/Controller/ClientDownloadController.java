package com.example.clientexcelsavedownloadqilish.Controller;

import com.example.clientexcelsavedownloadqilish.Entity.Client;
import com.example.clientexcelsavedownloadqilish.Repository.ClientRepository;
import com.example.clientexcelsavedownloadqilish.Service.ClientService;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class ClientDownloadController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/downloadExcelFile")
    public void downloadExcelFile(HttpServletResponse response) throws IOException {
        List<Client> clients = (List<Client>)clientRepository.findAll();
        ByteArrayInputStream byteArrayInputStream = clientService.Download(clients);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=clients.xlsx");
        IOUtils.copy(byteArrayInputStream, response.getOutputStream());

    }
}
