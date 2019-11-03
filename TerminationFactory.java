public class TerminationFactory {

	AVLTree T1;  // a tree storing the tanks which await termination. 
	AVLTree T2;	 // a tree stroring the tanks after termination.
	
	/**
	 * Creates a new empty factor.
	 */
	public TerminationFactory(){
		this.T1 = new AVLTree();
		this.T2 = new AVLTree();
	}
	
	/**
	 * Inserts a new tank into the factory to await termination.
	 * 
	 * You may assume that each tank has a unique serial number.
	 * 
	 * Should run in O(log(n1)) where n1 is the size of T1.
	 * 
	 * @param t - the new tank.
	 */
	public void insert(Tank t){

		T1.insert(t);
	}
	
	
	/**
	 * Checks whether a given tank was terminated, is awaiting termination or is not part of the data structure.
	 * A tank is considered terminated only if the dismantleTanks() function
	 * was called after the tank was inserted. 
	 * In this case the function should return 0.
	 * 
	 * If a tank was inserted and the dismantleTanks() function was not called,
	 * then the tank is considered to be awaiting termination.
	 * In this case the funcion should return 1.
	 *
	 * In the case the tank was never inserted the function should return 2.
	 * 
	 * Should run in time O(log(n1) + log(n2)) where n1 and n2 are the size of T1 and T2 respectively.
	 * @param t - the tank to examine.
	 * @return 0,1 or 2, depending on the situation of the tank.
	 */
	public int checkTerminated(Tank t){
		if (this.T2.search(t) != null){
			return 0;
		} else if (this.T1.search(t) != null){
			return 1;
		} else {
			return 2;
		}
	}
	
	
	/**
	 * Returns a sorted array of all the tanks which were already terminated.
	 * A tank is considered terminated only if the dismantleTanks() function
	 * was called after the tank was inserted.
	 * 
	 * Should run in time O(n2), where n2 is the size of T2.
	 * @return an array sorted according to serial numbers.
	 */
	public Tank[] listTerminatedTanks(){

		return this.T2.inorder();
	}
	
	/**
	 * Returns a sorted array of all the tanks which await termination.
	 * A tank is considered to await termination if it was inserted into the factory and the
	 * dismantleTanks() function was not called after insertion.
	 * 
	 * Should run in time O(n1), where n1 is the size of T1.
	 * @return an array sorted according to serial numbers.
	 */
	public Tank[] listNonTerminatedTanks(){

		return this.T1.inorder();
	}
	
	/**
	 * Dismantle all tanks which await termination. In essance, should move all tanks from T1, to T2.
	 * Should run in time O(nlog(n)), where n is the total number of tanks in the data structure.
	 * 
	 */
	public void dismantleTanks(){
		Tank[] temp = T1.inorder();
		for (int i = 0; i < temp.length; i++){
				T2.insert(temp[i]);
		}
		T1.root = null;
		T1.size = 0;
	}
}
