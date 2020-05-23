package com.bookstore.utility;

public class Trie {

	// Alphabet size (# of symbols)
	private static final int ALPHABET_SIZE = 26;
	
	private TrieNode root;
	
	public Trie() {
		root = new TrieNode();
	}
	
	// trie node
	private class TrieNode {
		TrieNode[] children = new TrieNode[ALPHABET_SIZE];

		// isWord is true if the node represents
		// end of a word
		boolean isWord;
		Integer index = null;
		
		TrieNode() {
			isWord = false;
			for (int i = 0; i < ALPHABET_SIZE; i++)
				children[i] = null;
		}
	}

	// If not present, inserts key into trie
	// If the key is prefix of trie node,
	// just marks leaf node
	public void insert(String key, Integer index) {
		int level;
		int length = key.length();
		
		TrieNode pCrawl = root;

		for (level = 0; level < length; level++) {
			int letter = key.charAt(level) - 'a';
			if (pCrawl.children[letter] == null)
				pCrawl.children[letter] = new TrieNode();

			pCrawl = pCrawl.children[letter];
		}

		// mark last node as leaf
		pCrawl.isWord = true;
		pCrawl.index = index;
	}

	// Returns true if key presents in trie, else false
	public Integer search(String key) {
		int level;
		int length = key.length();
		int index;
		TrieNode pCrawl = root;

		for (level = 0; level < length; level++) {
			index = key.charAt(level) - 'a';

			if (pCrawl.children[index] == null)
				return null;

			pCrawl = pCrawl.children[index];
		}

		return (pCrawl != null && pCrawl.isWord) ? pCrawl.index : null;
	}
	
}