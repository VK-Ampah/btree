package com.btrees.btree.model;
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
public class TreeNode {
    private int value;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int value) {
        this.value = value;
        left = null;
        right = null;
    }

}

