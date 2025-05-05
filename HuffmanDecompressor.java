package org.example;

import java.io.*;
import java.util.*;

// Clase para manejar la descompresión de archivos
public class HuffmanDecompressor {
    public static void decompress(String huffFilePath, String treeFilePath) throws IOException, ClassNotFoundException {
        // Reconstruir el árbol
        HuffmanNode root = FileUtils.readHuffmanTree(treeFilePath);

        // Resto del código permanece igual...
        byte[] compressedData = FileUtils.readCompressedFile(huffFilePath);
        String decodedContent = decodeContent(compressedData, root);

        String outputFilePath = huffFilePath.replace(".huff", "_decompressed.txt");
        FileUtils.saveTextFile(decodedContent, outputFilePath);

        System.out.println("Descompresión completada. Archivo creado: " + outputFilePath);
    }

    private static String decodeContent(byte[] compressedData, HuffmanNode root) {
        StringBuilder bits = new StringBuilder();
        for (byte b : compressedData) {
            for (int i = 7; i >= 0; i--) {
                bits.append((b & (1 << i)) != 0 ? '1' : '0');
            }
        }

        StringBuilder decoded = new StringBuilder();
        HuffmanNode current = root;

        for (int i = 0; i < bits.length(); i++) {
            char bit = bits.charAt(i);
            if (bit == '0') {
                current = current.left;
            } else {
                current = current.right;
            }

            if (current.left == null && current.right == null) {
                decoded.append(current.character);
                current = root;
            }
        }

        return decoded.toString();
    }
}
