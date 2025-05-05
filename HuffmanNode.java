package org.example;

import java.util.*;
import java.io.Serializable;

// Clase para representar nodos del árbol de Huffman
class HuffmanNode implements Comparable<HuffmanNode>, Serializable {
    char character;
    int frequency;
    HuffmanNode left, right;

    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    public HuffmanNode(char character, int frequency, HuffmanNode left, HuffmanNode right) {
        this.character = character;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(HuffmanNode other) {
        return this.frequency - other.frequency;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
}
