package org.example;

import java.util.*;

// Clase para operaciones relacionadas con el árbol de Huffman
public class HuffmanTree {
    public static HuffmanNode buildTree(Map<Character, Integer> frequencies) {
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();

        // Crear nodos hoja para cada carácter
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            queue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // Construir el árbol
        while (queue.size() > 1) {
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();
            HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency, left, right);
            queue.add(parent);
        }

        return queue.poll();
    }

    public static Map<Character, String> generateCodes(HuffmanNode root) {
        Map<Character, String> codes = new HashMap<>();
        generateCodesRecursive(root, "", codes);
        return codes;
    }

    private static void generateCodesRecursive(HuffmanNode node, String code, Map<Character, String> codes) {
        if (node == null) return;

        if (node.isLeaf()) {
            codes.put(node.character, code);
            return;
        }

        generateCodesRecursive(node.left, code + "0", codes);
        generateCodesRecursive(node.right, code + "1", codes);
    }
}
