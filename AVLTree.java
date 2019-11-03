public class AVLTree {

    AVLNode root; 	// The tree root.
    int size;		// The size of the tree.
   
    /**
     * Construct an empty tree.
     */
    public AVLTree() {
       	this.size = 0;
       	this.root = null;
    }

    /**
     * Insert tank into the tree; You may assume that every tank is unique.
     * That is, no tank will appear twice.
     * 
     * @param tank the tank to insert.
     */
    public void insert(Tank tank) {
        if (tank == null){
            return;
        }
        // tank_node is the new node into which tank was inserted
        AVLNode tank_node = BSTinsert(tank);
        AVLNode tank_node_parent = tank_node.parent;
        while (tank_node_parent != null) {
            // checks if the tree is out of balance
            if (Math.abs(balanceFactor(tank_node_parent)) > 1) {
                rebalance(tank_node_parent);
            }
            else {
                updateHeight(tank_node_parent);
            }
            tank_node_parent = tank_node_parent.parent;
        }
        // the tree is balanced
        this.size++;
    }

    /**
     * return the balance factor of the tree
     * @param avl_node the root of the tree
     * @return balanceFactor
     */
    private int balanceFactor(AVLNode avl_node){
        // the node have no children there for the balance is 0
        if ((avl_node.right == null) && (avl_node.left == null)) {
            return 0;
        }
        // only have a left child
        else if ((avl_node.right == null) && (avl_node.left != null)) {
            return (avl_node.left.height + 1);
        }
        // only have a right child
        else if ((avl_node.right != null) && (avl_node.left == null)){
            return (0 - (avl_node.right.height + 1));
        }
        // have two children
        else {
            return (avl_node.left.height) - (avl_node.right.height);
        }
    }
    // update the height of the node
    private void updateHeight(AVLNode avl_node){
        // the node is a leaf
        if ((avl_node.right == null) && (avl_node.left == null)){
            avl_node.height =  0;
        }
        // only have a left child
        else if ((avl_node.right == null) && (avl_node.left != null)){
            avl_node.height = avl_node.left.height + 1;
        }
        //only have a right child
        else if ((avl_node.right != null) && (avl_node.left == null)){
            avl_node.height = avl_node.right.height + 1;
        }
        // have two children
        else {
            avl_node.height = (Math.max((avl_node.left.height), (avl_node.right.height)) + 1);
        }
    }

    /**
     * rebalnce the tree
     * @param node_a the node it's tree we rebalance
     */
    private void rebalance(AVLNode node_a){
        AVLNode node_b;
        // checks which child of the node is taller
        // node_a only have a right child
        if (node_a.left == null){
            node_b = node_a.right;
        }
        // node_a only have a left child
        else if (node_a.right == null){
            node_b = node_a.left;
        }
        // left child is taller
        else if (node_a.left.height > node_a.right.height) {
            node_b = node_a.left;
        }
        // right child is taller
        else {
            node_b = node_a.right;
        }
        // left child
        if (balanceFactor(node_a) == 2) {
            // left subtree of the left child - RR
            if(balanceFactor(node_a) * balanceFactor(node_b) > 0){
                RRrotation(node_a);
            }
            // the right subtree of the left child - RL (left first)
            else {
                RLrotation(node_a);
            }
        }
        // right child
        else if (balanceFactor(node_a) == (-2)){
            // right subtree of the right side - LL
            if(balanceFactor(node_a) * balanceFactor(node_b) > 0){
                LLrotation(node_a);
            }
            // the left subtree of the right child - LR (right first)
            else {
                LRrotation(node_a);
            }
        }
    }

    private void LLrotation(AVLNode x) {
        AVLNode x_right = x.right;
        AVLNode x_right_left = x_right.left;
        AVLNode x_parent = x.parent;
        // x have a parent
        if (x_parent != null) {
            // x is a right child
            if (x.tank.compareTo(x_parent.tank) > 0) {
                x_parent.right = x_right;
            }
            // x is a left child
            else {
                x_parent.left = x_right;
            }
        }
        x_right.parent = x_parent;
        x.right = x_right_left;
        x_right.left = x;
        x.parent = x_right;
        // x_right have a left  child
        if (x_right_left != null) {
            x_right_left.parent = x;
        }
        if (this.root == x){
            this.root = x_right;
        }
        updateHeight(x);
    }

    private void RRrotation(AVLNode x){

        AVLNode x_left = x.left;
        AVLNode x_left_right = x_left.right;
        AVLNode x_parent = x.parent;
        // x have a parent
        if (x_parent != null) {
            // x is a right child
            if (x.tank.compareTo(x_parent.tank) > 0) {
                x_parent.right = x_left;
            }
            // x is a left child
            else {
                x_parent.left = x_left;
            }
        }
        x_left.parent = x_parent;
        x.left = x_left_right;
        x_left.right = x;
        x.parent = x_left;
        // x_left have a right  child
        if (x_left_right != null) {
            x_left_right.parent = x;
        }
        if (this.root == x){
            this.root = x_left;
        }
        updateHeight(x);
    }

    private void RLrotation(AVLNode x){
        AVLNode x_left = x.left;
        LLrotation(x_left);
        RRrotation(x);
        updateHeight(x_left);

    }

    private void LRrotation(AVLNode x){
        AVLNode x_right = x.right;
        RRrotation(x_right);
        LLrotation(x);
        updateHeight(x_right);

    }

    /**
     * Insert a new node with data tank
     * @param tank the data of the new node
     * @return the new AVLNode
     */
    private AVLNode BSTinsert(Tank tank){
        if (tank == null){
            return null;
        }

        AVLNode tank_node = new AVLNode(tank);
        AVLNode node = null;
        AVLNode root = this.root;

        while (root != null){
            node = root;
            // tank_node.key < root.key
            if(tank_node.tank.compareTo(root.tank) < 0 ){
                root = root.left;
            }
            // tank_node.key > root.key
            else {
                root = root.right;
            }
        }
        tank_node.parent = node;
        // the tree was empty
        if (node ==  null){
            this.root = tank_node;
        }
        // tank_node.key < node.key
        else if(tank_node.tank.compareTo(node.tank) < 0){
            node.left = tank_node;
            updateHeight(node);
        }
        else {
            node.right = tank_node;
            updateHeight(node);
        }

        return tank_node;
    }

    /**
     * Search for a tank in the tree.
     * 
     * @param t the tank to search for.
     * @return the matching tank or null if not found.
     */
    public Tank search(Tank t) {

        int currentValue;
        // the value of the node we are looking for
        int searchValue = t.serialNumber();
        AVLNode current = root;

        while (current != null) {
            currentValue = current.tank.serialNumber();
            // the node we are looking for is to the left of the current node
            if (searchValue < currentValue) {
                current = current.left;
                // the node we are looking for is to the right of current node
            } else if (searchValue > currentValue) {
                current = current.right;
                // found a node with the same value
            } else {
                return current.tank;

            }
        }
            // no matching tank was found
            return null;

    }
    
    /**
     * Traverse the contents of this tree in an 'inorder' manner and return and array
     * containing the traversal of the tree.
     * 
     * @return a sorted array of the tree's content.
     */
     Tank[] inorder(){
         // new array in the size of the tree
        Tank[] arr = new Tank[this.size];
        int index = 0;
         inorder(root, arr, index);
        return arr;

     }
     private int  inorder(AVLNode r, Tank[] arr, int index){
         // base case
         if (r == null) {
             return index;
         }
         index = inorder(r.left, arr, index);
         arr[index++] =  r.tank;
         index = inorder(r.right, arr, index);
         return index;
     }
}