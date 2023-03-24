package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "files_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilesModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "picBye", length = 100000)
    private byte[] picByte;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="user_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private Users user;

    public FilesModel(Long userId, String name, String type, byte[] picByte) {
        this.userId = userId;
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }
}
