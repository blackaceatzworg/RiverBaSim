package riverbasim;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import org.geotools.data.shapefile.dbf.DbaseFileReader;
import org.geotools.data.shapefile.dbf.DbaseFileReader.Row;

import repast.simphony.context.DefaultContext;
import repast.simphony.context.space.gis.GeographyFactoryFinder;
import repast.simphony.space.gis.Geography;
import repast.simphony.space.gis.GeographyParameters;

public class WaterPlantContext extends DefaultContext<WaterPlant>
{
	private TreeMap<String,WaterPlant>	map;
	private HashSet<WaterPlant> setBesos;
	private HashMap<WaterPlant, WaterPlant> flow;
	private Geography<WaterPlant> waterPlantGeography;
	
	public HashMap<WaterPlant, WaterPlant> getFlow() {
		return flow;
	}

	public void setFlow(HashMap<WaterPlant, WaterPlant> flow) {
		this.flow = flow;
	}

	public WaterPlantContext()
	{
		super("WaterPlantContext");

		System.out.println("WaterPlantContext building river section context and projections");


		
		
		/* Create a Geography to store junctions in spatially */
		GeographyParameters<WaterPlant> geoParams = new GeographyParameters<WaterPlant>();
		waterPlantGeography = GeographyFactoryFinder.createGeographyFactory(null).createGeography("WaterPlantGeography", this, geoParams);
		
		System.out.println("Created WaterPlantGeography");
		
		map = new TreeMap<String,WaterPlant>();
	}
	
	public void setWaterPlants(Set<WaterPlant> wps) {
		Iterator<WaterPlant> it = wps.iterator();
		while(it.hasNext()) {
			WaterPlant wp = it.next();
			System.out.println("Adding " + wp + " attached to "+wp.getRiverSectionLocation().toString());
			this.add(wp);
		}

//		File shapefile = null;
//		ShapefileLoader<WaterPlant> riverLoader = null;
//		try {
//			shapefile = new File("./contrib/spain-latest/buildings.shp");
//			riverLoader = new ShapefileLoader<WaterPlant>(WaterPlant.class,
//					shapefile.toURL(), waterPlantGeography, this);
//		} catch (java.net.MalformedURLException e) {
//			e.printStackTrace();
//		}
//		while (riverLoader.hasNext()) {
//			riverLoader.next();
//		}
//
//		setBesos = new HashSet<WaterPlant>();
//		for (WaterPlant p : waterPlantGeography.getAllObjects()) {
//			Geometry geom = waterPlantGeography.getGeometry(p);
//			Coordinate coord = geom.getCoordinate();
//			MultiLineString line = (MultiLineString)geom;
//			if(p.getType().contains("wastewater_plant")) {
//				setBesos.add(p);
//				System.out.println(p.getType() + " is at: (" + coord.x + ","
//						+ coord.y + ") [size of set: " + setBesos.size() + "]");
//			} else {
//				System.out.println("borrando " + p);
//				this.remove(p);
//			}
//		}
//		
//		flow = new HashMap<WaterPlant,WaterPlant>();
//		HashSet<WaterPlant> tempBesos = new HashSet<WaterPlant>();
//		tempBesos.addAll(setBesos);
//		Iterator<WaterPlant> it1, it2;
//		it1 = setBesos.iterator();
//		while(it1.hasNext()) {
//			WaterPlant p1 = it1.next();
//			Geometry geom1 = waterPlantGeography.getGeometry(p1);
//			Coordinate coord1 = geom1.getCoordinate();
//			MultiLineString line1 = (MultiLineString)geom1;
//			tempBesos.remove(p1);
//			it2 = tempBesos.iterator();
//			double min = Double.POSITIVE_INFINITY;
//			WaterPlant nearest = null;
//			while(it2.hasNext()) {
//				WaterPlant p2 = it2.next();
//				Geometry geom2 = waterPlantGeography.getGeometry(p2);
//				Coordinate coord2 = geom2.getCoordinate();
//				MultiLineString line2 = (MultiLineString)geom2;
//				double candidate = coord1.distance(coord2);
//				if(candidate < min) {
//					nearest = p2;
//					min = candidate;
//				}
//			}
//			flow.put(p1, nearest);
//		}
	}
	
	public static void main(String args[]) {
		File selectedFile = new File("C:/Users/Luis/RiverBaSim/RiverBaSim/contrib/spain-latest/buildings.dbf");
		FileChannel in = null;
		try {
			in = new FileInputStream(selectedFile).getChannel();
		} catch (FileNotFoundException ex) {
		}
		DbaseFileReader r = null;
		try {
			r = new DbaseFileReader(in, false, Charset.forName("ISO-8859-1"));
		} catch (IOException ex1) {
		}

		Object[] fields = new Object[r.getHeader().getNumFields()];
		while (r.hasNext()) {
			try {
				r.readEntry(fields);
				Row row = r.readRow();
				String type = (String) row.read(2);
				if(type.toLowerCase().contains("industrial")) {
					System.out.println(type);
				}
			} catch (IOException ex1) {
			}
		}
		try {
			r.close();
		} catch (IOException ex1) {
		}
	}

	public Geography<WaterPlant> getWaterPlantGeography() {
		return waterPlantGeography;
	}
}
