// Basic node stored in AVL trees

class AVLNode {
	
	Tank tank;				// The data in the node
	AVLNode parent;		// The parent
	AVLNode left;       // Left child
	AVLNode right;      // Right child
	int height;       	// Height

	/**
	 * A standard constructor, sets all pointers to null.
	 * 
	 * @param tank - the data of the node.
	 */
    AVLNode(Tank tank) {
		this.parent = null;
		this.left = null;
		this.right = null;
		this.tank = tank;
		this.height = 0;
    }
    
    /**
     * A standard constructor
     * 
     * @param tank - the data of the node.
     * @param lt - the left child.
     * @param rt - the right child.
     * @param parent - the parent.
     */
    AVLNode(Tank tank, AVLNode lt, AVLNode rt, AVLNode parent){
    	this.tank = tank;
    	this.parent = parent;
    	this.left = lt;
    	this.right = rt;
    }

}