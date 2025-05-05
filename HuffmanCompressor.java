package org.example;

import java.io.*;
import java.util.*;

// Clase para manejar la compresión de archivos
public class HuffmanCompressor {
    public static void compress(String inputFilePath) throws IOException {
        // Leer el contenido del archivo
        String content = FileUtils.readTextFile(inputFilePath);

        // Calcular frecuencias
        Map<Character, Integer> frequencies = calculateFrequencies(content);

        // Construir el árbol de Huffman
        HuffmanNode root = HuffmanTree.buildTree(frequencies);

        // Generar códigos
        Map<Character, String> codes = HuffmanTree.generateCodes(root);

        // Codificar el contenido
        String encodedContent = encodeContent(content, codes);

        // Guardar archivos
        String baseName = inputFilePath.replaceFirst("[.][^.]+$", "");
        FileUtils.saveCompressedFile(encodedContent, baseName + ".huff");
        FileUtils.saveHuffmanTree(root, baseName + ".hufftree");

        System.out.println("Compresión completada. Archivos creados: " +
                baseName + ".huff y " + baseName + ".hufftree");
    }

    private static Map<Character, Integer> calculateFrequencies(String content) {
        Map<Character, Integer> frequencies = new HashMap<>();
        for (char c : content.toCharArray()) {
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        }
        return frequencies;
    }

    private static String encodeContent(String content, Map<Character, String> codes) {
        StringBuilder encoded = new StringBuilder();
        for (char c : content.toCharArray()) {
            encoded.append(codes.get(c));
        }
        return encoded.toString();
    }
}
