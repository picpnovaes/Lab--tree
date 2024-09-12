package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import tree.AVLTree;

public class AppTest {

    @Test
    public void inserir() {
        AVLTree b = new AVLTree();
        b.insereElemento(1);
        b.insereElemento(2);
        b.insereElemento(3);
        b.insereElemento(4);  
        b.insereElemento(5);  
        assertEquals(true, b.buscaElemento(1));
        assertEquals(true, b.buscaElemento(2));
        assertEquals(true, b.buscaElemento(3));
        assertArrayEquals(new int[]{2,1,4,3,5}, b.preOrdem());  
    }
}