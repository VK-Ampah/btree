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
public class BinarySearchTree {
    private TreeNode root;

    public void insert(int value) {
        root = insertRec(root, value);
    }

    private TreeNode insertRec(TreeNode root, int value) {
        if (root == null) {
            root = new TreeNode(value);
            return root;
        }
        if (value < root.getValue()) {
            root.setLeft(insertRec(root.getLeft(), value));
        } else if (value > root.getValue()) {
            root.setRight(insertRec(root.getRight(), value));
        }
        return root;
    }
}

