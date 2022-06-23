package com.example.clientexcelsavedownloadqilish.Service;

import com.example.clientexcelsavedownloadqilish.Entity.Client;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ClientServiceInterface {
    ByteArrayInputStream Download(List<Client> clients);
}
