/*******************************************************************************
 * Copyright (c) 2013 RiverBaSim - River Basin scenario Simulator    	        
 * 
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Luis Oliva - Created class and main functionalities
 ******************************************************************************/

package riverbasim;

import java.util.HashMap;
import java.util.LinkedHashMap;

import repast.simphony.engine.environment.RunEnvironment;


/**
 * This class represents the data structure describing a feature of water 
 * (e.g., amount, solid concentration...). It is used internally to implement
 * a basic structure to store historical data about a feature, thus allowing
 * decoupling agents logic with regards to the order of execution of their behaviors
 * @author Luis
 *
 */
public class WaterFeature {
	private LinkedHashMap<Integer,Double>  feature;

	public WaterFeature(Number tick, Double amount){
		this.feature = new LinkedHashMap<Integer, Double>();
		//this.feature.put(0, amount);
		this.feature.put(tick.intValue(), amount);
		this.feature.put(tick.intValue()+1, amount);
	}
	
	public WaterFeature(){
		this.feature = new LinkedHashMap<Integer, Double>();
	}
	
	public HashMap<Integer,Double> getFeature(){
		return this.feature;
	}
	public Double get(Number tick){
		return this.feature.get(tick.intValue());
	}
	
	public void put(Number tick, Double amount){
		this.feature.put(tick.intValue(), amount);
		// Remove old values; we only keep two values
		this.feature.remove(tick.intValue()-2);
	}
	
	public String toString(){
		/*
		Set<Integer> keys = this.feature.keySet();
		Iterator<Integer> itr = keys.iterator();
		String result = "";
		while (itr.hasNext()){
			Integer tick = itr.next();
			result += tick+":"+this.feature.get(tick)+"|";
		}
		return result;
		*/
		Double tick = RunEnvironment.getInstance().getCurrentSchedule().getTickCount();
		String result = this.feature.get(tick.intValue()).toString();
		return result;
	}
}