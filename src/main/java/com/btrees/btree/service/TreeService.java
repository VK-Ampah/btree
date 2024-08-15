package com.btrees.btree.service;

import com.btrees.btree.model.BinarySearchTree;
import com.btrees.btree.model.PreviousTree;
import com.btrees.btree.repository.PreviousTreeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeService {

    @Autowired
    private PreviousTreeRepository repository;

    public BinarySearchTree buildTree(List<Integer> numbers) {
        BinarySearchTree tree = new BinarySearchTree();
        for (int num : numbers) {
            tree.insert(num);
        }
        return tree;
    }

    public void saveTree(String inputNumbers, String treeStructure) {
        PreviousTree previousTree = new PreviousTree();
        previousTree.setInputNumbers(inputNumbers);
        previousTree.setTreeStructure(treeStructure);
        repository.save(previousTree);
    }

    public List<PreviousTree> getAllPreviousTrees() {
        return repository.findAll();
    }
}
