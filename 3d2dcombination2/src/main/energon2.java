package main;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GraphicsConfiguration;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.media.j3d.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;
import javax.media.*;

import com.sun.j3d.utils.behaviors.mouse.MouseBehaviorCallback;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;

import blkGUIone.GUI2;
import javafx.scene.transform.TransformChangedEvent;

public final class energon2 extends JPanel {
    int s = 0, count = 0;
    
    public static String file = "src/temp.txt";

    public energon2() {

        setLayout(GUI2.comb);
        GraphicsConfiguration gc=SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(gc);
        add("Center", canvas3D);

        BranchGroup scene = createSceneGraph();
        scene.compile();

        // SimpleUniverse is a Convenience Utility class
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);


        // This moves the ViewPlatform back a bit so the
        // objects in the scene can be viewed.
        simpleU.getViewingPlatform().setNominalViewingTransform();
        simpleU.addBranchGraph(scene);
        
    }
    public BranchGroup createSceneGraph() {
    	//All shapes, mouse rotation variables, floor... etc in one branchgroup
        BranchGroup lineGroup = new BranchGroup();
        //appearance variable for future decorations of 3d models
        Appearance app = new Appearance();
        PolygonAttributes pa=new PolygonAttributes();
        pa.setPolygonMode(PolygonAttributes.POLYGON_FILL);
        pa.setCullFace(PolygonAttributes.CULL_NONE);
        Material mat=new Material();
        mat.setEmissiveColor(new Color3f(1f,1f,0.8f));
        mat.setAmbientColor(new Color3f(1f,1f,0.8f));
        mat.setDiffuseColor(new Color3f(1f,1f,0.8f));
        mat.setSpecularColor(new Color3f(1f,1f,0.8f));
        mat.setLightingEnable(true);
        RenderingAttributes ra=new RenderingAttributes();
        ra.setIgnoreVertexColors(true);
        ColoringAttributes ca=new ColoringAttributes();
        ca.setShadeModel(ColoringAttributes.SHADE_GOURAUD);
        ca.setColor(new Color3f(0.2f,0.5f,0.9f));
        app.setColoringAttributes(ca);
        app.setRenderingAttributes(ra);   
        app.setMaterial(mat);
        app.setPolygonAttributes(pa);
        
        //Group of rotating 3D shapes initialized
        TransformGroup objRotate = new TransformGroup();
        objRotate.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
        Appearance ap = new Appearance();
        
        // The name of the text file to read the x,y points from
        String fileName = file;
        
        ///Initialize ArrayList of x,y points
    	ArrayList<Float> newnumlist = new ArrayList<Float>();

        // This will reference one line at a time
        String line = null;
        
        //Initialize arraylists of everything for shapes/floors
        TransformGroup Translist = new TransformGroup();
        ArrayList<MouseRotate> mrlist = new ArrayList();
        ArrayList<MouseTranslate> mtlist = new ArrayList();
        ArrayList<MouseZoom> mzlist = new ArrayList();
        ArrayList<QuadArray> quadlist = new ArrayList();
        ArrayList<Point3f[]> ptslist = new ArrayList();
        ArrayList<Shape3D> shlist = new ArrayList();
        ArrayList<MouseRotate> mrlist2 = new ArrayList();
        ArrayList<MouseZoom> mzlist2 = new ArrayList();
        ArrayList<MouseTranslate> mtlist2 = new ArrayList();
        //temporary transform for a loop
        Transform3D temptrans = new Transform3D();

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            
            ///Grabs z value from GUI height input from user
            Float z = (float) (FileChooser.getZ() * 0.02); 

            while((line = bufferedReader.readLine()) != null) {
            	///Initialize a variable to take in a new point
            	///converted to int later since combining strings needed
            	String newnum = "";
            	String shape = "";
            	for (int b = 0; b < 4; b++) {
            		shape += Character.toString(line.charAt(b));
            	}
            	if (!shape.equals("line")) {
            		int x = 4;
            		if (shape.equals("arc ")) {
            			x = 4;
            		} else {
            			x = 5;
            		}
            		for (int i = x; i < line.length(); i++) {
            			if ((Character.toString(line.charAt(i))).equals(" ")) {
            				newnumlist.add(Float.parseFloat(newnum));
            				newnum = "";
            			} else {
            				newnum += Character.toString(line.charAt(i));
            			}
            		}
            		//new initialization for next new shape loop
            		objRotate = new TransformGroup();
            		//initialize a general shape variable
            		Node arc = null;
            		if (shape.equals("arc ")) {
            			arc = new Cylinder( (float) ((newnumlist.get(2))/Math.pow(10, 1.5)),z, ap);
            		} else if (shape.equals("rect")) {
            			arc = new Box((float) ((newnumlist.get(2))/Math.pow(10, 1.5)),z/2, (float) ((newnumlist.get(3))/Math.pow(10, 1.5)), ap);
            		} else if (shape.equals("cone")) {
            			arc = new Cone((float) ((newnumlist.get(2))/Math.pow(10, 1.5)),z, ap);
            		} else if (shape.equals("sphe")) {
            			arc = new Sphere((float) ((newnumlist.get(2))/Math.pow(10, 1.5)),ap);
            		} else {
            			System.out.println("ERROR shape not specified. Check .txt spelling");
            		}
            		//rotate shapes 90 degrees to make them standing
            		temptrans.rotX(Math.PI / 2);
            		//read location from textfile array
            		if (shape.equals("rect")) {
            			temptrans.setTranslation(new Vector3f((float) (newnumlist.get(0)/Math.pow(10, 1.5)), (float) ((float) newnumlist.get(1)/Math.pow(10, 1.5)), z/2));
            		} else if (shape.equals("sphe")) {
            			temptrans.setTranslation(new Vector3f((float) (newnumlist.get(0)/Math.pow(10, 1.5)), (float) (newnumlist.get(1)/Math.pow(10, 1.5)), (float) ((float) newnumlist.get(2)/Math.pow(10, 1.5))));
            		} else {
            			temptrans.setTranslation(new Vector3f(((float) (newnumlist.get(0)/Math.pow(10, 1.5))), ((float) (newnumlist.get(1)/Math.pow(10, 1.5))), (z/2)));
            		}
            		objRotate.setTransform(temptrans);
					objRotate.addChild(arc);
            		Translist.addChild(objRotate);
            		//make more mouse variables for each shape
            		mrlist.add(new MouseRotate());
            		mzlist.add(new MouseZoom());
            		mtlist.add(new MouseTranslate());
            		shape = "";
            		//initialize again for next shape
            		newnumlist = new ArrayList();
            		temptrans = new Transform3D();
            		
            	} else if (shape.equals("line")) {
            		//loop through, read lines, add them as float numbers to an array
            		for (int i = 5; i < line.length(); i++) {
            			if ((Character.toString(line.charAt(i))).equals(" ")) {
            				newnumlist.add(Float.parseFloat(newnum));
            				newnum = "";
            			} else {
            				newnum += Character.toString(line.charAt(i));
            			}
            		}
            		//create floor with points from text file
            		QuadArray lsa2 = new QuadArray(48,QuadArray.COORDINATES|QuadArray.NORMALS);
                    Point3f [] pts2=new Point3f[2];
                    for(int i=0;i<2;i++)pts2[i]=new Point3f();
                    pts2[0].x=(float) (newnumlist.get(0)/Math.pow(10, 1.5));pts2[0].y=(float) (newnumlist.get(1)/Math.pow(10, 1.5));pts2[0].z=0f;
                    pts2[1].x=(float) (newnumlist.get(2)/Math.pow(10, 1.5));pts2[1].y=(float) (newnumlist.get(3)/Math.pow(10, 1.5));pts2[1].z=0f;
                    ptslist.add(pts2);
                    lsa2.setCoordinates(0, pts2);
                    quadlist.add(lsa2);
                    pts2 = new Point3f[2];
            		mrlist2.add(new MouseRotate());
            		mzlist2.add(new MouseZoom());
            		mtlist2.add(new MouseTranslate());
            		shape = "";
            		newnumlist = new ArrayList();
            		
            	}
            		
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
        }
        //new transformgroup for the floor
        TransformGroup lines = new TransformGroup();
        lines.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    	lines.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	///Initialize and setup mouse rotation
    	MouseRotate mrl=new MouseRotate();
        mrl.setSchedulingBounds(new BoundingSphere());
        mrl.setTransformGroup(lines);
        lineGroup.addChild(mrl);
        ///Initialize and setup mouse zooming
        ///Note: use alt-click & dragging to zoom in/out
        MouseZoom mzl = new MouseZoom();
        mzl.setTransformGroup(lines);
        lineGroup.addChild(mzl);
        mzl.setSchedulingBounds(new BoundingSphere());
        ///Initialize and setup mouse translation
        ///Note: use right click dragging to move object
        MouseTranslate mtl = new MouseTranslate();
        mtl.setSchedulingBounds(new BoundingSphere());
        mtl.setTransformGroup(lines);
        lineGroup.addChild(mtl);
        //loop to set points for each floor
        for (int j = 0; j < ptslist.size(); j++) {
        	Shape3D sh2 = new Shape3D();
        	System.out.println(ptslist.get(j)[0]);
        	quadlist.get(j).setCoordinates(0, ptslist.get(j));
        	sh2.setGeometry(quadlist.get(j));
        	sh2.setAppearance(app);
            sh2.setPickable(true);
            shlist.add(sh2);
        }
        //add floor as new shape in it's own group
        for (int h = 0; h < shlist.size(); h++) {
        	//TransformGroup linetrans = new TransformGroup();
        	Translist.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        	Translist.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
            mrlist2.get(h).setSchedulingBounds(new BoundingSphere());
            mrlist2.get(h).setTransformGroup(Translist);
            lineGroup.addChild(mrlist2.get(h));
            ///Initialize and setup mouse zooming
            ///Note: use alt-click & dragging to zoom in/out
            mzlist2.get(h).setTransformGroup(Translist);
            lineGroup.addChild(mzlist2.get(h));
            mzlist2.get(h).setSchedulingBounds(new BoundingSphere());
            ///Initialize and setup mouse translation
            ///Note: use right click dragging to move object
            mtlist2.get(h).setSchedulingBounds(new BoundingSphere());
            mtlist2.get(h).setTransformGroup(Translist);
            lineGroup.addChild(mtlist2.get(h));
            Translist.addChild(shlist.get(h));
        	lineGroup.addChild(Translist);
        	Translist = new TransformGroup();
        }
        //setup mouse rotation for all shapes
        for (int k = 0; k < mrlist.size(); k++) {
        	Translist.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        	Translist.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
            mrlist.get(k).setSchedulingBounds(new BoundingSphere());
            mrlist.get(k).setTransformGroup(Translist);
            lineGroup.addChild(mrlist.get(k));
            ///Initialize and setup mouse zooming
            ///Note: use alt-click & dragging to zoom in/out
            mzlist.get(k).setTransformGroup(Translist);
            lineGroup.addChild(mzlist.get(k));
            mzlist.get(k).setSchedulingBounds(new BoundingSphere());
            ///Initialize and setup mouse translation
            ///Note: use right click dragging to move object
            mtlist.get(k).setSchedulingBounds(new BoundingSphere());
            mtlist.get(k).setTransformGroup(Translist);
            lineGroup.addChild(mtlist.get(k));
        }
        lineGroup.addChild(Translist);
        lineGroup.addChild(createLight2());
        Material ma = new Material();
        ma.setDiffuseColor(1f, 1f, 0.0f);
        ap.setMaterial(ma);
        return lineGroup;
    }
    
    
    private Light createLight2(){
        DirectionalLight light = new DirectionalLight( true,
                        new Color3f(1f, 1f, 0.0f),
                        new Vector3f(-1.0f, -1.0f, -1.0f));

        light.setInfluencingBounds(new BoundingSphere(new Point3d(), 100.0));
  
        return light;
    }

    public static void main(String[] args) {
        GUI2.birdyf.add(new JScrollPane(new energon2()));
    }
}