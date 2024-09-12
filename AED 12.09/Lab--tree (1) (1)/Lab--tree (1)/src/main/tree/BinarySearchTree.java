package tree;

import estrut.Tree;
import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree implements Tree {
    private Node root;

    private static class Node {
        int value;
        Node left, right;

        Node(int value) {
            this.value = value;
        }
    }

    @Override
    public boolean buscaElemento(int valor) {
        return buscaElementoRec(root, valor) != null;
    }

    private Node buscaElementoRec(Node node, int valor) {
        if (node == null || node.value == valor) {
            return node;
        }
        if (valor < node.value) {
            return buscaElementoRec(node.left, valor);
        }
        return buscaElementoRec(node.right, valor);
    }

    @Override
    public int minimo() {
        if (root == null) {
            throw new IllegalStateException("A árvore está vazia.");
        }
        return minimoRec(root);
    }

    private int minimoRec(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.value;
    }

    @Override
    public int maximo() {
        if (root == null) {
            throw new IllegalStateException("A árvore está vazia.");
        }
        return maximoRec(root);
    }

    private int maximoRec(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node.value;
    }

    @Override
    public void insereElemento(int valor) {
        root = insereElementoRec(root, valor);
    }

    private Node insereElementoRec(Node node, int valor) {
        if (node == null) {
            node = new Node(valor);
            return node;
        }
        if (valor < node.value) {
            node.left = insereElementoRec(node.left, valor);
        } else if (valor > node.value) {
            node.right = insereElementoRec(node.right, valor);
        }
        return node;
    }

    @Override
    public void remove(int valor) {
        root = removeRec(root, valor);
    }

    private Node removeRec(Node node, int valor) {
        if (node == null) {
            return null;
        }
        if (valor < node.value) {
            node.left = removeRec(node.left, valor);
        } else if (valor > node.value) {
            node.right = removeRec(node.right, valor);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            node.value = minimoRec(node.right);
            node.right = removeRec(node.right, node.value);
        }
        return node;
    }

    @Override
    public int[] preOrdem() {
        List<Integer> result = new ArrayList<>();
        preOrdemRec(root, result);
        return result.stream().mapToInt(i -> i).toArray();
    }

    private void preOrdemRec(Node node, List<Integer> result) {
        if (node != null) {
            result.add(node.value);
            preOrdemRec(node.left, result);
            preOrdemRec(node.right, result);
        }
    }

    @Override
    public int[] emOrdem() {
        List<Integer> result = new ArrayList<>();
        emOrdemRec(root, result);
        return result.stream().mapToInt(i -> i).toArray();
    }

    private void emOrdemRec(Node node, List<Integer> result) {
        if (node != null) {
            emOrdemRec(node.left, result);
            result.add(node.value);
            emOrdemRec(node.right, result);
        }
    }

    @Override
    public int[] posOrdem() {
        List<Integer> result = new ArrayList<>();
        posOrdemRec(root, result);
        return result.stream().mapToInt(i -> i).toArray();
    }

    private void posOrdemRec(Node node, List<Integer> result) {
        if (node != null) {
            posOrdemRec(node.left, result);
            posOrdemRec(node.right, result);
            result.add(node.value);
        }
    }
}
