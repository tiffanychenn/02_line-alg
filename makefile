all: Image.java
	javac Image.java
	java Image
	rm Image.class
	display image.ppm
run: all
clean:
	rm image.ppm
convert:
	convert image.ppm image.png