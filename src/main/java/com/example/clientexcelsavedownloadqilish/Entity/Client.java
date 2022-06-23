package com.example.clientexcelsavedownloadqilish.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "client")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    private Long ID;
    private String BRANCH;
    private String idClient;
    private String name;
    private String codeCountry;
    private String codeType;
    private String codeResident;
    private String codeSubject;
    private String codeFrom;
    private Double state;
    private Double kodErr;
    private String fillName;
    private Double signRegister;
    private String crmId;
    private String subbranch;
    //    @CreationTimestamp
//    @Column(nullable = false, updatable = false)
//    private Timestamp dateOpen;
//
//    @UpdateTimestamp
//    @Column(nullable = false)
//    private Timestamp dateClose;

}
