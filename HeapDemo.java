package data;

public  class HeapDemo<T extends Comparable<T>> {
    
    private Node<T>[] heapArray;
    private int maxSize;
    private int currentSize;
    //private Node<T> node;
     
    public HeapDemo(int mx) {
        maxSize = mx;
        currentSize = 0;
        heapArray = new Node[maxSize];
        //node = new Node(T);
    }
    
    //public abstract <T> int compare(T t1,T t2);
     
    public boolean isEmpty() {
        return (currentSize == 0)? true : false;
    }
     
    public boolean isFull() {
        return (currentSize == maxSize)? true : false;
    }
     
    public boolean insert(T key) {
        if(isFull()) {
            return false;
        }
        Node newNode = new Node((Comparable) key);
        heapArray[currentSize] = newNode;
        trickleUp(currentSize++);
        return true;
    }
    //向上调整
    public void trickleUp(int index) {
        int parent = (index - 1) / 2; //父节点的索引
        Node bottom = heapArray[index]; //将新加的尾节点存在bottom中
        while(index > 0 && heapArray[parent].getKey().compareTo((T) bottom.getKey()) < 0) {
            heapArray[index] = heapArray[parent];
            index = parent;
            parent = (parent - 1) / 2;
        }
        heapArray[index] = bottom;
    }
     
    public Node remove() {
        Node root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }
    //向下调整
    public void trickleDown(int index) {
        Node top = heapArray[index];
        int largeChildIndex;
        while(index < currentSize/2) { //while node has at least one child
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = leftChildIndex + 1;
            //find larger child
            if(rightChildIndex < currentSize &&  //rightChild exists?
                    heapArray[leftChildIndex].getKey().compareTo(heapArray[rightChildIndex].getKey())<0) {
                largeChildIndex = rightChildIndex;
            }
            else {
                largeChildIndex = leftChildIndex;
            }
            if(top.getKey().compareTo(heapArray[largeChildIndex].getKey()) >=0 ) {
                break;
            }
            heapArray[index] = heapArray[largeChildIndex];
            index = largeChildIndex;
        }
        heapArray[index] = top;
    }
    //根据索引改变堆中某个数据
	public  boolean change(int index, T newValue) {
        if(index < 0 || index >= currentSize) {
            return false;
        }
        T oldValue = (T) heapArray[index].getKey();
        heapArray[index].setKey(newValue);
        if(oldValue.compareTo(newValue)<0) {
        	
            trickleUp(index);
        }
        else {
            trickleDown(index);
        }
        return true;
    }
  //自适应
	public  void HeapAdjust(Node[] array, int parent, int length) {
		 
	    Node temp =  array[parent]; // temp保存当前父节点
	    int child = 2 * parent + 1; // 先获得左孩子
	    while (child < length) {
	        // 如果有右孩子结点，并且右孩子结点的值大于左孩子结点，则选取右孩子结点
	        if (child + 1 < length && array[child].getKey().compareTo(array[child + 1].getKey()) < 0) {
	            child++;
	        }
	        // 如果父结点的值已经大于孩子结点的值，则直接结束
	        if (temp.getKey().compareTo(array[child].getKey()) >= 0)
	            break;
	        // 把孩子结点的值赋给父结点
	        array[parent] = array[child];
	        // 选取孩子结点的左孩子结点,继续向下筛选
	        parent = child;
	        child = 2 * child + 1;
	    }
	    array[parent] = temp;
	}
	
	/**
     * 交换元素
     * @param arr
     * @param a
     * @param b
     */
    public void swap(int a ,int b){
        Node<T> temp= heapArray[a];
        heapArray[a] = heapArray[b];
        heapArray[b] = (Node<T>) temp;
    }
    //最大堆排序
    public void heapsort()
    {
    	for(int j=currentSize-1;j>0;j--){
            swap(0,j);//将堆顶元素与末尾元素进行交换
            HeapAdjust(heapArray,0,j);//重新对堆进行调整
            //trickleDown(0);
        }
    }
     
    public void displayHeap() {
        System.out.println("heapArray(array format): ");
        for(int i = 0; i < currentSize; i++) {
            if(heapArray[i] != null) {
                System.out.println(heapArray[i].getKey().toString() + " ");
            }
            else {
                System.out.print("--");
            }
        }
    }

}
class Node<T extends Comparable<T>> {
    private T iData;
    public Node() {
    	
    }
    public Node(T key) {
        iData = key;
    }
     

	public T getKey() {
        return iData;
    }
     
    public void setKey(T newValue) {
        iData = newValue;
    }


}
