package org.example;

import java.io.*;

// Clase de utilidades para manejo de archivos
public class FileUtils {
    public static String readTextFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    public static void saveCompressedFile(String encodedContent, String filePath) throws IOException {
        int length = encodedContent.length();
        int byteLength = (length + 7) / 8;
        byte[] bytes = new byte[byteLength];

        for (int i = 0; i < length; i++) {
            if (encodedContent.charAt(i) == '1') {
                bytes[i / 8] |= (1 << (7 - (i % 8)));
            }
        }

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(bytes);
        }
    }

    public static void saveHuffmanTree(HuffmanNode root, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(root);
        }
    }

    public static HuffmanNode readHuffmanTree(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (HuffmanNode) ois.readObject();
        }
    }

    public static byte[] readCompressedFile(String filePath) throws IOException {
        File file = new File(filePath);
        byte[] bytes = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(bytes);
        }
        return bytes;
    }

    public static void saveTextFile(String content, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
    }
}
