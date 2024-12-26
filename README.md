[Demo](https://youtu.be/fxn8qjG3zgI)
          
          Book Browsing by Selected Field

A Java-based GUI application that allows users to browse a large collection of over 9,000 books, 
ordered dynamically by user-selected fields such as ISBN, title, or average rating. 
Built with custom data structures for efficient data retrieval and a clean, user-friendly interface.


          Features

          
	•	Dynamic Ordering: Quickly browse books by any field (e.g., ISBN, title, author) using a TreeMap-based index.
	•	Interactive Navigation: Navigate through the collection using First, Last, Previous, and Next buttons for seamless browsing.
	•	Efficient Data Retrieval: Implements custom binary search trees (BST) to build a TreeMap for fast add and search operations.
	•	HashMap Integration: Uses a HashMap to map fields to corresponding TreeMaps, enabling dynamic field selection.
	•	User-Friendly GUI: Designed using Java Swing, featuring a clean layout and intuitive controls.


        Technologies and Concepts Used
        
	•	Data Structures:
	            Binary Search Tree (BST) modified to a TreeMap.
	            HashMap for mapping fields to TreeMaps.
	•	Java Swing GUI: Developed an interactive graphical interface for browsing.
	•	Generics: Implemented for flexibility and type safety in TreeMap and nodes.
	•	JavaDoc Documentation: Fully documented code with clean style and adherence to JavaDoc guidelines.


       How It Works

       
	1.	TreeMap for Each Field: Each field (e.g., ISBN, title, rating) is indexed by a TreeMap, built from a dataset of 9,000+ books.
	2.	Field Selection: Users select a field from a dropdown (combo box) to reorder books dynamically.
	3.	Navigation: Navigate through the dataset using interactive buttons (First, Last, Previous, Next).
	4.	Real-Time Updates: Changes in field selection instantly update the ordering in the GUI.
	5.	Efficient Search: BST ensures quick add and retrieval operations for large datasets.
