package com.btrees.btree.model;

import jakarta.persistence.*;

//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@Entity
public class PreviousTree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String inputNumbers;
    @Lob
    private String treeStructure;

}
