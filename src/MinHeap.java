public class MinHeap {
    
    private Node[] NHeap;
    private int size;
    private int maxsize;
    
 
    private static final int FRONT = 1;
 
    public MinHeap(int maxsize)
    {
        this.maxsize = maxsize;
        this.size = 0;
        NHeap = new Node[this.maxsize + 1];
    }
 
    private int parent(int pos)
    {
        return pos / 2;
    }
 
    private int leftChild(int pos)
    {
        return (2 * pos);
    }
    
    public int size() {
    	return size;
    }
 
    private int rightChild(int pos)
    {
        return (2 * pos) + 1;
    }
 
    private boolean isLeaf(int pos)
    {
        if (pos >=  (size / 2)  &&  pos <= size)
        { 
            return true;
        }
        return false;
    }
 
    private void swap(int fpos, int spos)
    {
        Node tmp;
        tmp = NHeap[fpos];
        NHeap[fpos] = NHeap[spos];
        NHeap[spos] = tmp;
        
 
    }
 
    private void minHeapify(int pos)
    {
    	if (isLeaf(pos)) return;

    	int leftChild = leftChild(pos);
    	int rightChild = rightChild(pos);

    	int smallestChild = leftChild;
    	if (rightChild <= size && NHeap[rightChild].getF() < NHeap[leftChild].getF())
    	{
    	    smallestChild = rightChild;
    	}
    	if (NHeap[smallestChild] != null && NHeap[pos].getF() > NHeap[smallestChild].getF())
    	{
    	    swap(pos, smallestChild);
    	    minHeapify(smallestChild);
    	}
    }
 
    public void insert(Node element)
    {
        NHeap[++size] = element;
        int current = size;

        while (current > 1 && (NHeap[current].getF() < NHeap[parent(current)].getF()))
        {
            swap(current,parent(current));
            current = parent(current);
        }   
    }
    
     public void first(Node element)
    {
        NHeap[0] = element; 
    }
    public void print()
    {
        for (int i = 1; i <= ((size/2)-1); i++ )
        {
            System.out.print(" PARENT : " + NHeap[i].getF() + " LEFT CHILD : " + NHeap[2*i].getF() 
                + " RIGHT CHILD :" + NHeap[2 * i  + 1].getF());
            System.out.println();
        } 
    }
 
    public void minHeap()
    {
        for (int pos = (size / 2); pos >= 1 ; pos--)
        {
            minHeapify(pos);
        }
    }
 
    public Node remove()
    {
    	Node popped = NHeap[FRONT];
        NHeap[FRONT] = NHeap[size--]; 
        minHeapify(FRONT);
        return popped;
    }
}