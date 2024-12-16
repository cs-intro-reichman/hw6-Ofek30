import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {
	

	public static void main(String[] args) {
		

		
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:



		Color[][] tinypic = read("tinypic.ppm");
		  
		Color[][] thor = read("thor.ppm");
		Color[][] ironman = read("ironman.ppm");
		//thor=scaled(thor, 400, 600);
	

		
		//print(ironman);
		setCanvas(ironman);
		//display(ironman);
		//display(flippedVertically(ironman));
		
		
		
		//setCanvas(blend(thor,ironman,0.8));
		//display(blend(thor,ironman,0.8));
		//setCanvas(blend(thor,ironman,0.9));
		//display(blend(thor,ironman,0.9));
		morph(thor, ironman, 6);

		 
		
		
		
		

		// Creates an image which will be the result of various 
		// image processing operations:
		Color[][] image;

		// Tests the horizontal flipping of an image:
		image = flippedHorizontally(tinypic);
		System.out.println();
		print(image);
		
		//// Write here whatever code you need in order to test your work.
		//// You can continue using the image array.
	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	public static Color[][] read(String fileName) {
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
		Color[][] image = new Color[numRows][numCols];
	
		for(int i=0;i<numRows;i++)
		{
			for(int r=0;r<numCols;r++)
			{
				Color c=new Color(in.readInt(),in.readInt(),in.readInt());
				image[i][r]=c;
			}
		}
		// Reads the RGB values from the file into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		//// Replace the following statement with your code.
		return image;
	}

    // Prints the RGB values of a given color.
	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	private static void print(Color[][] image) {
		for(int i=0;i<image.length;i++)
		{
			for(int r=0;r<image[i].length;r++)
			{
				print(image[i][r]);
				if(r!=image[i].length)
				System.out.print("   ");
			}
			System.out.println();
		}
		//// Replace this comment with your code
		//// Notice that all you have to so is print every element (i,j) of the array using the print(Color) function.
	}
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		Color[][] c=new Color[image.length][image[0].length];

		for(int i=0;i<image.length;i++)
		{
			for(int r=0;r<image[i].length;r++)
		    {
				c[i][image[0].length-r-1]=image[i][r];
		    }
			
		}
		//// Replace the following statement with your code
		return c;
	}
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		Color[][] c=new Color[image.length][image[0].length];
		
		for(int i=0;i<image.length;i++)
		{
			for(int r=0;r<image[i].length;r++)
		    {
				c[image.length-1-i][r]=image[i][r];
		    }
			
		}
		//// Replace the following statement with your code
		return c;
	}
	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	private static Color luminance(Color pixel) 
	{
		int r=pixel.getRed();
		int g=pixel.getGreen();
		int b=pixel.getBlue();
		int lum = (int)(0.299 * r + 0.587 * g + 0.114*b);
		Color c=new Color(lum, lum, lum);
		//// Replace the following statement with your code
		return c;
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		for(int i=0;i<image.length;i++)
		{
			for(int r=0;r<image[i].length;r++)
			{
				image[i][r]=luminance(image[i][r]);

			}
		}

		//// Replace the following statement with your code
		return image;
	}	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		 // Get the original image dimensions
		 int col = image[0].length;
		 int row = image.length; 
		 Color[][] scaledImage = new Color[height][width];
		 double x = (double) col / width;
		 double y = (double) row / height;
 
		 for (int i = 0; i < height; i++) {
			 for (int r = 0; r < width; r++) {
				 
				 int x1 = (int) (r * x);
				 int y1 = (int) (i * y);
 
				 scaledImage[i][r] = image[y1][x1];
			 }
		 }
 //// Replace the following statement with your code
		 return scaledImage;
		
	}
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		int r1=c1.getRed(),r2=c2.getRed();
		int g1=c1.getGreen(),g2=c2.getGreen();
		int b1=c1.getBlue(),b2=c2.getBlue();

		int r=(int) (alpha * r1 + (1-alpha) * r2);
		int g=(int) (alpha * g1 + (1-alpha) * g2);
		int b=(int) (alpha * b1 + (1-alpha) * b2);
		
		
		Color c=new Color(r,g,b);
		//// Replace the following statement with your code
		return c;
	}
	
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) 
	{
		Color[][] image=new Color[image1.length][image1[0].length];
	
		for(int i=0;i<image1.length;i++)
		{
			for(int r=0;r<image1[i].length;r++)
			{
				image[i][r]=blend(image1[i][r], image2[i][r], alpha);

			}
		}
		//// Replace the following statement with your code
		return image;
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		double alpha=(double)1/(n-1);
		System.out.println(alpha);
		
		Color [][] newtarget=scaled(target, source[0].length, source.length);
		
		

		for(int i=0;i<n;i++)
		{
			Color[][] c=blend(newtarget, source, alpha*i);
		    setCanvas(c);	
			display(c);
			StdDraw.pause(1000);
		}
		

		//// Replace this comment with your code
	}
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(height, width);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}

