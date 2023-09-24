default: run

# Compilations
FrontEnd.class: FrontEnd.java
	javac FrontEnd.java
Book.class: Book.java
	javac Book.java
CollectionOfBooks.class: CollectionOfBooks.java
	javac CollectionOfBooks.java
KVPair.class: KVPair.java
	javac KVPair.java
MapInterface.class: MapInterface.java
	javac MapInterface.java
Map.class: Map.java
	javac Map.java

# Run program
run: FrontEnd.class Book.class CollectionOfBooks.class KVPair.class MapInterface.class Map.class
	java FrontEnd

# Run tester
LibraryTester.class: LibraryTester.java
	javac -cp .:junit5.jar LibraryTester.java
test: LibraryTester.class FrontEnd.class Book.class CollectionOfBooks.class KVPair.class MapInterface.class Map.class
	java -jar junit5.jar -cp . --scan-classpath --include-classname="LibraryTester"

# Clean
clean:
	rm *.class
