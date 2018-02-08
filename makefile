all: Image.java
	javac Image.java
	java Image
	rm Image.class
clean:
	rm image.ppm
convert:
	convert image.ppm image.png
display:
	display image.ppm