package com.btrees.btree.service;

import com.btrees.btree.model.BinarySearchTree;
import com.btrees.btree.model.PreviousTree;
import com.btrees.btree.repository.PreviousTreeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TreeServiceTest {

    @Autowired
    private TreeService treeService;

    @Autowired
    private PreviousTreeRepository previousTreeRepository;

    // clearing database before each test (avoid errors)
    @BeforeEach
    void setUp() {
        previousTreeRepository.deleteAll();
    }

    // Test context
    @Test
    void contextLoads() {
    }

    @Test
    void testTreeConstruction() {
        BinarySearchTree tree = treeService.buildTree(List.of(5, 3, 7, 2, 4, 6, 8));
        assertNotNull(tree);
    }

    @Test
    void testGetAllPreviousTrees() throws Exception {
        // Create and save the first tree
        String numbers1 = "5,3,7";
        BinarySearchTree tree1 = treeService.buildTree(List.of(5, 3, 7));
        String treeJson1 = new ObjectMapper().writeValueAsString(tree1);
        treeService.saveTree(numbers1, treeJson1);

        // Create and save the second tree
        String numbers2 = "2,8,4";
        BinarySearchTree tree2 = treeService.buildTree(List.of(2, 8, 4));
        String treeJson2 = new ObjectMapper().writeValueAsString(tree2);
        treeService.saveTree(numbers2, treeJson2);

        // Retrieve all saved trees
        List<PreviousTree> previousTrees = treeService.getAllPreviousTrees();

        // Check that two trees have been saved
        assertEquals(2, previousTrees.size());

        // Verify that the retrieved data matches what was saved
        assertTrue(previousTrees.stream().anyMatch(tree -> tree.getInputNumbers().equals(numbers1) && tree.getTreeStructure().equals(treeJson1)));
        assertTrue(previousTrees.stream().anyMatch(tree -> tree.getInputNumbers().equals(numbers2) && tree.getTreeStructure().equals(treeJson2)));
    }
}
