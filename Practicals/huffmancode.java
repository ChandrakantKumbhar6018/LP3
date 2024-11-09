import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

class HuffmanNode {
  char ch;
  int freq;
  HuffmanNode left, right;

  HuffmanNode(char ch, int freq, HuffmanNode left, HuffmanNode right) {
    this.ch = ch;
    this.freq = freq;
    this.left = left;
    this.right = right;
  }
}

class HuffmanEncoding {
  private static Map<Character, String> huffmanCodes = new HashMap<>();
  private static HuffmanNode root;

  public static void buildHuffmanTree(String text) {

    Map<Character, Integer> frequencyMap = new HashMap<>();
    for (char ch : text.toCharArray()) {
      frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
    }

    PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>((a, b) -> a.freq - b.freq);

    for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
      priorityQueue.add(new HuffmanNode(entry.getKey(), entry.getValue(), null, null));
    }

    while (priorityQueue.size() > 1) {
      HuffmanNode left = priorityQueue.poll();
      HuffmanNode right = priorityQueue.poll();
      int sum = left.freq + right.freq;
      priorityQueue.add(new HuffmanNode('\0', sum, left, right));
    }

    root = priorityQueue.poll();

    generateCodes(root, "");
  }

  private static void generateCodes(HuffmanNode node, String code) {
    if (node == null)
      return;

    if (node.left == null && node.right == null) {
      huffmanCodes.put(node.ch, code);
    }

    generateCodes(node.left, code + "0");
    generateCodes(node.right, code + "1");
  }

  public static String encode(String text) {
    StringBuilder encodedText = new StringBuilder();
    for (char ch : text.toCharArray()) {
      encodedText.append(huffmanCodes.get(ch));
    }
    return encodedText.toString();
  }

  public static String decode(String encodedText) {
    StringBuilder decodedText = new StringBuilder();
    HuffmanNode current = root;

    for (int i = 0; i < encodedText.length(); i++) {
      current = encodedText.charAt(i) == '0' ? current.left : current.right;

      if (current.left == null && current.right == null) {
        decodedText.append(current.ch);
        current = root;
      }
    }
    return decodedText.toString();
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the text to encode: ");
    String text = scanner.nextLine();

    buildHuffmanTree(text);

    System.out.println("Huffman Codes: " + huffmanCodes);

    String encodedText = encode(text);
    System.out.println("Encoded Text: " + encodedText);

    String decodedText = decode(encodedText);
    System.out.println("Decoded Text: " + decodedText);
    scanner.close();

  }
}
