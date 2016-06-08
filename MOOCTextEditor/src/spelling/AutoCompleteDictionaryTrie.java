package spelling;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		
		String lowerCaseWord = word.toLowerCase();
		TrieNode curr = root;
		
		for(int i = 0;i<lowerCaseWord.length();i++){
			char c = lowerCaseWord.charAt(i);
			if(curr.getValidNextCharacters().contains(c)){
				curr = curr.getChild(c);
			}else{
				curr = curr.insert(c);
			}
		}
		
		if(!curr.endsWord()){
			curr.setEndsWord(true);
			size++;
		}
		
	    return false;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		String lowerCaseWord = s.toLowerCase();
		TrieNode curr = root;
		
		for(int i = 0;i<lowerCaseWord.length();i++){
			char c = lowerCaseWord.charAt(i);
			if(curr.getValidNextCharacters().contains(c)){
				curr = curr.getChild(c);
			}else{
				return false;
			}
		}
		
		return curr.endsWord();
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions

    	 String prefixToCheck = prefix.toLowerCase();
    	 List<String> result = new LinkedList<String>();
    	 TrieNode curr = root;
    	 for (int i = 0; i < prefixToCheck.length(); i++) {
    		 char c = prefixToCheck.charAt(i);
    		 if (curr.getValidNextCharacters().contains(c)) {
 				curr = curr.getChild(c);
 			} else {
 				return result;
 			}
    	 }
    	 int count = 0;
    	 
    	 //if prefix is a word
    	 if (curr.endsWord()) {
    		 result.add(curr.getText());
    		 count++;
    	 }
    	 
    	 //start with last letter of prefix
    	 TrieNode afterPrefix = curr;
    	 List<Character> validChildren = new LinkedList<Character>(afterPrefix.getValidNextCharacters());
    	 //queue of nodes that completes the stem
    	 List<TrieNode> queue = new LinkedList<TrieNode>(); 
    	 
    	 //add validChildren of prefix's last char to queue
    	 for (int i = 0; i < validChildren.size(); i++) {
    		 char c = validChildren.get(i);
    		 queue.add(afterPrefix.getChild(c));
    	 }
    	 
    	 //while queue isn't empty and count < numComplete,
    	 //remove first entry of queue, if it completes a word, add count.
    	 while (!queue.isEmpty() && count < numCompletions) {
    		 TrieNode cNode = queue.remove(0);
    		 if (cNode.endsWord()) {
    			 result.add(cNode.getText());
    			 count++;
    		 }
    		 
    		 List<Character> nextValidChildren = new LinkedList<Character>(cNode.getValidNextCharacters());
        	 for (int i = 0; i < nextValidChildren.size(); i++) {
        		 char c = nextValidChildren.get(i);
        		 queue.add(cNode.getChild(c));
        	 }
    	 }
         return result;
     }    	 
    	 
 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}