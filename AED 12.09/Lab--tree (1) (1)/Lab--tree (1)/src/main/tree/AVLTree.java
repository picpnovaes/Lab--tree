package tree;

import java.util.ArrayList;
import java.util.List;

public class AVLTree extends BinarySearchTree {

    private class Node {
        int value;
        Node left, right;
        int height;

        Node(int d) {
            value = d;
            height = 1;
        }
    }

    private Node root;

    // Método para inserir um elemento e balancear a árvore
    public void insereElemento(int value) {
        root = insert(root, value);
    }

    private Node insert(Node node, int value) {
        if (node == null)
            return new Node(value);

        if (value < node.value)
            node.left = insert(node.left, value);
        else if (value > node.value)
            node.right = insert(node.right, value);
        else
            return node; // Elementos duplicados não são permitidos

        // Atualiza a altura deste nó ancestral
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Verifica o fator de balanceamento para ver se este nó ficou desbalanceado
        int balance = getBalance(node);

        // Caso 1 - Rotação à Direita
        if (balance > 1 && value < node.left.value)
            return rotateRight(node);

        // Caso 2 - Rotação à Esquerda
        if (balance < -1 && value > node.right.value)
            return rotateLeft(node);

        // Caso 3 - Rotação Esquerda-Direita
        if (balance > 1 && value > node.left.value) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Caso 4 - Rotação Direita-Esquerda
        if (balance < -1 && value < node.right.value) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // Rotação à direita
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Executa a rotação
        x.right = y;
        y.left = T2;

        // Atualiza as alturas
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Retorna nova raiz
        return x;
    }

    // Rotação à esquerda
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Executa a rotação
        y.left = x;
        x.right = T2;

        // Atualiza as alturas
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Retorna nova raiz
        return y;
    }

    // Obtém a altura de um nó
    private int height(Node node) {
        if (node == null)
            return 0;

        return node.height;
    }

    // Obtém o fator de balanceamento de um nó
    private int getBalance(Node node) {
        if (node == null)
            return 0;

        return height(node.left) - height(node.right);
    }

    // Método para a ordem pré-ordem
    public int[] preOrdem() {
        // Lógica para fazer a travessia pré-ordem e retornar o array
        // (exemplo básico para visualizar os valores)
        List<Integer> result = new ArrayList<>();
        preOrderHelper(root, result);
        return result.stream().mapToInt(i -> i).toArray();
    }

    private void preOrderHelper(Node node, List<Integer> result) {
        if (node != null) {
            result.add(node.value);
            preOrderHelper(node.left, result);
            preOrderHelper(node.right, result);
        }
    }

    // Método de busca de elementos
    public boolean buscaElemento(int value) {
        return buscaElemento(root, value);
    }

    private boolean buscaElemento(Node node, int value) {
        if (node == null)
            return false;

        if (value == node.value)
            return true;

        return value < node.value ? buscaElemento(node.left, value) : buscaElemento(node.right, value);
    }
}