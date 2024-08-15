package com.btrees.btree.controller;

import com.btrees.btree.model.BinarySearchTree;
import com.btrees.btree.model.PreviousTree;
import com.btrees.btree.service.TreeService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tree")
public class TreeController {

    @Autowired
    private TreeService treeService;

    @GetMapping("/enter-numbers")
    public String enterNumbersPage() {
        // display a form to make a post request to /process-numbers
        // and a button to make a get request to /previous-trees
        return "<html>" +
                "<body>" +
                "<form action='/tree/process-numbers' method='post'>" +
                "<input type='text' name='numbers'>" +
                "<button type='submit'>Submit</button>" +
                "</form>" +
                "<form action='/tree/previous-trees' method='get'>" +
                "<button type='submit'>Show Previous</button>" +
                "</form>" +
                "</body>" +
                "</html>";
    }
    @PostMapping("/process-numbers")
    public BinarySearchTree processNumbers(@RequestParam String numbers) throws JsonProcessingException {
        // Convert the string to a list of integers
        List<Integer> numberList = Arrays.stream(numbers.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        // Build the binary search tree using the TreeService
        BinarySearchTree tree = treeService.buildTree(numberList);

        // Convert the tree to a JSON string (assuming a method to do this exists)
        String treeJson = new ObjectMapper().writeValueAsString(tree);

        // Save the tree and input numbers to the database
        treeService.saveTree(numbers, treeJson);

        // Return the constructed tree
        return tree;
    }


    @GetMapping("/previous-trees")
    public List<PreviousTree> getPreviousTrees() {
        return treeService.getAllPreviousTrees();
    }
}
